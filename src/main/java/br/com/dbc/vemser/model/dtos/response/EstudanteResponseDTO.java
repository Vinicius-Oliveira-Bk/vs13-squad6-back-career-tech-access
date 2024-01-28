package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.dtos.request.EstudanteRequestDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import lombok.Data;

@Data
public class EstudanteResponseDTO extends EstudanteRequestDTO {
    private Long id;
    private ClienteResponseDTO cliente;
}
