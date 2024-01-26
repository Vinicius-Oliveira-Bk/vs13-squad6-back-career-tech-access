package br.com.dbc.vemser.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.AgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendaService {
    private final ClienteService clienteService;
    private final ProfissionalMentorService profissionalMentorService;
    private final ObjectMapper objectMapper;
    private final AgendaRepository agendaRepository;

    public AgendaResponseDTO cadastrarHorario(Long idProfissionalMentor, AgendaRequestDTO agendaRequestDTO) throws Exception {
        List<Agenda> agendamentos = agendaRepository.getAll();
        ProfissionalMentor profissionalMentor = profissionalMentorService.getProfissionalMentor(idProfissionalMentor);

        Agenda agenda = objectMapper.convertValue(agendaRequestDTO, Agenda.class);
        agenda.setProfissionalMentor(profissionalMentor);
        validarHorarioAgendamento(agenda, agendamentos);
        return objectMapper.convertValue(agendaRepository.create(agenda), AgendaResponseDTO.class);
    }

    public AgendaResponseDTO agendarHorario(Long idCliente, Long idAgenda) throws Exception {
        Agenda agenda = agendaRepository.getById(idAgenda);
        Cliente cliente = clienteService.getCliente(idCliente);

        validarDisponibilidadeAgenda(agenda);
        clienteService.validarCliente(cliente);

        agenda.setCliente(cliente);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);

        return objectMapper.convertValue(agendaRepository.update(idAgenda, agenda), AgendaResponseDTO.class);
    }

    public AgendaResponseDTO reagendarHorario(Long idAgendaAtual, Long idNovaAgenda) throws Exception {
            Agenda agenda = agendaRepository.getById(idAgendaAtual);
            Agenda novaAgenda = agendaRepository.getById(idNovaAgenda);

            validarDisponibilidadeAgenda(novaAgenda);

            novaAgenda.setCliente(agenda.getCliente());
            novaAgenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);
            agenda.setCliente(null);
            agenda.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);
            agendaRepository.update(idNovaAgenda, novaAgenda);
            agendaRepository.update(idAgendaAtual, agenda);
            return objectMapper.convertValue(novaAgenda, AgendaResponseDTO.class);
    }

    public AgendaResponseDTO getById(Long idAgenda) throws Exception {
        return objectMapper.convertValue(agendaRepository.getById(idAgenda), AgendaResponseDTO.class);
    }

    public List<AgendaResponseDTO> listAllByStatus(StatusAgendaEnum statusAgendaEnum) throws Exception {
        return listAll()
                .stream()
                .filter(x -> x.getStatusAgendaEnum()
                        .equals(statusAgendaEnum))
                .collect(Collectors.toList());
    }

    public List<AgendaResponseDTO> listAll() throws Exception {
            List<Agenda> agendamentos = agendaRepository.getAll();

            return agendamentos.stream()
                    .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                    .collect(Collectors.toList());
    }

    public List<AgendaResponseDTO> listAllByCliente(Long idCliente) throws Exception {
        List<Agenda> agendamentos = agendaRepository.getAll();

        agendamentos = agendamentos.stream()
                .filter(x -> x.getCliente().getId().equals(idCliente))
                .collect(Collectors.toList());

        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<AgendaResponseDTO> listAllByProfissional(Long idProfissional) throws Exception {
        List<Agenda> agendamentos = agendaRepository.getAll();

        agendamentos = agendamentos.stream()
                .filter(x -> x.getProfissionalMentor().getId().equals(idProfissional))
                .collect(Collectors.toList());

        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public void delete(Long idAgenda) throws Exception {
        getAgenda(idAgenda);
        agendaRepository.delete(idAgenda);
    }


    public AgendaResponseDTO cancelarHorario(Long idCliente, Long idAgenda) throws Exception {
        Agenda agenda = agendaRepository.getById(idAgenda);

        agenda.setCliente(null);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.CANCELADO);
        return objectMapper.convertValue(agendaRepository.update(idAgenda, agenda), AgendaResponseDTO.class);
    }

    public Agenda getAgenda(Long idAgenda) throws Exception {
        return agendaRepository.getById(idAgenda);
    }

    public boolean validarHorarioAgendamento(Agenda agenda, List<Agenda> agendamentos) throws RegraDeNegocioException {
        for (Agenda agendamento : agendamentos) {

            if (!agenda.getProfissionalMentor().equals(agendamento.getProfissionalMentor())) {
                continue;
            }
            //início igual ou maior ao início de outra agenda
            if ((agenda.getDataHoraInicio().isEqual(agendamento.getDataHoraInicio())
                    || agenda.getDataHoraInicio().isAfter(agendamento.getDataHoraInicio()))
                &&
                    (agenda.getDataHoraInicio().isBefore(agendamento.getDataHoraFim()))) {
                throw new RegraDeNegocioException("❌ Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }

            //fim igual ou menor ao fim de outra agenda
            if ((agenda.getDataHoraFim().isEqual(agendamento.getDataHoraFim())
                    || agenda.getDataHoraFim().isBefore(agendamento.getDataHoraFim()))
                &&
                (agenda.getDataHoraFim().isAfter(agendamento.getDataHoraInicio()))) {
                throw new RegraDeNegocioException("❌ Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }
        }
        return true;
    }

    public boolean validarDisponibilidadeAgenda(Agenda agenda) {
        if (Objects.isNull(agenda.getCliente())) {
            throw new IllegalArgumentException("❌ Já há cliente agendado para este horário, agendamento cancelado.");
        }
        return true;
    }
}
