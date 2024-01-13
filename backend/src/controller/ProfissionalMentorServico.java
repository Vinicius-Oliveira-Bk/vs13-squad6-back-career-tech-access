package controller;

import exceptions.BancoDeDadosException;
import model.entidades.ProfissionalMentor;
import repository.ProfissionalMentorRepository;

public class ProfissionalMentorServico {

    private ProfissionalMentorRepository mentorRepository;

    public ProfissionalMentorServico(ProfissionalMentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public void cadastrar(ProfissionalMentor mentor) {
        try {
            mentorRepository.cadastrar(mentor);
            System.out.println("✅ Profissional Mentor cadastrado!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
       try {
            mentorRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarUm(Long id) {
        try {
            mentorRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Integer id, ProfissionalMentor mentor) {
        try {
            mentorRepository.atualizar(id, mentor);
            System.out.println("✅ Profissional Mentor atualizado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id) {
        try {
            mentorRepository.remover(id);
            System.out.println("✅ Profissional Mentor deletado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
