package entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.TipoUsuarioEnum;
import interfaces.IDocumentacaoPessoal;

public class Cliente extends Usuario implements IDocumentacaoPessoal {
    private String plano;
    private String interesses;
    private String imagemDocumento;
    private boolean controleParental;
    private boolean acessoPcd;

    public Cliente() {}

    public Cliente(long id, String nome, String cpf, String dataDeNascimento, ArrayList<Endereco> enderecos,
            ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, String plano, String interesses,
            String imagemDocumento, boolean controleParental, boolean acessoPcd) {
        super(id, nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo);
        this.plano = plano;
        this.interesses = interesses;
        this.imagemDocumento = imagemDocumento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getInteresses() {
        return interesses;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }

    public String getImagemDocumento() {
        return imagemDocumento;
    }

    public void setImagemDocumento(String imagemDocumento) {
        this.imagemDocumento = imagemDocumento;
    }

    public boolean isControleParental() {
        return controleParental;
    }

    public void setControleParental(boolean controleParental) {
        this.controleParental = controleParental;
    }

    public boolean isAcessoPcd() {
        return acessoPcd;
    }

    public void setAcessoPcd(boolean acessoPcd) {
        this.acessoPcd = acessoPcd;
    }

    @Override
    public boolean validarCPF(String cpf) {
        return false;
    }

    @Override
    public boolean validarIdade(LocalDate dataNascimento) {
        return false;
    }

    @Override
    public boolean validarPlano(String plano) {
        return false;
    }

    @Override
    public boolean validarInteresses(String interesses) {
        return false;
    }

    @Override
    public boolean validarImagemDocumento(String imagemDocumento) {
        return false;
    }

    @Override
    public boolean validarControleParental(boolean controleParental) {
        return false;
    }

    @Override
    public boolean validarAcessoPcd(boolean acessoPcd) {
        return false;
    }
}
