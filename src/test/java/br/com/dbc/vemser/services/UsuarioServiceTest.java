package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.LoginRequestDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestAdminDTO;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.CargoEnum;
import br.com.dbc.vemser.repository.UsuarioRepository;
import br.com.dbc.vemser.utils.UsuarioServiceTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private CargoService cargoService;

    private Usuario usuarioDefault;
    private static final Pageable paginacao = PageRequest.of(0, 10);
    private static Page<Usuario> usuarios;

    @BeforeAll
    static void setUpAll() {
        Usuario usuario1 = UsuarioServiceTestUtils.createUsuarioDefault();
        Usuario usuario2 = UsuarioServiceTestUtils.createUsuarioDefault();
        Usuario usuario3 = UsuarioServiceTestUtils.createUsuarioDefault();

        usuario2.setCpf("98765432100");
        usuario2.setEmail("usuario2@usuario.com");
        usuario3.setCpf("12345678911");
        usuario3.setEmail("usuario3@usuario.com");

        List<Usuario> usuariosList = List.of(usuario1, usuario2, usuario3);

        usuarios = new PageImpl<>(usuariosList, paginacao, usuariosList.size());
    }

    @BeforeEach
    void setUp() {
        usuarioDefault = UsuarioServiceTestUtils.createUsuarioDefault();
    }

    @Test
    @DisplayName("Deve criar usuário sendo chamado por admin")
    void deveCriarUsuarioSendoChamadoPorAdmin() throws RegraDeNegocioException {
        // Given
        UsuarioRequestAdminDTO usuarioRequestAdminDTO = UsuarioServiceTestUtils.createUsuarioRequestAdminDTO();

        // When
        when(objectMapper.convertValue(usuarioRequestAdminDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(cargoService.getCargo(anyString())).thenReturn(UsuarioServiceTestUtils.createCargoUsuario());
        when(passwordEncoder.encode(usuarioRequestAdminDTO.getSenha())).thenReturn(usuarioDefault.getSenha());
        when(objectMapper.convertValue(usuarioDefault, UsuarioResponseCompletoDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseCompletoDTO());

        // Then
        UsuarioResponseCompletoDTO usuarioResponseCompletoDTO = usuarioService.createByAdmin(usuarioRequestAdminDTO);

        assertEquals(usuarioDefault.getId(), usuarioResponseCompletoDTO.getId());
    }

    @Test
    @DisplayName("Não deve criar usuário sendo chamado por admin, usuário com este cpf existente")
    void naoDeveCriarUsuarioSendoChamadoPorAdminUsuarioPoisJaExisteUmUsuarioComEsteCpf() {
        // Given
        UsuarioRequestAdminDTO usuarioRequestAdminDTO = UsuarioServiceTestUtils.createUsuarioRequestAdminDTO();

        // When
        when(objectMapper.convertValue(usuarioRequestAdminDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(usuarioDefault);

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.createByAdmin(usuarioRequestAdminDTO));
    }

    @Test
    @DisplayName("Não deve criar usuário sendo chamado por admin, usuário com este email existente")
    void naoDeveCriarUsuarioSendoChamadoPorAdminPoisJaExisteUmUsuarioComEsteEmail() {
        // Given
        UsuarioRequestAdminDTO usuarioRequestAdminDTO = UsuarioServiceTestUtils.createUsuarioRequestAdminDTO();

        // When
        when(objectMapper.convertValue(usuarioRequestAdminDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioDefault));

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.createByAdmin(usuarioRequestAdminDTO));
    }

    @Test
    @DisplayName("Deve criar usuário")
    void deveCriarUsuario() throws RegraDeNegocioException {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();
        usuarioDefault = UsuarioServiceTestUtils.createBaseUser();

        // When
        when(objectMapper.convertValue(usuarioRequestDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(cargoService.getCargo(CargoEnum.ROLE_USUARIO.name())).thenReturn(UsuarioServiceTestUtils.createCargoUsuario());
        when(passwordEncoder.encode(usuarioRequestDTO.getSenha())).thenReturn(usuarioDefault.getSenha());
        when(objectMapper.convertValue(usuarioDefault, UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());

        // Then
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.create(usuarioRequestDTO);

        assertEquals(usuarioDefault.getId(), usuarioResponseDTO.getId());
    }

    @Test
    @DisplayName("Não deve criar usuário, usuário com este cpf existente")
    void naoDeveCriarUsuarioPoisJaExisteUmUsuarioComEsteCpf() {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();

        // When
        when(objectMapper.convertValue(usuarioRequestDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(usuarioDefault);

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.create(usuarioRequestDTO));
    }

    @Test
    @DisplayName("Não deve criar usuário, usuário com este email existente")
    void naoDeveCriarUsuarioPoisJaExisteUmUsuarioComEsteEmail() {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();

        // When
        when(objectMapper.convertValue(usuarioRequestDTO, Usuario.class)).thenReturn(usuarioDefault);
        when(usuarioRepository.findByCpf(anyString())).thenReturn(null);
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioDefault));

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.create(usuarioRequestDTO));
    }

    @Test
    @DisplayName("Deve listar todos os usuários")
    void deveListarTodosOsUsuarios() throws BancoDeDadosException {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Usuario usuario1 = usuarios.getContent().get(0);
        Usuario usuario2 = usuarios.getContent().get(1);
        Usuario usuario3 = usuarios.getContent().get(2);

        // When
        when(usuarioRepository.findAll(pageable)).thenReturn(usuarios);
        when(objectMapper.convertValue(usuario1, UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());
        when(objectMapper.convertValue(usuario2, UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());
        when(objectMapper.convertValue(usuario3, UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());

        // Then
        Page<UsuarioResponseDTO> usuariosResponse = usuarioService.listAll(pageable);

        assertEquals(3, usuariosResponse.getTotalElements());
        assertEquals(usuarios.getContent().get(0).getId(), usuariosResponse.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Deve atualizar usuário")
    void deveAtualizarUsuario() throws Exception {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioDefault));
        when(objectMapper.convertValue(usuarioDefault, UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());

        // Then
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.update(1L, usuarioRequestDTO);

        assertEquals(usuarioDefault.getId(), usuarioResponseDTO.getId());
    }

    @Test
    @DisplayName("Não deve atualizar usuário, usuário não encontrado")
    void naoDeveAtualizarUsuarioPoisUsuarioNaoEncontrado() {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.update(1L, usuarioRequestDTO));
    }

    @Test
    @DisplayName("Não deve atualizar usuário pois já existe usuário com o mesmo cpf")
    void naoDeveAtualizarUsuarioPoisJaExisteUsuarioComMesmoCpf() {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();
        usuario.setCpf("98765432100");

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByCpf(anyString())).thenReturn(usuarioDefault);

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.update(1L, usuarioRequestDTO));
    }

    @Test
    @DisplayName("Não deve atualizar usuário pois já existe usuário com o mesmo email")
    void naoDeveAtualizarUsuarioPoisJaExisteUsuarioComMesmoEmail() {
        // Given
        UsuarioRequestDTO usuarioRequestDTO = UsuarioServiceTestUtils.createUsuarioRequestDTO();
        usuarioRequestDTO.setEmail("usuario@gmail.com");
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();
        usuario.setEmail("usuario2@gmail.com");

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioDefault));

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.update(1L, usuarioRequestDTO));
    }

    @Test
    @DisplayName("Deve deletar usuário")
    void deveDeletarUsuario() throws Exception {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Then
        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    @DisplayName("Não deve deletar usuário, usuário não encontrado")
    void naoDeveDeletarUsuarioPoisUsuarioNaoEncontrado() {
        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.delete(1L));
    }

    @Test
    @DisplayName("Deve listar usuário por id")
    void deveListarUsuarioPorId() throws Exception {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(objectMapper.convertValue(usuario, UsuarioResponseCompletoDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseCompletoDTO());

        // Then
        UsuarioResponseCompletoDTO usuarioResponseCompletoDTO = usuarioService.listById(1L);

        assertEquals(usuario.getId(), usuarioResponseCompletoDTO.getId());
    }

    @Test
    @DisplayName("Não deve listar usuário por id, usuário não encontrado")
    void naoDeveListarUsuarioPorIdPoisUsuarioNaoEncontrado() {
        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.listById(1L));
    }

    @Test
    @DisplayName("Deve remover endereço")
    void deveRemoverEndereco() throws Exception {
        // Given
        Long idEnderecoRemocao = 1L;

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioDefault));

        // Then
        usuarioService.removerEndereco(usuarioDefault.getId(), idEnderecoRemocao);

        verify(usuarioRepository, times(1)).save(usuarioDefault);
    }

    @Test
    @DisplayName("Deve retornar o usuario com id passado")
    void deveRetornarUsuarioAoPassarIDRelatorioDeUsuario() {
        // Given
        Set<Usuario> setUsuarios = Set.of(UsuarioServiceTestUtils.createBaseUser());


        // When
        when(usuarioRepository.relatorioUsuario(anyLong())).thenReturn(setUsuarios);
        when(objectMapper.convertValue(setUsuarios.toArray()[0], UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());

        // Then
        Set<UsuarioResponseDTO> usuariosResponse = usuarioService.relatorioUsuario(1L);
        assertEquals(1, usuariosResponse.size());
    }

    @Test
    @DisplayName("Deve retornar a lista de relatorio de usuario")
    void deveRetornarListaDeRelatorioDeUsuario() {
        // Given
        Set<Usuario> setUsuarios = Set.of(UsuarioServiceTestUtils.createBaseUser(), UsuarioServiceTestUtils.createUsuarioDefault());
        UsuarioResponseDTO usuarioResponseDTO2 = UsuarioServiceTestUtils.createUsuarioResponseDTO();
        usuarioResponseDTO2.setId(2L);

        // When
        when(usuarioRepository.relatorioUsuario(anyLong())).thenReturn(setUsuarios);
        when(objectMapper.convertValue(setUsuarios.toArray()[0], UsuarioResponseDTO.class)).thenReturn(UsuarioServiceTestUtils.createUsuarioResponseDTO());
        when(objectMapper.convertValue(setUsuarios.toArray()[1], UsuarioResponseDTO.class)).thenReturn(usuarioResponseDTO2);


        // Then
        Set<UsuarioResponseDTO> usuariosResponse = usuarioService.relatorioUsuario(null);
        assertEquals(2, usuariosResponse.size());
    }

    @Test
    @DisplayName("Deve retornar o usuario com id passado")
    void deveRetornarUsuarioAoPassarID() {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Then
        Optional<Usuario> usuarioResponse = usuarioService.findById(1L);
        assertEquals(usuario.getId(), usuarioResponse.get().getId());
    }

    @Test
    @DisplayName("Deve retornar o usuário por email")
    void deveRetornarUsuarioPorEmail() {
        // When
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioDefault));

        // Then
        Usuario usuarioResponse = usuarioService.findByEmail(usuarioDefault.getEmail());
        assertEquals(usuarioDefault.getId(), usuarioResponse.getId());
    }

    @Test
    @DisplayName("Deve desativar usuário passando id")
    void deveDesativarUsuarioPassandoID() throws Exception {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Then
        usuarioService.ativarInativarUsuario(1L);

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve desativar usuário passando id")
    void deveAtivarUsuarioPassandoID() throws Exception {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();
        usuario.setAtivo(false);

        // When
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Then
        usuarioService.ativarInativarUsuario(1L);

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("Deve verificar se o usuário está ativo")
    void deveVerificarSeOUsuarioEstaAtivo() throws RegraDeNegocioException {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(usuario.getEmail());

        // When
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        // Then
        usuarioService.userIsAtivo(loginRequestDTO);
    }

    @Test
    @DisplayName("Deve dar erro ao verificar se o usuário está ativo")
    void deveDarErroAoVerificarSeOUsuarioEstaAtivo() {
        // Given
        Usuario usuario = UsuarioServiceTestUtils.createBaseUser();
        usuario.setAtivo(false);
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail(usuario.getEmail());

        // When
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        // Then
        assertThrows(RegraDeNegocioException.class, () -> usuarioService.userIsAtivo(loginRequestDTO));
    }

    @Test
    @DisplayName("Deve atualizar roles de usuário")
    void deveAtualizarRolesDeUsuario() {
        // Given
        Set<Cargo> cargos = Set.of(UsuarioServiceTestUtils.createCargoUsuario());

        // Then
        usuarioService.atualizarRole(usuarioDefault, cargos);

        verify(usuarioRepository, times(1)).save(usuarioDefault);
    }
}