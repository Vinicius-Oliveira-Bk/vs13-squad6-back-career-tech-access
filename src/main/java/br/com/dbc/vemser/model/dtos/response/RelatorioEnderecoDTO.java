package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.enums.TipoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioEnderecoDTO {

    @Schema(description = "Código do endereço", example = "1")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço", example = "Complemento")
    private String complemento;

    @Schema(description = "Bairro do endereço", example = "Bairro")
    private String cep;

    @Schema(description = "Cidade do endereço", example = "Cidade")
    private String cidade;

    @Schema(description = "Estado do endereço", example = "Estado")
    private String estado;

    @Schema(description = "País do endereço", example = "País")
    private String pais;

    @Schema(description = "Tipo do endereço", example = "RESIDENCIAL")
    private TipoEnum tipoEndereco;

}
