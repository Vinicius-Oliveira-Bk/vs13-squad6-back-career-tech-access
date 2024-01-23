package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private UsuarioResponseDTO usuario;
    private PlanoEnum tipoPlano;
    private Character controleParental;
}
