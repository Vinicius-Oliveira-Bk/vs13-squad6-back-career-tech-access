package com.dbc.services;

import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Usuario;
import com.dbc.repository.UsuarioRepository;

public class UsuarioServico {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void cadastrar(Usuario usuario) {
        try {

            if (usuario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            usuarioRepository.cadastrar(usuario);
            System.out.println("\nUsuário adicinado com sucesso!\n");
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarUm(Long idUsuario) {
        try {
            Usuario usuario = usuarioRepository.listarUm(idUsuario);

            if (usuario != null) {
                System.out.println("\nUsuário encontrado!\n");
                System.out.println(usuario.toString());
            } else {
                System.err.println("\nUsuário não encontrado com o ID: " + idUsuario + "\n");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarTodos() {
        try {
            List<Usuario> listar = usuarioRepository.listar();
            for (Usuario usuario : listar) {
                System.out.println(usuario.toString());
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Usuario usuario) {
        try {
            usuarioRepository.atualizar(id, usuario);
            System.out.println("\nUsuário editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            usuarioRepository.remover(id);
            System.out.println("\nUsuário removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
