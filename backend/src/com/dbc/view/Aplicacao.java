package com.dbc.view;

import java.util.Scanner;

import com.dbc.model.entities.Estudante;
import com.dbc.model.entities.Pcd;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.model.entities.ProfissionalRealocacao;
import com.dbc.model.enums.TipoUsuarioEnum;
import com.dbc.services.*;
import com.dbc.utils.CustomScanner;
import com.dbc.utils.Utils;

public class Aplicacao {
    public static void iniciarAplicacao() {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();
        EstudanteServico estudanteServico = new EstudanteServico();
        UsuarioServico usuarioServico = new UsuarioServico();
        PcdServico pcdServico = new PcdServico();
        ProfissionalRealocacaoServico profissionalRealocacaoServico = new ProfissionalRealocacaoServico();
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
                                    estudanteServico.cadastrar(estudante);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Selecione o ID do estudante a ser consultado: ");
                                    System.out.println(estudanteServico.obterPorId((long) idUsuario));
                                    sc.nextLine();
                                    break;
                                case 3:
                                    estudanteServico.listarTodos();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    int idEstudante = scanner.nextInt("Selecione o ID do estudante a ser atualizado: ");
                                    Estudante estudanteASerAtualizado = estudanteServico.obterPorId((long) idEstudante);
                                    Utils.rotinaCadastroEstudante(estudanteASerAtualizado);
                                    sc.nextLine();
                                    break;
                                case 5:
                                    int idUsuarioExcluido = scanner.nextInt("Selecione o ID do estudante a ser excluído: ");
                                    estudanteServico.remover((long) idUsuarioExcluido);
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
                                    Pcd pcd = new Pcd();

                                    Utils.rotinaCadastroUsuario(pcd, TipoUsuarioEnum.PCD);
                                    Utils.rotinaCadastroCliente(pcd);
                                    Utils.rotinaCadastroPcd(pcd);

                                    pcdServico.cadastrar(pcd);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Selecione o ID do pcd a ser consultado: ");
                                    System.out.println(pcdServico.listarUm((long) idUsuario));
                                    sc.nextLine();
                                    break;
                                case 3:
                                    pcdServico.listarTodos();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    int idPcd = scanner.nextInt("Selecione o ID do pcd a ser atualizado: ");
                                    Pcd pcdASerAtualizado = pcdServico.listarUm((long) idPcd);
                                    Utils.rotinaCadastroPcd(pcdASerAtualizado);
                                    sc.nextLine();
                                    break;
                                case 5:
                                    int idUsuarioExcluido = scanner.nextInt("Selecione o ID do pcd a ser excluído: ");
                                    pcdServico.remover((long) idUsuarioExcluido);
                                    sc.nextLine();
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

                                    Utils.rotinaCadastroUsuario(profissionalRealocacao, TipoUsuarioEnum.PROFISSIONAL_REALOCACAO);
                                    Utils.rotinaCadastroCliente(profissionalRealocacao);
                                    Utils.rotinaCadastroProfissionalRealocacao(profissionalRealocacao);

                                    profissionalRealocacaoServico.cadastrar(profissionalRealocacao);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja visualizar: ");
                                    profissionalRealocacaoServico.listarUm((long) idUsuario);
                                    sc.nextLine();
                                    break;
                                case 3:
                                    profissionalRealocacaoServico.listar();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja atualizar: ");
                                    profissionalRealocacaoServico.listarUm((long) idUsuario);

                                    ProfissionalRealocacao profissionalRealocacaoAtualizar = new ProfissionalRealocacao();
                                    Utils.rotinaCadastroUsuario(profissionalRealocacaoAtualizar, TipoUsuarioEnum.PROFISSIONAL_REALOCACAO);
                                    Utils.rotinaCadastroCliente(profissionalRealocacaoAtualizar);
                                    profissionalRealocacaoAtualizar.setProfissao(scanner.nextLine("Informe sua profissão: "));
                                    profissionalRealocacaoAtualizar.setObjetivoProfissional(scanner.nextLine("Informe seu objetivo profissional: "));

                                    profissionalRealocacaoServico.atualizar((long) idUsuario, profissionalRealocacaoAtualizar);
                                    break;
                                case 5:
                                    int idUsuarioDeletar = scanner.nextInt("Informe o id do profissional em realocação que deseja deletar: ");
                                    profissionalRealocacaoServico.remover((long) idUsuarioDeletar);
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
                            int idMentor = scanner.nextInt("Selecione o ID do mentor a ser consultado: ");
                            System.out.println(profissionalMentorServico.listarUm((long) idMentor));
                            sc.nextLine();
                            break;
                        case 3:
                            profissionalMentorServico.listarTodos();
                            scanner.nextLine();
                            break;
                        case 4:
                            int idMentorAtualizar = scanner.nextInt("Selecione o ID do Mentor a ser atualizado: ");
                            ProfissionalMentor mentorASerAtualizado = profissionalMentorServico.listarUm((long) idMentorAtualizar);
                            Utils.rotinaCadastroMentor(mentorASerAtualizado);
                            break;
                        case 5:
                            int idUsuarioExcluido = scanner.nextInt("Selecione o ID do Mentor a ser excluído: ");
                            usuarioServico.remover((long) idUsuarioExcluido);
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