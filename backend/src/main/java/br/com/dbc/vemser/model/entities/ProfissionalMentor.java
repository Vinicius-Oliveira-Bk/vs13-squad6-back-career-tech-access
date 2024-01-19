package br.com.dbc.vemser.model.entities;

import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;

public class ProfissionalMentor extends Usuario {
    private Usuario usuario;
    private Cliente cliente;
    private AreaAtuacaoEnum areaAtuacao;
    private NivelExperienciaEnum nivelExperienciaEnum;
    private String carteiraDeTrabalho;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProfissionalMentor() {
    }

    public ProfissionalMentor(TipoUsuarioEnum tipo, AreaAtuacaoEnum areaAtuacao, NivelExperienciaEnum nivelExperienciaEnum, String carteiraDeTrabalho) {
        super();
        this.areaAtuacao = areaAtuacao;
        this.nivelExperienciaEnum = nivelExperienciaEnum;
        this.carteiraDeTrabalho = carteiraDeTrabalho;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tArea de Atuacao .......: " + this.areaAtuacao +
                "\n\tNivel de Experiencia ..: " + this.nivelExperienciaEnum +
                "\n\tCarteira de Trabalho ..: " + this.carteiraDeTrabalho +
                "\n";
    }

    public void setId(Long id) {
        super.setId(id);
    }
}
