package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final String RESOURCE_NOT_FOUND = "Não foi possível encontrar usuário com este filtro.";

    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario usuarioEntity = objectMapper.convertValue(usuarioRequestDTO, Usuario.class);
        usuarioRepository.save(usuarioEntity);
        UsuarioResponseDTO usuarioResponseDTO = objectMapper.convertValue(usuarioEntity, UsuarioResponseDTO.class);
//        emailService.sendEmail(usuarioResponseDTO, usuarioResponseDTO.getEmail(), EmailTemplate.CRIAR_USUARIO);
        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> listAll() throws BancoDeDadosException {
        List<Usuario> usuarioEntity= usuarioRepository.findAll();

        return usuarioEntity.stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario buscaUsuario = getUsuario(id);
        buscaUsuario.setNome(usuarioRequestDTO.getNome());
        buscaUsuario.setDataNascimento(usuarioRequestDTO.getDataNascimento());
        buscaUsuario.setCpf(usuarioRequestDTO.getCpf());
        buscaUsuario.setEmail(usuarioRequestDTO.getEmail());
        buscaUsuario.setSenha(usuarioRequestDTO.getSenha());
        buscaUsuario.setEhPcd(usuarioRequestDTO.getEhPcd());
        buscaUsuario.setTipoDeficiencia(usuarioRequestDTO.getTipoDeficiencia());
        buscaUsuario.setCertificadoDeficienciaGov(usuarioRequestDTO.getCertificadoDeficienciaGov());
        buscaUsuario.setImagemDocumento(usuarioRequestDTO.getImagemDocumento());
        usuarioRepository.save(buscaUsuario);
        return objectMapper.convertValue(buscaUsuario, UsuarioResponseDTO.class);
    }

    public void delete(Long id) throws Exception {
        Usuario usuario = getUsuario(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseCompletoDTO listById(Long id) throws Exception {
        return objectMapper.convertValue(getUsuario(id), UsuarioResponseCompletoDTO.class);
    }

    public Usuario getUsuario(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
    }

}
