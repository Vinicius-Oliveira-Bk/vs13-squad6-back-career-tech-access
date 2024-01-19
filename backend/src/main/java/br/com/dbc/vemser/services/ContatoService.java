package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Contato;
import br.com.dbc.vemser.repository.ContatoRepository;

public class ContatoService {

    private ContatoRepository contatoRepository;

    public ContatoService() {
        contatoRepository = new ContatoRepository();
    }

    public void cadastrar(Contato contato) {
        try {
            contatoRepository.cadastrar(contato);
            System.out.println("✅ Contato Adicionado com sucesso! " + contato);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            contatoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            contatoRepository.listarUm(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Contato contato) {
        try {
            contatoRepository.atualizar(id, contato);
            System.out.println("✅ Contato Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            contatoRepository.remover(id);
            System.out.println("✅ Contato Removido com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
