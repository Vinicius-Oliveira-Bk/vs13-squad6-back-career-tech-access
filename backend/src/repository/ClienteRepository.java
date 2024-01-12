package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exceptions.BancoDeDadosException;

public class ClienteRepository implements IRepository {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public void cadastrar(Object objeto) throws BancoDeDadosException {

    }

    @Override
    public List listar() throws BancoDeDadosException {
        return null;

    }

    @Override
    public boolean atualizar(Object id, Object objeto) throws BancoDeDadosException {
        return true;
    }

    @Override
    public boolean remover(Object id) throws BancoDeDadosException {
        return true;
    }

}