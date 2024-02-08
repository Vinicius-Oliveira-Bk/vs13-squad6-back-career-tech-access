package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.enums.CargoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    @Schema(description = "Informe o nome", required = true, example = "Gabriel Silva")
    private String nome;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve ser uma data passada")
    @Schema(description = "Data de Nascimento", required = true, example = "1990-01-30")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos numéricos")
    @Schema(description = "Informe o cpf sem pontuações", required = true, example = "32165498712")
    private String cpf;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido. Formato: email@provedor")
    @Schema(description = "Informe o e-mail", required = true, example = "seuemail@dominio.com")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @NotEmpty(message = "A senha não pode estar em branco")
    @Size(min = 8)
    @Schema(description = "Informe sua senha", required = true, example = "senha123")
    private String senha;

    @NotNull(message = "Acesso PCD inválido! O campo deve ser: 'S' ou 'N'")
    @Schema(description = "Informe se é uma pessoa com deficiência (s/n)", required = true, example = "S")
    private Character ehPcd;

    @Schema(description = "Tipo de deficiência que possuim", example = "Paralização das pernas")
    private String tipoDeficiencia;

    @Schema(description = "Comprovante de deficiência", example = "http://comprovante.com")
    private String certificadoDeficienciaGov;

    @Schema(description = "Link da imagem do documento", example = "http://imagemDocumento.com")
    private String imagemDocumento;

    @Schema(description = "Cargos do usuário", example = "[\"ROLE_ADMIN\", \"ROLE_USUARIO\"]")
    private Set<CargoEnum> cargos;
}
