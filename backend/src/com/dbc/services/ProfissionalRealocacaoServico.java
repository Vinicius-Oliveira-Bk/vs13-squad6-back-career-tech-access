package com.dbc.services;
import java.util.ArrayList;

import com.dbc.model.entities.ProfissionalRealocacao;

public class ProfissionalRealocacaoServico {
    private ArrayList<ProfissionalRealocacao> lista = new ArrayList<>();
    UsuarioServico usuarioServico = new UsuarioServico();

    public void cadastrar(ProfissionalRealocacao profissionalRealocacao) {
        if (profissionalRealocacao == null) {
            System.err.println("🚫 O usuário não pode ser nulo!");
        } else {
            lista.add(profissionalRealocacao);
            usuarioServico.cadastrar(profissionalRealocacao);
            System.out.println("✅ Profissional Realocacao cadastrado!");
        }
    }

    public ProfissionalRealocacao listarUm(Long id) {
        boolean profissionalRealocacaoEncontrado = false;

        for (ProfissionalRealocacao profissionalRealocacao : lista) {
            if (profissionalRealocacao.getId() == id.intValue()) {
                profissionalRealocacaoEncontrado = true;
                return profissionalRealocacao;
            }
        }

        if (!profissionalRealocacaoEncontrado) {
            System.err.println("🚫 Profissional Realocacao não encontrado!");
        }
        return null;
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum Profissional Realocacao cadastrado!");
            return;
        }

        for (ProfissionalRealocacao profissionalRealocacao : lista) {
            System.out.println(profissionalRealocacao);
        }
    }

    public void atualizar(Long id, ProfissionalRealocacao profissionalRealocacaoAtualiza) {
        for (int i = 0; i < lista.size(); i++) {
            ProfissionalRealocacao profissionalRealocacao = lista.get(i);

            if (profissionalRealocacao.getId() == id) {
                profissionalRealocacao.setNome(profissionalRealocacaoAtualiza.getNome());
                profissionalRealocacao.setCpf(profissionalRealocacaoAtualiza.getCpf());
                profissionalRealocacao.setDataNascimento(profissionalRealocacaoAtualiza.getDataNascimento());
                profissionalRealocacao.setEmail(profissionalRealocacaoAtualiza.getEmail());
                profissionalRealocacao.setPlano(profissionalRealocacaoAtualiza.getPlano());
                profissionalRealocacao.setInteresses(profissionalRealocacaoAtualiza.getInteresses());
                profissionalRealocacao.setImagemDocumento(profissionalRealocacaoAtualiza.getImagemDocumento());
                profissionalRealocacao.setControleParental(profissionalRealocacaoAtualiza.getControleParental());
                profissionalRealocacao.setAcessoPcd(profissionalRealocacaoAtualiza.getAcessoPcd());
                profissionalRealocacao.setProfissao(profissionalRealocacaoAtualiza.getProfissao());
                profissionalRealocacao.setObjetivoProfissional(profissionalRealocacaoAtualiza.getObjetivoProfissional());
                System.out.println("✅ Profissional Realocação atualizado!");
                return;
            }
        }

        System.err.println("🚫 Usuário não encontrado!");
    }

    public void remover(Long id) {
        ProfissionalRealocacao profissionalRealocacaoDeletar = null;

        for (ProfissionalRealocacao profissionalRealocacao : lista) {
            if (profissionalRealocacao.getId() == id) {
                profissionalRealocacaoDeletar = profissionalRealocacao;
            }
        }

        if (profissionalRealocacaoDeletar != null) {
            lista.remove(profissionalRealocacaoDeletar);
            usuarioServico.remover(profissionalRealocacaoDeletar.getId());
            System.out.println("✅ Usuário removido!");
        } else {
            System.err.println("🚫 Usuário não encontrado!");
        }
    }
}
