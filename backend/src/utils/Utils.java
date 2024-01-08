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
        } catch (Exception e) {
            System.err.println("🚫 Erro ao limpar o console: " + e.getMessage());
        }
    }

    public static void exibirEntidadeManipulada(String entidade) {
        System.out.println(String.format("%20s", entidade));
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }
}
