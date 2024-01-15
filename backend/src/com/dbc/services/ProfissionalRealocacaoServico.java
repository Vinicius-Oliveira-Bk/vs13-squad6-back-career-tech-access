package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.ProfissionalRealocacao;
import com.dbc.repository.ProfissionalRealocacaoRepository;

public class ProfissionalRealocacaoServico {

    private ProfissionalRealocacaoRepository profissionalRealocacaoRepository;

    public ProfissionalRealocacaoServico() {
        profissionalRealocacaoRepository = new ProfissionalRealocacaoRepository();
    }

    public void cadastrar(ProfissionalRealocacao profissionalRealocacao) {
        try {
            profissionalRealocacaoRepository.cadastrar(profissionalRealocacao);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            profissionalRealocacaoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            profissionalRealocacaoRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, ProfissionalRealocacao profissionalRealocacao) {
        try {
            profissionalRealocacaoRepository.atualizar(id, profissionalRealocacao);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            profissionalRealocacaoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}