package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RelatorioAgendaDTO {

    @Schema(description = "Código da agenda", example = "1")
    private Long codigoAgenda;

    @Schema(description = "Data de início do agendamento", example = "2021-10-10T10:00:00")
    private LocalDateTime dataInicioAgendamento;

    @Schema(description = "Data de fim do agendamento", example = "2021-10-10T11:00:00")
    private LocalDateTime dataFimAgendamento;

    @Schema(description = "Status da agenda", example = "AGENDADO")
    private StatusAgendaEnum statusAgenda;

    @Schema(description = "Cliente da agenda")
    private RelatorioAgendaClienteDTO cliente;

    @Schema(description = "Mentor da agenda")
    private RelatorioAgendaMentorDTO mentor;

}
