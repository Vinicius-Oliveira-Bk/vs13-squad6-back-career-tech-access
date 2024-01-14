package com.dbc.model.entities;

import java.time.LocalDate;

public class Usuario {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String senha;
    private Character acessoPcd;
    private Long tipoUsuario;
    private String interesses;
    private String imagemDocumento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", acessoPcd=" + acessoPcd +
                ", tipoUsuario=" + tipoUsuario +
                ", interesses='" + interesses + '\'' +
                ", imagemDocumento='" + imagemDocumento + '\'' +
                '}';
    }
}