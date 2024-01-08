import entidades.Contato;
import enums.TipoEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.UsuarioServico;

public class Main {
    public static void main(String[] args) {
        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();

//        // Cadastrando alguns usuários
//        Pcd pcd1 = new Pcd("Maria Joana Texeira", "06497895622", "05-12-1990", null, null,
//                "mariatexeira@gmail.com", TipoUsuarioEnum.PCD, PlanoEnum.GRATUITO, "vazio", "vazio", true, true, "vazio", "vazio");
//
//        usuarioServico.cadastrar(pcd1);
//
//        // Adicionando alguns contatos no usuário
//        Contato contato1 = new Contato("teste1", "99999999999", null);
//        Contato contato2 = new Contato("teste2", "22222222222", null);
//
//        // Vinculando o contato com o usuário
//        usuarioServico.vincularContato(pcd1, contato1);
//        usuarioServico.vincularContato(pcd1, contato2);
//
//        usuarioServico.listarTodos();
//
//        // Cria o endereço - Testes Vinicius
//        Endereco endereco = new Endereco("Rua das Flores", "123", "Casa", "12345-6789", "Atibaia", "SP", "Brasil",
//                TipoEnum.RESIDENCIAL);
//
//        // Cadastra o endereço
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