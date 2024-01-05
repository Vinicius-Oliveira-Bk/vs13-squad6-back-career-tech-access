import entidades.Endereco;
import enums.TipoEnum;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Endereco endereco = new Endereco(TipoEnum.RESIDENCIAL, "rua teste", "bairro teste",
                "cidade teste", "estado teste", "pais teste", "12345678", 1);
        System.out.println(endereco.getTipo());
    }
}