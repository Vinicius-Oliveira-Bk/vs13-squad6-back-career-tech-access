package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    private Long id;
    private String descricao;
    private String telefone;
    private TipoEnum tipo;

}
