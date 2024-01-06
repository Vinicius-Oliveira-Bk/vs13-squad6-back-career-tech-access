import entidades.Contato;
import servicos.ContatoServico;
import entidades.Usuario;
import servicos.UsuarioServico;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();

        // Cadastrando alguns usuários
        Usuario usuario1 = new Usuario(1, "Maria Joana Texeira", "06497895622", "05-12-1990", null, null,  "mariatexeira@gmail.com", null);
        Usuario usuario2 = new Usuario(2, "Carlos Pinto da Silva", "23569548966", "31-02-2000",  null, null, "carlossilva@gmail.com", null);

        usuarioServico.cadastrar(usuario1);

        // Adicionando alguns contatos no usuário
        Contato contato1 = new Contato("teste1", "99999999999", null);
        Contato contato2 = new Contato("teste2", "22222222222", null);

        // Vinculando o contato com o usuário
        usuarioServico.vincularContato(usuario1, contato1);
        usuarioServico.vincularContato(usuario1, contato2);

        usuarioServico.listarTodos();



    }
  
}
