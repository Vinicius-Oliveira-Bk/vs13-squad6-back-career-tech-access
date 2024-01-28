package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.ProfissionalRealocacaoRequestDTO;
import br.com.dbc.vemser.model.dtos.response.ProfissionalRealocacaoResponseDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalRealocacao;
import br.com.dbc.vemser.model.enums.EmailTemplate;
import br.com.dbc.vemser.repository.ProfissionalRealocacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalRealocacaoService {

    private final ProfissionalRealocacaoRepository profissionalRealocacaoRepository;
    private final ClienteService clienteService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public ProfissionalRealocacaoResponseDTO create(ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO, Long idCliente) throws Exception {
        Cliente cliente = clienteService.getCliente(idCliente);

        ProfissionalRealocacao profissionalRealocacaoEntity = objectMapper.convertValue(profissionalRealocacaoRequestDTO, ProfissionalRealocacao.class);
        profissionalRealocacaoEntity.setCliente(cliente);
        profissionalRealocacaoRepository.create(profissionalRealocacaoEntity);
        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        emailService.sendEmail(profissionalRealocacaoResponseDTO.getCliente().getUsuario(), profissionalRealocacaoResponseDTO.getCliente().getUsuario().getEmail(), EmailTemplate.CRIAR_USUARIO);
        return profissionalRealocacaoResponseDTO;
    }

    public List<ProfissionalRealocacaoResponseDTO> listAll() throws BancoDeDadosException {
        List<ProfissionalRealocacao> profissionalRealocacaoEntity= profissionalRealocacaoRepository.getAll();
        List<ProfissionalRealocacaoResponseDTO> profissionalRealocacaoResponseDTO = profissionalRealocacaoEntity.stream()
                .map(profissionalRealocacao -> objectMapper.convertValue(profissionalRealocacao, ProfissionalRealocacaoResponseDTO.class))
                .collect(Collectors.toList());
        return profissionalRealocacaoResponseDTO;
    }

    public ProfissionalRealocacaoResponseDTO update(Long idProfissionalRealocacao, ProfissionalRealocacaoRequestDTO profissionalRealocacaoRequestDTO) throws Exception {
        ProfissionalRealocacao buscaProfissionalRealocacao = getProfissionalRealocacao(idProfissionalRealocacao);

        ProfissionalRealocacao profissionalRealocacaoEntity = objectMapper.convertValue(profissionalRealocacaoRequestDTO, ProfissionalRealocacao.class);
        profissionalRealocacaoRepository.update(idProfissionalRealocacao, profissionalRealocacaoEntity);
        profissionalRealocacaoEntity.setId(idProfissionalRealocacao);
        profissionalRealocacaoEntity.setUsuario(buscaProfissionalRealocacao.getUsuario());
        //profissionalRealocacaoEntity.setCliente();

        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        return profissionalRealocacaoResponseDTO;
    }

    public void delete(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacao = getProfissionalRealocacao(idProfissionalRealocacao);
        profissionalRealocacaoRepository.delete(idProfissionalRealocacao);
    }

    public ProfissionalRealocacaoResponseDTO listById(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacaoEntity = getProfissionalRealocacao(idProfissionalRealocacao);
        ProfissionalRealocacaoResponseDTO profissionalRealocacaoResponseDTO = objectMapper.convertValue(profissionalRealocacaoEntity, ProfissionalRealocacaoResponseDTO.class);
        return profissionalRealocacaoResponseDTO;

    }

    public ProfissionalRealocacao getProfissionalRealocacao(Long idProfissionalRealocacao) throws Exception {
        ProfissionalRealocacao profissionalRealocacaoRecuperado = profissionalRealocacaoRepository.getAll().stream()
                .filter(profissionalRealocacao -> profissionalRealocacao.getId().equals(idProfissionalRealocacao))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("O profissional realocacao de ID " + idProfissionalRealocacao + " não foi encontrado!"));
        return profissionalRealocacaoRecuperado;
    }
}