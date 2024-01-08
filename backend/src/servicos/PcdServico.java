package servicos;

import entidades.Pcd;

import java.util.ArrayList;

public class PcdServico {
    private ArrayList<Pcd> lista = new ArrayList<>();
    UsuarioServico usuarioServico = new UsuarioServico();

    public void cadastrar(Pcd pcd) {
        if (pcd == null) {
            System.err.println("🚫 O usuário não pode ser nulo!");
        } else {
            lista.add(pcd);
            usuarioServico.cadastrar(pcd);
            System.out.println("✅ Pcd cadastrado!");
        }
    }

    public void listarUm(long id) {
        boolean pcdEncontrado = false;

        for (Pcd pcd : lista) {
            if (pcd.getId() == id) {
                System.out.println(pcd);
                pcdEncontrado = true;
                break;
            }
        }
        if (!pcdEncontrado) {
            System.err.println("🚫 Pcd não encontrado!");
        }
    }

    public void listarTodos() {
        if (lista.isEmpty()) {
            System.err.println("🚫 Nenhum pcd cadastrado!");
            return;
        }

        for (Pcd pcd : lista) {
            System.out.println(pcd);
        }
    }

    public void atualizar(long id, Pcd pcdAtualiza) {

        for (int i = 0; i < lista.size(); i++) {
            Pcd pcd = lista.get(i);

            if (pcd.getId() == id) {
                pcd.setNome(pcdAtualiza.getNome());
                pcd.setCpf(pcdAtualiza.getCpf());
                pcd.setDataDeNascimento(pcdAtualiza.getDataDeNascimento());
                pcd.setEnderecos(pcdAtualiza.getEnderecos());
                pcd.setContatos(pcdAtualiza.getContatos());
                pcd.setEmail(pcdAtualiza.getEmail());
                pcd.setTipo(pcdAtualiza.getTipo());
                pcd.setPlano(pcdAtualiza.getPlano());
                pcd.setInteresses(pcdAtualiza.getInteresses());
                pcd.setImagemDocumento(pcdAtualiza.getImagemDocumento());
                pcd.setControleParental(pcdAtualiza.getControleParental());
                pcd.setAcessoPcd(pcdAtualiza.getAcessoPcd());
                pcd.setTipoDeficiencia(pcdAtualiza.getTipoDeficiencia());
                pcd.setCertificadoDeficienciaGov(pcdAtualiza.getCertificadoDeficienciaGov());
                System.out.println("✅ Usuário atualizado!");
                return;
            }
        }

        System.err.println("🚫 Usuário não encontrado!");
    }

    public void deletar(long id) {
        Pcd pcdDeletar = null;

        for (Pcd pcd : lista) {
            if (pcd.getId() == id) {
                pcdDeletar = pcd;
            }
        }

        if (pcdDeletar != null) {
            lista.remove(pcdDeletar);
            usuarioServico.deletar(pcdDeletar.getId());
            System.out.println("✅ Usuário removido!");
        } else {
            System.err.println("🚫 Usuário não encontrado!");
        }
    }
}