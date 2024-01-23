package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.request.PcdRequestDTO;
import br.com.dbc.vemser.model.dtos.response.PcdResponseDTO;
import br.com.dbc.vemser.model.entities.Pcd;
import br.com.dbc.vemser.repository.PcdRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PcdService {

    private final PcdRepository pcdRepository;
    private final ObjectMapper objectMapper;

    public PcdResponseDTO create(PcdRequestDTO pcdRequestDTO) throws Exception {
        Pcd pcdEntity = objectMapper.convertValue(pcdRequestDTO, Pcd.class);
        pcdRepository.create(pcdEntity);
        PcdResponseDTO pcdResponseDTO = objectMapper.convertValue(pcdEntity, PcdResponseDTO.class);
        return pcdResponseDTO;
    }

    public List<PcdResponseDTO> listAll() throws BancoDeDadosException {
        List<Pcd> pcdEntity= pcdRepository.getAll();
        List<PcdResponseDTO> pcdResponseDTO = pcdEntity.stream()
                .map(pcd -> objectMapper.convertValue(pcd, PcdResponseDTO.class))
                .collect(Collectors.toList());
        return pcdResponseDTO;
    }

    public PcdResponseDTO update(Long idPcd, PcdRequestDTO pcdRequestDTO) throws Exception {
        Pcd buscaPcd = getPcd(idPcd);

        Pcd pcdEntity = objectMapper.convertValue(pcdRequestDTO, Pcd.class);
        pcdRepository.update(idPcd, pcdEntity);
        pcdEntity.setId(idPcd);

        PcdResponseDTO pcdResponseDTO = objectMapper.convertValue(pcdEntity, PcdResponseDTO.class);
        return pcdResponseDTO;
    }

    public void delete(Long idPcd) throws Exception {
        Pcd pcd = getPcd(idPcd);
        pcdRepository.delete(idPcd);
    }

    public PcdResponseDTO listById(Long idPcd) throws Exception {
        Pcd pcdEntity = getPcd(idPcd);
        PcdResponseDTO pcdResponseDTO = objectMapper.convertValue(pcdEntity, PcdResponseDTO.class);
        return pcdResponseDTO;
    }

    private Pcd getPcd(Long idPcd) throws Exception {
        Pcd pcdRecuperado = pcdRepository.getAll().stream()
                .filter(pcd -> pcd.getId().equals(idPcd))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("O PCD de ID " + idPcd + " não foi encontrado!"));
        return pcdRecuperado;
    }

}
