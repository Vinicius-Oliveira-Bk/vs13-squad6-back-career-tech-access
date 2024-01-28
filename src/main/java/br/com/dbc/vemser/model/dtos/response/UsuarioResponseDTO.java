package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.dtos.request.UsuarioRequestDTO;
import lombok.Data;

@Data
public class UsuarioResponseDTO extends UsuarioRequestDTO {
    private Long id;
}
