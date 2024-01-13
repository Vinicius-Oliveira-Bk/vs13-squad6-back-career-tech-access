package com.dbc.services;

import java.util.ArrayList;
import com.dbc.model.entities.Contato;

public class ContatoServico {
    private static ArrayList<Contato> lista = new ArrayList<>();

    public void cadastrar(Contato contato) {
        lista.add(contato);
        System.out.println("✅ Contato adicionado com sucesso!");
    }

    public Contato listarUm(Long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            System.err.println("🚫 Não há nenhum contato com o id informado!");
            return null;
        }
        System.out.println("✅ Contato Encontrado.");
        return lista.stream().filter(x -> x.getId() == id).findFirst().get();
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

    public void atualizar(Long id, Contato contatoNovo) {
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

    public void remover(Long id) {
        if (lista.stream().filter(x -> x.getId() == id).findFirst().isEmpty()) {
            System.err.println("🚫 Não há nenhum contato com o id informado!");
            return;
        }
        
        lista.remove(id.intValue());
        System.out.println("✅ Contato removido com sucesso!");
    }
}
