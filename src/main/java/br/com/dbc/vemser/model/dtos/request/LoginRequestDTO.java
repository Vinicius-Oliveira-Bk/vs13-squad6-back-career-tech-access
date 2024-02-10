package br.com.dbc.vemser.model.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {

    @NotNull
    @Schema(description = "Login do usuário", required = true, example = "admin@dominio.com")
    private String email;

    @NotNull
    @Schema(description = "Senha do usuário", required = true, example = "senha123")
    private String senha;
}
