package controller;

import exceptions.BancoDeDadosException;
import entities.Cliente;
import repository.ClienteRepository;

import java.util.List;

public class ClienteServico {
    private ClienteRepository clienteRepository;

    public ClienteServico() {
        clienteRepository = new ClienteRepository();
    }

    // Criação de um objeto Cliente
    public void adicionarCliente(Cliente cliente) {
        try {
            // Adicione aqui suas validações, se necessário
            Cliente clienteAdicionado = clienteRepository.cadastrar(cliente);
            System.out.println("Cliente adicionado com sucesso! " + clienteAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Remoção de um objeto Cliente
    public void removerCliente(Integer id) {
        try {
            boolean conseguiuRemover = clienteRepository.remover(id);
            System.out.println("Cliente removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // Atualização de um objeto Cliente
    public void editarCliente(Integer id, Cliente cliente) {
        try {
            boolean conseguiuEditar = clienteRepository.editar(id, cliente);
            System.out.println("Cliente editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // Leitura de todos os Clientes
    public void listarClientes() {
        try {
            List<Cliente> listar = clienteRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}