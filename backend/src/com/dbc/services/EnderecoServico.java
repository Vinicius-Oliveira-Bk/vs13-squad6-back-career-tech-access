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
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            enderecoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            enderecoRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Endereco endereco) {
        try {
            enderecoRepository.atualizar(id, endereco);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            enderecoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}