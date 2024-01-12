package controller;

import model.entidades.ProfissionalMentor;

import java.util.ArrayList;

public class ProfissionalMentorServico {
    private ArrayList<ProfissionalMentor> lista = new ArrayList<>();
    UsuarioServico usuarioServico = new UsuarioServico();

    public void cadastrar(ProfissionalMentor profissionalMentor) {
        if (profissionalMentor == null) {
            System.err.println("🚫 O profissional não pode ser nulo!");
        } else {
            lista.add(profissionalMentor);
            usuarioServico.cadastrar(profissionalMentor);
            System.out.println("✅ Profissional Mentor cadastrado!");
        }
    }

    public void listarUm(long id) {
        boolean profissionalMentorEncontrado = false;

        for (ProfissionalMentor profissionalMentor : lista) {
            if (profissionalMentor.getId() == id) {
                System.out.println(profissionalMentor);
                profissionalMentorEncontrado = true;
                break;
            }
        }
        if (!profissionalMentorEncontrado) {
            System.err.println("🚫 Profissional Mentor não encontrado!");
        }
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum Profissional Mentor cadastrado!");
            return;
        }

        for (ProfissionalMentor profissionalMentor : lista) {
            System.out.println(profissionalMentor);
        }
    }

    public void atualizar(long id, ProfissionalMentor profissionalMentorAtualiza) {

        for (int i = 0; i < lista.size(); i++) {
            ProfissionalMentor profissionalMentor = lista.get(i);

            if (profissionalMentor.getId() == id) {
                profissionalMentor.setNome(profissionalMentorAtualiza.getNome());
                profissionalMentor.setCpf(profissionalMentorAtualiza.getCpf());
                profissionalMentor.setDataDeNascimento(profissionalMentorAtualiza.getDataDeNascimento());
                profissionalMentor.setEnderecos(profissionalMentorAtualiza.getEnderecos());
                profissionalMentor.setContatos(profissionalMentorAtualiza.getContatos());
                profissionalMentor.setEmail(profissionalMentorAtualiza.getEmail());
                profissionalMentor.setTipo(profissionalMentorAtualiza.getTipo());
                profissionalMentor.setAreaAtuacao(profissionalMentorAtualiza.getAreaAtuacao());
                profissionalMentor.setNivelExperienciaEnum(profissionalMentorAtualiza.getNivelExperienciaEnum());
                profissionalMentor.setCarteiraDeTrabalho(profissionalMentorAtualiza.getCarteiraDeTrabalho());
                profissionalMentor
                        .setCertificadosDeCapacitacao(profissionalMentorAtualiza.getCertificadosDeCapacitacao());
                System.out.println("✅ Profissional Mentor atualizado!");
                return;
            }
        }

        System.err.println("🚫 Profissional Mentor não encontrado!");
    }

    public void deletar(long id) {
        ProfissionalMentor profissionalMentorDeletar = null;

        for (ProfissionalMentor profissionalMentor : lista) {
            if (profissionalMentor.getId() == id) {
                profissionalMentorDeletar = profissionalMentor;
            }
        }

        if (profissionalMentorDeletar != null) {
            lista.remove(profissionalMentorDeletar);
            usuarioServico.deletar(profissionalMentorDeletar.getId());
            System.out.println("✅ Profissional Mentor removido!");
        } else {
            System.err.println("🚫 Usuário não encontrado!");
        }
    }
}
