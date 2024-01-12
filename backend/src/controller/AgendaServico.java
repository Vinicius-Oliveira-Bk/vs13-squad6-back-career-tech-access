package controller;

import enums.StatusAgendaEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entities.Agenda;

public class AgendaServico {
    public void cadastrarDisponibilidade(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        Agenda agenda = new Agenda();
        
    }

    public void removerDisponibilidade(long idAgenda) {

    }

    public void agendarHorario(long idAgenda) {

    }

    public void reagendarHorario(long idAgendaAtual) {

    }

    public Agenda listarUm(long idAgenda) {
        return null;
    }

    public ArrayList<Agenda> listarTodos(StatusAgendaEnum statusAgendaEnum) {
        return null;
    }

    public ArrayList<Agenda> listarTodosPorCliente(long idCliente) {
        return null;
    }

    public void cancelarHorario(long idAgenda) {

    }
}