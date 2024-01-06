package servicos;

import entidades.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoServico {

    private final ArrayList<Endereco> enderecos = new ArrayList<>();

    public EnderecoServico() {}

    public void cadastrar(Endereco endereco) {
        enderecos.add(endereco);
    }

    public Endereco listarUm(Long id) {
        if (enderecos.isEmpty()) {
            return null;
        }

        for (Endereco endereco : enderecos) {
            if (endereco.getId().equals(id)) {
                return endereco;
            }
        }

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
        }
    }

    public void deletar(Long id) {
        enderecos.removeIf(endereco -> endereco.getId().equals(id));
    }
}

