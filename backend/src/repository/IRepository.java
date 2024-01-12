package repository;

import exceptions.BancoDeDadosException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<CHAVE, OBJETO> {
    Integer getProximoId(Connection connection) throws SQLException;
    void cadastrar(OBJETO objeto) throws BancoDeDadosException;
    List<OBJETO> listar() throws BancoDeDadosException;
    boolean atualizar(CHAVE id, OBJETO objeto) throws BancoDeDadosException;
    boolean remover(CHAVE id) throws BancoDeDadosException;
}
