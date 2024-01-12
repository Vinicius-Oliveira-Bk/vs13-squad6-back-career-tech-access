package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import model.entidades.Agenda;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<CHAVE, OBJETO> {

    Integer getProximoId(Connection connection) throws SQLException;
    OBJETO cadastrar(OBJETO objeto) throws BancoDeDadosException;
    List<OBJETO> listar() throws BancoDeDadosException;
    OBJETO listarUm(CHAVE id) throws BancoDeDadosException;
    boolean atualizar(CHAVE id, OBJETO objeto) throws BancoDeDadosException;
    boolean remover(CHAVE id) throws BancoDeDadosException;
}
