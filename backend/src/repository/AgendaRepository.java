package repository;

import exceptions.BancoDeDadosException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AgendaRepository implements IRepository{

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public void cadastrar(Object o) throws BancoDeDadosException {

    }

    @Override
    public List listar() throws BancoDeDadosException {
        return null;
    }

    @Override
    public boolean atualizar(Object id, Object o) throws BancoDeDadosException {
        return false;
    }

    @Override
    public boolean remover(Object id) throws BancoDeDadosException {
        return false;
    }
}
