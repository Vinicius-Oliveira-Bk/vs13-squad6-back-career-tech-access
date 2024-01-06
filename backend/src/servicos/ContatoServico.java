package servicos;

import entidades.Contato;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ContatoServico {
    private static ArrayList<Contato> lista = new ArrayList<Contato>();

    public void cadastrar(Contato contato) {
        if (contato == null) {
            throw new IllegalArgumentException("O contato não pode ser nulo!");
        }

        lista.add(contato);
        System.out.println("Contato adicionado com sucesso!");
    }

    public Contato listarUm(long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            throw new NoSuchElementException("Não há nenhum contato com o id informado!");
        }

        System.out.println("Contato Encontrado.");
        return lista.stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public ArrayList<Contato> listarTodos() {
        return lista;
    }

    public void atualizar(long id, Contato contatoNovo) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            throw new NoSuchElementException("Não há nenhum contato com o id informado!");
        }

        Contato contato = lista.stream().filter(x -> x.getId() == id).findFirst().get();
        contato.setTipo(contatoNovo.getTipo() != null ? contatoNovo.getTipo() : contato.getTipo());
        contato.setDescricao(contatoNovo.getDescricao() != null ? contatoNovo.getDescricao() : contato.getDescricao());
        contato.setTelefone(contatoNovo.getTelefone() != null ? contatoNovo.getTelefone() : contato.getTelefone());
    }

    public void deletar(long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            throw new NoSuchElementException("Não há nenhum contato com o id informado!");
        }

        lista.remove((int) id);
    }
}
