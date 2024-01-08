package utils;

<<<<<<< HEAD
import entidades.Contato;
=======
import java.time.LocalDate;
>>>>>>> feature/feat-implementar-operacoes

public class Validacoes {
    public static boolean validarData(LocalDate data) {
        return true;
    }

    public static boolean validarContato(Contato contato) {
        if (contato.getDescricao() == null) {
            System.err.println("🚫 A descrição do contato não pode ser nula!");
            return false;
        }
        if (contato.getTelefone() == null) {
            System.err.println("🚫 O número de telefone não pode ser nula!");
            return false;
        }
        if (contato.getTipo() == null) {
            System.err.println("🚫 O tipo do contato deve ser informado!");
            return false;
        }
        return true;
    }
}
