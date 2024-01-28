package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.EstudanteRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ClienteResponseDTO;
import br.com.dbc.vemser.model.dtos.response.EstudanteResponseDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Estudante;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.EstudanteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EstudanteService {

    private final EstudanteRepository estudanteRepository;
    private final ClienteService clienteService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public EstudanteResponseDTO create(EstudanteRequestDTO estudanteRequestDTO, Long idCliente) throws Exception {
        Cliente cliente = clienteService.getCliente(idCliente);

        Estudante estudanteEntity = objectMapper.convertValue(estudanteRequestDTO, Estudante.class);
        estudanteEntity.setCliente(cliente);
        estudanteRepository.create(estudanteEntity);
        EstudanteResponseDTO estudanteResponseDTO = objectMapper.convertValue(estudanteEntity, EstudanteResponseDTO.class);
        emailService.sendEmail(estudanteResponseDTO.getCliente().getUsuario(), estudanteResponseDTO.getCliente().getUsuario().getEmail(), EmailTemplate.CRIAR_USUARIO);
        return estudanteResponseDTO;
    }

    public List<EstudanteResponseDTO> listAll() throws BancoDeDadosException {
        List<Estudante> estudanteEntity= estudanteRepository.getAll();
        List<EstudanteResponseDTO> estudanteResponseDTO = estudanteEntity.stream()
                .map(estudante -> objectMapper.convertValue(estudante, EstudanteResponseDTO.class))
                .collect(Collectors.toList());
        return estudanteResponseDTO;
    }

    public EstudanteResponseDTO update(Long idEstudante, EstudanteRequestDTO estudanteRequestDTO) throws Exception {
        Estudante buscaEstudante = getEstudante(idEstudante);

        Estudante estudanteEntity = objectMapper.convertValue(estudanteRequestDTO, Estudante.class);
        estudanteRepository.update(idEstudante, estudanteEntity);
        estudanteEntity.setId(idEstudante);

        EstudanteResponseDTO estudanteResponseDTO = objectMapper.convertValue(estudanteEntity, EstudanteResponseDTO.class);
        return estudanteResponseDTO;
    }

    public void delete(Long idEstudante) throws Exception {
        Estudante estudante = getEstudante(idEstudante);
        estudanteRepository.delete(idEstudante);
    }

    public EstudanteResponseDTO listById(Long idEstudante) throws Exception {
        Estudante estudanteEntity = getEstudante(idEstudante);
        EstudanteResponseDTO estudanteResponseDTO = objectMapper.convertValue(estudanteEntity, EstudanteResponseDTO.class);
        return estudanteResponseDTO;
    }

    private Estudante getEstudante(Long idEstudante) throws Exception {
        Estudante estudanteRecuperado = estudanteRepository.getAll().stream()
                .filter(estudante -> estudante.getId().equals(idEstudante))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("O Estudante de ID " + idEstudante + " não foi encontrado!"));
        return estudanteRecuperado;
    }

}
