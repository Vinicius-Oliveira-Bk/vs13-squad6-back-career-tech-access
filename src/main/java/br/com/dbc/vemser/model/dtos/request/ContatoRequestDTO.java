package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
