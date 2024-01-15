package com.dbc.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;

import com.dbc.interfaces.IDocumentacaoPessoal;
import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoUsuarioEnum;

public class Cliente extends Usuario implements IDocumentacaoPessoal {

    private Usuario usuario;
    private Long id;
    private PlanoEnum plano;
    private TipoUsuarioEnum tipoCliente;
    private String interesses;
    private String imagemDocumento;
    private Character controleParental;
    private Character acessoPcd;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, PlanoEnum plano, String interesses, String imagemDocumento, Character controleParental, Character acessoPcd) {
        super();
        this.plano = plano;
        this.interesses = interesses;
        this.imagemDocumento = imagemDocumento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public Cliente(Long id, PlanoEnum plano, TipoUsuarioEnum tipoCliente, String interesses, String imagemDocumento, Character controleParental, Character acessoPcd) {
        this.id = id;
        this.plano = plano;
        this.tipoCliente = tipoCliente;
        this.interesses = interesses;
        this.imagemDocumento = imagemDocumento;
        this.controleParental = controleParental;
        this.acessoPcd = acessoPcd;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanoEnum getPlano() {
        return plano;
    }

    public void setPlano(PlanoEnum plano) {
        this.plano = plano;
    }

    public TipoUsuarioEnum getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoUsuarioEnum tipoCliente) {
        this.tipoCliente = tipoCliente;
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

    public void setImagemDocumento(String imagemDocummento) {
        this.imagemDocumento = imagemDocummento;
    }

    public Character getControleParental() {
        return controleParental;
    }

    public void setControleParental(Character controleParental) {
        this.controleParental = controleParental;
    }

    public Character getAcessoPcd() {
        return acessoPcd;
    }

    public void setAcessoPcd(Character acessoPcd) {
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
    public Character validarControleParental(boolean controleParental) {
        return null;
    }

    @Override
    public Character validarAcessoPcd(boolean acessoPcd) {
        return null;
    }

    @Override
    public Character validarControleParental(Character controleParental) {
        return controleParental;
    }

    @Override
    public Character validarAcessoPcd(Character acessoPcd) {
        return acessoPcd;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tCliente Id .............: " + this.id +
                "\n\tPlano .................: " + this.plano +
                "\n\tInteresses ............: " + this.interesses +
                "\n\tControle Parental .....: " + (this.controleParental.equals('S') ? "SIM" : "NÃO") +
                "\n\tAcesso Pcd ............: " + (this.acessoPcd.equals('S') ? "SIM" : "NÃO");
    }
}