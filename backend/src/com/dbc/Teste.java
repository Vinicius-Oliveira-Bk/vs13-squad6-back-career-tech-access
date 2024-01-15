package com.dbc;
import com.dbc.model.entities.*;
import com.dbc.model.enums.*;
import com.dbc.services.*;
import com.dbc.utils.CustomScanner;

import java.time.LocalDate;
import java.util.Scanner;


public class Teste {
    public static void main(String[] args) {
        UsuarioServico usuarioServico = new UsuarioServico();
        ClienteServico clienteServico = new ClienteServico();
        EstudanteServico estudanteServico = new EstudanteServico();
        PcdServico pcdServico = new PcdServico();
        CustomScanner scanner = new CustomScanner();

//        Usuario u = new Usuario("ga", LocalDate.of(2000,1,12), "32165498778", "ga@gmail.com", "3120", 'S', TipoUsuarioEnum.ESTUDANTE, "AEJOASIEA", null, null, "aesaeas");
//        Cliente c = new Cliente(u, PlanoEnum.BASICO, TipoUsuarioEnum.ESTUDANTE, "interessses", "imagem", 'S');
//        Pcd pcd = new Pcd(c, "tipoDeficiencia", "certificadoDeConclusao");

        //Usuario
//        usuarioServico.cadastrar(u);
//        usuarioServico.listarNaoClientes();
//        usuarioServico.listarTodos();
//        System.out.println(usuarioServico.listarUm(7L));
//        usuarioServico.remover(7L);
//        usuarioServico.atualizar(7L, usuarioServico.listarUm(9L));

        //Cliente
//        int x = scanner.nextInt("Informe o id do usuário para cadastrar o cliente: ");
//        clienteServico.cadastrar(c, (long) x);
//        clienteServico.listarTodos();
//        System.out.println(clienteServico.listarUm(12L));
//        clienteServico.atualizar(3L, clienteServico.listarUm(7L));
//        clienteServico.remover(12L);

        //Pcd
//        clienteServico.listarNaoPcds();
//        int x = scanner.nextInt("Informe o id do usuário para cadastrar o pcd: ");
//        pcd.getCliente().setId((long) x);
//        pcdServico.cadastrar(pcd);
//        pcdServico.listarTodos();
//        System.out.println(pcdServico.listarUm(15L));
//        pcdServico.atualizar(16L, pcdServico.listarUm(20L));

        //Estudante
//        clienteServico.listarNaoEstudantes();
//        int x = scanner.nextInt("Informe o id do usuário para cadastrar o estudante: ");
//        pcd.getCliente().setId((long) x);
//        pcdServico.cadastrar(pcd);
//        pcdServico.listarTodos();
//        System.out.println(pcdServico.listarUm(15L));
//        pcdServico.atualizar(16L, pcdServico.listarUm(20L));
//        pcdServico.remover(19L);

    }
}
