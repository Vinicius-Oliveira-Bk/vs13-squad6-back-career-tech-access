package com.dbc;
import com.dbc.model.entities.*;
import com.dbc.model.enums.*;
import com.dbc.services.*;
import com.dbc.utils.CustomScanner;

import java.time.LocalDate;
import java.util.Scanner;


public class Teste {
    public static void main(String[] args) {
//        UsuarioServico usuarioServico = new UsuarioServico();
//        ProfissionalRealocacaoServico profissionalRealocacaoServico = new ProfissionalRealocacaoServico();
//        ProfissionalMentorServico profissionalMentorServico = new ProfissionalMentorServico();
//        PcdServico pcdServico = new PcdServico();
//        EstudanteServico estudanteServico = new EstudanteServico();
//        EnderecoServico enderecoServico = new EnderecoServico();
//        ContatoServico contatoServico = new ContatoServico();
//        ClienteServico clienteServico = new ClienteServico();
        AgendaServico agendaServico = new AgendaServico();;
//        agendaServico.listarTodos();
//        usuarioServico.listarTodos();
//        profissionalRealocacaoServico.listarTodos();
//        profissionalMentorServico.listarTodos();
//        pcdServico.listarTodos();
//        estudanteServico.listarTodos();
//        enderecoServico.listarTodos();
//        contatoServico.listarTodos();
//        clienteServico.listarTodos();
//        agendaServico.listarTodos();
//        agendaServico.listarUm(1L);
//        agendaServico.cadastrarDisponibilidade();

        Usuario u = new Usuario("ga", LocalDate.of(2000,1,12), "32165498778", "ga@gmail.com", "3120", 'S', TipoUsuarioEnum.ESTUDANTE, "AEJOASIEA", null, null, "aesaeas");
        UsuarioServico usuarioServico = new UsuarioServico();
        ProfissionalMentorServico pms = new ProfissionalMentorServico();

        ClienteServico cs = new ClienteServico();
        EstudanteServico es = new EstudanteServico();
        PcdServico ps = new PcdServico();
        ProfissionalRealocacaoServico prs = new ProfissionalRealocacaoServico();

//        usuarioServico.cadastrar(u);
        Cliente c = new Cliente(u, PlanoEnum.BASICO, TipoUsuarioEnum.ESTUDANTE, "interessses", "imagem", 'S');
//        cs.adicionar(c, 1L);
        Estudante e = new Estudante(c, "123", "comprovante", "instituicao", "ads", LocalDate.of(2010,1,12), LocalDate.of(2012,1,12));
        ProfissionalRealocacao pr = new ProfissionalRealocacao(c, "profissao", "objetivoProfissional");
        Pcd pcd = new Pcd(c, "tipoDeficiencia", "certificadoDeConclusao");

        //estudante
//        Scanner scanner = new Scanner(System.in);
//        cs.listarNaoEstudantes();
//        System.out.println("Informe o id do cliente: ");
//        int x =scanner.nextInt();
//        e.getCliente().setId((long) x);
//        es.cadastrar(e);
//        System.out.println(e);


//        Scanner scanner = new Scanner(System.in);
//        cs.listarNaoProfissionaisRealocacao();
//        System.out.println("Informe o id do cliente: ");
//        int x = scanner.nextInt();
//        pr.getCliente().setId((long) x);
//        prs.cadastrar(pr);
//        System.out.println(pr);

        //pcd
        Scanner scanner = new Scanner(System.in);
        cs.listarNaoPcds();
        System.out.println("Informe o id do cliente: ");
//        int x =scanner.nextInt();
//        pcd.getCliente().setId((long) x);
//        ps.cadastrar(pcd);
//        ps.listarTodos();
//        ps.listarUm(1L);
//        ps.remover(14L);
        ps.atualizar(15L, pcd);


//        ProfissionalMentor p = new ProfissionalMentor(u, AreaAtuacaoEnum.COMERCIO, NivelExperienciaEnum.JUNIOR, "123123");
//        pms.cadastrar(p);


//        Agenda agenda = new Agenda(null, p, LocalDateTime.of(2024,1,12,12,10,00,00), LocalDateTime.of(2024,1,12,13,10,00,00), StatusAgendaEnum.DISPONIVEL);
//        Agenda agenda1 = new Agenda(null, p, LocalDateTime.of(2024,1,12,12,10,00,00), LocalDateTime.of(2024,1,12,13,10,00,00), StatusAgendaEnum.DISPONIVEL);
//        Agenda agenda2 = new Agenda(null, p, LocalDateTime.of(2024,1,12,12,11,00,00), LocalDateTime.of(2024,1,12,13,11,00,00), StatusAgendaEnum.DISPONIVEL);
//        Agenda agenda3 = new Agenda(null, p, LocalDateTime.of(2024,1,12,12,9,00,00), LocalDateTime.of(2024,1,12,13,11,00,00), StatusAgendaEnum.DISPONIVEL);
//        Agenda agenda4 = new Agenda(null, p, LocalDateTime.of(2024,1,12,8,0,00,00), LocalDateTime.of(2024,1,12,9,0,00,00), StatusAgendaEnum.DISPONIVEL);
//        Agenda agenda5 = new Agenda(null, p, LocalDateTime.of(2024,1,12,9,0,00,00), LocalDateTime.of(2024,1,12,10,0,00,00), StatusAgendaEnum.DISPONIVEL);
//
////        agendaServico.listarTodos();
//
//
//        agendaServico.cadastrarDisponibilidade(agenda);
//        agendaServico.cadastrarDisponibilidade(agenda1);
//        agendaServico.cadastrarDisponibilidade(agenda2);
//        agendaServico.cadastrarDisponibilidade(agenda3);
//        agendaServico.cadastrarDisponibilidade(agenda4);
//        agendaServico.cadastrarDisponibilidade(agenda5);

//        agendaServico.listarTodos();
//
//        agendaServico.listarUm(1L);
//        agendaServico.listarUm(8L);
//
//
//        agendaServico.listarTodosPorStatus(StatusAgendaEnum.DISPONIVEL);
//        agendaServico.listarTodosPorStatus(StatusAgendaEnum.AGENDADO);
//        agendaServico.listarTodosPorStatus(StatusAgendaEnum.CANCELADO);
//        agendaServico.listarTodosPorStatus(StatusAgendaEnum.PRESENTE);
//
//        agendaServico.listarTodosPorCliente(1L);
//        agendaServico.listarTodosPorCliente(2L);
//
//        agendaServico.listarTodosPorProfissional(1L);
//        agendaServico.listarTodosPorProfissional(2L);
//
//        agendaServico.reagendarHorario(1L);
//        agendaServico.reagendarHorario(8L);
//
////        existente
//        agendaServico.cancelarHorario(1L,1L);
////        cliente inexistente
//        agendaServico.cancelarHorario(5L,1L);
//        //horário inexistente
//        agendaServico.cancelarHorario(1L,8L);
//
//        agendaServico.removerDisponibilidade(1L);
//        agendaServico.removerDisponibilidade(0L);
//        agendaServico.removerDisponibilidade(9L);
//
//        agendaServico.agendarHorario(1L, 1L);
//        agendaServico.agendarHorario(2L, 2L);
//        agendaServico.agendarHorario(3L, 3L);
//        agendaServico.agendarHorario(5L, 4L);
//        agendaServico.agendarHorario(1L, 5L);
//
//        agendaServico.reagendarHorario(1L);
//
//        agendaServico.cancelarHorario(1L, 1L);
    }
}
