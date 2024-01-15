package com.dbc.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;

public interface IRepository<CHAVE, OBJETO> {
    Long getProximoId(Connection connection) throws SQLException;
    OBJETO cadastrar(OBJETO objeto) throws BancoDeDadosException;
    OBJETO listarUm(CHAVE id) throws BancoDeDadosException;
    List<OBJETO> listar() throws BancoDeDadosException;
    boolean atualizar(CHAVE id, OBJETO objeto) throws BancoDeDadosException;
    boolean remover(CHAVE id) throws BancoDeDadosException;
}