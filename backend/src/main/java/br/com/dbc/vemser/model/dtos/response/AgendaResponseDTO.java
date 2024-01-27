package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponseDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private ProfissionalMentorResponseDTO profissionalMentor;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendaEnum statusAgendaEnum;
}
