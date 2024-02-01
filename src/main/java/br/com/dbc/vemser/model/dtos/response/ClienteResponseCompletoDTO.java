package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.Data;

@Data
public class ClienteResponseCompletoDTO {
    private Long id;
    private UsuarioResponseCompletoDTO usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;
}
