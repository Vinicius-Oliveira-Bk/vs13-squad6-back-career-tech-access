package br.com.dbc.vemser.model.entities;

import java.time.LocalDate;

public class Estudante extends Cliente {
    private Cliente cliente;
    private Long id;
    private String matricula;
    private String comprovanteMatricula;
    private String instituicao;
    private String curso;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString() {
        return "\nEstudante" +
                "\nId .....................: " + id +
                "\nMatricula ..............: " + matricula +
                "\nComprovante de Matricula: " + comprovanteMatricula +
                "\nInstituicao ............: " + instituicao +
                "\nCurso ..................: " + curso +
                "\nData de Inicio .........: " + dataInicio +
                "\nData de Termino ........: " + dataTermino +
                "\n";
    }
}