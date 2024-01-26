package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character acessoPcd;
    private TipoUsuarioEnum tipoUsuario;
    private String interesses;
    private List<Contato> contatos;
    private List<Endereco> enderecos;
    private String imagemDocumento;
}