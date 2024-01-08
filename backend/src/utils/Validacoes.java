package utils;

public class Validacoes {
    public static boolean validarString(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean validarInteiro(int input) {
        return input >= 0;
    }

    public static boolean validarDouble(double input) {
        return input >= 0;
    }
}
