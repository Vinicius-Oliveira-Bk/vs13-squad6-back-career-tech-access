package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.TipoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioContatoDTO {

    @Schema(description = "Código do contato", example = "1")
    private String descricao;

    @Schema(description = "Telefone do contato", example = "51999999999")
    private String telefone;

    @Schema(description = "Tipo do contato", example = "COMERCIAL")
    private TipoEnum tipoContato;

}
