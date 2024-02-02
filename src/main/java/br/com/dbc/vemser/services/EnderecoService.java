package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final String RESOURCE_NOT_FOUND = "Não foi encontrado nenhum endereço com este filtro.";

    public EnderecoResponseDTO create(Long idUsuario, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        Endereco endereco = objectMapper.convertValue(enderecoRequestDTO, Endereco.class);
        Usuario usuario = usuarioService.getUsuario(idUsuario);

        usuario.getEnderecos().add(endereco);
        endereco.getUsuarios().add(usuario);
        enderecoRepository.save(endereco);
        EnderecoResponseDTO enderecoResponseDTO = objectMapper.convertValue(endereco, EnderecoResponseDTO.class);
        return enderecoResponseDTO;
    }

    public List<EnderecoResponseDTO> listAll() throws BancoDeDadosException {
        List<Endereco> enderecosEntity= enderecoRepository.findAll();
        return enderecosEntity.stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoResponseDTO update(Long id, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        Endereco buscaEndereco = getEndereco(id);
        buscaEndereco.setCep(enderecoRequestDTO.getCep());
        buscaEndereco.setCidade(enderecoRequestDTO.getCidade());
        buscaEndereco.setEstado(enderecoRequestDTO.getEstado());
        buscaEndereco.setPais(enderecoRequestDTO.getPais());
        buscaEndereco.setNumero(enderecoRequestDTO.getNumero());
        buscaEndereco.setComplemento(enderecoRequestDTO.getComplemento());
        buscaEndereco.setTipo(enderecoRequestDTO.getTipo());
        buscaEndereco.setLogradouro(enderecoRequestDTO.getLogradouro());
        enderecoRepository.save(buscaEndereco);

        return objectMapper.convertValue(buscaEndereco, EnderecoResponseDTO.class);

    }

    public void delete(Long id) throws Exception {
        Endereco buscaEndereco = getEndereco(id);
        buscaEndereco.getUsuarios()
                .forEach(usuario -> usuarioService.removerEndereco(usuario, buscaEndereco));
        buscaEndereco.setUsuarios(null);
        enderecoRepository.delete(buscaEndereco);
    }

    public EnderecoResponseDTO listById(Long id) throws Exception {
        return objectMapper.convertValue(getEndereco(id), EnderecoResponseDTO.class);
    }

    public List<Endereco> getEnderecosByUser(Long idUsuario) throws BancoDeDadosException {
        return enderecoRepository.findByUsuarios_Id(idUsuario);
    }

    private Endereco getEndereco(Long id) throws RegraDeNegocioException {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
    }
}