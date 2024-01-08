package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entidades.Estudante;
import enums.PlanoEnum;
import enums.TipoEstudanteEnum;
import enums.TipoUsuarioEnum;
import servicos.ContatoServico;
import servicos.EnderecoServico;
import servicos.UsuarioServico;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
                            usuarioServico.listarTodos();
                            sc.nextLine();
                            break;
                        case 2:
                            Utils.limparConsole();
                            Utils.exibirEntidadeManipulada("Estudante");
                            Utils.exibirMenuOperacoes();

                            opcao = scanner.nextInt();

                            switch (opcao) {
                                case 0:
                                    System.out.println("\n👋 Até mais!\n");
                                    break;
                                case 1:
                                    Estudante estudante = new Estudante();
                                    
                                    estudante.setNome(scanner.nextLine("Digite o nome: "));
                                    estudante.setCpf(scanner.nextLine("Digite o CPF: "));

                                    String dataNascimentoString = scanner.nextLine("Digite a data de nascimento (dd/MM/yyyy): ");
                                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
                                    estudante.setDataDeNascimento(dataNascimento);

                                    estudante.setEmail(scanner.nextLine("Digite o email: "));
                                    estudante.setTipo(TipoUsuarioEnum.ESTUDANTE);

                                    PlanoEnum planoEscolhido = PlanoEnum.GRATUITO;
                                    planoEscolhido = (PlanoEnum) Utils.exibirMenuEnumDinamico(planoEscolhido);
                                    estudante.setPlano(planoEscolhido);

                                    estudante.setInteresses(scanner.nextLine("Digite um interesse: "));
                                    estudante.setImagemDocummento(scanner.nextLine("Digite o link da imagem do seu documento: "));
                                    estudante.setControleParental(scanner.nextLine("Tem controle parental (1 - SIM / 2 - NÃO)? ").equals("1"));
                                    estudante.setAcessoPcd(scanner.nextLine("Tem acesso PCD (1 - SIM / 2 - NÃO)? ").equals("1"));

                                    estudante.setMatricula(scanner.nextLine("Digite a matrícula: "));
                                    estudante.setComprovanteMatricula(scanner.nextLine("Digite o link do comprovante de matrícula: "));

                                    TipoEstudanteEnum tipoEstudante = TipoEstudanteEnum.ENSINO_FUNDAMENTAL;
                                    estudante.setTipoEstudante(tipoEstudante);

                                    estudante.setCurso(scanner.nextLine("Digite o curso: "));
                                    estudante.setInstituicao(scanner.nextLine("Digite a instituição: "));

                                    String dataInicioString = scanner.nextLine("Digite a data de início (dd/MM/yyyy): ");
                                    LocalDate dataInicio = LocalDate.parse(dataInicioString, formatter);
                                    estudante.setDataInicio(dataInicio);

                                    String dataFimString = scanner.nextLine("Digite a data de fim (dd/MM/yyyy): ");
                                    LocalDate dataFim = LocalDate.parse(dataFimString, formatter);
                                    estudante.setDataFim(dataFim);

                                    usuarioServico.cadastrar(estudante);
                                    break;
                                case 2:
                                    int idUsuario = scanner.nextInt("Selecione o ID do estudante a ser consultado: ");
                                    var tipoEsperado = TipoUsuarioEnum.ESTUDANTE;

                                    if(usuarioServico.getTipoUsuario(idUsuario) != tipoEsperado) {
                                        System.err.println("🚫 Tipo de usuário inválido!");
                                        break;
                                    }

                                    usuarioServico.listarUm((long) idUsuario);
                                    sc.nextLine();
                                    break;
                                case 3:
                                    usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.ESTUDANTE);
                                    sc.nextLine();
                                    break;
                                case 4:
                                    int idUsuarioAtualizado = scanner.nextInt("Selecione o ID do estudante a ser atualizado: ");
                                    usuarioServico.atualizar((long) idUsuarioAtualizado, new Estudante());
                                    sc.nextLine();
                                    break;
                                case 5:
                                    int idUsuarioExcluido = scanner.nextInt("Selecione o ID do estudante a ser excluído: ");
                                    usuarioServico.deletar((long) idUsuarioExcluido);
                                    sc.nextLine();
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
                            sc.close();
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