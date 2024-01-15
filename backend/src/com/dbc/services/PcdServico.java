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
            if (pcd.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }
            Pcd pcdAdicionado = pcdRepository.cadastrar(pcd);
            System.out.println("pcd adicinado com sucesso! " + pcdAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Pcd listarUm(Long idPcd) {
        try {
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
            boolean conseguiuEditar = pcdRepository.atualizar(id, pcd);
            System.out.println("pcd editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            boolean conseguiuRemover = pcdRepository.remover(id);
            System.out.println("pcd removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
