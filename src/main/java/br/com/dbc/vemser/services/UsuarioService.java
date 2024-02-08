package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseDTO;
import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final CargoService cargoService;
    private final String RESOURCE_NOT_FOUND = "Não foi possível encontrar usuário com este filtro.";

    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) throws RegraDeNegocioException {
        Usuario usuario = objectMapper.convertValue(usuarioRequestDTO, Usuario.class);
        usuarioExistenteCreate(usuarioRequestDTO);

        Set<Cargo> cargos = usuarioRequestDTO.getCargos()
                .stream()
                .map(cargo -> cargoService.getCargo(cargo.name()))
                .collect(Collectors.toSet());

        usuario.setCargos(cargos);
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
        UsuarioResponseDTO usuarioResponseDTO = objectMapper.convertValue(usuario, UsuarioResponseDTO.class);
        emailService.sendEmail(usuarioResponseDTO, usuarioResponseDTO.getEmail(), EmailTemplate.CRIAR_USUARIO);
        return usuarioResponseDTO;
    }


    public Page<UsuarioResponseDTO> listAll(Pageable pageable) throws BancoDeDadosException {
        Page<Usuario> usuarioEntity= usuarioRepository.findAll(pageable);
        Page<UsuarioResponseDTO> usuarios = usuarioEntity.map(usuario -> objectMapper.convertValue(usuario, UsuarioResponseDTO.class));
        return usuarios;
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        Usuario buscaUsuario = getUsuario(id);
        usuarioExistenteUpdate(buscaUsuario, usuarioRequestDTO);
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

    public void removerEndereco(Usuario usuario, Endereco endereco) {
        usuario.getEnderecos().remove(endereco);
        usuarioRepository.save(usuario);
    }

    public boolean usuarioExistenteCreate(UsuarioRequestDTO newUser) throws RegraDeNegocioException {
        if (usuarioRepository.findByCpf(newUser.getCpf()) != null) {
            throw new RegraDeNegocioException("Já existe um usuário com este CPF.");
        }
        if (usuarioRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um usuário com este Email.");
        }
        return true;
    }

    public boolean usuarioExistenteUpdate(Usuario oldUser, UsuarioRequestDTO newUser) throws RegraDeNegocioException {
        if (!oldUser.getCpf().equals(newUser.getCpf())) {
            if (usuarioRepository.findByCpf(newUser.getCpf()) != null) {
                throw new RegraDeNegocioException("Já existe um usuário com este CPF.");
            }
        }
        if (!oldUser.getEmail().equals(newUser.getEmail())) {
            if (usuarioRepository.findByEmail(newUser.getEmail()) != null) {
                throw new RegraDeNegocioException("Já existe um usuário com este Email.");
            }
        }
        return true;
    }

    public Set<UsuarioResponseDTO> relatorioUsuario(Long idUsuario) {
        if (idUsuario == null) {
            idUsuario = -1L;
        }

        return usuarioRepository.relatorioUsuario(idUsuario).stream().map(usuario -> objectMapper.convertValue(usuario, UsuarioResponseDTO.class)).collect(Collectors.toSet());
    }

    public Optional<Usuario> findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public UsuarioResponseDTO getLoggedUser() {
        return objectMapper.convertValue(findById(getIdLoggedUser()), UsuarioResponseDTO.class);
    }

    public Long getIdLoggedUser() {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

    }

    public Usuario findByEmail(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(RESOURCE_NOT_FOUND));
    }

}
