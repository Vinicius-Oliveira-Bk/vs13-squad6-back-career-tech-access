package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.dtos.request.PcdRequestDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import lombok.Data;

@Data
public class PcdResponseDTO extends PcdRequestDTO {
    private Long id;
    private ClienteResponseDTO cliente;
}
