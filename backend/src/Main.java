import entidades.Contato;
import entidades.Endereco;
import entidades.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat formato3 = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada3 = formato3.parse("21/07/2021");
        //System.out.println(formato3.format(dataFormatada3));

        ArrayList<Contato> listaContatos = new ArrayList<>();
        ArrayList<Endereco> listaEndereco = new ArrayList<>();

        Usuario usuario = new Usuario("Maria Joana Texeira", "06497895622", formato3.parse("21/07/2090"),  listaEndereco, listaContatos, "mariatexeira@gmail.com", null);

        System.out.println(usuario);
    }
}
