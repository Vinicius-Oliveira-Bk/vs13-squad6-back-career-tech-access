package br.com.dbc.vemser.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AgendaRequestDTO {
    @NotNull(message = "A data de início deve ser informada.")
    @Future(message = "A data de início não pode ser menor ou igual sa data atual.")
    private LocalDateTime dataHoraInicio;
    @NotNull(message = "A data de fim deve ser informada.")
    @Future(message = "A data de fim não pode ser menor ou igual sa data atual.")
    private LocalDateTime dataHoraFim;
}
