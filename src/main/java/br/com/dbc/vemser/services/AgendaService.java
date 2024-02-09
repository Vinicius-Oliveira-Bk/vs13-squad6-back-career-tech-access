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
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public AgendaResponseDTO alterarHorario(Long idAgenda, AgendaRequestDTO agendaRequestDTO) throws Exception {
        List<Agenda> agendamentos = agendaRepository.findAll();
        Agenda novaAgenda = objectMapper.convertValue(agendaRequestDTO, Agenda.class);
        Agenda agenda = getAgenda(idAgenda);
        novaAgenda.setProfissionalMentor(agenda.getProfissionalMentor());
        validarHorarioAgendamento(novaAgenda, agendamentos);

        agenda.setDataHoraInicio(novaAgenda.getDataHoraInicio());
        agenda.setDataHoraFim(novaAgenda.getDataHoraFim());

        return objectMapper.convertValue(agendaRepository.save(agenda), AgendaResponseDTO.class);
    }

    public AgendaResponseDTO agendarHorario(Long idAgenda, Long idCliente) throws Exception {
        Agenda agenda = getAgenda(idAgenda);
        Cliente cliente = clienteService.getCliente(idCliente);
        validarDisponibilidadeAgenda(agenda);
        validarProfissionalDiferenteDeCliente(cliente, agenda.getProfissionalMentor());
        agenda.setCliente(cliente);
        agenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);

        agendaRepository.save(agenda);
        AgendarEmailDTO agendarEmailDTO = criarObjetoEmail(agenda);
        emailService.sendEmail(agendarEmailDTO, cliente.getUsuario().getEmail(), EmailTemplate.AGENDAR_HORARIO);
        return objectMapper.convertValue(agenda, AgendaResponseDTO.class);
    }

    public Page<AgendaResponseDTO> listAll(Pageable pageable,
                                           @Nullable Long idAgenda,
                                           @Nullable StatusAgendaEnum statusAgendaEnum,
                                           @Nullable Long idProfissionalMentor,
                                           @Nullable Long idCliente) throws Exception {
        Integer status = statusAgendaEnum != null ? statusAgendaEnum.ordinal() : null;
        Page<Agenda> agendamentos = agendaRepository.findAll(pageable, idAgenda, status, idProfissionalMentor, idCliente);
        return agendamentos.map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class));
    }

    public Page<AgendaResponseDTO> listAllAgendasClienteLogado(Pageable pageable,
                                           @Nullable StatusAgendaEnum statusAgendaEnum,
                                           @Nullable Long idProfissionalMentor) throws RegraDeNegocioException {
        Integer status = statusAgendaEnum != null ? statusAgendaEnum.ordinal() : null;
        Long idUsuarioLogado = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Cliente cliente = clienteService.getByUsuario(idUsuarioLogado);
        Page<Agenda> agendamentos = agendaRepository.findAllClienteLogado(pageable, status, idProfissionalMentor, cliente.getId());
        return agendamentos.map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class));
    }

    public Page<AgendaResponseDTO> listAllAgendasProfissionalLogado(Pageable pageable,
                                                  @Nullable StatusAgendaEnum statusAgendaEnum) throws RegraDeNegocioException {
        Integer status = statusAgendaEnum != null ? statusAgendaEnum.ordinal() : null;
        Long idUsuarioLogado = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        ProfissionalMentor profissionalMentor = profissionalMentorService.getByUsuario(idUsuarioLogado);
        Page<Agenda> agendamentos = agendaRepository.findAllProfissionalLogado(pageable, status, profissionalMentor.getId());
        return agendamentos.map(agendamento -> objectMapper.convertValue(agendamento, AgendaResponseDTO.class));
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

    public String marcarPresente(Long idAgenda) throws Exception {
        Agenda agenda = getAgenda(idAgenda);
        if (Objects.isNull(agenda.getCliente())) {
            throw new RegraDeNegocioException("Não é possível marcar como presente, não há cliente cadastrado.");
        }
        if (agenda.getDataHoraInicio().isAfter(LocalDateTime.now())) {
            throw new RegraDeNegocioException("Não é possível marcar como presente, o horário da agenda é posterior ao atual.");
        }
        agenda.setStatusAgendaEnum(StatusAgendaEnum.PRESENTE);
        agendaRepository.save(agenda);
        return "Presença marcada com sucesso.";
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

    public void validarProfissionalDiferenteDeCliente(Cliente cliente, ProfissionalMentor profissionalMentor) throws RegraDeNegocioException {
        if (profissionalMentor.getUsuario().getId().equals(
                cliente.getUsuario().getId()
        )) {
            throw new RegraDeNegocioException("Não é possível agendar um horário sendo o profissional e o mentorado.");
        }
    }

    public Set<RelatorioAgendaDTO> relatorioAgenda(Long idAgenda) {
        idAgenda = idAgenda == null ? -1 : idAgenda;

        return agendaRepository.relatorioAgenda(idAgenda).stream().map(AgendaMapper::agendaToRelatorioAgendaDTO).collect(Collectors.toSet());
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
}
