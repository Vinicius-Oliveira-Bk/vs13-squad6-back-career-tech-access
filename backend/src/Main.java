import entidades.Contato;
import entidades.Endereco;
import enums.TipoEnum;
import servicos.ContatoServico;
import entidades.Usuario;
import servicos.EnderecoServico;
import servicos.UsuarioServico;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {

        // Testes de Contatos e Usuário
//        UsuarioServico usuarioServico = new UsuarioServico();
//        ContatoServico contatoServico = new ContatoServico();
//
//        // Cadastrando alguns usuários
//        Usuario usuario1 = new Usuario(1, "Maria Joana Texeira", "06497895622", "05-12-1990", null, null,  "mariatexeira@gmail.com", null);
//        Usuario usuario2 = new Usuario(2, "Carlos Pinto da Silva", "23569548966", "31-02-2000",  null, null, "carlossilva@gmail.com", null);
//
//        usuarioServico.cadastrar(usuario1);
//
//        // Adicionando alguns contatos no usuário
//        Contato contato1 = new Contato("teste1", "99999999999", null);
//        Contato contato2 = new Contato("teste2", "22222222222", null);
//
//        // Vinculando o contato com o usuário
//        usuarioServico.vincularContato(usuario1, contato1);
//        usuarioServico.vincularContato(usuario1, contato2);
//
//        usuarioServico.listarTodos();

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
