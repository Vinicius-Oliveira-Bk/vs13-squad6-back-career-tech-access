package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Agenda {
    private Long id;
    private Cliente cliente;
    private ProfissionalMentor profissionalMentor;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendaEnum statusAgendaEnum;
}
