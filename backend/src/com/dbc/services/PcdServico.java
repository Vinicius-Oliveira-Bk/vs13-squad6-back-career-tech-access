package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.entities.Pcd;
import com.dbc.repository.ClienteRepository;
import com.dbc.repository.PcdRepository;

import java.util.List;

public class PcdServico {
    private PcdRepository pcdRepository;

    public PcdServico() {
        pcdRepository = new PcdRepository();
    }
    public void adicionarPcd(Pcd pcd) {
        try {

            if (pcd.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Pcd pcdAdicionado = pcdRepository.adicionar(pcd);
            System.out.println("pcd adicinado com sucesso! " + pcdAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void removerPcd(Integer id) {
        try {
            boolean conseguiuRemover = pcdRepository.remover(id);
            System.out.println("pcd removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void editarPcd(Integer id, Pcd pcd) {
        try {
            boolean conseguiuEditar = pcdRepository.editar(id, pcd);
            System.out.println("pcd editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarPcds() {
        try {
            List<Pcd> listar = pcdRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Cliente listarUmPcd(long idPcd) {
        try {
            return pcdRepository.listarUm((int) idPcd);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
