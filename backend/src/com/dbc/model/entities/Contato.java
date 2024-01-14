package com.dbc.model.entities;

import com.dbc.model.enums.TipoEnum;

public class Contato {
    private Long id;
    private String descricao;
    private String telefone;
    private TipoEnum tipo;

    public Contato() { }

    public Contato(String descricao, String telefone, TipoEnum tipo) {
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n\tContato Id ............: " + this.id +
               "\n\tDescrição .............: " + this.descricao +
               "\n\tTelefone ..............: " + this.telefone +
               "\n\tTipo de contato .......: " + this.tipo;
    }
}
