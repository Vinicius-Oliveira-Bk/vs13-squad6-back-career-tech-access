package entidades;

import enums.tipoEstudanteEnum;

import java.time.LocalDate;

public class Estudante extends Cliente {
    private long matricula;
    private String comprovanteMatricula;
    private tipoEstudanteEnum tipoEstudante;
    private String cruso;
    private String instituicao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Estudante(String nome, String cpf, LocalDate dataNascimento, String plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd, long matricula, String comprovanteMatricula, tipoEstudanteEnum tipoEstudante, String cruso, String instituicao, LocalDate dataInicio, LocalDate dataFim) {
        super(nome, cpf, dataNascimento, plano, interesses, imagemDocummento, controleParental, acessoPcd);
        this.matricula = matricula;
        this.comprovanteMatricula = comprovanteMatricula;
        this.tipoEstudante = tipoEstudante;
        this.cruso = cruso;
        this.instituicao = instituicao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getComprovanteMatricula() {
        return comprovanteMatricula;
    }

    public void setComprovanteMatricula(String comprovanteMatricula) {
        this.comprovanteMatricula = comprovanteMatricula;
    }

    public tipoEstudanteEnum getTipoEstudante() {
        return tipoEstudante;
    }

    public void setTipoEstudante(tipoEstudanteEnum tipoEstudante) {
        this.tipoEstudante = tipoEstudante;
    }

    public String getCruso() {
        return cruso;
    }

    public void setCruso(String cruso) {
        this.cruso = cruso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
