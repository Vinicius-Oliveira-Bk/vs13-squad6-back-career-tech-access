package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.repository.ProfissionalMentorRepository;

import java.util.List;

public class ProfissionalMentorServico {

    private ProfissionalMentorRepository mentorRepository = new ProfissionalMentorRepository();

    public ProfissionalMentorServico() {
        mentorRepository = new ProfissionalMentorRepository();
    }

    public void cadastrar(ProfissionalMentor mentor) {
        try {
            mentorRepository.cadastrar(mentor, Long.valueOf(2));
            System.out.println("\n✅ Profissional Mentor cadastrado!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
       try {
           List<ProfissionalMentor> listar = mentorRepository.listar();
              for (ProfissionalMentor mentor : listar) {
                System.out.println(mentor.toString());
              }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public ProfissionalMentor listarUm(Long id) {
        try {
            ProfissionalMentor mentor = mentorRepository.listarUm(id);

            if (mentor != null) {
                System.out.println("\nMentor encontrado!\n");
                return mentor;
            } else {
                System.err.println("\nMentor não encontrado com o ID: " + id + "\n");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Long id, ProfissionalMentor mentor) {
        try {
            mentorRepository.atualizar(id, mentor);
            System.out.println("\n✅ Profissional Mentor atualizado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            mentorRepository.remover(id);
            System.out.println("\n✅ Profissional Mentor deletado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
