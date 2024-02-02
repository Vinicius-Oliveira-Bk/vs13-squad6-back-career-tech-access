package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.TipoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EnderecoRequestDTO {
    @NotEmpty
    @Size(max = 250)
    @Schema(description = "Informe o logradouro do endereço", example = "Rua do Norte")
    private String logradouro;
    @NotBlank
    @Schema(description = "Informe o numero do endereço", example = "95")
    private String numero;
    @Schema(description = "Caso haja, informe o complemento do endereço", example = "Apartamento 06")
    private String complemento;
    @NotBlank
    @Size(max = 8)
    @Schema(description = "Informe com apenas números o cep do endereço", example = "66666222")
    private String cep;
    @NotBlank
    @Size(max = 250)
    @Schema(description = "Informe a cidade do endereço", example = "Presidente Bernardes")
    private String cidade;
    @NotBlank
    @Schema(description = "Informe o estado do endereço", example = "São Paulo")
    private String estado;
    @NotBlank
    @Schema(description = "Informe o país do endereço", example = "Brasil")
    private String pais;
    @NotNull
    @Schema(description = "Informe o tipo do seu endereço RESIDENCIAL ou COMERCIAL", example = "COMERCIAL")
    private TipoEnum tipo;
}
