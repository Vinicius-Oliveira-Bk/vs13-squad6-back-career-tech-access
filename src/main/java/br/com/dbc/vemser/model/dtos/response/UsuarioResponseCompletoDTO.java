package br.com.dbc.vemser.model.dtos.response;

import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioResponseCompletoDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character acessoPcd;
    private TipoUsuarioEnum tipoUsuario;
    private String interesses;
    private String imagemDocumento;
    private List<Contato> contatos;
    private List<Endereco> enderecos;
}
