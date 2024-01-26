package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseCompletoDTO {
    private Long id;
    private UsuarioResponseCompletoDTO usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;
}
