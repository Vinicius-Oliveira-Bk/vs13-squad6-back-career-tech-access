package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.EnderecoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.EnderecoResponseDTO;
import br.com.dbc.vemser.model.entities.Endereco;
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


    public EnderecoResponseDTO create(EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        Endereco endereco = objectMapper.convertValue(enderecoRequestDTO, Endereco.class);
        enderecoRepository.create(endereco);
        EnderecoResponseDTO enderecoResponseDTO = objectMapper.convertValue(endereco, EnderecoResponseDTO.class);
        return enderecoResponseDTO;
    }

    public List<EnderecoResponseDTO> listAll() throws BancoDeDadosException {
        List<Endereco> enderecosEntity= enderecoRepository.getAll();
        List<EnderecoResponseDTO> enderecosResponseDTO = enderecosEntity.stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoResponseDTO.class))
                .collect(Collectors.toList());
        return enderecosResponseDTO;
    }

    public EnderecoResponseDTO update(Long id, EnderecoRequestDTO enderecoRequestDTO) throws Exception {
        Endereco buscaEndereco = getEndereco(id);

        Endereco enderecoEntity = objectMapper.convertValue(enderecoRequestDTO, Endereco.class);
        enderecoRepository.update(id, enderecoEntity);
        enderecoEntity.setId(id);

        EnderecoResponseDTO enderecoResponseDTO = objectMapper.convertValue(enderecoEntity, EnderecoResponseDTO.class);
        return enderecoResponseDTO;
    }

    public void delete(Long id) throws Exception {
        Endereco buscaEndereco = getEndereco(id);
        enderecoRepository.delete(id);
    }

    public EnderecoResponseDTO listById(Long id) throws Exception {
        Endereco enderecoEntity = getEndereco(id);
        EnderecoResponseDTO enderecoResponseDTO = objectMapper.convertValue(enderecoEntity, EnderecoResponseDTO.class);
        return enderecoResponseDTO;
    }

    private Endereco getEndereco(Long id) throws RegraDeNegocioException {
        try {
            Endereco enderecoRecuperado = enderecoRepository.getById(id);
            return enderecoRecuperado;
        } catch (Exception ex) {
            throw new RegraDeNegocioException("Nenhum endereço encontrado para o Id: " + id);
        }
    }

}