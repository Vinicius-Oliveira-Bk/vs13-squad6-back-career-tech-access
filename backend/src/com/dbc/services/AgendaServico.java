package com.dbc.services;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Agenda;
import com.dbc.model.enums.StatusAgendaEnum;
import com.dbc.repository.AgendaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaServico {
    private AgendaRepository agendaRepository;
    public AgendaServico () {
        agendaRepository  = new AgendaRepository();
    }
    public void cadastrarDisponibilidade(Agenda agenda) {
        try {
            List<Agenda> agendamentos = agendaRepository.listar();
            if (!validarHorarioAgendamento(agenda, agendamentos)) {
                return;
            }
            Agenda agendaCadastrada = agendaRepository.cadastrar(agenda);
            System.out.println("✅ Horário cadastrado com sucesso!");
        } catch (BancoDeDadosException e) {
            System.err.println("🚫 ERRO: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerDisponibilidade(Long idAgenda) {
        try {
            boolean horarioRemovido = agendaRepository.remover(idAgenda);
            if (horarioRemovido) {
                System.out.println("✅ Agendamento de id "+idAgenda+" removido com sucesso!");
            };
        } catch (Exception e) {
            System.err.println("🚫 Não foi possível remover o agendamento!");
            e.printStackTrace();
        }
    }

    public void agendarHorario(Long idAgenda) {

    }

    public void reagendarHorario(Long idAgendaAtual) {

    }

    public Agenda listarUm(Long idAgenda) {
        return null;
    }

    public ArrayList<Agenda> listarTodos(StatusAgendaEnum statusAgendaEnum) {
        return null;
    }

    public ArrayList<Agenda> listarTodosPorCliente(Long idCliente) {
        return null;
    }

    public void cancelarHorario(Long idAgenda) {

    }

    public static boolean validarHorarioAgendamento(Agenda agenda, List<Agenda> agendamentos) {
        for (Agenda agendamento : agendamentos) {
            //data anterior
            if (agenda.getDataHoraInicio().isBefore(LocalDateTime.now()) || agenda.getDataHoraFim().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Não é possível cadastrar um agendamento em um horário passado, favor verifique.");
            }
            //Início no meio de horário já marcado
            if (agenda.getDataHoraInicio().isBefore(agendamento.getDataHoraFim())
                    && agenda.getDataHoraInicio().isAfter(agendamento.getDataHoraInicio())) {
                throw new IllegalArgumentException("Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }
            //Fim no meio de horário já marcado
            if (agenda.getDataHoraFim().isBefore(agendamento.getDataHoraFim())
                    && agenda.getDataHoraFim().isAfter(agendamento.getDataHoraInicio())) {
                throw new IllegalArgumentException("Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }
            //Início == início já marcado
            if (agenda.getDataHoraInicio() == agendamento.getDataHoraInicio() && agenda.getDataHoraFim() == agendamento.getDataHoraFim()) {
                throw new IllegalArgumentException("Este horário já está cadastrado em sua agenda, favor verificar.");
            }
            //Fim == fim já marcado
            if (agenda.getDataHoraInicio() == agendamento.getDataHoraInicio()) {
                throw new IllegalArgumentException("Já há horário cadastrado com esta data/hora inicial, verifique sua agenda.");
            }
            //Horário já cadastrado
            if (agenda.getDataHoraFim() == agendamento.getDataHoraFim()) {
                throw new IllegalArgumentException("Já há horário cadastrado com esta data/hora final, verifique sua agenda.");
            }
        }
        return true;
    }
}
