package com.dbc.services;

import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.repository.ClienteRepository;

public class ClienteServico {
    private ClienteRepository clienteRepository;

    public ClienteServico() {
        clienteRepository = new ClienteRepository();
    }

    public void cadastrar(Cliente cliente, Long idUsuario) {
        try {
            Cliente clienteAdicionado = clienteRepository.cadastrar(cliente, idUsuario);
            System.out.println("cliente adicinado com sucesso! " + clienteAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            boolean conseguiuRemover = clienteRepository.remover(id);
            System.out.println("cliente removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Cliente cliente) {
        try {
            boolean conseguiuEditar = clienteRepository.atualizar(id, cliente);
            System.out.println("cliente editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            List<Cliente> listar = clienteRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public Cliente listarUm(Long idCliente) {
        try {
            return clienteRepository.listarUm(idCliente);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validarCliente(Cliente cliente) {
        return true;
    }
}
