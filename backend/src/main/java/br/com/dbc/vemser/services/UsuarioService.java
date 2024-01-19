package br.com.dbc.vemser.services;

import java.util.List;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void cadastrar(Usuario usuario) {
        try {
            usuarioRepository.cadastrar(usuario);
            System.out.println("\n✅ Usuário Adicionado Com Sucesso!\n");
        } catch (BancoDeDadosException e) {
            System.out.println("❌ ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        try {
            usuarioRepository.remover(id);
            System.out.println("\n✅ Usuário removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Long id, Usuario usuario) {
        try {
            usuarioRepository.atualizar(id, usuario);
            System.out.println("\n✅ Usuário editado com sucesso!");
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

    public Usuario listarUm(Long idUsuario) {
        try {
            Usuario usuario = usuarioRepository.listarUm(idUsuario);

            if(usuario == null) {
                System.out.println("\n");
            }

            if (usuario != null) {
                return usuario;
            } else {
                System.err.println("\n❌ Usuário não encontrado com o ID: " + idUsuario + "\n");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
