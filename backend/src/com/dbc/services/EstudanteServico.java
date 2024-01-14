package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Estudante;
import com.dbc.repository.EstudanteRepository;

import java.util.List;

public class EstudanteServico {
    private EstudanteRepository estudanteRepository = new EstudanteRepository();

    public void adicionarEstudante(Estudante estudante) {
        try {

            if (estudante.getComprovanteMatricula() == null) {
                throw new Exception("É preciso anexar o comprovante de matrícula!");
            }

            estudanteRepository.cadastrar(estudante);
            System.out.println("\nEstudante adicinado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // remoção
    public void removerEstudante(Long id) {
        try {
            estudanteRepository.remover(id);
            System.out.println("\nEstudante removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarEstudante(Long id, Estudante estudante) {
        try {
            estudanteRepository.atualizar(id, estudante);
            System.out.println("\nEstudante editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarTodosEstudantes() {
        try {
            List<Estudante> listar = estudanteRepository.listar();
            for (Estudante estudante : listar) {
                System.out.println(estudante.toString());
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void obterEstudantePorId(Long idEstudante) {
        try {
            Estudante estudante = estudanteRepository.listarUm(idEstudante);

            if (estudante != null) {
                System.out.println("\nEstudante encontrado!\n");
                System.out.println(estudante.toString());
            } else {
                System.err.println("\nEstudante não encontrado com o ID: " + idEstudante + "\n");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}
