package utils;

import java.time.LocalDate;
import java.util.Scanner;

import entidades.ProfissionalRealocacao;
import entidades.Usuario;
import enums.PlanoEnum;
import enums.TipoEnum;
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
                                    ProfissionalRealocacao profissionalRealocacao = new ProfissionalRealocacao();
                                    profissionalRealocacao.setNome(scanner.nextLine("Informe seu nome"));
                                    profissionalRealocacao.setCpf(scanner.nextLine("Informe seu Cpf"));
                                    profissionalRealocacao.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                                    profissionalRealocacao.setEnderecos(null);
                                    profissionalRealocacao.setContatos(null);
                                    profissionalRealocacao.setEmail(scanner.nextLine("Informe seu email"));
                                    int tipoCliente = 2;
                                    profissionalRealocacao.setTipo(TipoUsuarioEnum.fromValor(tipoCliente));
                                    System.out.println("1 - Gratuito");
                                    System.out.println("2 - Básico");
                                    System.out.println("3 - Premium");
                                    int planoCliente = scanner.nextInt("Informe o número correspondente ao seu plano");
                                    profissionalRealocacao.setPlano(PlanoEnum.fromValor(planoCliente));
                                    profissionalRealocacao.setInteresses(scanner.nextLine("Informe seus interesses"));
                                    profissionalRealocacao.setImagemDocummento(scanner.nextLine("Informe o link da imagem do seu documento"));
                                    profissionalRealocacao.setControleParental(false);
                                    profissionalRealocacao.setAcessoPcd(false);
                                    profissionalRealocacao.setNome(scanner.nextLine("Informe sua profissão"));
                                    profissionalRealocacao.setNome(scanner.nextLine("Informe seu objetivo profissional"));
                                    usuarioServico.cadastrar(profissionalRealocacao);
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