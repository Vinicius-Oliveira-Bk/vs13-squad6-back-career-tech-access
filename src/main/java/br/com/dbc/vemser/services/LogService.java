package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.dtos.response.LogDTO;
import br.com.dbc.vemser.model.entities.Log;
import br.com.dbc.vemser.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;

    public List<LogDTO> findAllByStatusCode(String statusCode) {
        return logRepository.findAllByStatusCode(statusCode).stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
    }

}
