package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.mappers.ClienteMapper;
import br.com.dbc.vemser.model.dtos.request.ClienteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseCompletoDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.entities.AreaInteresse;
import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreasDeInteresse;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.CargoRepository;
import br.com.dbc.vemser.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CargoService cargoService;
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private final String RESOURCE_NOT_FOUND = "Não foi possível encontrar clientes com este filtro.";
    private final CargoRepository cargoRepository;

    public ClienteResponseDTO create(ClienteRequestDTO clienteRequestDTO, @Nullable Long idUsuario) throws Exception {
        Usuario usuario;
        if (idUsuario != null) {
            usuario = usuarioService.getUsuario(idUsuario);
        } else {
            usuario = usuarioService.getUsuario(usuarioService.getIdLoggedUser());
        }
        List<Cliente> clientesEntity= clienteRepository.findAll();

        boolean clienteExistente = clientesEntity.stream()
                .anyMatch(cliente -> cliente.getUsuario().getId().equals(idUsuario));

        if (clienteExistente) {
            throw new RegraDeNegocioException("Já existe um cliente com o mesmo id_usuario.");
        }

        Cliente cliente = ClienteMapper.clienteRequestDTOtoEntity(clienteRequestDTO);


        usuario.getCargos().add(cargoService.getCargo("ROLE_CLIENTE"));

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

    public void delete(Long id) throws RegraDeNegocioException {
        Cliente cliente = getCliente(id);
        if (cliente.getAgendas() != null && !cliente.getAgendas().isEmpty()) {
            throw new RegraDeNegocioException("Há agendas cadastradas com este cliente, não é possível deletá-lo.");
        }
        clienteRepository.delete(cliente);
        Set<Cargo> cargos = cliente.getUsuario().getCargos();
        cargos.remove(cargoService.getCargo("ROLE_CLIENTE"));
        usuarioService.atualizarRole(cliente.getUsuario(), cargos);
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

    public Cliente getByUsuario(Long idUsuario) throws RegraDeNegocioException {
        return clienteRepository.findByUsuario_Id(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException(RESOURCE_NOT_FOUND));
    }

    public String ativarInativarCliente(@Nullable Long idCliente) throws Exception {
        Cliente cliente;
        String message;
        Usuario usuario;
        Set<Cargo> cargos;
        if (idCliente != null) {
            cliente = getCliente(idCliente);
            usuario = cliente.getUsuario();
            cargos = usuario.getCargos();
            cliente.setAtivo(!cliente.isAtivo());
            if (!cliente.isAtivo()) {
                message = "Cliente inativado com sucesso.";
                cargos.remove(cargoService.getCargo("ROLE_CLIENTE"));
            } else {
                message = "Cliente ativado com sucesso.";
                cargos.add(cargoService.getCargo("ROLE_CLIENTE"));
            }
        } else {
            cliente = getByUsuario(usuarioService.getIdLoggedUser());
            usuario = cliente.getUsuario();
            cargos = usuario.getCargos();
            cliente.setAtivo(!cliente.isAtivo());
            if (!cliente.isAtivo()) {
                message = "Cliente inativado com sucesso.";
                cargos.remove(cargoService.getCargo("ROLE_CLIENTE"));
            } else {
                message = "Cliente ativado com sucesso.";
                cargos.add(cargoService.getCargo("ROLE_CLIENTE"));
            }
        }
        usuarioService.atualizarRole(usuario, cargos);
        clienteRepository.save(cliente);
        return message;
    }
}

