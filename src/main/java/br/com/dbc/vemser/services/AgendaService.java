package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.EmailMapper;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.AgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgendaService {
    private final ClienteService clienteService;
    private final ProfissionalMentorService profissionalMentorService;
    private final ObjectMapper objectMapper;
    private final AgendaRepository agendaRepository;
    private final EmailService emailService;
    private final String RESOURCE_NOT_FOUND = "Não foi encontrada nenhuma agenda com este filtro.";

    public AgendaResponseDTO cadastrarHorario(Long idProfissionalMentor, AgendaRequestDTO agendaRequestDTO) throws Exception {
        List<Agenda> agendamentos = agendaRepository.findAll();
        ProfissionalMentor profissionalMentor = profissionalMentorService.getProfissionalMentor(idProfissionalMentor);

        Agenda agenda = objectMapper.convertValue(agendaRequestDTO, Agenda.class);
        agenda.setProfissionalMentor(profissionalMentor);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);
        validarHorarioAgendamento(agenda, agendamentos);
        return objectMapper.convertValue(agendaRepository.save(agenda), AgendaResponseDTO.class);
    }

    public AgendaResponseDTO agendarHorario(Long idAgenda, Long idCliente) throws Exception {
        Agenda agenda = getAgenda(idAgenda);
        Cliente cliente = clienteService.getCliente(idCliente);

        validarDisponibilidadeAgenda(agenda);

        agenda.setCliente(cliente);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);

        agendaRepository.save(agenda);

        emailService.sendEmail(EmailMapper.agendaToAgendaEmailDTO(agenda), cliente.getUsuario().getEmail(), EmailTemplate.AGENDAR_HORARIO);
        return objectMapper.convertValue(agenda, AgendaResponseDTO.class);
    }

    public AgendaResponseDTO reagendarHorario(Long idAgendaAtual, Long idNovaAgenda) throws Exception {
            Agenda agenda = getAgenda(idAgendaAtual);
            Agenda novaAgenda = getAgenda(idNovaAgenda);

            validarDisponibilidadeAgenda(novaAgenda);

            novaAgenda.setCliente(agenda.getCliente());
            novaAgenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);

            agenda.setCliente(null);
            agenda.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);

            agendaRepository.save(novaAgenda);
            agendaRepository.save(agenda);
            return objectMapper.convertValue(novaAgenda, AgendaResponseDTO.class);
    }

    public AgendaResponseDTO getById(Long idAgenda) throws Exception {
        return objectMapper.convertValue(getAgenda(idAgenda), AgendaResponseDTO.class);
    }

    public List<AgendaResponseDTO> listAllByStatus(StatusAgendaEnum statusAgendaEnum) throws Exception {
        List<Agenda> agendamentos = agendaRepository.findByStatusAgendaEnum(statusAgendaEnum);
        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<AgendaResponseDTO> listAll() throws Exception {
        List<Agenda> agendamentos = agendaRepository.findAll();

        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .collect(Collectors.toList());
    }

    public List<AgendaResponseDTO> listAllByCliente(Long idCliente) throws Exception {
        List<Agenda> agendamentos = agendaRepository.findByCliente_Id(idCliente);

        agendamentos = agendamentos.stream()
                .filter(x -> Objects.nonNull(x.getCliente()))
                .filter(y -> y.getCliente().getId().equals(idCliente))
                .toList();

        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .toList();
    }

    public List<AgendaResponseDTO> listAllByProfissional(Long idProfissional) throws Exception {
        List<Agenda> agendamentos = agendaRepository.findByProfissionalMentor_Id(idProfissional);

        return agendamentos.stream()
                .map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class))
                .toList();
    }

    public void delete(Long idAgenda) throws Exception {
        Agenda agenda = getAgenda(idAgenda);
        agendaRepository.delete(agenda);
    }


    public void cancelarHorario(Long idAgenda) {
        Agenda agenda = agendaRepository.getById(idAgenda);

        agenda.setCliente(null);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);
        agendaRepository.save(agenda);
    }

    public Agenda getAgenda(Long idAgenda) throws Exception {
        return agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
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
                throw new RegraDeNegocioException("❌ O profissional já possui agendamento neste horário, agendamento cancelado.");
            }

            //fim igual ou menor ao fim de outra agenda
            if ((agenda.getDataHoraFim().isEqual(agendamento.getDataHoraFim())
                    || agenda.getDataHoraFim().isBefore(agendamento.getDataHoraFim()))
                &&
                (agenda.getDataHoraFim().isAfter(agendamento.getDataHoraInicio()))) {
                throw new RegraDeNegocioException("❌ O profissional já possui agendamento neste horário, agendamento cancelado.");
            }
        }
        return true;
    }

    public boolean validarDisponibilidadeAgenda(Agenda agenda) throws RegraDeNegocioException {
        if (Objects.nonNull(agenda.getCliente())) {
            throw new RegraDeNegocioException("❌ Já há cliente agendado para este horário, agendamento cancelado.");
        }
        return true;
    }
}
