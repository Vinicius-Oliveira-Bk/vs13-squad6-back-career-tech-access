package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.interfaces.IVinculosUsuario;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements IVinculosUsuario {

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
    private ArrayList<Contato> contatos;
    private ArrayList<Endereco> enderecos;
    private String imagemDocumento;

    public boolean vincularContato(Usuario usuario, Contato contato) {
        ArrayList<Contato> contatos = usuario.getContatos() != null ? usuario.getContatos() : new ArrayList<>();
        contatos.add(contato);
        usuario.setContatos(contatos);
        System.out.println("✅ Contato vinculado!");
        return true;
    }

    public boolean vincularEndereco(Usuario usuario, Endereco endereco) {
        ArrayList<Endereco> enderecos = usuario.getEnderecos() != null ? usuario.getEnderecos() : new ArrayList<>();
        enderecos.add(endereco);
        usuario.setEnderecos(enderecos);
        System.out.println("✅ Endereço vinculado!");
        return true;
    }

}