package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.repository.ProfissionalMentorRepository;

public class ProfissionalMentorService {
    private ProfissionalMentorRepository mentorRepository;

    public ProfissionalMentorService() {
        mentorRepository = new ProfissionalMentorRepository();
    }

    public void cadastrar(ProfissionalMentor mentor) {
        try {
            mentorRepository.cadastrar(mentor);
            System.out.println("\n✅ Profissional Mentor cadastrado!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public ProfissionalMentor listarUm(Long id) {
        try {
            if(id == null) System.out.println("\n❌ ID não pode ser nulo!");
            return mentorRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listarTodos() {
        try {
             mentorRepository.listar().forEach(System.out::println);
         } catch (BancoDeDadosException e) {
             e.printStackTrace();
         }
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