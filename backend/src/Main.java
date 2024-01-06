import entidades.Usuario;
import servicos.UsuarioServico;

public class Main {
    public static void main(String[] args) {

        Usuario usuario1 = new Usuario(1, "Maria Joana Texeira", "06497895622", "05-12-1990",  null, null, "mariatexeira@gmail.com", null);
        Usuario usuario2 = new Usuario(2, "Carlos Pinto da Silva", "23569548966", "31-02-2000",  null, null, "carlossilva@gmail.com", null);

        UsuarioServico contatoServico = new UsuarioServico();
        contatoServico.cadastrar(usuario1);
        contatoServico.cadastrar(usuario2);

        contatoServico.listarTodos();
//        contatoServico.listarUm(3);
//        contatoServico.atualizar(1, usuario1);
//
//        contatoServico.listarTodos();
//        contatoServico.deletar(1);
//        contatoServico.listarTodos();

    }
}
