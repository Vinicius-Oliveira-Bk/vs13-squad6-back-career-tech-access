package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoRequestDTO {
    private Long id;
    @NotBlank
    private String descricao;
    @NotNull
    @Size(max = 13)
    private String telefone;
    @NotNull
    private TipoEnum tipo;
}
