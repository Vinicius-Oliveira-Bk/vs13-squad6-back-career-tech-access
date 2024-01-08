package utils;

import java.time.LocalDate;
import java.util.Scanner;

import entidades.ProfissionalMentor;
import enums.AreaAtuacaoEnum;
import enums.NivelExperienciaEnum;
import enums.TipoUsuarioEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.ProfissionalMentorServico;
import servicos.UsuarioServico;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();

        UsuarioServico usuarioServico = new UsuarioServico();
        ContatoServico contatoServico = new ContatoServico();
        EnderecoServico enderecoServico = new EnderecoServico();

        ProfissionalMentorServico profissionalMentorServico = new ProfissionalMentorServico();

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

                            ProfissionalMentor profissionalMentor = new ProfissionalMentor();
                            profissionalMentor.setNome(scanner.nextLine("Informe seu nome"));
                            profissionalMentor.setCpf(scanner.nextLine("Informe seu Cpf"));
                            profissionalMentor.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                            profissionalMentor.setEnderecos(null);
                            profissionalMentor.setContatos(null);
                            profissionalMentor.setEmail(scanner.nextLine("Informe seu email"));
                            profissionalMentor.setTipo(TipoUsuarioEnum.fromValor(4));//chumbado
                            System.out.println("≫  Informe sua área de atuação:");
                            System.out.println("\t1 - TI");
                            System.out.println("\t2 - SAUDE");
                            System.out.println("\t3 - EDUCACAO");
                            System.out.println("\t4 - FINANCAS");
                            System.out.println("\t5 - MARKETING");
                            System.out.println("\t6 - JURIDICO");
                            System.out.println("\t7 - ENGENHARIA");
                            System.out.println("\t8 - DESIGN");
                            System.out.println("\t9 - COMERCIO");
                            System.out.println("\t10 - MEIO_AMBIENTE");
                            System.out.println("\t11 - CONSULTORIA");
                            System.out.println("\t12 - RH");
                            System.out.println("\t13 - OUTROS");
                            profissionalMentor.setAreaAtuacao(AreaAtuacaoEnum.fromValor(scanner.nextInt()));
                            System.out.println("≫  Informe seu nível de experiência: ");
                            System.out.println("\t1 - JUNIOR");
                            System.out.println("\t2 - PLENO");
                            System.out.println("\t3 - SÊNIOR");
                            profissionalMentor.setNivelExperienciaEnum(NivelExperienciaEnum.fromValor(scanner.nextInt()));
                            profissionalMentor.setCarteiraDeTrabalho(scanner.nextLine("Informe o link da imagem do seu documento"));
                            profissionalMentor.setCertificadosDeCapacitacao(null);
                            profissionalMentorServico.cadastrar(profissionalMentor);

                            break;
                        case 2:
                            System.out.println("\nListar um");
                            idUsuario = scanner.nextInt("Informe o ID do usuário que deseja listar: ");
                            profissionalMentorServico.listarUm(idUsuario);
                            break;
                        case 3:
                            System.out.println("\nListar todos");
                            usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.MENTOR);
                            sc.nextLine();
                            break;
                        case 4:
                            System.out.println("\nAtualizar");

                            idUsuario = scanner.nextInt("Informe o ID do usuário que deseja alterar: ");

                            ProfissionalMentor profissionalAtualizado = new ProfissionalMentor();

                            profissionalAtualizado.setNome(scanner.nextLine("Informe seu nome"));
                            profissionalAtualizado.setCpf(scanner.nextLine("Informe seu Cpf"));
                            profissionalAtualizado.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                            profissionalAtualizado.setEnderecos(null);
                            profissionalAtualizado.setContatos(null);
                            profissionalAtualizado.setEmail(scanner.nextLine("Informe seu email"));
                            profissionalAtualizado.setTipo(TipoUsuarioEnum.fromValor(4));//chumbado
                            System.out.println("≫  Informe sua área de atuação:");
                            System.out.println("\t1 - TI");
                            System.out.println("\t2 - SAUDE");
                            System.out.println("\t3 - EDUCACAO");
                            System.out.println("\t4 - FINANCAS");
                            System.out.println("\t5 - MARKETING");
                            System.out.println("\t6 - JURIDICO");
                            System.out.println("\t7 - ENGENHARIA");
                            System.out.println("\t8 - DESIGN");
                            System.out.println("\t9 - COMERCIO");
                            System.out.println("\t10 - MEIO_AMBIENTE");
                            System.out.println("\t11 - CONSULTORIA");
                            System.out.println("\t12 - RH");
                            System.out.println("\t13 - OUTROS");
                            profissionalAtualizado.setAreaAtuacao(AreaAtuacaoEnum.fromValor(scanner.nextInt()));
                            System.out.println("≫  Informe seu nível de experiência: ");
                            System.out.println("\t1 - JUNIOR");
                            System.out.println("\t2 - PLENO");
                            System.out.println("\t3 - SÊNIOR");
                            profissionalAtualizado.setNivelExperienciaEnum(NivelExperienciaEnum.fromValor(scanner.nextInt()));
                            profissionalAtualizado.setCarteiraDeTrabalho(scanner.nextLine("Informe o link da imagem do seu documento"));
                            profissionalAtualizado.setCertificadosDeCapacitacao(null);
                            profissionalMentorServico.atualizar(idUsuario, profissionalAtualizado);
                            break;
                        case 5:
                            System.out.println("\nDeletar");

                            idUsuario = scanner.nextInt("Informe o ID do usuário que deseja excluir: ");
                            profissionalMentorServico.deletar(idUsuario);
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