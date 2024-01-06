import entidades.Contato;
import servicos.ContatoServico;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        Contato c = new Contato();
        Contato c1 = new Contato();
        Contato c2 = new Contato();
        Contato c3 = new Contato();
        Contato c4 = new Contato();

        ContatoServico cs = new ContatoServico();
        cs.cadastrar(c);
        cs.cadastrar(c1);
        cs.cadastrar(c2);
        cs.cadastrar(c3);
        cs.cadastrar(c4);

        System.out.println(cs.listarTodos());
        try {
            System.out.println(cs.listarUm(4));
            System.out.println(cs.listarUm(5));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}