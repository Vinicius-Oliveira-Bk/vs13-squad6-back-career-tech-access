import entidades.*;

import enums.*;

import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.UsuarioServico;
import utils.CustomScanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        CustomScanner customScanner = new CustomScanner();

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();

        ArrayList<Endereco> enderecosList = new ArrayList<>();
        ArrayList<Contato> contatosList = new ArrayList<>();

        ProfissionalMentor profissionalMentor = new ProfissionalMentor();

        System.out.println("\nDados do usuário:");
        System.out.print("Insira o nome do usuário: ");
        profissionalMentor.setNome(customScanner.nextLine());

        System.out.print("Insira o cpf: ");
        profissionalMentor.setCpf(customScanner.nextLine());

        System.out.print("Insira a data de nascimento: ");

        String dataNascimentoString = customScanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dataNascimentoString);

        if (matcher.matches()) {
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
            profissionalMentor.setDataDeNascimento(dataNascimento);
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

        // Cadastrando um profissional mentor
        System.out.println("Informe sua área de atuação (1 - TI, 2 - SAUDE, 3 - EDUCACAO, 4 - FINANCAS, 5 - MARKETING, 6 - JURIDICO, 7 - ENGENHARIA, 8 - DESIGN, 9 - COMERCIO, 10 - MEIO_AMBIENTE, 11 - CONSULTORIA, 12 - RH, 13 - OUTROS: ");
        int areaAtuacao = customScanner.nextInt();
        profissionalMentor.setAreaAtuacao(AreaAtuacaoEnum.fromValor(areaAtuacao));

        System.out.println("Informe seu nível de experiência (1 - JUNIOR, 2 - PLENO, 3 - SENIOR: ");
        int nivelExperiencia = customScanner.nextInt();
        profissionalMentor.setNivelExperienciaEnum(NivelExperienciaEnum.fromValor(nivelExperiencia));

        System.out.println("Informe o nº da sua carteira de trabalho: ");
        profissionalMentor.setCarteiraDeTrabalho(customScanner.nextLine());

        System.out.println("Quantos certificados você vai informar? ");
        int quantidadeCertificados = customScanner.nextInt();

        ArrayList<String> certificadosList = new ArrayList<>();

        for (int i = 0; i < quantidadeCertificados; i++) {
            System.out.println("Informe o link do seu certificado: ");
            String linkCertificado = customScanner.nextLine();
            certificadosList.add(linkCertificado);
        }

        profissionalMentor.setCertificadosDeCapacitacao(certificadosList);

        System.out.println();
        usuarioServico.cadastrar(profissionalMentor);
        System.out.println();

        usuarioServico.listarTodos();

//        // Vinculando o contato com o usuário
//        usuarioServico.vincularContato(pcd1, contato1);
//        usuarioServico.vincularContato(pcd1, contato2);
//
    }
}