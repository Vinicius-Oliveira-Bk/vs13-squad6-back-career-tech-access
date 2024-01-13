package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.entities.Usuario;
import com.dbc.repository.ClienteRepository;
import com.dbc.repository.UsuarioRepository;

import java.util.List;

public class ClienteServico {
    private ClienteRepository clienteRepository;

    public ClienteServico() {
        clienteRepository = new ClienteRepository();
    }
    public void adicionarCliente(Cliente cliente) {
        try {

            if (cliente.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Usuario clienteAdicionado = clienteRepository.adicionar(cliente);
            System.out.println("cliente adicinado com sucesso! " + clienteAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void removerCliente(Integer id) {
        try {
            boolean conseguiuRemover = clienteRepository.remover(id);
            System.out.println("cliente removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void editarCliente(Integer id, Cliente cliente) {
        try {
            boolean conseguiuEditar = clienteRepository.editar(id, cliente);
            System.out.println("cliente editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    public void listarCliente() {
        try {
            List<Cliente> listar = clienteRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}
