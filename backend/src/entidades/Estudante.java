package entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import enums.TipoEstudanteEnum;
import enums.TipoUsuarioEnum;

public class Estudante extends Cliente {
    // TODO: revisar se matricula é long ou String
    private long matricula;
    private String comprovanteMatricula;
    private TipoEstudanteEnum tipoEstudante;
    private String curso;
    private String instituicao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Estudante() {
    }

    public Estudante(long id, String nome, String cpf, String dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, String plano, String interesses, String imagemDocumento, boolean controleParental, boolean acessoPcd, long matricula, String comprovanteMatricula, TipoEstudanteEnum tipoEstudante, String curso, String instituicao, LocalDate dataInicio, LocalDate dataFim) {
        super(id, nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo, plano, interesses, imagemDocumento, controleParental, acessoPcd);
        this.matricula = matricula;
        this.comprovanteMatricula = comprovanteMatricula;
        this.tipoEstudante = tipoEstudante;
        this.curso = curso;
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

    public TipoEstudanteEnum getTipoEstudante() {
        return tipoEstudante;
    }

    public void setTipoEstudante(TipoEstudanteEnum tipoEstudante) {
        this.tipoEstudante = tipoEstudante;
    }

    public String getCruso() {
        return curso;
    }

    public void setCruso(String curso) {
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
}
