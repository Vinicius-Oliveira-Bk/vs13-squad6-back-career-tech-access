package servicos;

import entidades.Contato;
import entidades.Endereco;
import entidades.Usuario;

import java.util.ArrayList;

public class UsuarioServico {

    private ArrayList<Usuario> lista = new ArrayList<>();

    public void cadastrar(Usuario usuario) {
        if (usuario == null) {
            System.err.println("O usuário não pode ser nulo!");
        } else {
            lista.add(usuario);
            System.out.println("Usuário cadastrado!");
        }
    }

    public void listarTodos() {
        for (Usuario usuario : lista) {
            System.out.println(usuario);
        }
    }
    public void listarUm(long id) {
        boolean usuarioEncontrado = false;

        for (Usuario usuario : lista) {
            if (usuario.getId() == id) {
                System.out.println(usuario);
                usuarioEncontrado = true;
                break;
            }
        }
        if (!usuarioEncontrado) {
            System.err.println("Usuário não encontrado!");
        }
    }

    public void atualizar(long id, Usuario usuarioAtualiza) {
        for (int i = 0; i < lista.size(); i++) {
            Usuario usuario = lista.get(i);

            if (usuario.getId() == id) {
                usuario.setNome(usuarioAtualiza.getNome());
                usuario.setCpf(usuarioAtualiza.getCpf());
                usuario.setDataDeNascimento(usuarioAtualiza.getDataDeNascimento());
                usuario.setEmail(usuarioAtualiza.getEmail());
                usuario.setTipo(usuarioAtualiza.getTipo());
            }
        }
    }

    public void deletar(long id) {
        Usuario usuarioDeletar = null;

        for(Usuario usuario : lista) {
            if (usuario.getId() == id) {
                usuarioDeletar = usuario;
            }
        }

        if (usuarioDeletar != null) {
            lista.remove(usuarioDeletar);
            System.out.println("Usuário removido!");
        } else {
            System.err.println("Usuário não encontrado!");
        }
    }

    public boolean vincularContato(Usuario usuario, Contato contato) {
        ArrayList<Contato> contatos = usuario.getContatos() != null ? usuario.getContatos() : new ArrayList<>();
        contatos.add(contato);
        usuario.setContatos(contatos);
        return true;
    }

    public boolean vincularEndereco(Usuario usuario, Endereco endereco) {
        ArrayList<Endereco> enderecos = usuario.getEnderecos() != null ? usuario.getEnderecos() : new ArrayList<>();
        enderecos.add(endereco);
        usuario.setEnderecos(enderecos);
        return true;
    }

}
