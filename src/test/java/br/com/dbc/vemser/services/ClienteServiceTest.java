package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    @DisplayName("Deveria listar todos os clientes com sucesso")
    public void deveriaListarTodosOsClientesComSucesso() throws Exception {
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
    @DisplayName("Deveria remover um cliente com sucesso")
    public void deveriaRemoverClienteComSucesso() throws Exception {

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

    private static Usuario retornarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

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

}