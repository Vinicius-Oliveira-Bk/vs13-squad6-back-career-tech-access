package utils;

public class Menu {
    public static void main(String[] args) {
        CustomScanner scanner = new CustomScanner();
        
        int opcao;
        
        // TODO: remover todos os "scanner.nextInt()" presentes nos switches secundarios
        // foi gerado somente para fins de teste pelos outros membros
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
                    Utils.exibirEntidadeManipulada("Cliente");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0: 
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1:
                            System.out.println("\nCadastrar \n");
                            scanner.nextInt();
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 2:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Contato");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0: 
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1:
                            System.out.println("\nCadastrar \n");
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            scanner.nextInt();
                            break;
                    }
                    break;
                case 3:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Endereço");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0: 
                            System.out.println("\n👋 Até mais!\n");
                            scanner.nextInt();
                            break;
                        case 1:
                            System.out.println("\nCadastrar \n");
                            scanner.nextInt();
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 4:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Estudante");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 0: 
                            System.out.println("\n👋 Até mais!\n");
                            break;
                        case 1:
                            System.out.println("\nCadastrar \n");
                            scanner.nextInt();
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 5:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("PCD");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("\nCadastrar \n");
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 6:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Profissional mentor");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("\nCadastrar \n");
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                case 7:
                    Utils.limparConsole();
                    Utils.exibirEntidadeManipulada("Profissional em realocação");
                    Utils.exibirMenuOperacoes();
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.println("\nCadastrar \n");
                            break;
                        case 2:
                            System.out.println("\nListar");
                            scanner.nextInt();
                            break;
                        case 3:
                            System.out.println("\nAtualizar");
                            scanner.nextInt();
                            break;
                        case 4:
                            System.out.println("\nDeletar");
                            scanner.nextInt();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("🚫 Opção inválida!");
                            break;
                    }
                    break;
                default:
                    System.out.println("🚫 Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}