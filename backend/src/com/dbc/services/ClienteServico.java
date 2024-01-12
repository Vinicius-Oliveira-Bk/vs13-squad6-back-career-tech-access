package com.dbc.services;

import com.dbc.model.entities.Cliente;
import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoUsuarioEnum;

import java.util.ArrayList;

public class ClienteServico {
    private ArrayList<Cliente> lista = new ArrayList<>();

    public void cadastrar(Cliente cliente) {
        if (cliente == null) {
            System.err.println("🚫 O usuário não pode ser nulo!");
        } else {
            lista.add(cliente);
            System.out.println("✅ Cliente cadastrado!");
        }
    }

    public Cliente listarUm(long id) {
        for (Cliente cliente : lista) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum usuário cadastrado!");
            return;
        }

        for (Cliente cliente : lista) {
            System.out.println(cliente);
        }
    }

    public void listarTodosPorTipo(TipoUsuarioEnum tipoUsuario) {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum usuário cadastrado!");
            return;
        }

        if (tipoUsuario == null) {
            System.err.println("🚫 Tipo de usuário não pode ser nulo!");
            return;
        }

        lista.stream()
                .filter(cliente -> cliente.getTipo() == tipoUsuario)
                .forEach(System.out::println);
    }

    public void atualizar(long id, Cliente clienteAtualiza) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente cliente = lista.get(i);
            if (cliente.getId() == id) {
                cliente.setPlano(clienteAtualiza.getPlano());
                cliente.setInteresses(clienteAtualiza.getInteresses());
                System.out.println("✅ Cliente atualizado!");
            }
        }
        System.err.println("🚫 Cliente não encontrado!");
    }

    public void deletar(long id) {  
        Cliente clienteDeletar = null;

        for (Cliente cliente : lista) {
            if (cliente.getId() == id) {
                clienteDeletar = cliente;
            }
        }

        if (clienteDeletar != null) {
            lista.remove(clienteDeletar);
            System.out.println("✅ Cliente removido!");
        } else {
            System.err.println("🚫 Cliente não encontrado!");
        }
    }
}
