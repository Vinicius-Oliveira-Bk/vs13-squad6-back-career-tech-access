package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.TipoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoRequestDTO {
    @NotBlank
    @Schema(description = "Informe a descrição do seu contato", example = "Contato da casa da rua X")
    private String descricao;
    @NotNull
    @Size(max = 13)
    @Schema(description = "Informe o número do telefone DDD9NUMERO sem espaços", example = "12977776666")
    private String telefone;
    @NotNull
    @Schema(description = "Informe o tipo do seu contato RESIDENCIAL ou COMERCIAL", example = "COMERCIAL")
    private TipoEnum tipo;
}
