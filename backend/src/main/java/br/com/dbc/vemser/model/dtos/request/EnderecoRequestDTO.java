package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {
    private Long id;
    @NotEmpty
    @Size(max = 250)
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    @Size(max = 8)
    private String cep;
    @NotBlank
    @Size(max = 250)
    private String cidade;
    @NotBlank
    private String estado;
    @NotBlank
    private String pais;
    @NotNull
    private TipoEnum tipo;
}
