package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.Cliente;
import entidades.Estudante;
import entidades.ProfissionalRealocacao;
import entidades.Usuario;
import enums.PlanoEnum;
import enums.TipoEstudanteEnum;
import enums.TipoUsuarioEnum;

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

    public static Enum exibirMenuEnumDinamico(Enum tipo) {
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

            try {
                opcaoEscolhida = scanner.nextInt();

                if (opcaoEscolhida == 0)
                    return null;

                if (opcaoEscolhida < 0 || opcaoEscolhida > opcoesDoEnum.length) {
                    System.err.println("🚫 Entrada inválida! Insira uma opção válida.");
                } else {
                    return opcoesDoEnum[opcaoEscolhida - 1];
                }
            } catch (InputMismatchException e) {
                System.err.println("🚫 Entrada inválida! Insira uma opção das exibidas.");
                scanner.nextLine();
            }
        } while (true);
    }

    /*
     * Rotinas: funcoes que apresentam todos os atributos e regras
     * necessárias para o cadastro de uma entidade.
     */
    public static void rotinaCadastroContatosEenderecos() {
        /*
         * Atributos - Contato:
         * - descricao : String
         * - telefone : String
         * - tipo : TipoEnum
         * 
         * Atributos - Endereco:
         * - logradouro : String
         * - numero : String
         * - complemento : String
         * - cep : String
         * - cidade : String
         * - estado : String
         * - pais : String
         * - tipo : TipoEnum
         */
    }

    public static void rotinaCadastroUsuario(Usuario usuario, TipoUsuarioEnum tipoUsuario) {
        try {
            usuario.setNome(scanner.nextLine("Digite o nome: "));
            usuario.setCpf(scanner.nextLine("Digite o CPF: "));

            String dataNascimentoString = scanner.nextLine("Digite a data de nascimento (dd/MM/yyyy): ");
            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);
            usuario.setDataDeNascimento(dataNascimento);

            usuario.setEmail(scanner.nextLine("Digite o email: "));

            usuario.setTipo(tipoUsuario);
        } catch (Exception e) {
            System.err.println("🚫 Entrada inválida! Por favor informe os valores corretamente.");
            scanner.nextLine();
        }
    }

    public static void rotinaCadastroCliente(Cliente cliente) {
        try {
            PlanoEnum planoEscolhido = PlanoEnum.GRATUITO;
            planoEscolhido = (PlanoEnum) Utils.exibirMenuEnumDinamico(planoEscolhido);
            cliente.setPlano(planoEscolhido);

            cliente.setInteresses(scanner.nextLine("Digite um interesse: "));
            cliente.setImagemDocummento(scanner.nextLine("Digite o link da imagem do seu documento: "));
            cliente.setControleParental(scanner.nextLine("Tem controle parental (1 - SIM / 2 - NÃO)? ").equals("1"));
            cliente.setAcessoPcd(scanner.nextLine("Tem acesso PCD (1 - SIM / 2 - NÃO)? ").equals("1"));
        } catch (Exception e) {
            System.err.println("🚫 Entrada inválida! Por favor informe os valores corretamente.");
            scanner.nextLine();
        }
    }

    public static void rotinaCadastroEstudante(Estudante estudante) {
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
    }

    public static void rotinaCadastroProfissionalRealocacao(ProfissionalRealocacao profissionalRealocacao) {
        profissionalRealocacao.setProfissao(scanner.nextLine("Digite a sua profissão: "));
        profissionalRealocacao.setObjetivoProfissional(scanner.nextLine("Digite o seu objetivo profissional: "));
    }


    public static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
