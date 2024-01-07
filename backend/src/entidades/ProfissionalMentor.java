package entidades;

import enums.AreaAtuacaoEnum;
import enums.NivelExperienciaEnum;
import enums.TipoUsuarioEnum;
import interfaces.IDocumentacaoProfissional;

import java.util.ArrayList;

public class ProfissionalMentor extends Usuario implements IDocumentacaoProfissional {
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
    private ArrayList<String> certificadosDeCapacitacao;
    private boolean documentosValidados = false;

    public ProfissionalMentor() {}

    public ProfissionalMentor(String nome, String cpf, String dataDeNascimento, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, String email, TipoUsuarioEnum tipo, AreaAtuacaoEnum areaAtuacao, NivelExperienciaEnum nivelExperienciaEnum, String carteiraDeTrabalho, ArrayList<String> certificadosDeCapacitacao) {
        super(nome, cpf, dataDeNascimento, enderecos, contatos, email, tipo);
        this.areaAtuacao = areaAtuacao;
        this.nivelExperienciaEnum = nivelExperienciaEnum;
        this.carteiraDeTrabalho = carteiraDeTrabalho;
        this.certificadosDeCapacitacao = certificadosDeCapacitacao;
    }

    public AreaAtuacaoEnum getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacaoEnum areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public NivelExperienciaEnum getNivelExperienciaEnum() {
        return nivelExperienciaEnum;
    }

    public void setNivelExperienciaEnum(NivelExperienciaEnum nivelExperienciaEnum) {
        this.nivelExperienciaEnum = nivelExperienciaEnum;
    }

    public String getCarteiraDeTrabalho() {
        return carteiraDeTrabalho;
    }

    public void setCarteiraDeTrabalho(String carteiraDeTrabalho) {
        this.carteiraDeTrabalho = carteiraDeTrabalho;
    }

    public ArrayList<String> getCertificadosDeCapacitacao() {
        return certificadosDeCapacitacao;
    }

    public void setCertificadosDeCapacitacao(ArrayList<String> certificadosDeCapacitacao) {
        this.certificadosDeCapacitacao = certificadosDeCapacitacao;
    }

    public boolean isDocumentosValidados() {
        return documentosValidados;
    }

    @Override
    public boolean validarDocumentos() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nArea de Atuacao .......: " + this.areaAtuacao +
                "\nNivel de Experiencia ..: " + this.nivelExperienciaEnum +
                "\nCarteira de Trabalho ..: " + this.carteiraDeTrabalho +
                "\nDocumentos Validados ..: " + this.documentosValidados;
    }
}
