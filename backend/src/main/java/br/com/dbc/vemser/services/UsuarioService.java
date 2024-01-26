package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario usuarioEntity = objectMapper.convertValue(usuarioRequestDTO, Usuario.class);
        usuarioRepository.create(usuarioEntity);
        UsuarioResponseDTO usuarioResponseDTO = objectMapper.convertValue(usuarioEntity, UsuarioResponseDTO.class);
        return usuarioResponseDTO;
    }

    public List<UsuarioResponseDTO> listAll() throws BancoDeDadosException {
        List<Usuario> usuarioEntity= usuarioRepository.getAll();
        List<UsuarioResponseDTO> usuarioResponseDTO = usuarioEntity.stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
        return usuarioResponseDTO;
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario buscaUsuario = getUsuario(id);

        Usuario usuarioEntity = objectMapper.convertValue(usuarioRequestDTO, Usuario.class);
        usuarioRepository.update(id, usuarioEntity);
        usuarioEntity.setId(id);
        return objectMapper.convertValue(usuarioEntity, UsuarioResponseDTO.class);
    }

    public void delete(Long id) throws Exception {
        getUsuario(id);
        usuarioRepository.delete(id);
    }

    public UsuarioResponseCompletoDTO listById(Long id) throws Exception {
        Usuario usuarioEntity = getUsuario(id);
        return objectMapper.convertValue(usuarioEntity, UsuarioResponseCompletoDTO.class);
    }

    public Usuario getUsuario(Long id) throws Exception {
        Usuario usuarioRecuperado = usuarioRepository.getById(id);
        if (ObjectUtils.isEmpty(usuarioRecuperado)) {
            throw new RegraDeNegocioException("O Usuário de ID " + id + " não foi encontrado!");
        }
        return usuarioRecuperado;
    }

}
