package br.com.dbc.vemser.services;


import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ContatoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ContatoResponseDTO;
import br.com.dbc.vemser.model.entities.Contato;
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


    public ContatoResponseDTO create(ContatoRequestDTO contatoRequestDTO) throws Exception {
        Contato contato = objectMapper.convertValue(contatoRequestDTO, Contato.class);
        contatoRepository.create(contato);
        ContatoResponseDTO contatoResponseDTO = objectMapper.convertValue(contato, ContatoResponseDTO.class);
        return contatoResponseDTO;
    }

    public List<ContatoResponseDTO> listAll() throws BancoDeDadosException {
        List<Contato> contatosEntity= contatoRepository.getAll();
        List<ContatoResponseDTO> contatosResponseDTO = contatosEntity.stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoResponseDTO.class))
                .collect(Collectors.toList());
        return contatosResponseDTO;
    }

    public ContatoResponseDTO update(Long id, ContatoRequestDTO contatoRequestDTO) throws Exception {
        Contato buscaContato = getContato(id);

        Contato contatoEntity = objectMapper.convertValue(contatoRequestDTO, Contato.class);
        contatoRepository.update(id, contatoEntity);
        contatoEntity.setId(id);

        ContatoResponseDTO contatoResponseDTO = objectMapper.convertValue(contatoEntity, ContatoResponseDTO.class);
        return contatoResponseDTO;
    }

    public void delete(Long id) throws Exception {
        Contato buscaContato = getContato(id);
        contatoRepository.delete(id);
    }

    public ContatoResponseDTO listById(Long id) throws Exception {
        Contato contatoEntity = getContato(id);
        ContatoResponseDTO contatoResponseDTO = objectMapper.convertValue(contatoEntity, ContatoResponseDTO.class);
        return contatoResponseDTO;
    }

    private Contato getContato(Long id) throws RegraDeNegocioException {
        try {
            Contato contatoRecuperado = contatoRepository.getById(id);
            return contatoRecuperado;
        } catch (Exception ex) {
            throw new RegraDeNegocioException("Nenhum contato encontrado para o Id: " + id);
        }
    }

}