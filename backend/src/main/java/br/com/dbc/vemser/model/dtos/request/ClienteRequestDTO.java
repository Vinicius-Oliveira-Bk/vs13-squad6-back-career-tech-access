package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {
    private Long id;
    private Long idUsuario;
    @NotNull
    private PlanoEnum tipoPlano;
    @NotNull
    private Character controleParental;
}
