package com.dbc.model.entities;

import com.dbc.interfaces.IVinculosUsuario;
import com.dbc.model.enums.TipoUsuarioEnum;
import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario implements IVinculosUsuario {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private Character acessoPcd;
    private TipoUsuarioEnum tipoUsuario;
    private String interesses;
    private ArrayList<Contato> contatos;
    private ArrayList<Endereco> enderecos;
    private String imagemDocumento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getAcessoPcd() {
        return acessoPcd;
    }

    public void setAcessoPcd(Character acessoPcd) {
        this.acessoPcd = acessoPcd;
    }

    public TipoUsuarioEnum getTipo() {
        return tipoUsuario;
    }

    public void setTipo(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "\nUsuario" +
                "\n\tId ....................: " + this.id +
                "\n\tNome ..................: " + this.nome +
                "\n\tData de Nascimento ....: " + this.dataNascimento +
                "\n\tCPF ...................: " + this.cpf +
                "\n\tEmail .................: " + this.email +
                "\n\tAcesso PCD ............: " + this.acessoPcd +
                "\n\tTipo de Usuario .......: " + this.tipoUsuario +
                "\n\tInteresses ............: " + this.interesses +
                "\n\tImagem Documento ......: " + this.imagemDocumento +
                "\n";
    }

    public boolean vincularContato(Usuario usuario, Contato contato) {
        ArrayList<Contato> contatos = usuario.getContatos() != null ? usuario.getContatos() : new ArrayList<>();
        contatos.add(contato);
        usuario.setContatos(contatos);
        System.out.println("✅ Contato vinculado!");
        return true;
    }

    public boolean vincularEndereco(Usuario usuario, Endereco endereco) {
        ArrayList<Endereco> enderecos = usuario.getEnderecos() != null ? usuario.getEnderecos() : new ArrayList<>();
        enderecos.add(endereco);
        usuario.setEnderecos(enderecos);
        System.out.println("✅ Endereço vinculado!");
        return true;
    }
}