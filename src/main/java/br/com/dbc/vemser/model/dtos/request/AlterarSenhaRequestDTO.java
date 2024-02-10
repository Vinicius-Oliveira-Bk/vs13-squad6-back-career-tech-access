package br.com.dbc.vemser.model.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AlterarSenhaRequestDTO {

    @NotNull(message = "A senha não pode ser nula")
    @NotEmpty(message = "A senha não pode estar em branco")
    @Size(min = 8)
    @Schema(description = "Informe a nova senha", required = true, example = "senha1234")
    private String senha;

    @NotNull(message = "A senha não pode ser nula")
    @NotEmpty(message = "A senha não pode estar em branco")
    @Size(min = 8)
    @Schema(description = "Confirme a nova senha", required = true, example = "senha1234")
    private String senhaConfirmacao;
}
