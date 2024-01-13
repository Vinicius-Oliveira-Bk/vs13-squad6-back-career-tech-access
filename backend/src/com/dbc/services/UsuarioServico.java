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
            System.out.println("usuário adicinado com sucesso! " + usuarioAdicionado);
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
            System.out.println("usuário removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void atualizar(Long id, Usuario usuario) {
        try {
            boolean conseguiuEditar = usuarioRepository.atualizar(id, usuario);
            System.out.println("usuário editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarTodos() {
        try {
            List<Usuario> listar = usuarioRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}
