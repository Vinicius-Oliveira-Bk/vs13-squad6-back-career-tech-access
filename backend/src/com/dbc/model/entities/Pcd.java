package com.dbc.model.entities;

import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoUsuarioEnum;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pcd extends Cliente {
    private Cliente cliente;
    private Long id;
    private String tipoDeficiencia;
    private String certificadoDeficienciaGov;

    public Pcd() {
    }

    public Pcd(Cliente cliente, String tipoDeficiencia, String certificadoDeficienciaGov) {
        this.cliente = cliente;
        this.tipoDeficiencia = tipoDeficiencia;
        this.certificadoDeficienciaGov = certificadoDeficienciaGov;
    }

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
                "\n\tPcd Id .............: " + this.id +
                "\n\tTipo de Deficiencia ...: " + this.tipoDeficiencia +
                "\n\tCertificado de Deficiencia do Governo ...: " + this.certificadoDeficienciaGov +
                "\n";
    }
}
