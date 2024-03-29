package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.ClienteMapper;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CargoService cargoService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeAll
    public static void setup() {
        Mockito.mockStatic(ClienteMapper.class);
    }

    @Test
    @DisplayName("Deve criar um novo cliente com sucesso")
    public void deveCriarClienteComSucesso() throws Exception {
        // ARRANGE
        ClienteRequestDTO clienteRequestDTOMock = retornarClienteRequestDTO();
        Usuario usuarioMock = retornarUsuario();
        Cliente clienteMock = retornarCliente();
        ClienteResponseDTO clienteResponseDTOMock = retornarClienteResponseDTO();

        // ACT
        when(usuarioService.getUsuario(1L)).thenReturn(usuarioMock);


        when(ClienteMapper.clienteRequestDTOtoEntity(clienteRequestDTOMock)).thenReturn(clienteMock);
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        when(clienteRepository.save(any())).thenReturn(clienteMock);
        when(objectMapper.convertValue(clienteMock, ClienteResponseDTO.class)).thenReturn(clienteResponseDTOMock);

        ClienteResponseDTO clienteResponseDTO = clienteService.create(clienteRequestDTOMock, 1L);

        // ASSERT
        assertNotNull(clienteResponseDTO);
        assertEquals(clienteResponseDTO.getId(), clienteResponseDTOMock.getId());
    }

    @Test
    @DisplayName("Deve criar um Cliente com usuário logado")
    public void deveCriarClienteComUsuarioLogado() throws Exception {
        // ARRANGE
        ClienteRequestDTO clienteRequestDTOMock = retornarClienteRequestDTO();
        Usuario usuarioMock = retornarUsuario();
        Cliente clienteMock = retornarCliente();
        ClienteResponseDTO clienteResponseDTOMock = retornarClienteResponseDTO();

        // ACT
        when(usuarioService.getIdLoggedUser()).thenReturn(1L);
        when(usuarioService.getUsuario(1L)).thenReturn(usuarioMock);

        when(ClienteMapper.clienteRequestDTOtoEntity(clienteRequestDTOMock)).thenReturn(clienteMock);
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        when(clienteRepository.save(any())).thenReturn(clienteMock);
        when(objectMapper.convertValue(clienteMock, ClienteResponseDTO.class)).thenReturn(clienteResponseDTOMock);

        ClienteResponseDTO clienteResponseDTO = clienteService.create(clienteRequestDTOMock, null);

        // ASSERT
        assertNotNull(clienteResponseDTO);
        assertEquals(clienteResponseDTO.getId(), clienteResponseDTOMock.getId());
    }

    @Test
    void deveLancarExecaoAoCriarClienteComUsuarioComClienteJaCriado() {
        // ARRANGE
        Cliente clienteMock = retornarCliente();
        Usuario usuarioMock = retornarUsuario();
        clienteMock.setUsuario(usuarioMock);

        List<Cliente> clientesMock = List.of(clienteMock);

        // ACT
        when(clienteRepository.findAll()).thenReturn(clientesMock);

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> clienteService.create(retornarClienteRequestDTO(), 1L));
    }

    @Test
    @DisplayName("Deve listar todos os clientes com sucesso")
    public void deveListarTodosOsClientesComSucesso() throws Exception {
        // ARRANGE
        Cliente clienteMock = retornarCliente();
        List<Cliente> clientesMock = Collections.singletonList(clienteMock);
        Page<Cliente> pageClientesMock = new PageImpl<>(clientesMock);

        Pageable pageable = PageRequest.of(0, 10);

        // ACT
        when(clienteRepository.findAll(pageable)).thenReturn(pageClientesMock);
        Page<ClienteResponseDTO> clientesResponseDTOPage = clienteService.listAll(pageable);

        // ASSERT
        assertNotNull(clientesResponseDTOPage);
        assertEquals(clientesResponseDTOPage.getContent().size(), clientesMock.size());
    }

    @Test
    @DisplayName("Deve remover um cliente com sucesso")
    public void deveRemoverClienteComSucesso() throws RegraDeNegocioException {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        Cliente clienteMock = retornarCliente();

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(java.util.Optional.of(clienteMock));

        clienteService.delete(idAleatorio);

        // ASSERT
        assertThrows(RegraDeNegocioException.class, () -> clienteService.delete(1L));
        verify(clienteRepository, times(1)).delete(clienteMock);
    }

    @Test
    @DisplayName("Deve atualizar um cliente com sucesso")
    public void deveAtualizarClienteComSucesso() throws Exception {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        ClienteRequestDTO clienteRequestDTOMock = retornarClienteRequestDTO();
        Cliente clienteMock = retornarCliente();
        ClienteResponseDTO clienteResponseDTOMock = retornarClienteResponseDTO();

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(Optional.of(clienteMock));
        when(clienteRepository.save(any())).thenReturn(clienteMock);
        when(objectMapper.convertValue(clienteMock, ClienteResponseDTO.class)).thenReturn(clienteResponseDTOMock);

        ClienteResponseDTO clienteResponseDTO = clienteService.update(idAleatorio, clienteRequestDTOMock);

        // ASSERT
        assertNotNull(clienteResponseDTO);
        assertEquals(clienteResponseDTO.getId(), clienteResponseDTOMock.getId());
    }


    @Test
    @DisplayName("Deve lançar exceção ao remover cliente com agendas associadas")
    public void deveLancarExcecaoAoRemoverClienteComAgendasAssociadas() {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        Cliente clienteMock = new Cliente();
        clienteMock.setAgendas(Collections.singletonList(new Agenda()));

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(java.util.Optional.of(clienteMock));

        // ASSERT
        RegraDeNegocioException exception = assertThrows(RegraDeNegocioException.class, () -> clienteService.delete(idAleatorio));
        assertEquals("Há agendas cadastradas com este cliente, não é possível deletá-lo.", exception.getMessage());

        verify(clienteRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve listar um cliente por id e converter para DTO")
    public void deveBuscarUmClientePorIdEconverterParaDTO() throws Exception {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        Cliente clienteMock = retornarCliente();
        ClienteResponseCompletoDTO clienteResponseCompletoDTO = retornarClienteResponseCompletoDTO();

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(java.util.Optional.of(clienteMock));
        when(objectMapper.convertValue(clienteMock, ClienteResponseCompletoDTO.class)).thenReturn(clienteResponseCompletoDTO);

        ClienteResponseCompletoDTO clienteResponseCompletoDTORetornado = clienteService.listById(idAleatorio);

        // ASSERT
        assertNotNull(clienteResponseCompletoDTORetornado);
        assertEquals(clienteResponseCompletoDTORetornado, clienteResponseCompletoDTO);
    }

    @Test
    @DisplayName("Deve retornar um cliente por id")
    public void deveRetornarClientePorId() throws Exception {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        Cliente clienteMock = retornarCliente();

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(java.util.Optional.of(clienteMock));

        Cliente clienteRetornado = clienteService.getCliente(idAleatorio);

        // ASSERT
        assertNotNull(clienteRetornado);
        assertEquals(clienteRetornado, clienteMock);
    }

    @Test
    @DisplayName("Deve retornar o cliente pelo ID do usuário")
    public void deveRetornarClientePeloIdDoUsuario() throws RegraDeNegocioException {
        // ARRANGE
        Long idUsuario = 1L;
        Cliente clienteMock = new Cliente();

        // ACT
        when(clienteRepository.findByUsuario_Id(idUsuario)).thenReturn(Optional.of(clienteMock));

        Cliente clienteRetornado = clienteService.getByUsuario(idUsuario);

        // ASSERT
        assertNotNull(clienteRetornado);
        assertEquals(clienteMock, clienteRetornado);
    }

    @Test
    @DisplayName("Deve ativarInativarCliente com sucesso")
    public void deveAtivarInativarClienteComSucesso() throws Exception {
        // ARRANGE
        Long idAleatorio = new Random().nextLong();
        Cliente clienteMock = retornarCliente();

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(Optional.of(clienteMock));

        clienteService.ativarInativarCliente(idAleatorio);

        // ASSERT
        assertFalse(clienteMock.isAtivo());

        // ACT
        when(clienteRepository.findById(idAleatorio)).thenReturn(Optional.of(clienteMock));

        clienteService.ativarInativarCliente(idAleatorio);

        // ASSERT
        assertTrue(clienteMock.isAtivo());

    }

    @Test
    @DisplayName("Deve desativar com sucesso com cliente logado")
    public void deveDesativarComSucessoComClienteLogado() throws Exception {
        // ARRANGE
        Cliente clienteMock = retornarCliente();

        // ACT
        when(usuarioService.getIdLoggedUser()).thenReturn(1L);
        when(clienteRepository.findByUsuario_Id(1L)).thenReturn(Optional.of(clienteMock));

        clienteService.ativarInativarCliente(null);

        // ASSERT
        assertFalse(clienteMock.isAtivo());
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

    private static ClienteRequestDTO retornarClienteRequestDTO() {
        return new ClienteRequestDTO(PlanoEnum.BASICO, 'N', 'N', 'N', "Professor de Matemática", "Auxiliar as pessoas", "321654987", "http://comprovantematricula.com.br", "UEM - Universidade Estadual de Maringá", "Bacharelado em Matemática", Arrays.asList(AreasDeInteresse.TI, AreasDeInteresse.DESIGN), LocalDate.parse("2022-06-01"), LocalDate.parse("2023-06-01"));

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

    private static ClienteResponseDTO retornarClienteResponseDTO() {
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

        clienteResponseDTO.setId(1L);
        clienteResponseDTO.setTipoPlano(PlanoEnum.BASICO);
        clienteResponseDTO.setControleParental('N');
        clienteResponseDTO.setEhEstudante('N');
        clienteResponseDTO.setEhProfissionalRealocacao('N');
        clienteResponseDTO.setProfissao("Professor de Matemática");
        clienteResponseDTO.setObjetivoProfissional("Auxiliar as pessoas");
        clienteResponseDTO.setMatricula("321654987");
        clienteResponseDTO.setComprovanteMatricula("http://comprovantematricula.com.br");
        clienteResponseDTO.setInstituicao("UEM - Universidade Estadual de Maringá");
        clienteResponseDTO.setCurso("Bacharelado em Matemática");
        clienteResponseDTO.setDataInicio(LocalDate.parse("2022-06-01"));
        clienteResponseDTO.setDataTermino(LocalDate.parse("2023-06-01"));

        List<AreaInteresse> interesses = new ArrayList<>();
        interesses.add(new AreaInteresse(1L, AreasDeInteresse.TI, retornarCliente()));
        interesses.add(new AreaInteresse(2L, AreasDeInteresse.DESIGN, retornarCliente()));

        clienteResponseDTO.setInteresses(interesses);

        clienteResponseDTO.setUsuario(retornarUsuarioResponseDTO());

        return clienteResponseDTO;
    }

    private static UsuarioResponseDTO retornarUsuarioResponseDTO() {
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setId(1L);
        usuarioResponseDTO.setNome("João");
        usuarioResponseDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioResponseDTO.setCpf("12312312312");
        usuarioResponseDTO.setEmail("joao@test.com");

        return usuarioResponseDTO;
    }

    private ClienteResponseCompletoDTO retornarClienteResponseCompletoDTO() {
        ClienteResponseCompletoDTO clienteResponseCompletoDTO = new ClienteResponseCompletoDTO();

        clienteResponseCompletoDTO.setId(1L);
        clienteResponseCompletoDTO.setTipoPlano(PlanoEnum.BASICO);
        clienteResponseCompletoDTO.setControleParental('N');
        clienteResponseCompletoDTO.setEhEstudante('N');
        clienteResponseCompletoDTO.setEhProfissionalRealocacao('N');
        clienteResponseCompletoDTO.setProfissao("Professor de Matemática");
        clienteResponseCompletoDTO.setObjetivoProfissional("Auxiliar as pessoas");
        clienteResponseCompletoDTO.setMatricula("321654987");
        clienteResponseCompletoDTO.setComprovanteMatricula("http://comprovantematricula.com.br");
        clienteResponseCompletoDTO.setInstituicao("UEM - Universidade Estadual de Maringá");
        clienteResponseCompletoDTO.setCurso("Bacharelado em Matemática");
        clienteResponseCompletoDTO.setDataInicio(LocalDate.parse("2022-06-01"));
        clienteResponseCompletoDTO.setDataTermino(LocalDate.parse("2023-06-01"));

        List<AreaInteresse> interesses = new ArrayList<>();
        interesses.add(new AreaInteresse(1L, AreasDeInteresse.TI, retornarCliente()));
        interesses.add(new AreaInteresse(2L, AreasDeInteresse.DESIGN, retornarCliente()));

        clienteResponseCompletoDTO.setInteresses(interesses);

        return clienteResponseCompletoDTO;
    }

}