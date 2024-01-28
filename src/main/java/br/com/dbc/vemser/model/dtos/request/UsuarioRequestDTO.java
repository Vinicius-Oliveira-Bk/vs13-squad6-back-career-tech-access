package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioRequestDTO {

    private Long id;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    private String nome;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento deve ser uma data passada")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou conter apenas espaços em branco")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "O email não pode ser nulo")
    @Email(message = "Email inválido. Formato: email@provedor")
    private String email;

    @NotNull(message = "Acesso PCD inválido! O campo deve ser: 'S' ou 'N'")
    private Character acessoPcd;


    @NotNull(message = "Tipo de usuário inválido. Escolha: MENTOR, ESTUDANTE, PCD OU PROFISSIONAL_RELOCACAO ")
    private TipoUsuarioEnum tipoUsuario;

    private String interesses;
    private String imagemDocumento;
}
