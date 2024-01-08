package utils;

import java.time.LocalDate;
import java.util.Scanner;

import entidades.ProfissionalRealocacao;
import enums.PlanoEnum;
import enums.TipoUsuarioEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.ProfissionalRealocacaoServico;
import servicos.UsuarioServico;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();
        ProfissionalRealocacaoServico profissionalRealocacaoServico = new ProfissionalRealocacaoServico();

        int opcao, idUsuario;

        do {
            Utils.limparConsole();
            Utils.telaInicial();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    Utils.limparConsole();
                    System.out.println("\n👋 Até mais!\n");
                    break;
                case 1:
                    Utils.limparConsole();
                    Utils.selecionarTipoCliente();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0:
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1: 
                            System.out.println("\nListar todos os tipos de clientes");
                            usuarioServico.listarTodos();
                            sc.nextLine();
                            break;
                        case 2:
                            Utils.limparConsole();
                            Utils.exibirEntidadeManipulada("Estudante");
                            Utils.exibirMenuOperacoes();

                            opcao = scanner.nextInt();
                            scanner.nextInt();

                            switch (opcao) {
                                case 0:
                                    System.out.println("\n👋 Até mais!\n");
                                    break;
                                case 1:
                                    System.out.println("\nCadastrar");

                                    // Exemplo de uso do CustomScanner
                                    String nome = scanner.nextLine("Digite o nome do estudante");
                                    System.out.println(nome);

                                    break;
                                case 2:
                                    System.out.println("\nListar um");
                                    break;
                                case 3:
                                    System.out.println("\nListar todos");
                                    break;
                                case 4:
                                    System.out.println("\nAtualizar");
                                    break;
                                case 5:
                                    System.out.println("\nDeletar");
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.err.println("🚫 Opção inválida!");
                                    break;
                            }
                            break;
                        case 3:
                            Utils.limparConsole();
                            Utils.exibirEntidadeManipulada("PCD (Pessoa com Deficiência)");
                            Utils.exibirMenuOperacoes();
                            opcao = scanner.nextInt();

                            switch (opcao) {
                                case 0:
                                    System.out.println("\n👋 Até mais!\n");
                                    break;
                                case 1:
                                    System.out.println("\nCadastrar");
                                    break;
                                case 2:
                                    System.out.println("\nListar um");
                                    break;
                                case 3:
                                    System.out.println("\nListar todos");
                                    break;
                                case 4:
                                    System.out.println("\nAtualizar");
                                    break;
                                case 5:
                                    System.out.println("\nDeletar");
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.err.println("🚫 Opção inválida!");
                                    break;
                            }
                            break;
                        case 4:
                            Utils.limparConsole();
                            Utils.exibirEntidadeManipulada("Profissional em realocação");
                            Utils.exibirMenuOperacoes();

                            opcao = scanner.nextInt();

                            switch (opcao) {
                                case 0:
                                    System.out.println("\n👋 Até mais!\n");
                                    break;
                                case 1:
                                    System.out.println("\nCadastrar");
                                    ProfissionalRealocacao profissionalRealocacao = new ProfissionalRealocacao();
                                    profissionalRealocacao.setNome(scanner.nextLine("Informe seu nome"));
                                    profissionalRealocacao.setCpf(scanner.nextLine("Informe seu Cpf"));
                                    profissionalRealocacao.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                                    profissionalRealocacao.setEnderecos(null);
                                    profissionalRealocacao.setContatos(null);
                                    profissionalRealocacao.setEmail(scanner.nextLine("Informe seu email"));
                                    profissionalRealocacao.setTipo(TipoUsuarioEnum.fromValor(2));//chumbado
                                    System.out.println("1 - Gratuito");
                                    System.out.println("2 - Básico");
                                    System.out.println("3 - Premium");
                                    profissionalRealocacao.setPlano(PlanoEnum.fromValor(scanner.nextInt("Informe o número correspondente ao seu plano")));
                                    profissionalRealocacao.setInteresses(scanner.nextLine("Informe seus interesses"));
                                    profissionalRealocacao.setImagemDocumento(scanner.nextLine("Informe o link da imagem do seu documento"));
                                    profissionalRealocacao.setControleParental(false);
                                    profissionalRealocacao.setAcessoPcd(false);
                                    profissionalRealocacao.setProfissao(scanner.nextLine("Informe sua profissão"));
                                    profissionalRealocacao.setObjetivoProfissional(scanner.nextLine("Informe seu objetivo profissional"));
                                    profissionalRealocacaoServico.cadastrar(profissionalRealocacao);
                                    break;
                                case 2:
                                    System.out.println("\nListar um");
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja visualizar");
                                    profissionalRealocacaoServico.listarUm(idUsuario);
                                    break;
                                case 3:
                                    System.out.println("\nListar todos");
                                    profissionalRealocacaoServico.listarTodos();
                                    break;
                                case 4:
                                    System.out.println("\nAtualizar");
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja atualizar");
                                    profissionalRealocacaoServico.listarUm(idUsuario);
                                    ProfissionalRealocacao profissionalRealocacaoAtualizar = new ProfissionalRealocacao();

                                    profissionalRealocacaoAtualizar.setNome(scanner.nextLine("Informe seu nome"));
                                    profissionalRealocacaoAtualizar.setCpf(scanner.nextLine("Informe seu Cpf"));
                                    profissionalRealocacaoAtualizar.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                                    profissionalRealocacaoAtualizar.setEnderecos(null);
                                    profissionalRealocacaoAtualizar.setContatos(null);
                                    profissionalRealocacaoAtualizar.setEmail(scanner.nextLine("Informe seu email"));
                                    profissionalRealocacaoAtualizar.setTipo(TipoUsuarioEnum.fromValor(2)); //chumbado
                                    System.out.println("1 - Gratuito");
                                    System.out.println("2 - Básico");
                                    System.out.println("3 - Premium");
                                    profissionalRealocacaoAtualizar.setPlano(PlanoEnum.fromValor(scanner.nextInt("Informe o número correspondente ao seu plano")));
                                    profissionalRealocacaoAtualizar.setInteresses(scanner.nextLine("Informe seus interesses"));
                                    profissionalRealocacaoAtualizar.setImagemDocumento(scanner.nextLine("Informe o link da imagem do seu documento"));
                                    profissionalRealocacaoAtualizar.setControleParental(false);
                                    profissionalRealocacaoAtualizar.setAcessoPcd(false);
                                    profissionalRealocacaoAtualizar.setProfissao(scanner.nextLine("Informe sua profissão"));
                                    profissionalRealocacaoAtualizar.setObjetivoProfissional(scanner.nextLine("Informe seu objetivo profissional"));
                                    profissionalRealocacaoServico.atualizar(idUsuario, profissionalRealocacaoAtualizar);
                                    break;
                                case 5:
                                    System.out.println("\nDeletar");
                                    int idUsuarioDeletar = scanner.nextInt("Informe o id do profissional em realocação que deseja deletar");
                                    profissionalRealocacaoServico.deletar(idUsuarioDeletar);
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.err.println("🚫 Opção inválida!");
                                    break;
                            }
                            break;
                        case 5:
                            break;
                        default:
                            System.err.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 2:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Profissional Mentor");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0:
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1:
                            System.out.println("\nCadastrar");
                            break;
                        case 2:
                            System.out.println("\nListar um");
                            break;
                        case 3:
                            System.out.println("\nListar todos");
                            break;
                        case 4:
                            System.out.println("\nAtualizar");
                            break;
                        case 5:
                            System.out.println("\nDeletar");
                            break;
                        case 6:
                            break;
                        default:
                            System.err.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                default:
                    System.err.println("🚫 Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}