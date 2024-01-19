package br.com.dbc.vemser.services;

import java.util.List;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Pcd;
import br.com.dbc.vemser.repository.PcdRepository;

public class PcdService {
    private PcdRepository pcdRepository;

    public PcdService() {
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
