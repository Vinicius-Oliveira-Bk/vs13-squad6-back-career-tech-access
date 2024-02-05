package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.AgendaMapper;
import br.com.dbc.vemser.mappers.EmailMapper;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.request.AgendarEmailDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.dtos.response.RelatorioAgendaDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.AgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
        AgendarEmailDTO agendarEmailDTO = criarObjetoEmail(agenda);
        emailService.sendEmail(agendarEmailDTO, cliente.getUsuario().getEmail(), EmailTemplate.AGENDAR_HORARIO);
        return objectMapper.convertValue(agenda, AgendaResponseDTO.class);
    }

    private AgendarEmailDTO criarObjetoEmail(Agenda agenda) {
        AgendarEmailDTO agendarEmailDTO = new AgendarEmailDTO();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("hh:mm");
        agendarEmailDTO.setNome(agenda.getCliente().getUsuario().getNome());
        agendarEmailDTO.setEmailCliente(agenda.getCliente().getUsuario().getEmail());
        agendarEmailDTO.setNomeProfissional(agenda.getProfissionalMentor().getUsuario().getNome());
        agendarEmailDTO.setDataInicio(agenda.getDataHoraInicio().format(formatterData));
        agendarEmailDTO.setDataFim(agenda.getDataHoraFim().format(formatterData));
        agendarEmailDTO.setHoraInicio(agenda.getDataHoraInicio().format(formatterHora));
        agendarEmailDTO.setHoraFim(agenda.getDataHoraFim().format(formatterHora));
        return agendarEmailDTO;
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

    public Page<AgendaResponseDTO> listAll(Pageable pageable) throws Exception {
        Page<Agenda> agendamentos = agendaRepository.findAll(pageable);
        return agendamentos.map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class));
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


    public void cancelarHorario(Long idAgenda) throws RegraDeNegocioException {
        Agenda agenda = agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));

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

            if (agenda.getDataHoraInicio().isEqual(agenda.getDataHoraFim())) {
                throw new RegraDeNegocioException("❌ Não é possível agendar com mesma data e horário em início e fim.");
            }

            if (agenda.getDataHoraFim().isBefore(agenda.getDataHoraInicio())) {
                throw new RegraDeNegocioException("❌ Não é possível agendar se a data de fim for menor que a data de início.");
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

    public Set<RelatorioAgendaDTO> relatorioAgenda(Long idAgenda) {
        idAgenda = idAgenda == null ? -1 : idAgenda;

        return agendaRepository.relatorioAgenda(idAgenda).stream().map(AgendaMapper::agendaToRelatorioAgendaDTO).collect(Collectors.toSet());
    }
}
