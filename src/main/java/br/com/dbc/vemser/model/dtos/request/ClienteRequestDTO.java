package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteRequestDTO {
    @NotNull
    private PlanoEnum tipoPlano;
    @NotNull
    private Character controleParental;
}
