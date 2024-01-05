package entidades;

import interfaces.IDocumentacaoPessoal;

import java.time.LocalDate;
import java.util.Date;

public class Cliente implements IDocumentacaoPessoal {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String plano;
    private String interesses;
    private String imagemDocummento;
    private boolean controleParental;
    private boolean acessoPcd;

    public Cliente(String nome, String cpf, LocalDate dataNascimento, String plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.plano = plano;
        this.interesses = interesses;
        this.imagemDocummento = imagemDocummento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
