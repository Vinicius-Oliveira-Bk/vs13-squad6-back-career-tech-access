package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Usuario;
import com.dbc.repository.UsuarioRepository;

import java.util.List;

public class UsuarioServico {
    private UsuarioRepository usuarioRepository;

    public UsuarioServico() {
        usuarioRepository = new UsuarioRepository();
    }

    public void cadastrar(Usuario usuario) {
        try {

            if (usuario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Usuario usuarioAdicionado = usuarioRepository.cadastrar(usuario);
            System.out.println("\nUsuário adicinado com sucesso!\n");
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // remoção
    public void remover(Long id) {
        try {
            boolean conseguiuRemover = usuarioRepository.remover(id);
            System.out.println("\nUsuário removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void atualizar(Long id, Usuario usuario) {
        try {
            boolean conseguiuEditar = usuarioRepository.atualizar(id, usuario);
            System.out.println("\nUsuário editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
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

    public Usuario listarUm(Long idUsuario) {
        try {
            Usuario usuario = usuarioRepository.listarUm(idUsuario);

            if (usuario != null) {
                System.out.println("\nUsuário encontrado!\n");
                return usuario;
            } else {
                System.err.println("\nUsuário não encontrado com o ID: " + idUsuario + "\n");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
}
