package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private UsuarioResponseDTO usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;
}
