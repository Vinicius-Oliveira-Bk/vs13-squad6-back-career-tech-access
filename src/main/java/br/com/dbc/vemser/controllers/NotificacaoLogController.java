package br.com.dbc.vemser.controllers;

import br.com.dbc.vemser.model.dtos.response.NotificacaoDTO;
import br.com.dbc.vemser.services.NotificacaoLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificacao-log")
public class NotificacaoLogController {

    private final NotificacaoLogService notificacaoLogService;

    @GetMapping("/by-dia-hora")
    public List<NotificacaoDTO> findAllByDiaHora(LocalDateTime diaHora) {
        return notificacaoLogService.findAllByDiaHora(diaHora);
    }

    @GetMapping
    public List<NotificacaoDTO> findAll() {
        return notificacaoLogService.findAll();
    }

    @GetMapping("/by-date")
    public List<NotificacaoDTO> findByDiaHoraBetween(String dataInicio, String dataFim) {
        return notificacaoLogService.findByDiaHoraBetween(dataInicio, dataFim);
    }
}
