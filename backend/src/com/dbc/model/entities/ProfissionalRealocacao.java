package com.dbc.model.entities;

import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoUsuarioEnum;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfissionalRealocacao extends Cliente {
    private String profissao;
    private String objetivoProfissional;

    public ProfissionalRealocacao() {}

    public ProfissionalRealocacao(String profissao, String objetivoProfissional) {
        this.profissao = profissao;
        this.objetivoProfissional = objetivoProfissional;
    }

    public ProfissionalRealocacao(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, PlanoEnum plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd, String profissao, String objetivoProfissional) {
        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo, plano, interesses, imagemDocummento, controleParental, acessoPcd);
        this.profissao = profissao;
        this.objetivoProfissional = objetivoProfissional;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getObjetivoProfissional() {
        return objetivoProfissional;
    }

    public void setObjetivoProfissional(String objetivoProfissional) {
        this.objetivoProfissional = objetivoProfissional;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tProfissao .............: " + this.profissao +
                "\n\tObjetivo Profissional .: " + this.objetivoProfissional +
                "\n";
    }
}