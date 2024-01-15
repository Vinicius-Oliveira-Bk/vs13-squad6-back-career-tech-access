package com.dbc.services;

import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Pcd;
import com.dbc.repository.PcdRepository;

public class PcdServico {
    private PcdRepository pcdRepository;

    public PcdServico() {
        pcdRepository = new PcdRepository();
    }

    public void cadastrar(Pcd pcd) {
        try {
            pcdRepository.cadastrar(pcd);
            System.out.println("\n✅ Pcd adicionado com sucesso!");
        } catch (BancoDeDadosException e) {
            System.out.println("\n❌ ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("\n❌ ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Pcd listarUm(Long idPcd) {
        try {
            System.out.println("\n✅ Pcd encontrado com sucesso!");
            return pcdRepository.listarUm(idPcd);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listarTodos() {
        try {
            List<Pcd> listar = pcdRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Pcd pcd) {
        try {
            pcdRepository.atualizar(id, pcd);
            System.out.println("\n✅ Pcd editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            pcdRepository.remover(id);
            System.out.println("\n✅ Pcd removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
