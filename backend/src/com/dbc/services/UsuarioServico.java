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

    public void adicionarUsuario(Usuario usuario) {
        try {

            if (usuario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Usuario usuarioAdicionado = usuarioRepository.adicionar(usuario);
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
    public void removerUsuario(Integer id) {
        try {
            boolean conseguiuRemover = usuarioRepository.remover(id);
            System.out.println("\nUsuário removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarUsuario(Integer id, Usuario usuario) {
        try {
            boolean conseguiuEditar = usuarioRepository.editar(id, usuario);
            System.out.println("\nUsuário editado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarTodosUsuarios() {
        try {
            List<Usuario> listar = usuarioRepository.listar();
            for (Usuario usuario : listar) {
                System.out.println(usuario.toString());
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void obterUsuarioPorId(Integer idUsuario) {
        try {
            Usuario usuario = usuarioRepository.obterPorId(idUsuario);

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

}
