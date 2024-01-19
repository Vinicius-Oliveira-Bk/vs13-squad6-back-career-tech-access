package br.com.dbc.vemser.services;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.repository.EnderecoRepository;

public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService() {
        enderecoRepository = new EnderecoRepository();
    }

    public void cadastrar(Endereco endereco) {
        try {
            enderecoRepository.cadastrar(endereco);
            System.out.println("\n✅ Endereco Adicionado com sucesso! " + endereco);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            enderecoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarUm(Long id) {
        try {
            enderecoRepository.listarUm(id);
            System.out.println("\n✅ Endereco Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Endereco endereco) {
        try {
            enderecoRepository.atualizar(id, endereco);
            System.out.println("\n✅ Endereco Editado com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            enderecoRepository.remover(id);
            System.out.println("\n✅ Endereco Removido com Sucesso");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}