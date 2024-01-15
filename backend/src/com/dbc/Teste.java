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
        EstudanteServico es = new EstudanteServico();
        PcdServico ps = new PcdServico();

        Usuario u = new Usuario("ga", LocalDate.of(2000,1,12), "32165498778", "ga@gmail.com", "3120", 'S', TipoUsuarioEnum.ESTUDANTE, "AEJOASIEA", null, null, "aesaeas");
        Cliente c = new Cliente(u, PlanoEnum.BASICO, TipoUsuarioEnum.ESTUDANTE, "interessses", "imagem", 'S');
        Pcd pcd = new Pcd(c, "tipoDeficiencia", "certificadoDeConclusao");

//        usuarioServico.cadastrar(u);
        usuarioServico.listarNaoClientes();
        CustomScanner scanner = new CustomScanner();
        int x = scanner.nextInt("Informe o id do usuário para cadastrar o cliente: ");
        clienteServico.cadastrar(c, (long) x);

    }
}
