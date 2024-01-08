package entidades;

import enums.PlanoEnum;
import enums.TipoUsuarioEnum;
import interfaces.IDocumentacaoPessoal;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Cliente extends Usuario implements IDocumentacaoPessoal {
    private PlanoEnum plano;
    private String interesses;
    private String imagemDocummento;
    private boolean controleParental;
    private boolean acessoPcd;

    public Cliente() {}

    public Cliente(PlanoEnum plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd) {
        this.plano = plano;
        this.interesses = interesses;
        this.imagemDocummento = imagemDocummento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, PlanoEnum plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd) {
        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo);

        this.plano = plano;
        this.interesses = interesses;
        this.imagemDocummento = imagemDocummento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public PlanoEnum getPlano() {
        return plano;
    }

    public void setPlano(PlanoEnum plano) {
        this.plano = plano;
    }

    public String getInteresses() {
        return interesses;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }

    public String getImagemDocummento() {
        return imagemDocummento;
    }

    public void setImagemDocummento(String imagemDocummento) {
        this.imagemDocummento = imagemDocummento;
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
        String cpfNumerico = cpf.replaceAll("[^\\d]", "");

        if (cpfNumerico.length() == 11) {
            return true;
        } else {
            System.err.println("Erro: CPF deve conter exatamente 11 dígitos.");
            return false;
        }
    }

    @Override
    public boolean validarIdade(LocalDate dataNascimento, boolean cadastroResponsavel) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataDezoitoAnosAtras = dataAtual.minusYears(18);

        if (!dataNascimento.isAfter(dataDezoitoAnosAtras)) {
            return true;
        } else {
            if (!cadastroResponsavel) {
                // Realizar o cadastro do responsável
                System.err.println("É necessário cadastrar o responsável antes de prosseguir.");
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean validarPlano(String plano) {
        return plano != null && !plano.isEmpty();
    }

    @Override
    public boolean validarInteresses(String interesses) {
        return interesses != null && !interesses.isEmpty();
    }

    @Override
    public boolean validarImagemDocumento(String imagemDocumento) {
        return imagemDocumento != null && !imagemDocumento.isEmpty();
    }

    @Override
    public boolean validarControleParental(boolean controleParental) {
        return true;
    }

    @Override
    public boolean validarAcessoPcd(boolean acessoPcd) {
        return true;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n\tPlano .................: " + this.plano +
                "\n\tInteresses ............: " + this.interesses +
                "\n\tControle Parental .....: " + (this.controleParental ? "Sim" : "Nao")  +
                "\n\tAcesso Pcd ............: " + (this.acessoPcd  ? "Sim" : "Nao");
    }

}