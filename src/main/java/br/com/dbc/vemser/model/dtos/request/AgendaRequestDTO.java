package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaRequestDTO {
    @NotNull(message = "A data de início deve ser informada.")
    @Future(message = "A data de início não pode ser menor ou igual sa data atual.")
    private LocalDateTime dataHoraInicio;
    @NotNull(message = "A data de fim deve ser informada.")
    @Future(message = "A data de fim não pode ser menor ou igual sa data atual.")
    private LocalDateTime dataHoraFim;
}
