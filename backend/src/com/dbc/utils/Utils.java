package com.dbc.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.dbc.exceptions.EntradaUsuarioException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.entities.Contato;
import com.dbc.model.entities.Endereco;
import com.dbc.model.entities.Estudante;
import com.dbc.model.entities.Pcd;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.model.entities.ProfissionalRealocacao;
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.AreaAtuacaoEnum;
import com.dbc.model.enums.NivelExperienciaEnum;
import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoEnum;
import com.dbc.model.enums.TipoUsuarioEnum;
import com.dbc.services.UsuarioServico;

public abstract class Utils {
    private static CustomScanner scanner = new CustomScanner();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // https://www.invertexto.com/simbolos-para-copiar
    // https://www.simbolosparacopiar.com/p/simbolos-redondos-e-quadrados.html?m=1
    public static void telaInicial() {
        System.out.println("┏━━━━ 🚀 Bem vindo ao Career Tech Access 🚀 ━━━┓");
        System.out.println("┃     Squad 06 - 13ª edição Vem Ser - DBC      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        exibirMenuEntidades();
    }

    public static void exibirMenuEntidades() {
        System.out.println("┏━━━━━━━━━━━ SELECIONE UMA ENTIDADE ━━━━━━━━━━━┓");
        System.out.println("┃ 0 - Sair                                     ┃");
        System.out.println("┃ 1 - Cliente                                  ┃");
        System.out.println("┃ 2 - Profissional mentor                      ┃");
        System.out.println("┃ 3 - Agendamento (Mentor)                     ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void selecionarTipoCliente() {
        System.out.println("┏━━━━━━━━━━━ SELECIONE O TIPO DE CLIENTE ━━━━━━━━━━━┓");
        System.out.println("┃ 0 - Sair                                          ┃");
        System.out.println("┃ 1 - Geral (todos)                                 ┃");
        System.out.println("┃ 2 - Estudante                                     ┃");
        System.out.println("┃ 3 - PCD (Pessoa com Deficiência)                  ┃");
        System.out.println("┃ 4 - Profissional em realocação                    ┃");
        System.out.println("┃ 5 - Voltar ao menu principal                      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void exibirMenuOperacoes() {
        System.out.println("┏━━━ Selecione uma operação 📝 ━━━┓");
        System.out.println("┃ 0 - Sair                        ┃");
        System.out.println("┃ 1 - Cadastrar                   ┃");
        System.out.println("┃ 2 - Listar um                   ┃");
        System.out.println("┃ 3 - Listar todos                ┃");
        System.out.println("┃ 4 - Atualizar                   ┃");
        System.out.println("┃ 5 - Deletar                     ┃");
        System.out.println("┃ 6 - Voltar ao menu              ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void exibirMenuAgendamento() {
        System.out.println("┏━━━   Selecione uma opção 📝  ━━━┓");
        System.out.println("┃ 0 - Sair                        ┃");
        System.out.println("┃ 1 - Cadastrar disponibilidade   ┃");
        System.out.println("┃ 2 - Remover disponibilidade     ┃");
        System.out.println("┃ 3 - Listar toda a agenda        ┃");
        System.out.println("┃ 4 - Listar um agendamento       ┃");
        System.out.println("┃ 5 - Agendar um horário          ┃");
        System.out.println("┃ 6 - Reagendar um horário        ┃");
        System.out.println("┃ 7 - Cancelar um horário         ┃");
        System.out.println("┃ 8 - Voltar ao menu              ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    // https://www.clubedohardware.com.br/forums/topic/1367870-como-limpar-a-tela-no-console-do-java/
    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

            // modo clássico
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.err.println("🚫 Erro ao limpar o console: " + e.getMessage());
        }
    }

    public static void exibirEntidadeManipulada(String entidade) {
        System.out.println(String.format("%20s", entidade));
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static Enum exibirMenuEnumDinamico(Enum tipo) throws EntradaUsuarioException {
        int opcaoEscolhida;
        Scanner scanner = new Scanner(System.in);
        var opcoesDoEnum = tipo.getClass().getEnumConstants();

        do {
            System.out.println("┏━━━ Selecione uma opção 📝 ━━━┓");
            System.out.println("┃ 0 - Sair                     ┃");

            for (int i = 0; i < opcoesDoEnum.length; i++) {
                System.out.println("┃ " + (i + 1) + " - " + opcoesDoEnum[i].toString());
            }

            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            opcaoEscolhida = scanner.nextInt();

            if (opcaoEscolhida == 0)
                break;

            if (opcaoEscolhida < 0 || opcaoEscolhida > opcoesDoEnum.length)
                throw new EntradaUsuarioException("🚫 Opção inválida! Por favor informe os valores corretamente.");

            return opcoesDoEnum[opcaoEscolhida - 1];
        } while (opcaoEscolhida != 0);

        return null;
    }

    public static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }

    /*
     * Rotinas: funcoes que apresentam todos os atributos e regras
     * necessárias para o cadastro de uma entidade.
     */
    public static void rotinaCadastroContatosEenderecos(Usuario usuario) {
        try {
            Contato contato = new Contato();
            contato.setDescricao(scanner.nextLine("Digite a descrição do contato: "));
            contato.setTelefone(scanner.nextLine("Digite o telefone de contato: "));

            TipoEnum tipoContato = TipoEnum.RESIDENCIAL;
            tipoContato = (TipoEnum) Utils.exibirMenuEnumDinamico(tipoContato);
            contato.setTipo(tipoContato);

            Endereco endereco = new Endereco();

            endereco.setLogradouro(scanner.nextLine("Digite o logradouro do endereço: "));
            endereco.setNumero(scanner.nextLine("Digite o número do endereço: "));
            endereco.setComplemento(scanner.nextLine("Digite o complemento do endereço: "));
            endereco.setCep(scanner.nextLine("Digite o CEP do endereço: "));
            endereco.setCidade(scanner.nextLine("Digite a cidade do endereço: "));
            endereco.setEstado(scanner.nextLine("Digite o estado do endereço: "));
            endereco.setPais(scanner.nextLine("Digite o país do endereço: "));

            TipoEnum tipoEndereco = TipoEnum.RESIDENCIAL;
            tipoEndereco = (TipoEnum) Utils.exibirMenuEnumDinamico(tipoEndereco);
            endereco.setTipo(tipoEndereco);
        } catch (Exception e) {
            System.err.println("🚫 Entrada inválida! Por favor informe os valores corretamente.");
            scanner.nextLine();
        }
    }

    public static Usuario rotinaCadastroUsuario(Usuario usuario, TipoUsuarioEnum tipoUsuario) {
        UsuarioServico usuarioServico = new UsuarioServico();
        try {
            usuario.setNome(scanner.nextLine("Digite o nome: "));
            usuario.setCpf(scanner.nextLine("Digite o CPF: "));
            String dataNascimentoString = scanner.nextLine("Digite a data de nascimento (dd/MM/yyyy): ");
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
            usuario.setDataNascimento(dataNascimento);
            usuario.setEmail(scanner.nextLine("Digite o email: "));
            usuario.setAcessoPcd(scanner.nextLine("Tem acesso PCD ( S / N)? ").toUpperCase().charAt(0));
            usuario.setTipo(tipoUsuario);
            usuario.setInteresses(scanner.nextLine("Informe seus interesses: "));
            usuario.setImagemDocumento(scanner.nextLine("Informe o link da imagem do documento: "));
            return usuario;
        } catch (Exception e) {
            System.err.println("🚫 Entrada inválida! Por favor informe os valores corretamente.");
            scanner.nextLine();
        }
        return null;
    }

    public static Cliente rotinaCadastroCliente(Cliente cliente) {
        int controleParental, acessoPcd;

        try {
            PlanoEnum planoEscolhido = PlanoEnum.GRATUITO;
            planoEscolhido = (PlanoEnum) Utils.exibirMenuEnumDinamico(planoEscolhido);
            cliente.setPlano(planoEscolhido);
            cliente.setControleParental(scanner.nextLine("Tem controle parental ( S / N )? ").toUpperCase().charAt(0));
            return cliente;
        } catch (Exception e) {
            System.err.println("🚫 Entrada inválida! Por favor informe os valores corretamente.");
            scanner.nextLine();
        }
        return null;
    }

    public static Estudante rotinaCadastroEstudante(Estudante estudante) {
        estudante.setMatricula(scanner.nextLine("Digite a matrícula: "));
        estudante.setComprovanteMatricula(scanner.nextLine("Digite o link do comprovante de matrícula: "));

        estudante.setCurso(scanner.nextLine("Digite o curso: "));
        estudante.setInstituicao(scanner.nextLine("Digite a instituição: "));

        String dataInicioString = scanner.nextLine("Digite a data de início (dd/MM/yyyy): ");
        LocalDate dataInicio = LocalDate.parse(dataInicioString, formatter);
        estudante.setDataInicio(dataInicio);
        String dataTerminoString = scanner.nextLine("Digite a data de término (dd/MM/yyyy): ");
        LocalDate dataTermino = LocalDate.parse(dataTerminoString, formatter);
        estudante.setDataTermino(dataTermino);
        return estudante;
        // String dataFimString = scanner.nextLine("Digite a data de fim (dd/MM/yyyy):
        // ");
        // LocalDate dataFim = LocalDate.parse(dataFimString, formatter);
        // // estudante.setDataFim(dataFim);
    }

    public static void rotinaCadastroPcd(Pcd pcd) {
        pcd.setTipoDeficiencia(scanner.nextLine("Digite o tipo de deficiência: "));
        pcd.setCertificadoDeficienciaGov(scanner.nextLine("Digite o link do certificado de deficiência: "));
    }

    public static void rotinaCadastroProfissionalRealocacao(ProfissionalRealocacao profissionalRealocacao) {
        profissionalRealocacao.setProfissao(scanner.nextLine("Digite a sua profissão: "));
        profissionalRealocacao.setObjetivoProfissional(scanner.nextLine("Digite o seu objetivo profissional: "));
    }

    public static void rotinaCadastroMentor(ProfissionalMentor mentor) {
        mentor.setCarteiraDeTrabalho(scanner.nextLine("Digite sua carteira de trabalho: "));

        AreaAtuacaoEnum areaAtuacao = AreaAtuacaoEnum.TI;
        mentor.setAreaAtuacao(areaAtuacao);

        NivelExperienciaEnum nivelExperiencia = NivelExperienciaEnum.JUNIOR;
        mentor.setNivelExperienciaEnum(nivelExperiencia);
    }
}
