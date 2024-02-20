package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.repository.EnderecoRepository;
import br.com.dbc.vemser.utils.EnderecoServiceTestUtils;
import br.com.dbc.vemser.utils.UsuarioServiceTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @Spy
    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco enderecoDefault;

    @BeforeEach
    void setUp() {
        enderecoDefault = EnderecoServiceTestUtils.createEnderecoDefault();
    }

    @Test
    @DisplayName("Deve criar endereço corretamente ao passar idUsuario")
    void deveCriarEnderecoCorretamentePassandoIdUsuario() throws Exception {
        // given
        Long idUsuario = 1L;
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoDefault();
        EnderecoRequestDTO enderecoRequestDTO = EnderecoServiceTestUtils.createEnderecoRequestDTO();
        Usuario usuarioMock = UsuarioServiceTestUtils.createUsuarioDefault();
        EnderecoResponseDTO enderecoResponseDTOMock = EnderecoServiceTestUtils.createEnderecoResponseDTO();

        // when
        when(objectMapper.convertValue(enderecoRequestDTO, Endereco.class)).thenReturn(enderecoMock);
        when(usuarioService.getUsuario(idUsuario)).thenReturn(usuarioMock);
        when(objectMapper.convertValue(enderecoMock, EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock);

        // then
        EnderecoResponseDTO result = enderecoService.create(idUsuario, enderecoRequestDTO);

        // verify
        assertEquals(enderecoResponseDTOMock, result);
    }

    @Test
    @DisplayName("Deve criar endereço corretamente ao não passar idUsuario")
    void deveCriarEnderecoCorretamenteNaoPassandoIdUsuario() throws Exception {
        // given
        EnderecoRequestDTO enderecoRequestDTO = EnderecoServiceTestUtils.createEnderecoRequestDTO();
        Usuario usuarioMock = UsuarioServiceTestUtils.createUsuarioDefault();
        EnderecoResponseDTO enderecoResponseDTOMock = EnderecoServiceTestUtils.createEnderecoResponseDTO();

        // when
        when(objectMapper.convertValue(enderecoRequestDTO, Endereco.class)).thenReturn(enderecoDefault);
        when(usuarioService.getUsuario(usuarioService.getIdLoggedUser())).thenReturn(usuarioMock);
        when(objectMapper.convertValue(enderecoDefault, EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoDefault);

        // then
        EnderecoResponseDTO result = enderecoService.create(null, enderecoRequestDTO);

        // verify
        assertEquals(enderecoResponseDTOMock, result);
    }

    @Test
    @DisplayName("Deve listar todos os endereços corretamente")
    void deveListarTodosOsEnderecosCorretamente() throws Exception {
        // given
        EnderecoResponseDTO enderecoResponseDTOMock = EnderecoServiceTestUtils.createEnderecoResponseDTO();
        Page<Endereco> pageEndereco = EnderecoServiceTestUtils.createPageEndereco();

        // when
        when(enderecoRepository.findAll(any(), any(), any(), any())).thenReturn(pageEndereco);
        when(objectMapper.convertValue(pageEndereco.toList().get(0), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
        when(objectMapper.convertValue(pageEndereco.toList().get(1), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
        when(objectMapper.convertValue(pageEndereco.toList().get(2), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);

        // then
        enderecoService.listAll(EnderecoServiceTestUtils.createPageable(), null, null, null);

        // verify
        assertEquals(enderecoResponseDTOMock, enderecoService.listAll(EnderecoServiceTestUtils.createPageable(), null, null, null).getContent().get(0));
    }

//    @Test
//    @DisplayName("Deve listar todos os endereços do usuário corretamente")
//    void deveListarTodosOsEnderecosDoUsuarioCorretamente() throws Exception {
//        // given
//        EnderecoResponseDTO enderecoResponseDTOMock = EnderecoServiceTestUtils.createEnderecoResponseDTO();
//        Page<Endereco> pageEndereco = EnderecoServiceTestUtils.createPageEndereco();
//
//        // when
//        when(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())).thenReturn(1L);
//        when(enderecoRepository.findAllUsuario(any(), any(), any())).thenReturn(pageEndereco);
//        when(objectMapper.convertValue(pageEndereco.toList().get(0), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
//        when(objectMapper.convertValue(pageEndereco.toList().get(1), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
//        when(objectMapper.convertValue(pageEndereco.toList().get(2), EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
//
//        // then
//        enderecoService.listAllUsuario(EnderecoServiceTestUtils.createPageable(), null);
//
//        // verify
//        assertEquals(enderecoResponseDTOMock, enderecoService.listAllUsuario(EnderecoServiceTestUtils.createPageable(), null).getContent().get(0));
//    }

    @Test
    @DisplayName("Deve atualizar endereço corretamente")
    void deveAtualizarEnderecoCorretamente() throws Exception {
        // given
        EnderecoRequestDTO enderecoRequestDTO = EnderecoServiceTestUtils.createEnderecoRequestDTO();
        EnderecoResponseDTO enderecoResponseDTOMock = EnderecoServiceTestUtils.createEnderecoResponseDTO();

        // when
        doReturn(enderecoDefault).when(enderecoService).getEndereco(1L);
        when(objectMapper.convertValue(enderecoDefault, EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);
        when(objectMapper.convertValue(enderecoDefault, EnderecoResponseDTO.class)).thenReturn(enderecoResponseDTOMock);

        // then
        EnderecoResponseDTO result = enderecoService.update(1L, enderecoRequestDTO);

        // verify
        assertEquals(enderecoResponseDTOMock, result);
    }

    @Test
    @DisplayName("Deve deletar endereço corretamente")
    void deveDeletarEnderecoCorretamente() throws Exception {
        // given
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoDefault();

        // when
        when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.of(enderecoMock));

        // then
        enderecoService.delete(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar endereço inexistente")
    void deveLancarExcecaoAoTentarDeletarEnderecoInexistente() throws Exception {
        // given
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoDefault();

        // when
        when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.of(enderecoMock));

        // then
        assertThrows(Exception.class, () -> enderecoService.delete(2L));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar endereço inexistente")
    void deveLancarExcecaoAoTentarAtualizarEnderecoInexistente() throws Exception {
        // given
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoDefault();

        // when
        when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.of(enderecoMock));

        // then
        assertThrows(Exception.class, () -> enderecoService.update(2L, EnderecoServiceTestUtils.createEnderecoRequestDTO()));
    }

    @Test
    @DisplayName("Deve lançar exceção se não achar o usuario")
    void deveLancarExcecaoSeNaoAcharUsuario() throws Exception {
        // given
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoComPessoa();

        // when
//        doReturn(enderecoMock).when(enderecoService).getEndereco(1L);
        when(usuarioService.getUsuario(1L)).thenReturn(null);

        // then
        assertThrows(Exception.class, () -> enderecoService.create(1L, EnderecoServiceTestUtils.createEnderecoRequestDTO()));
    }

    @Test
    @DisplayName("Deve mostrar endereço corretamente")
    void deveMostrarEnderecoCorretamente() throws Exception {
        // given
        Endereco enderecoMock = EnderecoServiceTestUtils.createEnderecoDefault();

        // when
        when(enderecoRepository.findById(1L)).thenReturn(java.util.Optional.of(enderecoMock));

        // then
        enderecoService.getEndereco(1L);
    }

}