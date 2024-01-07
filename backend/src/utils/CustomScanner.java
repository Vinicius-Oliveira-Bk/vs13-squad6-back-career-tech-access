package utils;

import java.util.Scanner;

public class CustomScanner { 
    private Scanner scanner;

    public CustomScanner() {
        this.scanner = new Scanner(System.in);
    }

    public int nextInt() {
        int input = 0;
        boolean isInputValido = false;
        
        while (!isInputValido) {
            try {
                System.out.print("≫ ");
                String userInput = scanner.nextLine().trim();
                input = Integer.parseInt(userInput);
                
                if(input < 0) throw new Exception();
                
                isInputValido = true;
            } catch (NumberFormatException e) {
                System.out.println("\n🚫 Entrada inválida! Digite um número inteiro.");
            } catch (Exception e) {
                System.out.println("\n🚫 Entrada inválida! Selecione uma opção listada.");
            }
        }
        
        return input;
    }

    public String nextLine() {
        String input = "";
        boolean isInputValido = false;

        while (!isInputValido) {
            try {
                System.out.print("≫ ");
                input = scanner.nextLine().trim();

                if (input.isEmpty()) throw new Exception();

                isInputValido = true;
            } catch (Exception e) {
                System.out.println("🚫 Entrada inválida!");
            }
        }

        return input;
    }
}

