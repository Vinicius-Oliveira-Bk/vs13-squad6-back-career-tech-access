package entidades;

import enums.TipoUsuarioEnum;

import java.util.ArrayList;

public class ProfissionalRealocacao extends Cliente {
    private String profissao;
    private String objetivoProfissional;
    private String areaInteresse;

    public ProfissionalRealocacao() {
    }

    public ProfissionalRealocacao(String profissao, String objetivoProfissional, String areaInteresse) {
        this.profissao = profissao;
        this.objetivoProfissional = objetivoProfissional;
        this.areaInteresse = areaInteresse;
    }

    public ProfissionalRealocacao(String nome, String cpf, String dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, String plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd, String profissao, String areaAtuacao, String objetivoProfissional, String areaInteresse) {
        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo, plano, interesses, imagemDocummento, controleParental, acessoPcd);
        this.profissao = profissao;
        this.objetivoProfissional = objetivoProfissional;
        this.areaInteresse = areaInteresse;
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

    public String getAreaInteresse() {
        return areaInteresse;
    }

    public void setAreaInteresse(String areaInteresse) {
        this.areaInteresse = areaInteresse;
    }

    public String toString() {
        return "ProfissionalRealocacao{" +
                "profissao='" + profissao + '\'' +
                ", objetivoProfissional='" + objetivoProfissional + '\'' +
                ", areaInteresse='" + areaInteresse + '\'' +
                '}';
    }
}