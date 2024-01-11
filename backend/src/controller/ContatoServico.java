package controller;

import java.util.ArrayList;

import model.entidades.Contato;
import utils.Validacoes;

public class ContatoServico {
    private static ArrayList<Contato> lista = new ArrayList<>();

    public void cadastrar(Contato contato) {
        if (!Validacoes.validarContato(contato)) {
            return;
        }
        lista.add(contato);
        System.out.println("✅ Contato adicionado com sucesso!");
    }

    public void listarUm(long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            System.err.println("🚫 Não há nenhum contato com o id informado!");
            return;
        }
        System.out.println("✅ Contato Encontrado.");
        System.out.println(lista.stream().filter(x -> x.getId() == id).findFirst().get());
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.out.println("🚫 A lista está vazia!");
            return;
        }
        for (Contato contato : lista) {
            System.out.println(contato);
        }
    }

    public void atualizar(long id, Contato contatoNovo) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            System.err.println("🚫 Não há nenhum contato com o id informado!");
            return;
        }

        Contato contato = lista.stream().filter(x -> x.getId() == id).findFirst().get();
        contato.setTipo(contatoNovo.getTipo() != null ? contatoNovo.getTipo() : contato.getTipo());
        contato.setDescricao(contatoNovo.getDescricao() != null ? contatoNovo.getDescricao() : contato.getDescricao());
        contato.setTelefone(contatoNovo.getTelefone() != null ? contatoNovo.getTelefone() : contato.getTelefone());
        System.out.println("✅ Contato editado com sucesso!");
    }

    public void deletar(long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            System.err.println("🚫 Não há nenhum contato com o id informado!");
            return;
        }
        lista.remove((int) id);
        System.out.println("✅ Contato removido com sucesso!");
    }
}
