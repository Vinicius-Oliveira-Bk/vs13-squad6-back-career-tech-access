package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfissionalRealocacao extends Cliente {
    private Cliente cliente;
    private String profissao;
    private String objetivoProfissional;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProfissionalRealocacao() {
    }

    public ProfissionalRealocacao(String profissao, String objetivoProfissional) {
        this.profissao = profissao;
        this.objetivoProfissional = objetivoProfissional;
    }

    public ProfissionalRealocacao(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos,
            ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, PlanoEnum plano, String interesses,
            String imagemDocummento, boolean controleParental, boolean acessoPcd, String profissao,
            String objetivoProfissional) {
        super();
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