package utils;

import entidades.Contato;
import entidades.Endereco;

public class Validacoes {
    public static boolean validarString(String input, String mensagemErro) {
        if (input != null && !input.isEmpty()) {
            return true;
        } else {
            System.err.println("❌ Erro: " + mensagemErro);
            return false;
        }
    }

    public static boolean validarDouble(double input, String mensagemErro) {
        if (input >= 0) {
            return true;
        } else {
            System.err.println("❌ Erro: " + mensagemErro);
            return false;
        }
    }

    public static boolean validarInteiro(int input, String mensagemErro) {
        if (input >= 0) {
            return true;
        } else {
            System.err.println("❌ Erro: " + mensagemErro);
            return false;
        }
    }

    public static boolean validarContato(Contato contato) {
        if (contato == null) {
            System.err.println("❌ Erro: Contato não pode ser nulo.");
            return false;
        }

        if (!validarString(contato.getDescricao(), "Descrição do contato não pode ser vazia.")) {
            return false;
        }

        if (!validarTelefone(contato.getTelefone())) {
            return false;
        }

        return true;
    }

    // Método específico para validar telefone com formato (XX)XXXXX-XXXX ou (XX)XXXX-XXXX
    public static boolean validarTelefone(String telefone) {
        String regexTelefone = "\\(\\d{2}\\)\\d{4,5}-\\d{4}";

        if (telefone.matches(regexTelefone)) {
            return true;
        } else {
            System.err.println("❌ Erro: Formato inválido para número de telefone.");
            return false;
        }
    }

    public static boolean validarEmail(String email) {
        String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        if (email.matches(regexEmail)) {
            return true;
        } else {
            System.err.println("❌ Erro: Email inválido.");
            return false;
        }
    }

    public static boolean validarEndereco(Endereco endereco) {
        if (endereco == null) {
            System.err.println("❌ Erro: Endereço não pode ser nulo.");
            return false;
        }

        if (!validarString(endereco.getLogradouro(), "Logradouro não pode ser vazio.")) {
            return false;
        }

        if (!validarString(endereco.getNumero(), "Número não pode ser vazio.")) {
            return false;
        }

        if (!validarInteiro(Integer.parseInt(endereco.getNumero()), "Número do endereço não pode ser negativo.")) {
            return false;
        }

        if (!validarString(endereco.getCep(), "CEP não pode ser vazio.")) {
            return false;
        }

        if (!validarString(endereco.getCidade(), "Cidade não pode ser vazia.")) {
            return false;
        }

        if (!validarString(endereco.getEstado(), "Estado não pode ser vazio.")) {
            return false;
        }

        if (!validarString(endereco.getPais(), "País não pode ser vazio.")) {
            return false;
        }

        return true;
    }
}