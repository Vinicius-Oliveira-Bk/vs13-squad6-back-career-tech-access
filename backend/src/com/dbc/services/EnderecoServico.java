package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Endereco;
import com.dbc.repository.EnderecoRepository;

public class EnderecoServico {

    private EnderecoRepository enderecoRepository;

    public EnderecoServico() {
        enderecoRepository = new EnderecoRepository();
    }

    public void cadastrar(Endereco endereco) {
        try {
            enderecoRepository.cadastrar(endereco);
            System.out.println("\n✅ Endereco Adicionado com sucesso! " + endereco);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            enderecoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            enderecoRepository.listarUm(id);
            System.out.println("\n✅ Endereco Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Endereco endereco) {
        try {
            enderecoRepository.atualizar(id, endereco);
            System.out.println("\n✅ Endereco Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            enderecoRepository.remover(id);
            System.out.println("\n✅ Endereco Removido com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}