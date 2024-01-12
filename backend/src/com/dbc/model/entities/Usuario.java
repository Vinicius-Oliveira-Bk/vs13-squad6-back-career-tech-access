package com.dbc.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Usuario {
    private long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private String cpf;
    private String email;
    private String senha;
    private Character acessoPcd;
    private Long tipoUsuario;
    private String interesses;
    private String imagemDocumento;

    private ArrayList<Usuario> usuarios;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getAcessoPcd() {
        return acessoPcd;
    }

    public void setAcessoPcd(Character acessoPcd) {
        this.acessoPcd = acessoPcd;
    }

    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", acessoPcd=" + acessoPcd +
                ", tipoUsuario=" + tipoUsuario +
                ", interesses='" + interesses + '\'' +
                ", imagemDocumento='" + imagemDocumento + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }

}
