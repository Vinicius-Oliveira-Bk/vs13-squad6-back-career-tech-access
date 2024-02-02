package br.com.dbc.vemser.services;


import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final String RESOURCE_NOT_FOUND = "Não foi encontrado nenhum contato com este filtro.";

    public ContatoResponseDTO create(Long idUsuario, ContatoRequestDTO contatoRequestDTO) throws Exception {
        Contato contato = objectMapper.convertValue(contatoRequestDTO, Contato.class);
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        contato.setUsuario(usuario);
            contatoRepository.save(contato);
        ContatoResponseDTO contatoResponseDTO = objectMapper.convertValue(contato, ContatoResponseDTO.class);
        return contatoResponseDTO;
    }

    public List<ContatoResponseDTO> listAll() throws BancoDeDadosException {
        List<Contato> contatosEntity= contatoRepository.findAll();
        return contatosEntity.stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ContatoResponseDTO update(Long id, ContatoRequestDTO contatoRequestDTO) throws Exception {
        Contato buscaContato = getContato(id);
        buscaContato.setTipo(contatoRequestDTO.getTipo());
        buscaContato.setTelefone(contatoRequestDTO.getTelefone());
        buscaContato.setDescricao(contatoRequestDTO.getDescricao());
        contatoRepository.save(buscaContato);

        return objectMapper.convertValue(buscaContato, ContatoResponseDTO.class);
    }

    public void delete(Long id) throws Exception {
        Contato buscaContato = getContato(id);
        contatoRepository.delete(buscaContato);
    }

    public ContatoResponseDTO listById(Long id) throws Exception {
        return objectMapper.convertValue(getContato(id), ContatoResponseDTO.class);
    }

    private Contato getContato(Long id) throws RegraDeNegocioException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
    }

    public List<Contato> getContatosByUser(Long idUsuario) throws BancoDeDadosException {
        return contatoRepository.findByUsuario_Id(idUsuario);
    }
}