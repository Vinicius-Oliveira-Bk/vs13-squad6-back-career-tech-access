package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.enums.TipoEnum;
import br.com.dbc.vemser.repository.ContatoRepository;
import br.com.dbc.vemser.utils.ContatoServiceTestUtils;
import br.com.dbc.vemser.utils.UsuarioServiceTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ContatoService contatoService;

    private static Contato contatoDefaultSemUsuario;

    private static final Pageable paginacao = PageRequest.of(0, 10);
    private static Page<Contato> contatos;

    @BeforeAll
    public static void setUpAll() {
        Contato contato1 = ContatoServiceTestUtils.createContatoDefault();
        Contato contato2 = ContatoServiceTestUtils.createContatoDefault();
        Contato contato3 = ContatoServiceTestUtils.createContatoDefault();

        contato2.setId(2L);
        contato3.setId(3L);

        List<Contato> listaContatos = List.of(contato1, contato2, contato3);

        contatos = new PageImpl<>(listaContatos, paginacao, listaContatos.size());

        contatoDefaultSemUsuario = ContatoServiceTestUtils.createContatoDefault();
    }

    @Test
    @DisplayName("Deve criar contato com sucesso ao passar o usuário")
    void deveCriarContatoComSucessoAoPassarOUsuario() throws Exception {
        // Given
        ContatoRequestDTO contatoRequestDTO = ContatoServiceTestUtils.createContatoRequestDTO();
        Long idUsuario = 1L;

        // When
        when(objectMapper.convertValue(contatoRequestDTO, Contato.class)).thenReturn(contatoDefaultSemUsuario);
        when(usuarioService.getUsuario(idUsuario)).thenReturn(UsuarioServiceTestUtils.createUsuarioDefault());
        when(contatoRepository.save(contatoDefaultSemUsuario)).thenReturn(ContatoServiceTestUtils.createContatoComUsuario());
        when(objectMapper.convertValue(contatoDefaultSemUsuario, ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());

        // Then
        ContatoResponseDTO contatoResponseDTO = contatoService.create(idUsuario, contatoRequestDTO);

        assertEquals(contatoResponseDTO.getTelefone(), contatoRequestDTO.getTelefone());
    }

    @Test
    @DisplayName("Deve criar contato com sucesso ao não passar o usuário")
    void deveCriarContatoComSucessoAoNaoPassarOUsuario() throws Exception {
        // Given
        ContatoRequestDTO contatoRequestDTO = ContatoServiceTestUtils.createContatoRequestDTO();
        Long idUsuario = null;

        // When
        when(objectMapper.convertValue(contatoRequestDTO, Contato.class)).thenReturn(contatoDefaultSemUsuario);
        when(usuarioService.getUsuario(usuarioService.getIdLoggedUser())).thenReturn(UsuarioServiceTestUtils.createUsuarioDefault());
        when(contatoRepository.save(contatoDefaultSemUsuario)).thenReturn(ContatoServiceTestUtils.createContatoComUsuario());
        when(objectMapper.convertValue(contatoDefaultSemUsuario, ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());

        // Then
        ContatoResponseDTO contatoResponseDTO = contatoService.create(idUsuario, contatoRequestDTO);

        assertEquals(contatoResponseDTO.getTelefone(), contatoRequestDTO.getTelefone());
    }

    @Test
    @DisplayName("Deve pegar o contato corretamente se existir")
    void devePegarOContatoCorretamenteSeExistir() throws Exception {
        // Given
        Long id = 1L;

        // When
        when(contatoRepository.findById(id)).thenReturn(Optional.of(contatoDefaultSemUsuario));
        when(objectMapper.convertValue(contatoDefaultSemUsuario, ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());

        // Then
        ContatoResponseDTO contatoResponseDTO = contatoService.listById(id);

        assertEquals(contatoResponseDTO.getTelefone(), contatoDefaultSemUsuario.getTelefone());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar pegar contato inexistente")
    void deveLancarExcecaoAoTentarPegarContatoInexistente() {
        // Given
        Long id = 1L;

        // When
        when(contatoRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(Exception.class, () -> contatoService.listById(id));
    }

    @Test
    @DisplayName("Deve listar todos os contatos")
    void deveListarTodosOsContatos() throws Exception {
        // Given
        Long idContato = null;
        Long idUsuario = null;
        Integer status = null;
        TipoEnum tipoEnum = null;

        // When
        when(contatoRepository.findAll(paginacao, status, idContato, idUsuario)).thenReturn(contatos);
        when(objectMapper.convertValue(contatos.toList().get(0), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());
        when(objectMapper.convertValue(contatos.toList().get(1), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());
        when(objectMapper.convertValue(contatos.toList().get(2), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());

        // Then
        Page<ContatoResponseDTO> contatosResponseDTO = contatoService.listAll(paginacao, idContato, idUsuario, tipoEnum);

        assertEquals(contatosResponseDTO.getContent().size(), contatos.getContent().size());
    }

    @Test
    @DisplayName("Deve atualizar contato corretamente")
    void deveAtualizarContatoCorretamente() throws Exception {
        // Given
        Long id = 1L;
        ContatoRequestDTO contatoRequestDTO = ContatoServiceTestUtils.createContatoRequestDTO();

        // When
        when(contatoRepository.findById(id)).thenReturn(Optional.of(contatoDefaultSemUsuario));
        when(objectMapper.convertValue(contatoDefaultSemUsuario, ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());
        when(contatoRepository.save(contatoDefaultSemUsuario)).thenReturn(contatoDefaultSemUsuario);

        // Then
        ContatoResponseDTO contatoResponseDTO = contatoService.update(id, contatoRequestDTO);

        assertEquals(contatoResponseDTO.getTelefone(), contatoRequestDTO.getTelefone());
    }

    @Test
    @DisplayName("Deve deletar contato corretamente")
    void deveDeletarContatoCorretamente() throws Exception {
        // Given
        Long id = 1L;

        // When
        when(contatoRepository.findById(id)).thenReturn(Optional.of(contatoDefaultSemUsuario));

        // Then
        contatoService.delete(id);

        verify(contatoRepository, times(1)).delete(contatoDefaultSemUsuario);
    }

    @Test
    void deveListarTodosOsContatosDoUsuarioLogado() throws Exception {
        // Given
        TipoEnum tipoEnum = null;
        SecurityContextHolder.setContext(securityContext);

        // When
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("1");
        when(contatoRepository.findAllUsuarioLogado(paginacao, null, 1L)).thenReturn(contatos);
        when(objectMapper.convertValue(contatos.toList().get(0), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());
        when(objectMapper.convertValue(contatos.toList().get(1), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());
        when(objectMapper.convertValue(contatos.toList().get(2), ContatoResponseDTO.class)).thenReturn(ContatoServiceTestUtils.createContatoResponseDTO());

        // Then
        Page<ContatoResponseDTO> contatosResponseDTO = contatoService.listAllUsuario(paginacao, tipoEnum);

        assertEquals(contatosResponseDTO.getContent().size(), contatos.getContent().size());
    }


}