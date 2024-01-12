package com.dbc.services;

import com.dbc.model.entities.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoServico {
    private final ArrayList<Endereco> enderecos = new ArrayList<>();

    public void cadastrar(Endereco endereco) {
        if (endereco == null) {
            System.err.println("🚫 O endereço não pode ser nulo!");
            return;
        }

        enderecos.add(endereco);
        System.out.println("\n✅ Endereço cadastrado!\n");
    }

    public Endereco listarUm(Long id) {
        if (enderecos.isEmpty()) {
            System.err.println("🚫 A lista de endereços está vazia!");
            return null;
        }

        for (Endereco endereco : enderecos) {
            if (endereco.getId().equals(id)) {
                return endereco;
            }
        }

        System.err.println("🚫 Endereço não encontrado!");
        return null;
    }

    public List<Endereco> listarTodos() {
        return enderecos;
    }

    public void atualizar(Long id, Endereco endereco) {
        Endereco enderecoExistente = listarUm(id);

        if (enderecoExistente != null) {
            enderecoExistente.setLogradouro(endereco.getLogradouro());
            enderecoExistente.setNumero(endereco.getNumero());
            enderecoExistente.setCep(endereco.getCep());
            enderecoExistente.setCidade(endereco.getCidade());
            enderecoExistente.setEstado(endereco.getEstado());
            enderecoExistente.setPais(endereco.getPais());
            enderecoExistente.setTipo(endereco.getTipo());
            System.out.println("✅ Endereço atualizado!");
        }

        System.err.println("🚫 Endereço não encontrado!");
    }

    public void deletar(Long id) {
        if (enderecos.isEmpty()) {
            System.err.println("🚫 A lista de endereços está vazia!");
            return;
        }

        enderecos.removeIf(endereco -> endereco.getId().equals(id));
        System.out.println("✅ Endereço deletado!");
    }
}
