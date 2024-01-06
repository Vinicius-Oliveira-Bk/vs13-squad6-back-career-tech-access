package utils;

public abstract class Utils {
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
        System.out.println("┃ 2 - Contato                                  ┃");
        System.out.println("┃ 3 - Endereço                                 ┃");
        System.out.println("┃ 4 - Estudante                                ┃");
        System.out.println("┃ 5 - PCD (Pessoa com Deficiência)             ┃");
        System.out.println("┃ 6 - Profissional mentor                      ┃");
        System.out.println("┃ 7 - Profissional em realocação               ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public static void exibirMenuOperacoes() {
        System.out.println("┏━━━ Selecione uma operação 📝 ━━━┓");
        System.out.println("┃ 0 - Sair                        ┃");
        System.out.println("┃ 1 - Cadastrar                   ┃");
        System.out.println("┃ 2 - Listar                      ┃");
        System.out.println("┃ 3 - Atualizar                   ┃");
        System.out.println("┃ 4 - Deletar                     ┃");
        System.out.println("┃ 5 - Voltar ao menu              ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void exibirEntidadeManipulada(String entidade) {
        System.out.println(String.format("%20s", entidade));
    }
}
