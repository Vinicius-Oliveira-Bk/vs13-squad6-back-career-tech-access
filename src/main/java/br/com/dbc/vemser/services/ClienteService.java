package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.ClienteMapper;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private final String RESOURCE_NOT_FOUND = "Não foi possível encontrar clientes com este filtro.";

    public ClienteResponseDTO create(ClienteRequestDTO clienteRequestDTO, Long idUsuario) throws Exception {
        List<Cliente> clientesEntity= clienteRepository.findAll();

        Usuario usuario = usuarioService.getUsuario(idUsuario);

        boolean clienteExistente = clientesEntity.stream()
                .anyMatch(cliente -> cliente.getUsuario().getId().equals(idUsuario));

        if (clienteExistente) {
            throw new RegraDeNegocioException("Já existe um cliente com o mesmo id_usuario.");
        }

        Cliente cliente = ClienteMapper.clienteRequestDTOtoEntity(clienteRequestDTO);

        cliente.setUsuario(usuario);
        Cliente criado = clienteRepository.save(cliente);

        criado.setInteresses(listAreaDeInteresseToAreaInteresse(clienteRequestDTO.getInteresses(), cliente));
        clienteRepository.save(criado);

        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(cliente, ClienteResponseDTO.class);
        emailService.sendEmail(clienteResponseDTO.getUsuario(), clienteResponseDTO.getUsuario().getEmail(), EmailTemplate.CRIAR_USUARIO);

        return clienteResponseDTO;
    }

    public Page<ClienteResponseDTO> listAll(Pageable pageable) throws BancoDeDadosException {
        Page<Cliente> clientesEntity= clienteRepository.findAll(pageable);
        Page<ClienteResponseDTO> clientesResponseDTO = clientesEntity.map(clienteEntity -> objectMapper.convertValue(clienteEntity, ClienteResponseDTO.class));
        return clientesResponseDTO;
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO clienteRequestDTO) throws Exception {
        Cliente buscaCliente = getCliente(id);

        buscaCliente.setTipoPlano(clienteRequestDTO.getTipoPlano());
        buscaCliente.setControleParental(clienteRequestDTO.getControleParental());
        buscaCliente.setEhEstudante(clienteRequestDTO.getEhEstudante());
        buscaCliente.setEhProfissionalRealocacao(clienteRequestDTO.getEhProfissionalRealocacao());
        buscaCliente.setProfissao(clienteRequestDTO.getProfissao());
        buscaCliente.setObjetivoProfissional(clienteRequestDTO.getObjetivoProfissional());
        buscaCliente.setMatricula(clienteRequestDTO.getMatricula());
        buscaCliente.setComprovanteMatricula(clienteRequestDTO.getComprovanteMatricula());
        buscaCliente.setInstituicao(clienteRequestDTO.getInstituicao());
        buscaCliente.setCurso(clienteRequestDTO.getCurso());
        buscaCliente.setInteresses(listAreaDeInteresseToAreaInteresse(clienteRequestDTO.getInteresses(), buscaCliente));
        buscaCliente.setDataInicio(clienteRequestDTO.getDataInicio());
        buscaCliente.setDataTermino(clienteRequestDTO.getDataTermino());
        clienteRepository.save(buscaCliente);

        ClienteResponseDTO clienteResponseDTO = objectMapper.convertValue(buscaCliente, ClienteResponseDTO.class);
        return clienteResponseDTO;
    }

    public void delete(Long id) throws Exception {
        Cliente cliente = getCliente(id);
        clienteRepository.delete(cliente);
    }

    public ClienteResponseCompletoDTO listById(Long id) throws Exception {
        return objectMapper.convertValue(getCliente(id), ClienteResponseCompletoDTO.class);
    }

    public Cliente getCliente(Long id) throws RegraDeNegocioException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
    }


    private List<AreaInteresse> listAreaDeInteresseToAreaInteresse(List<AreasDeInteresse> areaInteresses, Cliente cliente) {
        return areaInteresses.stream()
                .map(areasDeInteresse -> {
                    AreaInteresse areaInteresse = new AreaInteresse();

                    areaInteresse.setCliente(cliente);
                    areaInteresse.setInteresse(areasDeInteresse);

                    return areaInteresse;
                })
                .collect(Collectors.toList());
    }
}

