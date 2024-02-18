package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.ProfissionalMentorMapper;
import br.com.dbc.vemser.model.dtos.request.ProfissionalMentorRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalMentorResponseDTO;
import br.com.dbc.vemser.model.entities.*;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.ProfissionalMentorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfissionalMentorServiceTest {

    @Mock
    private ProfissionalMentorRepository profissionalMentorRepository;

    @Mock
    private CargoService cargoService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ProfissionalMentorService profissionalMentorService;

    @Test
    @DisplayName("Deve criar um novo profissional mentor")
    public void deveCriarUmNovoProfissionalMentor() throws Exception {
        // ARRANGE
        ProfissionalMentorRequestDTO profissionalMentorRequestDTOMock = retornarProfissionalMentorRequestDTO();
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        ProfissionalMentorResponseDTO profissionalMentorResponseDTOMock = retornarProfissionalMentorResponseDTO();

        // ACT
        when(usuarioService.getUsuario(1L)).thenReturn(retornarUsuario());

        Mockito.mockStatic(ProfissionalMentorMapper.class);

        when(ProfissionalMentorMapper.profissionalMentorToEntity(profissionalMentorRequestDTOMock)).thenReturn(profissionalMentorMock);
        when(profissionalMentorRepository.findAll()).thenReturn(new ArrayList<>());
        when(profissionalMentorRepository.save(profissionalMentorMock)).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(profissionalMentorMock, ProfissionalMentorResponseDTO.class)).thenReturn(profissionalMentorResponseDTOMock);

        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = profissionalMentorService.create(1L, profissionalMentorRequestDTOMock);

        // ASSERT
        assertNotNull(profissionalMentorResponseDTO);
        assertEquals(profissionalMentorResponseDTO.getCarteiraDeTrabalho(), profissionalMentorRequestDTOMock.getCarteiraDeTrabalho());
    }

    @Test
    @DisplayName("Deve listar todos os profissionais mentores")
    public void deveListarTodosOsProfissionaisMentores() throws BancoDeDadosException {
        // ARRANGE
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        List<ProfissionalMentor> profissionaisMentoresMock = new ArrayList<>();
        profissionaisMentoresMock.add(profissionalMentorMock);
        Page<ProfissionalMentor> pageProfissionaisMentoresMock = new PageImpl<>(profissionaisMentoresMock);

        Pageable pageable = PageRequest.of(0, 10);

        // ACT
        when(profissionalMentorRepository.findAll(pageable)).thenReturn(pageProfissionaisMentoresMock);
        Page<ProfissionalMentorResponseDTO> profissionaisMentoresDTOPage = profissionalMentorService.listAll(pageable);

        // ASSERT
        assertNotNull(profissionaisMentoresDTOPage);
        assertEquals(profissionaisMentoresDTOPage.getContent().size(), profissionaisMentoresMock.size());
    }

    @Test
    @DisplayName("Deve atualizar um profissional mentor")
    public void deveAtualizarUmProfissionalMentor() throws Exception {
        // ARRANGE
        ProfissionalMentorRequestDTO profissionalMentorRequestDTOMock = retornarProfissionalMentorRequestDTO();
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        ProfissionalMentorResponseDTO profissionalMentorResponseDTOMock = retornarProfissionalMentorResponseDTO();
        Long idAleatorio = new Random().nextLong();

        // ACT
        when(profissionalMentorRepository.findById(idAleatorio)).thenReturn(Optional.of(profissionalMentorMock));
        when(profissionalMentorRepository.save(profissionalMentorMock)).thenReturn(profissionalMentorMock);
        when(objectMapper.convertValue(profissionalMentorMock, ProfissionalMentorResponseDTO.class)).thenReturn(profissionalMentorResponseDTOMock);

        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = profissionalMentorService.update(idAleatorio, profissionalMentorRequestDTOMock);

        // ASSERT
        assertNotNull(profissionalMentorResponseDTO);
        assertEquals(profissionalMentorResponseDTO.getCarteiraDeTrabalho(), profissionalMentorRequestDTOMock.getCarteiraDeTrabalho());
    }

    @Test
    @DisplayName("Deve remover um profissional mentor")
    public void deveDeletarUmProfissionalMentor() throws Exception {
        // ARRANGE
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        Long idAleatorio = new Random().nextLong();

        // ACT
        when(profissionalMentorRepository.findById(idAleatorio)).thenReturn(Optional.of(profissionalMentorMock));

        profissionalMentorService.delete(idAleatorio);

        // ASSERT
        verify(profissionalMentorRepository, Mockito.times(1)).delete(profissionalMentorMock);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar um profissional mentor com agendas cadastradas")
    public void deveLancaExcecaoAoDeletarProfissionalMentorComAgendasCadastradas() {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        ProfissionalMentor profissionalMentorMock = new ProfissionalMentor();
        profissionalMentorMock.setAgendas(Collections.singletonList(new Agenda()));

        // ACT
        when(profissionalMentorRepository.findById(idAleatorio)).thenReturn(Optional.of(profissionalMentorMock));

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> profissionalMentorService.delete(idAleatorio));
    }

    @Test
    @DisplayName("Deve retornar um profissional mentor por ID")
    public void deveRetornarUsuarioPorID() throws Exception {
        // ARRANGE
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        Long idAleatorio = new Random().nextLong();

        // ACT
        when(profissionalMentorRepository.findByUsuario_Id(idAleatorio)).thenReturn(Optional.of(profissionalMentorMock));

        ProfissionalMentor profissionalMentor = profissionalMentorService.getByUsuario(idAleatorio);

        // ASSERT
        assertNotNull(profissionalMentor);
        assertEquals(profissionalMentor.getCarteiraDeTrabalho(), profissionalMentorMock.getCarteiraDeTrabalho());
    }

    @Test
    @DisplayName("Deve retornar um profissional mentor completo por ID")
    public void deveRetornarProfissionalMentorCompletoPorID() throws Exception {
        // ARRANGE
        Long idProfissionalMentor = new Random().nextLong();
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        ProfissionalMentorResponseCompletoDTO profissionalMentorResponseCompletoDTOMock = criarProfissionalMentorResponseCompletoDTO();

        // ACT
        when(profissionalMentorRepository.findById(idProfissionalMentor)).thenReturn(Optional.of(profissionalMentorMock));
        when(objectMapper.convertValue(profissionalMentorMock, ProfissionalMentorResponseCompletoDTO.class)).thenReturn(profissionalMentorResponseCompletoDTOMock);

        ProfissionalMentorResponseCompletoDTO profissionalMentorResponseCompletoDTO = profissionalMentorService.getById(idProfissionalMentor);

        // ASSERT
        assertNotNull(profissionalMentorResponseCompletoDTO);
        assertEquals(profissionalMentorResponseCompletoDTOMock.getId(), profissionalMentorResponseCompletoDTO.getId());
    }

    @Test
    @DisplayName("Deve ativar/inativar um profissional mentor")
    public void deveAtivarInativarProfissionalMentor() throws Exception {
        // ARRANGE
        ProfissionalMentor profissionalMentorMock = retornarProfissionalMentor();
        Long idAleatorio = new Random().nextLong();

        // ACT
        when(profissionalMentorRepository.findById(idAleatorio)).thenReturn(Optional.of(profissionalMentorMock));
        when(profissionalMentorRepository.save(profissionalMentorMock)).thenReturn(profissionalMentorMock);

        profissionalMentorService.ativarInativarProfissional(idAleatorio);

        // ASSERT
        verify(profissionalMentorRepository, Mockito.times(1)).save(profissionalMentorMock);
    }

    private static ProfissionalMentorRequestDTO retornarProfissionalMentorRequestDTO() {
        ProfissionalMentorRequestDTO profissionalMentorRequestDTO = new ProfissionalMentorRequestDTO();

        List<AreasDeInteresse> areasDeInteresse = new ArrayList<>();
        areasDeInteresse.add(AreasDeInteresse.TI);
        areasDeInteresse.add(AreasDeInteresse.DESIGN);
        profissionalMentorRequestDTO.setAtuacoes(areasDeInteresse);

        profissionalMentorRequestDTO.setNivelExperienciaEnum(NivelExperienciaEnum.JUNIOR);
        profissionalMentorRequestDTO.setCarteiraDeTrabalho("12312312312");

        return profissionalMentorRequestDTO;
    }

    private static ProfissionalMentorResponseDTO retornarProfissionalMentorResponseDTO() {
        ProfissionalMentorResponseDTO profissionalMentorResponseDTO = new ProfissionalMentorResponseDTO();

        profissionalMentorResponseDTO.setId(1L);

        List<AreaAtuacao> atuacoes = new ArrayList<>();
        atuacoes.add(new AreaAtuacao(1L, AreasDeInteresse.TI, retornarProfissionalMentor()));
        atuacoes.add(new AreaAtuacao(2L, AreasDeInteresse.DESIGN, retornarProfissionalMentor()));
        profissionalMentorResponseDTO.setAtuacoes(atuacoes);

        profissionalMentorResponseDTO.setNivelExperienciaEnum(NivelExperienciaEnum.JUNIOR);
        profissionalMentorResponseDTO.setCarteiraDeTrabalho("12312312312");

        return profissionalMentorResponseDTO;
    }

    private static ProfissionalMentorResponseCompletoDTO criarProfissionalMentorResponseCompletoDTO() {
        ProfissionalMentorResponseCompletoDTO profissionalMentorResponseCompletoDTO = new ProfissionalMentorResponseCompletoDTO();

        profissionalMentorResponseCompletoDTO.setId(1L);

        profissionalMentorResponseCompletoDTO.setAtuacoes(new ArrayList<>());
        profissionalMentorResponseCompletoDTO.getAtuacoes().add(new AreaAtuacao(1L, AreasDeInteresse.TI, retornarProfissionalMentor()));
        profissionalMentorResponseCompletoDTO.getAtuacoes().add(new AreaAtuacao(2L, AreasDeInteresse.DESIGN, retornarProfissionalMentor()));

        profissionalMentorResponseCompletoDTO.setNivelExperienciaEnum(NivelExperienciaEnum.JUNIOR);
        profissionalMentorResponseCompletoDTO.setCarteiraDeTrabalho("12312312312");

        return profissionalMentorResponseCompletoDTO;
    }

    private static ProfissionalMentor retornarProfissionalMentor() {
        ProfissionalMentor profissionalMentor = new ProfissionalMentor();

        profissionalMentor.setId(1L);
        profissionalMentor.setNivelExperienciaEnum(NivelExperienciaEnum.JUNIOR);
        profissionalMentor.setCarteiraDeTrabalho("12312312312");
        profissionalMentor.setAtivo(true);

        Usuario usuario = retornarUsuario();
        profissionalMentor.setUsuario(usuario);

        List<Agenda> agendas = new ArrayList<>();
        agendas.add(new Agenda(1L, LocalDateTime.parse("2024-01-10T17:19:00"), LocalDateTime.parse("2024-01-10T20:00:00"), StatusAgendaEnum.DISPONIVEL, retornarCliente(), profissionalMentor));

        return profissionalMentor;

    }
    private static Usuario retornarUsuario() {
        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setNome("João");
        usuario.setDataNascimento(LocalDate.parse("2022-06-01"));
        usuario.setCpf("12312312312");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("12345");
        usuario.setEhPcd('N');
        usuario.setTipoDeficiencia("Ocular");
        usuario.setCertificadoDeficienciaGov("teste.certificado.com");
        usuario.setImagemDocumento("imagem");
        usuario.setAtivo(true);

        return usuario;
    }

    private static Cliente retornarCliente() {
        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setTipoPlano(PlanoEnum.BASICO);
        cliente.setControleParental('N');
        cliente.setEhEstudante('N');
        cliente.setEhProfissionalRealocacao('N');
        cliente.setProfissao("Professor de Matemática");
        cliente.setObjetivoProfissional("Auxiliar as pessoas");
        cliente.setMatricula("321654987");
        cliente.setComprovanteMatricula("http://comprovantematricula.com.br");
        cliente.setInstituicao("UEM - Universidade Estadual de Maringá");
        cliente.setCurso("Bacharelado em Matemática");
        cliente.setDataInicio(LocalDate.parse("2022-06-01"));
        cliente.setDataTermino(LocalDate.parse("2023-06-01"));
        cliente.setAtivo(true);

        cliente.setUsuario(retornarUsuario());

        cliente.setInteresses(new ArrayList<>());
        cliente.getInteresses().add(new AreaInteresse(1L, AreasDeInteresse.TI, cliente));
        cliente.getInteresses().add(new AreaInteresse(2L, AreasDeInteresse.DESIGN, cliente));

        return cliente;
    }

}