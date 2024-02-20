package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.dtos.response.NotificacaoDTO;
import br.com.dbc.vemser.repository.NotificacaoLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacaoLogService {

    private final NotificacaoLogRepository notificacaoLogRepository;
    private final ObjectMapper objectMapper;

    public List<NotificacaoDTO> findAllByDiaHora(LocalDateTime diaHora) {
        return notificacaoLogRepository.findAllByDiaHora(diaHora)
                .stream()
                .map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class))
                .collect(Collectors.toList());
    }

    public List<NotificacaoDTO> findAll() {
        return notificacaoLogRepository.findAll()
                .stream()
                .map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class))
                .collect(Collectors.toList());
    }

    public List<NotificacaoDTO> findByDiaHoraBetween(String dataInicio, String dataFim) {
        return notificacaoLogRepository.findByDiaHoraBetween(LocalDateTime.parse(dataInicio), LocalDateTime.parse(dataInicio))
                .stream()
                .map(notificacao -> objectMapper.convertValue(notificacao, NotificacaoDTO.class))
                .collect(Collectors.toList());
    }

}
