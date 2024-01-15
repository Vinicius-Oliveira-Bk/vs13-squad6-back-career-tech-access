package com.dbc.services;

import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Estudante;
import com.dbc.repository.EstudanteRepository;

public class EstudanteServico {
    private EstudanteRepository estudanteRepository = new EstudanteRepository();

    public void cadastrar(Estudante estudante) {
        try {

            if (estudante.getComprovanteMatricula() == null) {
                throw new Exception("\n❌ É preciso anexar o comprovante de matrícula!");
            }

            estudanteRepository.cadastrar(estudante);
            System.out.println("\n✅ Estudante adicionado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("❌ ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarUm(Long idEstudante) {
        try {
            Estudante estudante = estudanteRepository.listarUm(idEstudante);

            if (estudante == null) {
                System.err.println("\n❌ Estudante não encontrado com o ID: " + idEstudante + "\n");
            } 
            
            System.out.println("\n✅ Estudante encontrado!\n");
            System.out.println(estudante.toString());
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            List<Estudante> listar = estudanteRepository.listar();
            for (Estudante estudante : listar) {
                System.out.println(estudante.toString());
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Estudante estudante) {
        try {
            estudanteRepository.atualizar(id, estudante);
            System.out.println("\n✅ Estudante editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            estudanteRepository.remover(id);
            System.out.println("\n✅ Estudante removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
