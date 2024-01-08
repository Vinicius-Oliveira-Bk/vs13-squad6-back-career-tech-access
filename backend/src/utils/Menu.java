package utils;

import java.util.Scanner;

import enums.TipoUsuarioEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.UsuarioServico;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();

        int opcao;

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
                            usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.MENTOR);
                            sc.nextLine();
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