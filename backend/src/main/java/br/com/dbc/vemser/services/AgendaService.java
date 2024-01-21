package br.com.dbc.vemser.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.repository.AgendaRepository;

public class AgendaService {
    private ClienteService clienteService = new ClienteService();
    private AgendaRepository agendaRepository = new AgendaRepository();

    public void cadastrarDisponibilidade(Agenda agenda) {
        try {
            List<Agenda> agendamentos = agendaRepository.getAll();

            if (!validarHorarioAgendamento(agenda, agendamentos)) {
                return;
            }

            agendaRepository.create(agenda);
            System.out.println("✅ Horário cadastrado com sucesso!");
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerDisponibilidade(Long idAgenda) {
        try {
            boolean horarioRemovido = agendaRepository.delete(idAgenda);

            if (horarioRemovido) {
                System.out.println("✅ Agendamento de id "+idAgenda+" removido com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("❌ Não foi possível remover o agendamento!");
            e.printStackTrace();
        }
    }

    public void agendarHorario(Long idCliente, Long idAgenda) {
        try {
            Agenda agenda = agendaRepository.getById(idAgenda);
            Cliente cliente = clienteService.listarUm(idCliente);

            if (validarDisponibilidadeAgenda(agenda) && clienteService.validarCliente(cliente)) {
                agenda.setCliente(cliente);
                agenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);
                agendaRepository.update(idAgenda, agenda);
                System.out.println("✅ Agendamento realizado com sucesso!");
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível realizar o agendamento!");
            e.printStackTrace();
        }
    }

    public void reagendarHorario(Long idAgendaAtual) {
        try {
            Agenda agenda = agendaRepository.getById(idAgendaAtual);

            if (Objects.isNull(agenda)) {
                throw new NoSuchElementException("❌ O Não há horário cadastrado com este id.");
            }

            if (agenda.getCliente() == null) {
                throw new IllegalArgumentException("❌ Não é possível reagendar um horário que não haja cliente.");
            }

            listarTodosPorStatus(StatusAgendaEnum.DISPONIVEL);
            Agenda novaAgenda = agendaRepository.getById(idAgendaAtual);

            if (validarDisponibilidadeAgenda(novaAgenda)) {
                novaAgenda.setCliente(agenda.getCliente());
                novaAgenda.setStatusAgendaEnum(StatusAgendaEnum.AGENDADO);
                agenda.setCliente(null);
                agenda.setStatusAgendaEnum(StatusAgendaEnum.DISPONIVEL);
                agendaRepository.update(novaAgenda.getId(), novaAgenda);
                agendaRepository.update(idAgendaAtual, agenda);
                System.out.println("✅ Reagendamento realizado com sucesso!");
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível realizar o reagendamento!");
            e.printStackTrace();
        }
    }

    public Agenda listarUm(Long idAgenda) {
        try {
            Agenda agenda = agendaRepository.getById(idAgenda);

            if (Objects.isNull(agenda)) {
                throw new NoSuchElementException("❌ O Não há horário cadastrado com este id.");
            }

            return agenda;
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível encontrar o agendamento!");
            e.printStackTrace();
        }
        return null;
    }

    public void listarTodos() {
        try {
            List<Agenda> agendamentos = agendaRepository.getAll();

            if (agendamentos.isEmpty()) {
                System.out.println("Não há nenhum horário cadastrado.");
                return;
            }

            for (Agenda agenda : agendamentos) {
                System.out.println(agenda.toString());
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        }
    }

    public void listarTodosPorCliente(Long idCliente) {
        try {
            List<Agenda> agendamentos = agendaRepository.getAll();

            agendamentos.stream()
                    .filter(x -> x.getCliente().getId() == idCliente)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (agendamentos.isEmpty()) {
                System.out.println("Não há nenhum agendamento para este cliente.");
            }

            for (Agenda agenda : agendamentos) {
                System.out.println(agenda.toString());
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível encontrar o agendamento!");
            e.printStackTrace();
        }
    }

    public void listarTodosPorProfissional(Long idProfissional) {
        try {
            List<Agenda> agendamentos = agendaRepository.getAll();

            agendamentos.stream()
                    .filter(x -> x.getProfissionalMentor().getId() == idProfissional)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (agendamentos.isEmpty()) {
                System.out.println("Não há nenhum horário cadastrado para este profissional.");
            }

            for (Agenda agenda : agendamentos) {
                System.out.println(agenda.toString());
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível encontrar o agendamento!");
            e.printStackTrace();
        }
    }

    public void listarTodosPorStatus(StatusAgendaEnum statusAgendaEnum) {
        try {
            List<Agenda> agendamentos = agendaRepository.getAll();

            agendamentos.stream()
                    .filter(x -> x.getStatusAgendaEnum() == statusAgendaEnum)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (agendamentos.isEmpty()) {
                System.out.println("Não há nenhum horário com este status.");
            }

            for (Agenda agenda : agendamentos) {
                System.out.println(agenda.toString());
            }
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível encontrar o agendamento!");
            e.printStackTrace();
        }
    }

    public void cancelarHorario(Long idCliente, Long idAgenda) {
        try {
            Agenda agenda = agendaRepository.getById(idAgenda);

            if (Objects.isNull(agenda)) {
                throw new NoSuchElementException("❌ O Não há horário cadastrado com este id.");
            }

            if (agenda.getCliente().getId() != idCliente) {
                throw new IllegalArgumentException("❌ Não é possível cancelar um horário que não pertença ao cliente selecionado.");
            }

            agenda.setCliente(null);
            agenda.setStatusAgendaEnum(StatusAgendaEnum.CANCELADO);
            agendaRepository.update(idAgenda, agenda);

            System.out.println("✅ Cancelamento realizado com sucesso!");
        } catch (BancoDeDadosException e) {
            System.err.println("❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Não foi possível encontrar o agendamento!");
            e.printStackTrace();
        }
    }

    public boolean validarHorarioAgendamento(Agenda agenda, List<Agenda> agendamentos) {
        for (Agenda agendamento : agendamentos) {
            //data anterior
            if (agenda.getDataHoraInicio().isBefore(LocalDateTime.now()) || agenda.getDataHoraFim().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("❌ Não é possível cadastrar um agendamento em um horário passado, favor verifique.");
            }

            //Início no meio de horário já marcado
            if (agenda.getDataHoraInicio().isBefore(agendamento.getDataHoraFim())
                    && agenda.getDataHoraInicio().isAfter(agendamento.getDataHoraInicio())) {
                throw new IllegalArgumentException("❌ Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }

            //Fim no meio de horário já marcado
            if (agenda.getDataHoraFim().isBefore(agendamento.getDataHoraFim())
                    && agenda.getDataHoraFim().isAfter(agendamento.getDataHoraInicio())) {
                throw new IllegalArgumentException("❌ Não é possível cadastrar neste horário, está havendo 'intercessão de horários'.");
            }

            //Início == início já marcado
            if (agenda.getDataHoraInicio() == agendamento.getDataHoraInicio() && agenda.getDataHoraFim() == agendamento.getDataHoraFim()) {
                throw new IllegalArgumentException("❌ Este horário já está cadastrado em sua agenda, favor verificar.");
            }

            //Fim == fim já marcado
            if (agenda.getDataHoraInicio() == agendamento.getDataHoraInicio()) {
                throw new IllegalArgumentException("❌ Já há horário cadastrado com esta data/hora inicial, verifique sua agenda.");
            }

            //Horário já cadastrado
            if (agenda.getDataHoraFim() == agendamento.getDataHoraFim()) {
                throw new IllegalArgumentException("❌ Já há horário cadastrado com esta data/hora final, verifique sua agenda.");
            }
        }
        return true;
    }

    public boolean validarDisponibilidadeAgenda(Agenda agenda) {
        if (agenda.getCliente() != null) {
            throw new IllegalArgumentException("❌ Já há cliente agendado para este horário, agendamento cancelado.");
        }

        return true;
    }
}
