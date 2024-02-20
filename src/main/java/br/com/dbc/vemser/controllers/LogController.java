package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.model.dtos.response.LogDTO;
import br.com.dbc.vemser.services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @GetMapping("/by-statuscode")
    public List<LogDTO> findAllByStatusCode(String statusCode) {
        return logService.findAllByStatusCode(statusCode);
    }

    @GetMapping
    public List<LogDTO> findAll() {
        return logService.findAll();
    }

    @GetMapping("/by-date")
    public List<LogDTO> findByDataBetween(String dataInicio, String dataFim) {
        return logService.findByDataBetween(dataInicio, dataFim);
    }

}
