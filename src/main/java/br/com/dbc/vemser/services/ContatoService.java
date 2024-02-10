package br.com.dbc.vemser.services;


import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.TipoEnum;
import br.com.dbc.vemser.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Usuario usuario;
        if (idUsuario != null) {
            usuario = usuarioService.getUsuario(idUsuario);
        } else {
            usuario = usuarioService.getUsuario(usuarioService.getIdLoggedUser());
        }
        contato.setUsuario(usuario);
            contatoRepository.save(contato);
        ContatoResponseDTO contatoResponseDTO = objectMapper.convertValue(contato, ContatoResponseDTO.class);
        return contatoResponseDTO;
    }

    public Page<ContatoResponseDTO> listAll(Pageable pageable,
                                            @Nullable Long idContato,
                                            @Nullable Long idUsuario,
                                            @Nullable TipoEnum tipoEnum) throws BancoDeDadosException {
        Integer status = tipoEnum != null ? tipoEnum.ordinal() : null;
        Page<Contato> contatos= contatoRepository.findAll(pageable, status, idContato, idUsuario);
        return contatos.map(contato -> objectMapper.convertValue(contato, ContatoResponseDTO.class));
    }

    public Page<ContatoResponseDTO> listAllUsuario(Pageable pageable,
                                                   @Nullable TipoEnum tipoEnum) throws BancoDeDadosException {

        Integer status = tipoEnum != null ? tipoEnum.ordinal() : null;
        Long idUsuarioLogado = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Page<Contato> contatos = contatoRepository.findAllUsuarioLogado(pageable, status, idUsuarioLogado);

        return contatos.map(contato -> objectMapper.convertValue(contato, ContatoResponseDTO.class));
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
}