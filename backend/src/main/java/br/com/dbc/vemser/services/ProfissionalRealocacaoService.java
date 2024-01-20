package br.com.dbc.vemser.services;

import java.util.ArrayList;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.ProfissionalRealocacao;
import br.com.dbc.vemser.repository.ProfissionalRealocacaoRepository;

public class ProfissionalRealocacaoService {
    private ArrayList<ProfissionalRealocacao> lista = new ArrayList<>();
    private ProfissionalRealocacaoRepository profRealocRepository;

    public ProfissionalRealocacaoService() {
        profRealocRepository = new ProfissionalRealocacaoRepository();
    }

    public void cadastrar(ProfissionalRealocacao profissionalRealocacao) {
        if (profissionalRealocacao == null) {
            System.err.println("❌ O usuário não pode ser nulo!");
        }

        try {
            lista.add(profissionalRealocacao);
            profRealocRepository.create(profissionalRealocacao);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }

        System.out.println("\n✅ Profissional Realocacao cadastrado!");
    }

    public void listarUm(Long id) {
        try {
            profRealocRepository.getById(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("❌ Nenhum Profissional Realocacao cadastrado!");
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
    }

    public void remover(Long id) {
        try {
            ProfissionalRealocacao profissionalRealocacaoDeletar = profRealocRepository.getById(id);

            if (profissionalRealocacaoDeletar == null)
            System.err.println("❌ Usuário não encontrado!");

            lista.remove(profissionalRealocacaoDeletar);
            profRealocRepository.delete(profissionalRealocacaoDeletar.getId());
            System.out.println("✅ Usuário removido!");
        } catch (BancoDeDadosException e) {
            System.err.println("❌ Erro ao remover usuário: " + e.getMessage());
        }
    }
}