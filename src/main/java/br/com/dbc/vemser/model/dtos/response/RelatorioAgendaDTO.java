package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioAgendaDTO {

    @Schema(description = "Código da agenda", example = "1")
    private Long id;

    @Schema(description = "Data de início do agendamento", example = "2021-10-10T10:00:00")
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Data de fim do agendamento", example = "2021-10-10T11:00:00")
    private LocalDateTime dataHoraFim;

    @Schema(description = "Status da agenda", example = "AGENDADO")
    private StatusAgendaEnum statusAgendaEnum;
}
