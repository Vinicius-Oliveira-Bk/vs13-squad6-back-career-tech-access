package com.dbc.view;

import java.util.Scanner;

import com.dbc.model.entities.Cliente;
import com.dbc.model.entities.Estudante;
import com.dbc.model.entities.Pcd;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.model.entities.ProfissionalRealocacao;
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.TipoUsuarioEnum;
import com.dbc.services.ClienteServico;

// import com.dbc.services.AgendaServico;
// import com.dbc.services.ContatoServico;
// import com.dbc.services.EnderecoServico;

import com.dbc.services.EstudanteServico;
import com.dbc.services.PcdServico;
import com.dbc.services.ProfissionalMentorServico;
import com.dbc.services.ProfissionalRealocacaoServico;
import com.dbc.services.UsuarioServico;
import com.dbc.utils.CustomScanner;
import com.dbc.utils.Utils;

public class Aplicacao {
    public static void iniciarAplicacao() {
        Scanner sc = new Scanner(System.in);
        CustomScanner scanner = new CustomScanner();

        // EnderecoServico enderecoServico = new EnderecoServico();
        // ContatoServico contatoServico = new ContatoServico();
        // AgendaServico agendaServico = new AgendaServico();

        UsuarioServico usuarioServico = new UsuarioServico();
        ClienteServico clienteServico = new ClienteServico();
        EstudanteServico estudanteServico = new EstudanteServico();
        PcdServico pcdServico = new PcdServico();
        ProfissionalMentorServico profissionalMentorServico = new ProfissionalMentorServico();
        ProfissionalRealocacaoServico profissionalRealocacaoServico = new ProfissionalRealocacaoServico();

        int opcao, idUsuario;

        do {
            // Utils.limparConsole();
            Utils.telaInicial();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    Utils.limparConsole();
                    System.out.println("\n👋 Até mais!\n");
                    break;
                case 1:
                    // Utils.limparConsole();
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
                            // Utils.limparConsole();
                            Utils.exibirEntidadeManipulada("Estudante");
                            Utils.exibirMenuOperacoes();

                            opcao = scanner.nextInt();

                            switch (opcao) {
                                case 0:
                                    System.out.println("\n👋 Até mais!\n");
                                    break;
                                case 1:
                                    Estudante estudante = new Estudante();
                                    
                                    Usuario usuario = new Usuario();
                                    usuario = Utils.rotinaCadastroUsuario(estudante, TipoUsuarioEnum.ESTUDANTE);
                                
                                    Cliente cliente = new Cliente();
                                    cliente = Utils.rotinaCadastroCliente(estudante);

                                    Utils.rotinaCadastroEstudante(estudante);
                                    usuarioServico.cadastrar(usuario);

                                    clienteServico.cadastrar(cliente, usuario.getId());
                                    estudante.setCliente(cliente);
                                    
                                    estudanteServico.cadastrar(estudante);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Selecione o ID do estudante a ser consultado: ");
                                    estudanteServico.listarUm((long) idUsuario);
                                    sc.nextLine();
                                    break;
                                case 3:
                                    estudanteServico.listarTodos();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    Estudante estudanteASerAtualizado = new Estudante();
                                    
                                    int idEstudante = scanner.nextInt("Selecione o ID do estudante a ser atualizado: ");
                                    estudanteServico.listarUm((long) idEstudante);

                                    Utils.rotinaCadastroEstudante(estudanteASerAtualizado);

                                    estudanteServico.atualizar((long) idEstudante, estudanteASerAtualizado);
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

                                    Usuario usuario = new Usuario();
                                    usuario = Utils.rotinaCadastroUsuario(pcd, TipoUsuarioEnum.PCD);
                                    
                                    Cliente cliente = new Cliente();
                                    cliente = Utils.rotinaCadastroCliente(pcd);

                                    Utils.rotinaCadastroPcd(pcd);
                                    usuarioServico.cadastrar(usuario);

                                    clienteServico.cadastrar(cliente, usuario.getId());
                                    pcd.setCliente(cliente);
                                    
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

                                    Usuario usuario = new Usuario();
                                    usuario = Utils.rotinaCadastroUsuario(profissionalRealocacao, TipoUsuarioEnum.PROFISSIONAL_REALOCACAO);
                                   
                                    Cliente cliente = new Cliente();
                                    cliente = Utils.rotinaCadastroCliente(profissionalRealocacao);

                                    Utils.rotinaCadastroProfissionalRealocacao(profissionalRealocacao);
                                    usuarioServico.cadastrar(usuario);

                                    clienteServico.cadastrar(cliente, usuario.getId());
                                    profissionalRealocacao.setCliente(cliente);

                                    profissionalRealocacaoServico.cadastrar(profissionalRealocacao);
                                    break;
                                case 2:
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja visualizar: ");
                                    profissionalRealocacaoServico.listarUm((long) idUsuario);
                                    sc.nextLine();
                                    break;
                                case 3:
                                    profissionalRealocacaoServico.listarTodos();
                                    sc.nextLine();
                                    break;
                                case 4:
                                    ProfissionalRealocacao profissionalRealocacaoAtualizar = new ProfissionalRealocacao();
                                    
                                    idUsuario = scanner.nextInt("Informe o id do profissional em realocação que deseja atualizar: ");
                                    profissionalRealocacaoServico.listarUm((long) idUsuario);

                                    Utils.rotinaCadastroUsuario(profissionalRealocacaoAtualizar, TipoUsuarioEnum.PROFISSIONAL_REALOCACAO);
                                    Utils.rotinaCadastroCliente(profissionalRealocacaoAtualizar);
                                    Utils.rotinaCadastroProfissionalRealocacao(profissionalRealocacaoAtualizar);

                                    profissionalRealocacaoServico.atualizar((long) idUsuario, profissionalRealocacaoAtualizar);
                                    break;
                                case 5:
                                    int idUsuarioDeletar = scanner.nextInt("Informe o id do profissional em realocação que deseja deletar: ");
                                    sc.nextLine();
                                    profissionalRealocacaoServico.remover((long) idUsuarioDeletar);
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

                            Usuario usuario = new Usuario();
                            usuario = Utils.rotinaCadastroUsuario(mentorCadastro, TipoUsuarioEnum.MENTOR);
                            
                            Cliente cliente = new Cliente();
                            cliente = Utils.rotinaCadastroCliente(cliente);

                            // TODO: revisar regra de negócio, se id_usuario é necessário em Mentor
                            // já que em Cliente já temos o id_usuario
                            Utils.rotinaCadastroMentor(mentorCadastro);
                            usuarioServico.cadastrar(usuario);

                            clienteServico.cadastrar(cliente, usuario.getId());
                            mentorCadastro.setCliente(cliente);

                            profissionalMentorServico.cadastrar(mentorCadastro);
                            break;
                        case 2:
                            int idMentor = scanner.nextInt("Selecione o ID do mentor a ser consultado: ");
                            profissionalMentorServico.listarUm((long) idMentor);
                            sc.nextLine();
                            break;
                        case 3:
                            profissionalMentorServico.listarTodos();
                            scanner.nextLine();
                            break;
                        case 4:
                            int idMentorAtualizar = scanner.nextInt("Selecione o ID do Mentor a ser atualizado: ");
                            ProfissionalMentor mentorASerAtualizado = (ProfissionalMentor) profissionalMentorServico.listarUm((long) idMentorAtualizar);
                            Utils.rotinaCadastroMentor(mentorASerAtualizado);
                            profissionalMentorServico.atualizar((long) idMentorAtualizar, mentorASerAtualizado);
                            break;
                        case 5:
                            int idUsuarioExcluido = scanner.nextInt("Selecione o ID do Mentor a ser excluído: ");
                            profissionalMentorServico.remover((long) idUsuarioExcluido);
                            break;
                        case 6:
                            break;
                        default:
                            System.err.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 3:
                    // TODO: finalizar implementação da agenda
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Agenda");
                    Utils.exibirMenuOperacoes();

                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0:
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1:
                            System.out.println("Cadastrar disponibilidade");
                            sc.nextLine();
                            break;
                        case 2:
                            System.out.println("Remover disponibilidade");
                            sc.nextLine();
                            break;
                        case 3:
                            System.out.println("Listar toda a agenda");
                            sc.nextLine();
                            break;
                        case 4:
                            System.out.println("Listar um agendamento");
                            sc.nextLine();    
                            break;
                        case 5:
                            System.out.println("Agendar um horário");
                            sc.nextLine();
                            break;
                        case 6:
                            System.out.println("Reagendar um horário");
                            sc.nextLine();
                            break;
                        case 7:
                            System.out.println("Cancelar um horário");
                            break;
                        case 8:
                            break;
                        default:
                            System.err.println("🚫 Opção inválida!");
                            break;
                    }
                default:
                    System.err.println("🚫 Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
