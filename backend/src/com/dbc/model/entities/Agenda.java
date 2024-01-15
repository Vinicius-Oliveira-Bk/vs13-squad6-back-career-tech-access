package com.dbc.model.entities;

import com.dbc.model.enums.StatusAgendaEnum;

import java.time.LocalDateTime;

public class Agenda {
    private Long id;
    private Cliente cliente;
    private ProfissionalMentor profissionalMentor;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendaEnum statusAgendaEnum;

    public Agenda() { }

    public Agenda(Cliente cliente, ProfissionalMentor profissionalMentor, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, StatusAgendaEnum statusAgendaEnum) {
        this.cliente = cliente;
        this.profissionalMentor = profissionalMentor;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.statusAgendaEnum = statusAgendaEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProfissionalMentor getProfissionalMentor() {
        return profissionalMentor;
    }

    public void setProfissionalMentor(ProfissionalMentor profissionalMentor) {
        this.profissionalMentor = profissionalMentor;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public StatusAgendaEnum getStatusAgendaEnum() {
        return statusAgendaEnum;
    }

    public void setStatusAgendaEnum(StatusAgendaEnum statusAgendaEnum) {
        this.statusAgendaEnum = statusAgendaEnum;
    }

    @Override
    public String toString() {
        return "\n\tAgenda Id .............: " + this.id +
               "\n\tMentor ................: " + this.profissionalMentor.getUsuario().getNome() +
               "\n\tCliente ...............: " + this.cliente.getUsuario().getNome() +
               "\n\tHorário Inicio ........: " + this.dataHoraInicio.toString() +
               "\n\tHorário Fim ...........: " + this.dataHoraFim.toString() +
               "\n\tStatus Agenda .........: " + this.statusAgendaEnum.name();
    }
}
