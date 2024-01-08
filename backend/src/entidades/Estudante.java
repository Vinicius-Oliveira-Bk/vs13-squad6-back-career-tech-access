package entidades;

import enums.PlanoEnum;
import enums.TipoEstudanteEnum;
import enums.TipoUsuarioEnum;

import java.time.LocalDate;
import java.util.ArrayList;

public class Estudante extends Cliente {
    private String matricula;
    private String comprovanteMatricula;
    private TipoEstudanteEnum tipoEstudante;
    private String curso;
    private String instituicao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Estudante() {
    }

    public Estudante(String matricula, String comprovanteMatricula, TipoEstudanteEnum tipoEstudante, String curso, String instituicao, LocalDate dataInicio, LocalDate dataFim) {
        this.matricula = matricula;
        this.comprovanteMatricula = comprovanteMatricula;
        this.tipoEstudante = tipoEstudante;
        this.curso = curso;
        this.instituicao = instituicao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Estudante(String nome, String cpf, LocalDate dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, PlanoEnum plano, String interesses, String imagemDocummento, boolean controleParental, boolean acessoPcd, String matricula, String comprovanteMatricula, TipoEstudanteEnum tipoEstudante, String curso, String instituicao, LocalDate dataInicio, LocalDate dataFim) {

        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo, plano, interesses, imagemDocummento, controleParental, acessoPcd);
        this.matricula = matricula;
        this.comprovanteMatricula = comprovanteMatricula;
        this.tipoEstudante = tipoEstudante;
        this.curso = curso;
        this.instituicao = instituicao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getComprovanteMatricula() {
        return comprovanteMatricula;
    }

    public void setComprovanteMatricula(String comprovanteMatricula) {
        this.comprovanteMatricula = comprovanteMatricula;
    }

    public TipoEstudanteEnum getTipoEstudante() {
        return tipoEstudante;
    }

    public void setTipoEstudante(TipoEstudanteEnum tipoEstudante) {
        this.tipoEstudante = tipoEstudante;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public String toString() {
        return super.toString() +
                "\n\tMatricula .............: " + this.matricula +
                "\n\tTipo de Estudante .....: " + this.tipoEstudante +
                "\n\tCurso .................: " + this.curso +
                "\n\tInstituicao ...........: " + this.instituicao +
                "\n\tData Inicio ...........: " + this.dataInicio +
                "\n\tData Fim ..............: " + this.dataFim +
                "\n";
    }
}
