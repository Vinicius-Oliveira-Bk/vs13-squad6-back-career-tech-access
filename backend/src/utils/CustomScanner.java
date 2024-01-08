package utils;

import java.util.Scanner;

public class CustomScanner { 
    private Scanner scanner;

    public CustomScanner() {
        this.scanner = new Scanner(System.in);
    }

    public int nextInt() {
        return nextInt("");
    }
 
    public int nextInt(String mensagem) {
        int input = 0;
        boolean isInputValido = false;
        
        while (!isInputValido) {
            try {
                if(!mensagem.isEmpty()) System.out.print("≫  "+ mensagem + ": ");
                else System.out.print("≫ ");

                String entradaDoUsuario = scanner.nextLine().trim();
                input = Integer.parseInt(entradaDoUsuario);
                
                if(input < 0) throw new Exception();
                
                isInputValido = true;
            } catch (NumberFormatException e) {
                System.err.println("\n🚫 Entrada inválida! Digite um número inteiro.");
            } catch (Exception e) {
                System.err.println("\n🚫 Entrada inválida! Selecione uma opção listada.");
            }
        }
        
        return input;
    }

    public String nextLine() {
        return nextLine();
    }  

    public String nextLine(String mensagem) {
        String input = "";
        boolean isInputValido = false;

        while (!isInputValido) {
            try {
                if(!mensagem.isEmpty()) System.out.print("≫  "+ mensagem + ": ");
                else System.out.print("≫ ");

                input = scanner.nextLine().trim();

                if (input.isEmpty()) throw new Exception();

                isInputValido = true;
            } catch (Exception e) {
                System.err.println("🚫 Entrada inválida!");
            }
        }

        return input;
    }
}

