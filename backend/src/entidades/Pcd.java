package entidades;

import enums.TipoUsuarioEnum;

import java.util.ArrayList;

public class Pcd extends Cliente {
    private String tipoDeficiencia;
    private String certificadoDeficienciaGov;

    public Pcd() {
    }

    public Pcd(String tipoDeficiencia, String certificadoDeficienciaGov) {
        this.tipoDeficiencia = tipoDeficiencia;
        this.certificadoDeficienciaGov = certificadoDeficienciaGov;
    }

    public Pcd(String nome, String cpf, String dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, String plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd, String tipoDeficiencia, String certificadoDeficienciaGov) {
        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo, plano, interesses, imagemDocummento, controleParental, acessoPcd);
        this.tipoDeficiencia = tipoDeficiencia;
        this.certificadoDeficienciaGov = certificadoDeficienciaGov;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public String getCertificadoDeficienciaGov() {
        return certificadoDeficienciaGov;
    }

    public void setCertificadoDeficienciaGov(String certificadoDeficienciaGov) {
        this.certificadoDeficienciaGov = certificadoDeficienciaGov;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tTipo de Deficiencia ...: " + this.tipoDeficiencia +
                "\n";
    }
}
