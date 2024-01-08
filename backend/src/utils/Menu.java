package utils;

import java.time.LocalDate;
import java.util.Scanner;

import entidades.Estudante;
import entidades.ProfissionalMentor;
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
                                    Utils.rotinaCadastroUsuario(estudante, TipoUsuarioEnum.ESTUDANTE);
                                    Utils.rotinaCadastroCliente(estudante);
                                    Utils.rotinaCadastroEstudante(estudante);
                                    usuarioServico.cadastrar(estudante);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Selecione o ID do estudante a ser consultado: ");
                                    System.out.println(usuarioServico.listarUm((long) idUsuario));
                                    sc.nextLine();
                                    break;
                                case 3:
                                    usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.ESTUDANTE);
                                    sc.nextLine();
                                    break;
                                case 4:
                                    int idEstudante = scanner.nextInt("Selecione o ID do estudante a ser atualizado: ");
                                    Estudante estudanteASerAtualizado = (Estudante) usuarioServico.listarUm((long) idEstudante);
                                    Utils.rotinaCadastroEstudante(estudanteASerAtualizado);
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
                                    ProfissionalRealocacao profissionalRealocacao = new ProfissionalRealocacao();
                                    Utils.rotinaCadastroUsuario(profissionalRealocacao, TipoUsuarioEnum.PROFISSIONAL);
                                    Utils.rotinaCadastroCliente(profissionalRealocacao);
                                    Utils.rotinaCadastroProfissionalRealocacao(profissionalRealocacao);
                                    usuarioServico.cadastrar(profissionalRealocacao);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Selecione o ID do profissional a ser consultado: ");
                                    System.out.println(usuarioServico.listarUm((long) idUsuario));
                                    sc.nextLine();
                                    break;
                                case 3:
                                    usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.PROFISSIONAL);
                                    sc.nextLine();
                                    profissionalRealocacaoServico.listarTodos();
                                    break;
                                case 4:
                                    // TODO: inserir o processo 
                                    int idProfissionalRealocacao = scanner.nextInt("Selecione o ID do Profissional a ser atualizado: ");
                                    ProfissionalRealocacao profissionalRealocacaoASerAtualizado = (ProfissionalRealocacao) usuarioServico.listarUm((long) idProfissionalRealocacao);
                                    Utils.rotinaCadastroProfissionalRealocacao(profissionalRealocacaoASerAtualizado);
                                    sc.nextLine();
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja atualizar");
                                    profissionalRealocacaoServico.listarUm(idUsuario);
                                    
                                    ProfissionalRealocacao profissionalRealocacaoAtualizar = new ProfissionalRealocacao();
                                
                                    profissionalRealocacaoAtualizar.setNome(scanner.nextLine("Informe seu nome"));
                                    profissionalRealocacaoAtualizar.setCpf(scanner.nextLine("Informe seu Cpf"));
                                    profissionalRealocacaoAtualizar.setDataDeNascimento(LocalDate.parse(scanner.nextLine("Informe sua data de nascimento (yyyy-mm-dd)")));
                                    profissionalRealocacaoAtualizar.setEnderecos(null);
                                    profissionalRealocacaoAtualizar.setContatos(null);
                                    profissionalRealocacaoAtualizar.setEmail(scanner.nextLine("Informe seu email"));
                                    profissionalRealocacaoAtualizar.setTipo(TipoUsuarioEnum.fromValor(2)); // chumbado
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
                                    int idUsuarioDeletar = scanner.nextInt("Informe o id do profissional em realocação que deseja deletar");
                                    profissionalRealocacaoServico.deletar(idUsuarioDeletar);
                                    int idUsuarioExcluido = scanner.nextInt("Selecione o ID do Profissional a ser excluído: ");
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
                            ProfissionalMentor mentorCadastro = new ProfissionalMentor();
                            Utils.rotinaCadastroUsuario(mentorCadastro, TipoUsuarioEnum.MENTOR);
                            Utils.rotinaCadastroMentor(mentorCadastro);
                            usuarioServico.cadastrar(mentorCadastro);

                            break;
                        case 2:
                            System.out.println("\nListar um");
                            int idMentor = scanner.nextInt("Selecione o ID do mentor a ser consultado: ");
                            System.out.println(usuarioServico.listarUm((long) idMentor));
                            sc.nextLine();

                            break;
                        case 3:
                            System.out.println("\nListar todos");
                            usuarioServico.listarTodosPorTipo(TipoUsuarioEnum.MENTOR);
                            scanner.nextLine();

                            break;
                        case 4:
                            System.out.println("\nAtualizar");

                            int idMentorAtualizar = scanner.nextInt("Selecione o ID do Mentor a ser atualizado: ");
                            ProfissionalMentor mentorASerAtualizado = (ProfissionalMentor) usuarioServico.listarUm((long) idMentor);
                            Utils.rotinaCadastroMentor(mentorASerAtualizado);

                            break;
                        case 5:
                            System.out.println("\nDeletar");

                            int idUsuarioExcluido = scanner.nextInt("Selecione o ID do Mentor a ser excluído: ");
                            usuarioServico.deletar((long) idUsuarioExcluido);

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