import entidades.Contato;
import entidades.Endereco;
import entidades.Pcd;
import enums.PlanoEnum;
import enums.TipoEnum;
import enums.TipoUsuarioEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.UsuarioServico;
import utils.CustomScanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();
        CustomScanner customScanner = new CustomScanner();


//        // Cadastrando alguns usuários
//        Pcd pcd1 = new Pcd("Maria Joana Texeira", "06497895622", "05-12-1990", null, null,
//                "mariatexeira@gmail.com", TipoUsuarioEnum.PCD, PlanoEnum.GRATUITO, "vazio", "vazio", true, true, "vazio", "vazio");

        ArrayList<Endereco> listaEndereco = new ArrayList<>();
        ArrayList<Contato> listaContato = new ArrayList<>();

        // Cadastrando PDC´s
        Pcd pcd1 = new Pcd("Maria Joana Texeira", "06497895622", null, null, null, "mariatexeira@gmail.com", TipoUsuarioEnum.PCD, PlanoEnum.GRATUITO, "vazio", "vazio", true, true, "Amputação da perna", "vazio");
        Pcd pcd2 = new Pcd("João Alves Neves", "06403502308", null, null, null, "joaoneves@gmail.com", TipoUsuarioEnum.PROFISSIONAL, PlanoEnum.PREMIUM, "vazio", "vazio", true, true, "Amputação da perna", "vazio");
        Pcd pcd3 = new Pcd("Carlos Maria Lima", "12345688", null, null, null, "carloslima@hotmail.com", TipoUsuarioEnum.ESTUDANTE, PlanoEnum.GRATUITO, "vazio", "vazio", true, true, "Amputação da perna", "vazio");
        Pcd pcd4 = new Pcd("Ana Carolina Almeida", "31565423176", null, null, null, "anaalmeida1@gmail.com", TipoUsuarioEnum.PROFISSIONAL, PlanoEnum.PREMIUM, "vazio", "vazio", true, true, "Amputação da perna", "vazio");
        Pcd pcd5 = new Pcd("Geraldo Nobrega Sousa", "20345489733", null, null, null, "geraldosousa@gmail.com", TipoUsuarioEnum.PROFISSIONAL, PlanoEnum.PREMIUM, "vazio", "vazio", true, true, "Amputação da perna", "vazio");

        usuarioServico.cadastrar(pcd1);
        usuarioServico.cadastrar(pcd2);
        usuarioServico.cadastrar(pcd3);
        usuarioServico.cadastrar(pcd4);
        usuarioServico.cadastrar(pcd5);

        Pcd pcd = new Pcd();

        System.out.println("\nDados do usuário:");
        System.out.print("Insira o nome do usuário: ");
        pcd.setNome(customScanner.nextLine());

        System.out.print("Insira o cpf: ");
        pcd.setCpf(customScanner.nextLine());

        System.out.print("Insira a data de nascimento: ");

        String dataNascimentoString = customScanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dataNascimentoString);

        if (matcher.matches()) {
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
            pcd.setDataDeNascimento(dataNascimento);
        } else {
            System.err.println("Formato inválido. Use o formato dd-MM-yyyy.");
        }

        // Cadastrando um endereço
        Endereco endereco = new Endereco();

        System.out.println("\nDados de endereço:");
        System.out.print("Insira o nome da rua: ");
        endereco.setLogradouro(customScanner.nextLine());

        System.out.print("Insira o número: ");
        endereco.setNumero(customScanner.nextLine());

        System.out.print("Insira um complemento: ");
        endereco.setComplemento(customScanner.nextLine());

        System.out.print("Insira o CEP: ");
        endereco.setCep(customScanner.nextLine());

        System.out.print("Insira a cidade: ");
        endereco.setCidade(customScanner.nextLine());

        System.out.print("Insira o estado: ");
        endereco.setEstado(customScanner.nextLine());

        System.out.print("Insira o país: ");
        endereco.setPais(customScanner.nextLine());

        System.out.print("Insira o tipo (1- Residencial; 2- Comercial): ");
        String opcao = customScanner.nextLine();

        if (opcao.equals("1")) {
            endereco.setTipo(TipoEnum.RESIDENCIAL);
        } else if (opcao.equals("2")){
            endereco.setTipo(TipoEnum.COMERCIAL);
        } else {
            System.err.println("Opção inválida!");
        }

        enderecoServico.cadastrar(endereco);

        // Cadastrando um contato
        Contato contato = new Contato();

        System.out.println("\nDados de telefone:");
        System.out.print("Insira uma descrição: ");
        contato.setDescricao(customScanner.nextLine());

        System.out.print("Insira um telefone: ");
        contato.setTelefone(customScanner.nextLine());

        System.out.print("Insira o tipo (1- Residencial; 2- Comercial): ");
        opcao = customScanner.nextLine();

        if (opcao.equals("1")) {
            contato.setTipo(TipoEnum.RESIDENCIAL);
        } else if (opcao.equals("2")){
            contato.setTipo(TipoEnum.COMERCIAL);
        } else {
            System.err.println("Opção inválida!");
        }

        System.out.println();
        contatoServico.cadastrar(contato);

        // Cadastrando um cliente pcd
        System.out.println("\nDados do PCD:");
        System.out.print("Insira um plano (1- Gratuito; 2- Básico; 3- Premium): ");
        pcd.setPlano(PlanoEnum.fromValor(customScanner.nextInt()));
        pcd.setInteresses("TI");

        System.out.print("Informe o link do documento: ");
        pcd.setImagemDocumento(customScanner.nextLine());

        System.out.print("Maior de 18 anos (1- Sim; 2- Não): ");
        opcao = customScanner.nextLine();
        if (opcao.equals("1")) {
            pcd.setControleParental(true);
        } else if (opcao.equals("2")) {
            pcd.setControleParental(false);
        } else {
            System.err.println("Opção inválida!");
        }

        System.out.print("Você é portador de deficiência (1- Sim; 2- Não): ");
        opcao = customScanner.nextLine();
        if (opcao.equals("1")) {
            pcd.setAcessoPcd(true);
        } else if (opcao.equals("2")) {
            pcd.setAcessoPcd(false);
        } else {
            System.err.println("Opção inválida!");
        }

        System.out.print("Possui certificado de deficiência (1- Sim; 2- Não): ");
        opcao = customScanner.nextLine();
        if (opcao.equals("1")) {
            pcd.setAcessoPcd(true);
        } else if (opcao.equals("2")) {
            pcd.setAcessoPcd(false);
        } else {
            System.err.println("Opção inválida!");
        }

        System.out.print("Informe o tipo de deficiência: ");
        pcd.setTipoDeficiencia(customScanner.nextLine());

        System.out.println();
        usuarioServico.cadastrar(pcd);
        System.out.println();

        usuarioServico.listarTodos();

//        listaEndereco.add(customScanner.nextLine());
//
//        System.out.print("Insira o contato: ");
//        listaContato.add();
//
//        System.out.print("Insira o email: ");
//        pcd.setEmail(customScanner.nextLine());
//
//
//
//

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