import entidades.Contato;
import entidades.Endereco;
import enums.TipoEnum;
import servicos.ContatoServico;
import entidades.Usuario;
import servicos.EnderecoServico;
import servicos.UsuarioServico;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {

        // Cria o endereço - Testes Thales
        
//        Contato c = new Contato();
//        Contato c1 = new Contato();
//        Contato c2 = new Contato();
//        Contato c3 = new Contato();
//        Contato c4 = new Contato();
//
//        ContatoServico cs = new ContatoServico();
//        cs.cadastrar(c);
//        cs.cadastrar(c1);
//        cs.cadastrar(c2);
//        cs.cadastrar(c3);
//        cs.cadastrar(c4);
//
//        System.out.println(cs.listarTodos());
//        try {
//            System.out.println(cs.listarUm(4));
//            System.out.println(cs.listarUm(5));
//        } catch (NoSuchElementException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Usuario usuario1 = new Usuario(1, "Maria Joana Texeira", "06497895622", "05-12-1990",  null, null, "mariatexeira@gmail.com", null);
//        Usuario usuario2 = new Usuario(2, "Carlos Pinto da Silva", "23569548966", "31-02-2000",  null, null, "carlossilva@gmail.com", null);
//
//        UsuarioServico contatoServico = new UsuarioServico();
//        contatoServico.cadastrar(usuario1);
//        contatoServico.cadastrar(usuario2);
//
//        contatoServico.listarTodos();

        // Cria o endereço - Testes Vinicius

//        Endereco endereco = new Endereco();
//        endereco.setId(1L);
//        endereco.setLogradouro("Rua das Flores");
//        endereco.setNumero("123");
//        endereco.setCep("12345-6789");
//        endereco.setCidade("Atibaia");
//        endereco.setEstado("SP");
//        endereco.setPais("Brasil");
//        endereco.setTipo(TipoEnum.RESIDENCIAL);
//
//        // Cadastra o endereço
//        EnderecoServico enderecoServico = new EnderecoServico();
//        enderecoServico.cadastrar(endereco);
//
//        // Lista o endereço
//        Endereco enderecoListado = enderecoServico.listarUm(endereco.getId());
//        System.out.println("Endereço listado:");
//        System.out.println("Logradouro: " + enderecoListado.getLogradouro());
//        System.out.println("Número: " + enderecoListado.getNumero());
//
//        // Atualiza o endereço
//        enderecoListado.setLogradouro("Rua Nova");
//        enderecoServico.atualizar(enderecoListado.getId(), enderecoListado);
//
//        // Lista novamente o endereço
//        enderecoListado = enderecoServico.listarUm(endereco.getId());
//        System.out.println("Endereço listado após a atualização:");
//        System.out.println("Logradouro: " + enderecoListado.getLogradouro());
    }
  
}
