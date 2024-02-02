package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendaResponseDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private ProfissionalMentorResponseDTO profissionalMentor;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendaEnum statusAgendaEnum;
}
