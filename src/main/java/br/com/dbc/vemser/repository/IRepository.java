package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;

public interface IRepository<CHAVE, OBJETO> {
    Long getProximoId(Connection connection) throws SQLException;
    OBJETO create(OBJETO objeto) throws BancoDeDadosException;
    OBJETO getById(CHAVE id) throws BancoDeDadosException;
    List<OBJETO> getAll() throws BancoDeDadosException;
    boolean update(CHAVE id, OBJETO objeto) throws BancoDeDadosException;
    boolean delete(CHAVE id) throws BancoDeDadosException;
}