package com.dbc.model.entities;

import com.dbc.model.enums.AreaAtuacaoEnum;
import com.dbc.model.enums.NivelExperienciaEnum;

public class ProfissionalMentor extends Usuario {
    private Usuario usuario;
    private Long id;
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ProfissionalMentor() {
    }

    public ProfissionalMentor(AreaAtuacaoEnum areaAtuacao, String carteiraDeTrabalho, NivelExperienciaEnum nivelExperienciaEnum) {
        super();
        this.areaAtuacao = areaAtuacao;
        this.carteiraDeTrabalho = carteiraDeTrabalho;
        this.nivelExperienciaEnum = nivelExperienciaEnum;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    @Override
    public String toString() {
        return super.toString() +
                "\n\tMentor Id .............: " + this.id +
                "\n\tArea de Atuacao .......: " + this.areaAtuacao +
                "\n\tNivel de Experiencia ..: " + this.nivelExperienciaEnum +
                "\n\tCarteira de Trabalho ..: " + this.carteiraDeTrabalho +
                "\n";
    }
}
