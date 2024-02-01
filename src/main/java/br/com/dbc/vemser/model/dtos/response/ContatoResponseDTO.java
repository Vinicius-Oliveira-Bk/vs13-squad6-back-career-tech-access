package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.Data;

@Data
public class ContatoResponseDTO {
    private Long id;
    private String descricao;
    private String telefone;
    private TipoEnum tipo;
}
