package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.dtos.request.PcdRequestDTO;
import br.com.dbc.vemser.model.dtos.request.ProfissionalRealocacaoRequestDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import lombok.Data;

@Data
public class ProfissionalRealocacaoResponseDTO extends ProfissionalRealocacaoRequestDTO {
    private Long id;
    private ClienteResponseDTO cliente;
}
