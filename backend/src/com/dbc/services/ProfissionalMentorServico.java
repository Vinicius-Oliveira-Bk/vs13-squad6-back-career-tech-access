package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.repository.ProfissionalMentorRepository;

public class ProfissionalMentorServico {

    private ProfissionalMentorRepository mentorRepository;

    public ProfissionalMentorServico() {
        mentorRepository = new ProfissionalMentorRepository();
    }

    public void cadastrar(ProfissionalMentor mentor) {
        try {
            mentorRepository.cadastrar(mentor);
            System.out.println("✅ Profissional Mentor cadastrado!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
       try {
            mentorRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public ProfissionalMentor listarUm(Long id) {
        try {
            return mentorRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Long id, ProfissionalMentor mentor) {
        try {
            mentorRepository.atualizar(id, mentor);
            System.out.println("✅ Profissional Mentor atualizado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            mentorRepository.remover(id);
            System.out.println("✅ Profissional Mentor deletado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
