package com.dbc.model.entities;

import java.util.concurrent.atomic.AtomicInteger;

import com.dbc.model.enums.TipoEnum;

public class Contato {
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String descricao;
    private String telefone;
    private TipoEnum tipo;

    public Contato() {
        this.id = count.incrementAndGet();
    }

    public Contato(String descricao, String telefone, TipoEnum tipo) {
        this.id = count.incrementAndGet();
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
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
