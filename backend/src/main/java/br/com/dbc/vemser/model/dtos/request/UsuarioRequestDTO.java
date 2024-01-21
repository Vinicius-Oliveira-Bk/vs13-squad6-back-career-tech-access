package br.com.dbc.vemser.model.dtos.request;

import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character acessoPcd;
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
