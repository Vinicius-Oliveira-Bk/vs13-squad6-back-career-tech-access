package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.AgendaRequestDTO;
import br.com.dbc.vemser.model.dtos.response.*;
import br.com.dbc.vemser.model.entities.*;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.AgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Teste das regras de negócio da agenda.")
class AgendaServiceTest {

    @Mock
    private ClienteService clienteService;
    @Mock
    private ProfissionalMentorService profissionalMentorService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private EmailService emailService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AgendaService agendaService;
    private static final Pageable paginacao = PageRequest.of(0, 10);
    private static Page<Agenda> agendas;


    List<Agenda> agendaList = new ArrayList<>();
    List<Agenda> agendaRelatorioList = new ArrayList<>();
    List<RelatorioAgendaDTO> relatorioAgendaDTOList = new ArrayList<>();
    ProfissionalMentor profissionalMentorMock, profissionalMentorClienteMock;
    Cliente clienteMock, clienteMentorMock;
    ClienteResponseDTO clienteResponseDTOMock;
    Usuario usuarioClienteMock, usuarioMentorMock, usuarioClienteMentorMock;
    UsuarioResponseDTO usuarioClienteResponseDTOMock;
    ProfissionalMentorResponseDTO profissionalMentorResponseDTOMock;
    RelatorioAgendaMentorDTO relatorioAgendaMentorDTO;

    @BeforeEach
    void beforeEach() {
        usuarioClienteMock = new Usuario(1L, "Gabriel Cliente Teste", LocalDate.of(2000, 10,01 ), "32165498712", "cliente@email.com", "senha", 'N', null, null, null, true, Set.of(getCargoClienteMock()), null, null, null, null);
        usuarioClienteResponseDTOMock = new UsuarioResponseDTO(1L, "Gabriel Cliente Teste", LocalDate.of(2000, 10,01 ), "32165498712", "cliente@email.com", 'N', null, null, null, true);
        usuarioMentorMock = new Usuario(2L, "Gabriel Profissional Teste", LocalDate.of(2000, 10,01 ), "32165498722", "mentor@email.com", "senha", 'N', null, null, null, true, Set.of(getCargoProfissionalMock()), null, null, null, null);
        usuarioClienteMentorMock = new Usuario(3L, "Gabriel Profissional Cliente Teste", LocalDate.of(2000, 10,01 ), "32165498732", "mentorCliente@email.com", "senha", 'N', null, null, null, true, Set.of(getCargoProfissionalMock(), getCargoClienteMock()), null, null, null, null);

        clienteMock = new Cliente(1L, PlanoEnum.BASICO, 'N', 'N', 'N', null, null, null, null, null, null, null, null, true, usuarioClienteMock, new ArrayList<>(), new ArrayList<>());
        clienteMentorMock = new Cliente(1L, PlanoEnum.BASICO, 'N', 'N', 'N', null, null, null, null, null, null, null, null, true, usuarioClienteMentorMock, new ArrayList<>(), new ArrayList<>());
        clienteResponseDTOMock = new ClienteResponseDTO(1L, usuarioClienteResponseDTOMock, PlanoEnum.BASICO, 'N', 'N', 'N', null, null, null, null, null, null, null, null, null);

        profissionalMentorMock = new ProfissionalMentor(1L, NivelExperienciaEnum.JUNIOR, "CarteiraTrabalho", true, usuarioMentorMock, new ArrayList<>(), new ArrayList<>());
        profissionalMentorClienteMock = new ProfissionalMentor(1L, NivelExperienciaEnum.JUNIOR, "CarteiraTrabalho", true, usuarioClienteMentorMock, new ArrayList<>(), new ArrayList<>());
        profissionalMentorResponseDTOMock = new ProfissionalMentorResponseDTO(1L, new ArrayList<>(),  NivelExperienciaEnum.JUNIOR, "CarteiraTrabalho");

        relatorioAgendaMentorDTO = new RelatorioAgendaMentorDTO("Gabriel Profissional Teste", "mentor@email.com", 'N', null, null, null, NivelExperienciaEnum.JUNIOR, "CarteiraTrabalho", null);

        agendaList.add(new Agenda(1L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO, clienteMock, profissionalMentorMock));
        agendaList.add(new Agenda(2L, LocalDateTime.of(2024, 05, 15, 17,30, 0), LocalDateTime.of(2024, 05, 15, 18,30, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock));
        agendaList.add(new Agenda(3L, LocalDateTime.of(2024, 05, 15, 18,30, 0), LocalDateTime.of(2024, 05, 15, 19,30, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock));
        agendaList.add(new Agenda(4L, LocalDateTime.of(2024, 02, 15, 16,30, 0), LocalDateTime.of(2024, 02, 15, 17,30, 0), StatusAgendaEnum.AGENDADO, clienteMock, profissionalMentorMock));
        agendaList.add(new Agenda(5L, LocalDateTime.of(2024, 02, 15, 16,30, 0), LocalDateTime.of(2024, 02, 15, 17,30, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorClienteMock));

        agendaRelatorioList.add(new Agenda(1L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO, clienteMock, profissionalMentorMock));
        agendaRelatorioList.add(new Agenda(2L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO, clienteMock, profissionalMentorMock));

        relatorioAgendaDTOList.add(new RelatorioAgendaDTO(1L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO));
        relatorioAgendaDTOList.add(new RelatorioAgendaDTO(1L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO));

        agendas = new PageImpl<>(agendaList, paginacao, agendaList.size());
    }

    @Test
    @DisplayName("Deve cadastrar um horário para um profissional mentor com sucesso.")
    public void deveCadastrarHorarioComSucesso() throws Exception {
        Long idMentor = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestValidaDTO();
        Agenda agendaMock = getAgendaValidaMock();
        AgendaResponseDTO agendaResponseDTOMock = getCadastroAgendaResponseValidaDTO();

        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(profissionalMentorService.getProfissionalMentor(anyLong())).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMock);
        when(objectMapper.convertValue(agendaMock, AgendaResponseDTO.class)).thenReturn(agendaResponseDTOMock);
        when(agendaRepository.save(agendaMock)).thenReturn(agendaMock);

        AgendaResponseDTO agendaResponseDTOCriada = agendaService.cadastrarHorario(idMentor, agendaRequestDTO);
        assertNotNull(agendaResponseDTOCriada);
        assertEquals(agendaResponseDTOMock, agendaResponseDTOCriada);
    }

    @Test
    @DisplayName("Não deve cadastrar um horário para um profissional mentor, horários início igual ao fim.")
    public void naoDeveCadastrarHorarioInicioIgualFim() throws Exception {
        Long idMentor = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestHorariosIguaisMock();
        Agenda agendaMock = getAgendaHorariosIguaisMock();

        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(profissionalMentorService.getProfissionalMentor(anyLong())).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMock);

        assertThrows(RegraDeNegocioException.class, () -> agendaService.cadastrarHorario(idMentor, agendaRequestDTO));
    }

    @Test
    @DisplayName("Não deve cadastrar um horário para um profissional mentor, horário início maior que fim.")
    public void naoDeveCadastrarHorarioInicioMaiorQueFim() throws Exception {
        Long idMentor = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestInicioMaiorQueFimMock();
        Agenda agendaMock = getAgendaInicioMaiorQueFimMock();

        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(profissionalMentorService.getProfissionalMentor(anyLong())).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMock);

        assertThrows(RegraDeNegocioException.class, () -> agendaService.cadastrarHorario(idMentor, agendaRequestDTO));
    }

    @Test
    @DisplayName("Não deve cadastrar um horário para um profissional mentor, horário início já marcado.")
    public void naoDeveCadastrarHorarioInicioJaMarcado() throws Exception {
        Long idMentor = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestInicioJaMarcado();
        Agenda agendaMock = getAgendaInicioJaMarcado();

        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(profissionalMentorService.getProfissionalMentor(anyLong())).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMock);

        assertThrows(RegraDeNegocioException.class, () -> agendaService.cadastrarHorario(idMentor, agendaRequestDTO));
    }

    @Test
    @DisplayName("Não deve cadastrar um horário para um profissional mentor, horário fim já marcado.")
    public void naoDeveCadastrarHorarioFimJaMarcado() throws Exception {
        Long idMentor = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestFimJaMarcado();
        Agenda agendaMock = getAgendaFimJaMarcado();

        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(profissionalMentorService.getProfissionalMentor(anyLong())).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMock);

        assertThrows(RegraDeNegocioException.class, () -> agendaService.cadastrarHorario(idMentor, agendaRequestDTO));
    }

    @Test
    @DisplayName("Deve alterar um horário da agenda para um novo horário com sucesso.")
    public void deveAlterarHorarioComSucesso() throws Exception {
        Long idAgenda = new Random().nextLong();
        AgendaRequestDTO agendaRequestDTO = getAgendaRequestValidaDTO();
        Agenda agendaMockNova = getAgendaValidaAlteradaMock();
        Agenda agendaMock = getAgendaValidaMock();
        AgendaResponseDTO agendaAlteradaResponseDTOMock = getAlteradaAgendaResponseValidaDTO();
        AgendaResponseDTO agendaResponseDTOMock = getCadastroAgendaResponseValidaDTO();


        when(agendaRepository.findAll()).thenReturn(agendaList);
        when(objectMapper.convertValue(agendaRequestDTO, Agenda.class)).thenReturn(agendaMockNova);
        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaMock));
        when(objectMapper.convertValue(agendaMock, AgendaResponseDTO.class)).thenReturn(agendaAlteradaResponseDTOMock);
        when(agendaRepository.save(agendaMock)).thenReturn(agendaMock);


        agendaAlteradaResponseDTOMock = agendaService.alterarHorario(idAgenda, agendaRequestDTO);
        assertNotNull(agendaAlteradaResponseDTOMock);
        assertNotEquals(agendaResponseDTOMock, agendaAlteradaResponseDTOMock);
    }

    @Test
    @DisplayName("Deve agendar um horário da agenda para um cliente.")
    public void deveAgendarHorarioParaUmClienteComSucesso() throws Exception {
        Long idAgenda = new Random().nextLong();
        Long idCliente = new Random().nextLong();

        Agenda agendaDisponivelMock = agendaList.get(1);
        AgendaResponseDTO agendaAgendadaResponseDTOMock = getAgendaAgendadaResponseValidaDTO();

        when(clienteService.getCliente(anyLong())).thenReturn(clienteMock);
        when(agendaRepository.findById(idAgenda)).thenReturn(Optional.of(agendaDisponivelMock));
        when(agendaRepository.save(agendaDisponivelMock)).thenReturn(agendaDisponivelMock);
        when(objectMapper.convertValue(agendaDisponivelMock, AgendaResponseDTO.class)).thenReturn(agendaAgendadaResponseDTOMock);

        agendaService.agendarHorario(idAgenda, idCliente);

        assertNotNull(agendaAgendadaResponseDTOMock);
        assertNotEquals(agendaDisponivelMock, agendaAgendadaResponseDTOMock);
    }

    @Test
    @DisplayName("Não deve agendar um horário, agenda ocupada.")
    public void naoDeveAgendarHorarioAgendaOcupada() throws Exception {
        Long idAgenda = new Random().nextLong();
        Long idCliente = new Random().nextLong();

        Agenda agendaAgendadaMock = agendaList.get(0);

        when(clienteService.getCliente(anyLong())).thenReturn(clienteMock);
        when(agendaRepository.findById(idAgenda)).thenReturn(Optional.of(agendaAgendadaMock));

        assertThrows(RegraDeNegocioException.class, () -> agendaService.agendarHorario(idAgenda, idCliente));
    }

    @Test
    @DisplayName("Não deve agendar um horário, usuário de cliente e mentor são iguais.")
    public void naoDeveAgendarHorarioUsuarioIgual() throws Exception {
        Long idAgenda = new Random().nextLong();
        Long idCliente = new Random().nextLong();

        Agenda agendaAgendadaMock = agendaList.get(4);

        when(clienteService.getCliente(anyLong())).thenReturn(clienteMentorMock);
        when(agendaRepository.findById(idAgenda)).thenReturn(Optional.of(agendaAgendadaMock));

        assertThrows(RegraDeNegocioException.class, () -> agendaService.agendarHorario(idAgenda, idCliente));
    }

    @Test
    @DisplayName("Deve deletar uma agenda que não possui cliente com sucesso.")
    public void deveDeletarUmaAgendaComSucesso() throws Exception {
        Long idAgenda = new Random().nextLong();
        Agenda agendaDisponivelMock = agendaList.get(1);

        when(agendaRepository.findById(idAgenda)).thenReturn(Optional.of(agendaDisponivelMock));

        agendaService.delete(idAgenda);

        verify(agendaRepository, times(1)).delete(agendaDisponivelMock);
    }

    @Test
    @DisplayName("Deve cancelar um horário com sucesso.")
    public void deveCancelarHorarioComSucesso() throws Exception {
        Long idAgenda = new Random().nextLong();
        Agenda agendaMockAgendada = agendaList.get(0);
        Agenda agendaMockDisponivel = agendaList.get(0);
        agendaMockDisponivel.setCliente(null);
        agendaMockDisponivel.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);

        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaMockAgendada));
        when(agendaRepository.save(agendaMockAgendada)).thenReturn(agendaMockAgendada);

        agendaService.cancelarHorario(idAgenda);

        verify(agendaRepository, times(1)).save(agendaMockAgendada);

        assertNotNull(agendaMockAgendada);
        assertEquals(agendaMockDisponivel, agendaMockAgendada);
    }

    @Test
    @DisplayName("Deve marcar presença em um horário com sucesso.")
    public void deveMarcarPresencaComSucesso() throws Exception {
        Long idAgenda = new Random().nextLong();
        Agenda agendaMockAgendada = agendaList.get(3);
        Agenda agendaMockPresente = agendaList.get(3);
        agendaMockPresente.setStatusAgendaEnum(StatusAgendaEnum.PRESENTE);

        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaMockAgendada));
        when(agendaRepository.save(agendaMockAgendada)).thenReturn(agendaMockAgendada);

        agendaService.marcarPresente(idAgenda);

        verify(agendaRepository, times(1)).save(agendaMockAgendada);

        assertNotNull(agendaMockAgendada);
        assertEquals(agendaMockPresente, agendaMockAgendada);
    }

    @Test
    @DisplayName("Não deve marcar presença, lançar exceção RegraDeNegocioException (horário posterior ao atual).")
    public void naoDeveMarcarPresencaHorarioPosterior() throws Exception {
        Long idAgenda = new Random().nextLong();
        Agenda agendaMockAgendada = agendaList.get(0);

        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaMockAgendada));

        assertThrows(RegraDeNegocioException.class, () -> agendaService.marcarPresente(idAgenda));
    }

    @Test
    @DisplayName("Não deve marcar presença, lançar exceção RegraDeNegocioException (agenda sem cliente).")
    public void naoDeveMarcarPresencaSemCliente() throws Exception {
        Long idAgenda = new Random().nextLong();
        Agenda agendaMockAgendada = agendaList.get(1);

        when(agendaRepository.findById(anyLong())).thenReturn(Optional.of(agendaMockAgendada));

        assertThrows(RegraDeNegocioException.class, () -> agendaService.marcarPresente(idAgenda));
    }

    @Test
    @DisplayName("Deve listar todas as agendas")
    void deveListarTodasAsAgendas() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Agenda agenda1 = agendas.getContent().get(0);
        Agenda agenda2 = agendas.getContent().get(1);
        Agenda agenda3 = agendas.getContent().get(2);

        when(agendaRepository.findAll(pageable, null, null, null, null)).thenReturn(agendas);
        when(objectMapper.convertValue(agenda1, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda2, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda3, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());

        Page<AgendaResponseDTO> agendaResponseDTOS = agendaService.listAll(pageable, null, null, null, null);

        assertEquals(5, agendaResponseDTOS.getTotalElements());
        assertEquals(agendas.getContent().get(0).getId(), agendaResponseDTOS.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve exibir o relatório de agendas.")
    public void deveExibirRelatorioDeAgenda() {
        Agenda agendaMock = agendaRelatorioList.get(0);
        Agenda agendaMock1 = agendaRelatorioList.get(1);

        when(agendaRepository.relatorioAgenda(null)).thenReturn(agendaRelatorioList);
        when(objectMapper.convertValue(agendaMock, RelatorioAgendaDTO.class)).thenReturn(getRelatorioAgendaDTO());
        when(objectMapper.convertValue(agendaMock1, RelatorioAgendaDTO.class)).thenReturn(getRelatorioAgendaDTO());

        List<RelatorioAgendaDTO> relatorioAgendaDTOListRecuperado = agendaService.relatorioAgenda(null);

        assertNotNull(relatorioAgendaDTOListRecuperado);
        assertEquals(2, relatorioAgendaDTOListRecuperado.size());
        assertEquals(relatorioAgendaDTOList.get(0).getId(), relatorioAgendaDTOListRecuperado.get(0).getId());
    }

    @Test
    @DisplayName("Deve listar as agendas do cliente logado.")
    public void deveListarAgendasDoCliente() throws RegraDeNegocioException {
        Agenda agenda1 = agendas.getContent().get(0);
        Agenda agenda2 = agendas.getContent().get(1);
        Agenda agenda3 = agendas.getContent().get(2);
        SecurityContextHolder.setContext(securityContext);
        Long tamanhoEsperado = 5L;

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(1L);
        when(clienteService.getByUsuario(anyLong())).thenReturn(clienteMock);
        when(agendaRepository.findAllClienteLogado(paginacao, null, null, clienteMock.getId())).thenReturn(agendas);
        when(objectMapper.convertValue(agenda1, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda2, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda3, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());

        Page<AgendaResponseDTO> agendaResponseDTOS = agendaService.listAllAgendasClienteLogado(paginacao, null, null);

        assertEquals(tamanhoEsperado, agendaResponseDTOS.getTotalElements());
        assertEquals(agendas.getContent().get(0).getId(), agendaResponseDTOS.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve listar as agendas do profissional mentor logado.")
    public void deveListarAgendasDoProfissionalMentor() throws RegraDeNegocioException {
        Agenda agenda1 = agendas.getContent().get(0);
        Agenda agenda2 = agendas.getContent().get(1);
        Agenda agenda3 = agendas.getContent().get(2);
        SecurityContextHolder.setContext(securityContext);
        Long tamanhoEsperado = 5L;

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(1L);
        when(profissionalMentorService.getByUsuario(anyLong())).thenReturn(profissionalMentorMock);
        when(agendaRepository.findAllProfissionalLogado(paginacao, null, profissionalMentorMock.getId())).thenReturn(agendas);
        when(objectMapper.convertValue(agenda1, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda2, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());
        when(objectMapper.convertValue(agenda3, AgendaResponseDTO.class)).thenReturn(getCadastroAgendaResponseValidaDTO());

        Page<AgendaResponseDTO> agendaResponseDTOS = agendaService.listAllAgendasProfissionalLogado(paginacao, null);

        assertEquals(tamanhoEsperado, agendaResponseDTOS.getTotalElements());
        assertEquals(agendas.getContent().get(0).getId(), agendaResponseDTOS.getContent().get(0).getId());
    }


    private Cargo getCargoProfissionalMock() {
        return new Cargo(1, "ROLE_PROFISSIONAL", null);
    }

    private Cargo getCargoClienteMock() {
        return new Cargo(1, "ROLE_CLIENTE", null);
    }

    private AgendaRequestDTO getAgendaRequestValidaDTO() {
        return new AgendaRequestDTO(LocalDateTime.of(2024, 05, 15, 21,30, 0), LocalDateTime.of(2024, 05, 15, 22,30, 0));
    }

    private AgendaRequestDTO getAgendaRequestInicioMaiorQueFimMock() {
        return new AgendaRequestDTO(LocalDateTime.of(2024, 05, 15, 18,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0));
    }

    private AgendaRequestDTO getAgendaRequestHorariosIguaisMock() {
        return new AgendaRequestDTO(LocalDateTime.of(2024, 05, 15, 18,30, 0), LocalDateTime.of(2024, 05, 15, 18,30, 0));
    }

    private AgendaRequestDTO getAgendaRequestInicioJaMarcado() {
        return new AgendaRequestDTO(LocalDateTime.of(2024, 05, 15, 18,40, 0), LocalDateTime.of(2024, 05, 15, 19,40, 0));
    }

    private AgendaRequestDTO getAgendaRequestFimJaMarcado() {
        return new AgendaRequestDTO(LocalDateTime.of(2024, 05, 15, 15,40, 0), LocalDateTime.of(2024, 05, 15, 16,40, 0));
    }

    private Agenda getAgendaValidaMock() {
        return new Agenda(4L, LocalDateTime.of(2024, 05, 15, 20,30, 0), LocalDateTime.of(2024, 05, 15, 21,30, 0), StatusAgendaEnum.DISPONIVEL, clienteMock, profissionalMentorMock);
    }

    private Agenda getAgendaValidaAlteradaMock() {
        return new Agenda(4L, LocalDateTime.of(2024, 05, 15, 21,30, 0), LocalDateTime.of(2024, 05, 15, 22,30, 0), StatusAgendaEnum.DISPONIVEL, clienteMock, profissionalMentorMock);
    }

    private Agenda getAgendaInicioJaMarcado() {
        return new Agenda(1L, LocalDateTime.of(2024, 05, 15, 18,40, 0), LocalDateTime.of(2024, 05, 15, 19,40, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock);
    }

    private Agenda getAgendaFimJaMarcado() {
        return new Agenda(1L, LocalDateTime.of(2024, 05, 15, 15,40, 0), LocalDateTime.of(2024, 05, 15, 16,40, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock);
    }

    private Agenda getAgendaInicioMaiorQueFimMock() {
        return new Agenda(1L, LocalDateTime.of(2024, 05, 15, 18,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock);
    }

    private Agenda getAgendaHorariosIguaisMock() {
        return new Agenda(1L, LocalDateTime.of(2024, 05, 15, 18,30, 0), LocalDateTime.of(2024, 05, 15, 18,30, 0), StatusAgendaEnum.DISPONIVEL, null, profissionalMentorMock);
    }

    private AgendaResponseDTO getCadastroAgendaResponseValidaDTO() {
        return new AgendaResponseDTO(1L, null, profissionalMentorResponseDTOMock, LocalDateTime.of(2024, 05, 15, 20,30, 0), LocalDateTime.of(2024, 05, 15, 21,30, 0), StatusAgendaEnum.DISPONIVEL);
    }

    private AgendaResponseDTO getAlteradaAgendaResponseValidaDTO() {
        return new AgendaResponseDTO(1L, null, profissionalMentorResponseDTOMock, LocalDateTime.of(2024, 05, 15, 21,30, 0), LocalDateTime.of(2024, 05, 15, 22,30, 0), StatusAgendaEnum.DISPONIVEL);
    }

    private AgendaResponseDTO getAgendaAgendadaResponseValidaDTO() {
        return new AgendaResponseDTO(2L, clienteResponseDTOMock, profissionalMentorResponseDTOMock, LocalDateTime.of(2024, 05, 15, 17,30, 0), LocalDateTime.of(2024, 05, 15, 18,30, 0), StatusAgendaEnum.AGENDADO);
    }

    private RelatorioAgendaDTO getRelatorioAgendaDTO() {
        return new RelatorioAgendaDTO(1L, LocalDateTime.of(2024, 05, 15, 16,30, 0), LocalDateTime.of(2024, 05, 15, 17,30, 0), StatusAgendaEnum.AGENDADO);
    }
}