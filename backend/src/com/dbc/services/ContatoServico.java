package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Contato;
import com.dbc.repository.ContatoRepository;

public class ContatoServico {

    private ContatoRepository contatoRepository;

    public ContatoServico() {
        contatoRepository = new ContatoRepository();
    }

    public void cadastrar(Contato contato) {
        try {
            contatoRepository.cadastrar(contato);
            System.out.println("✅ Contato Adicionado com sucesso! " + contato);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            contatoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            contatoRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Contato contato) {
        try {
            contatoRepository.atualizar(id, contato);
            System.out.println("✅ Contato Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            contatoRepository.remover(id);
            System.out.println("✅ Contato Removido com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
