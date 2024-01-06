import entidades.Contato;
import servicos.ContatoServico;
import entidades.Usuario;
import servicos.UsuarioServico;

public class Main {
    public static void main(String[] args) {
        
        Contato c = new Contato();
        Contato c1 = new Contato();
        Contato c2 = new Contato();
        Contato c3 = new Contato();
        Contato c4 = new Contato();

        ContatoServico cs = new ContatoServico();
        cs.cadastrar(c);
        cs.cadastrar(c1);
        cs.cadastrar(c2);
        cs.cadastrar(c3);
        cs.cadastrar(c4);

        System.out.println(cs.listarTodos());
        try {
            System.out.println(cs.listarUm(4));
            System.out.println(cs.listarUm(5));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
      
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
