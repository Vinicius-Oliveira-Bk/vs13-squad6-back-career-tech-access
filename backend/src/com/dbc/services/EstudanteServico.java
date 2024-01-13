package com.dbc.services;

import com.dbc.model.entities.Estudante;

import java.util.ArrayList;

public class EstudanteServico {
    private ArrayList<Estudante> lista = new ArrayList<>();
    UsuarioServico usuarioServico = new UsuarioServico();
    public void cadastrar(Estudante estudante) {
        if (estudante == null) {
            System.err.println("🚫 O usuário não pode ser nulo!");
        } else {
            lista.add(estudante);
            usuarioServico.cadastrar(estudante);
            System.out.println("✅ Estudante cadastrado!");
        }
    }

    public void listarUm(Long id) {
        boolean estudanteEncontrado = false;

        for (Estudante estudante : lista) {
            if (estudante.getId() == id.intValue()) {
                System.out.println(estudante);
                estudanteEncontrado = true;
                break;
            }
        }
        if (!estudanteEncontrado) {
            System.err.println("🚫 Estudante não encontrado!");
        }
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum estudante cadastrado!");
            return;
        }

        for (Estudante estudante : lista) {
            System.out.println(estudante);
        }
    }

    public void atualizar(Long id, Estudante estudanteAtualiza) {

        for (int i = 0; i < lista.size(); i++) {
            Estudante estudante = lista.get(i);

            if (estudante.getId() == id.intValue()) {
                estudante.setNome(estudanteAtualiza.getNome());
                estudante.setCpf(estudanteAtualiza.getCpf());
                estudante.setDataNascimento(estudanteAtualiza.getDataNascimento());
                estudante.setEmail(estudanteAtualiza.getEmail());
                estudante.setPlano(estudanteAtualiza.getPlano());
                estudante.setInteresses(estudanteAtualiza.getInteresses());
                estudante.setImagemDocumento(estudanteAtualiza.getImagemDocumento());
                estudante.setControleParental(estudanteAtualiza.getControleParental());
                estudante.setAcessoPcd(estudanteAtualiza.getAcessoPcd());
                estudante.setMatricula(estudanteAtualiza.getMatricula());
                estudante.setComprovanteMatricula(estudanteAtualiza.getComprovanteMatricula());
                estudante.setTipoEstudante(estudanteAtualiza.getTipoEstudante());
                estudante.setCurso(estudanteAtualiza.getCurso());
                estudante.setInstituicao(estudanteAtualiza.getInstituicao());
                estudante.setDataInicio(estudanteAtualiza.getDataInicio());
                estudante.setDataFim(estudanteAtualiza.getDataFim());
                System.out.println("✅ Estudante atualizado!");
                return;
            }
        }

        System.err.println("🚫 Usuário não encontrado!");
    }

    public void remover(Long id) {
        Estudante estudanteDeletar = null;

        for (Estudante estudante : lista) {
            if (estudante.getId() == id.intValue()) {
                estudanteDeletar = estudante;
            }
        }

        if (estudanteDeletar != null) {
            lista.remove(estudanteDeletar);
            usuarioServico.remover(estudanteDeletar.getId());
            System.out.println("✅ Usuário removido!");
        } else {
            System.err.println("🚫 Usuário não encontrado!");
        }
    }
}