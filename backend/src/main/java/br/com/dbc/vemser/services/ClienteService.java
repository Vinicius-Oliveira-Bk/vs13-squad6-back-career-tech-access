package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;


    public ClienteResponseDTO create(ClienteRequestDTO clienteRequestDTO, Long idUsuario) throws Exception {
        List<Cliente> clientesEntity= clienteRepository.getAll();

        boolean clienteExistente = clientesEntity.stream()
                .anyMatch(cliente -> cliente.getIdUsuario().equals(idUsuario));

        if (clienteExistente) {
            throw new RegraDeNegocioException("Já existe um cliente com o mesmo id_usuario.");
        }

        Cliente cliente = objectMapper.convertValue(clienteRequestDTO, Cliente.class);
        clienteRepository.create(cliente, idUsuario);
        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(cliente, ClienteResponseDTO.class);
        clienteResponseDTO.setIdUsuario(idUsuario);
        return clienteResponseDTO;
    }

    public List<ClienteResponseDTO> listAll() throws BancoDeDadosException {
        List<Cliente> clientesEntity= clienteRepository.getAll();
        List<ClienteResponseDTO> clientesResponseDTO = clientesEntity.stream()
                .map(clienteEntity -> objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class))
                .collect(Collectors.toList());
        return clientesResponseDTO;
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception {
        Cliente buscaCliente = getCliente(id);
        Cliente clienteEntity = objectMapper.convertValue(clienteRequestDTO, Cliente.class);
        clienteRepository.update(id, clienteEntity);
        clienteEntity.setId(id);
        clienteEntity.setIdUsuario(buscaCliente.getIdUsuario());

        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class);
        return clienteResponseDTO;
    }

    public void delete(Long id) throws Exception {
        Cliente buscaCliente = getCliente(id);
        clienteRepository.delete(id);
    }

    public ClienteResponseDTO listById(Long id) throws Exception {
        Cliente clienteEntity = getCliente(id);
        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class);
        return clienteResponseDTO;
    }

    private Cliente getCliente(Long id) throws RegraDeNegocioException {
        try {
            Cliente clienteRecuperado = clienteRepository.getById(id);
            return clienteRecuperado;
        } catch (Exception ex) {
            throw new RegraDeNegocioException("Nenhum cliente encontrado para o Id: " + id);
        }
    }

}

