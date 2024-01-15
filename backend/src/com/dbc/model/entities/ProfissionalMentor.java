package com.dbc.model.entities;

import java.util.ArrayList;

import com.dbc.interfaces.IDocumentacaoProfissional;
import com.dbc.model.enums.AreaAtuacaoEnum;
import com.dbc.model.enums.NivelExperienciaEnum;
import com.dbc.model.enums.TipoUsuarioEnum;

public class ProfissionalMentor extends Usuario implements IDocumentacaoProfissional {
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
    private ArrayList<String> certificadosDeCapacitacao;
    private boolean documentosValidados = false;

    public ProfissionalMentor() {
    }

    public ProfissionalMentor(TipoUsuarioEnum tipo, AreaAtuacaoEnum areaAtuacao,
            NivelExperienciaEnum nivelExperienciaEnum,
            String carteiraDeTrabalho, ArrayList<String> certificadosDeCapacitacao) {
        super();
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

    public void setCertificadosDeCapacitacao(String certificadosDeCapacitacao) {
        this.certificadosDeCapacitacao.add(certificadosDeCapacitacao);
    }

    public boolean isDocumentosValidados() {
        return documentosValidados;
    }

    @Override
    public boolean validarDocumentos() {
        return true;
    }

    @Override
    public void adicionarCertificados(String certificados) {
        certificadosDeCapacitacao.add(certificados);
        System.out.println("Certificado adicionado com sucesso!");
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tArea de Atuacao .......: " + this.areaAtuacao +
                "\n\tNivel de Experiencia ..: " + this.nivelExperienciaEnum +
                "\n\tCarteira de Trabalho ..: " + this.carteiraDeTrabalho +
                "\n\tDocumentos Validados ..: " + (this.documentosValidados ? "SIM" : "NÃO") +
                "\n";
    }

    public void setId(Long id) {
    }
}
