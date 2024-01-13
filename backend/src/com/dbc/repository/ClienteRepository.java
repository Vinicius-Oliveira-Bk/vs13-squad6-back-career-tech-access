package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.enums.PlanoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements Repositorio<Integer, Cliente> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_CLIENTE.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("SEQUENCE_CLIENTE");
        }
        return null;
    }

    @Override
    public Cliente adicionar(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long novoId = Long.valueOf(this.getProximoId(con));
            cliente.setId((int) novoId.longValue());

            String sql = "INSERT INTO USUARIO\n" +
                    "(ID, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA, INTERESSES, IMAGEMDOCUMENTO, TIPOCLIENTE, PLANO, CONTROLEPARENTAL, ACESSOPCD)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getSenha());
            stmt.setString(8, cliente.getInteresses());
            stmt.setString(9, cliente.getImagemDocumento());
            stmt.setString(10, cliente.getTipoCliente().name());
            stmt.setString(10, cliente.getPlano().name());
            stmt.setString(11, String.valueOf(cliente.getControleParental()));
            stmt.setString(12, String.valueOf(cliente.getAcessoPcd()));

            int res = stmt.executeUpdate();
            System.out.println("adicionarMentor.res=" + res);

            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean remover(Object id) throws BancoDeDadosException {
        Connection con = null;
        try {

            String sql = "DELETE FROM PESSOA WHERE id_cliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, (Integer) id);

            int res = stmt.executeUpdate();
            System.out.println("removerClientePorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Cliente> listar() throws BancoDeDadosException {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CLIENTE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("ID"));
                cliente.setNome(res.getString("NOME"));
                cliente.setDataNascimento(res.getDate("DATANASCIMENTO").toLocalDate());
                cliente.setCpf(res.getString("CPF"));
                cliente.setEmail(res.getString("EMAIL"));
                cliente.setSenha(res.getString("SENHA"));
                cliente.setInteresses(res.getString("INTERESSES"));
                cliente.setImagemDocumento(res.getString("IMAGEMDOCUMENTO"));
                cliente.setTipoCliente(TipoClienteEnum.valueOf(res.getString("TIPOCLIENTE")));
                cliente.setPlano(PlanoEnum.valueOf(res.getString("PLANO")));
                cliente.setControleParental(res.getString("CONTROLEPARENTAL").charAt(0));
                cliente.setAcessoPcd(res.getString("ACESSOPCD").charAt(0));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    @Override
    public Cliente listarUm(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getInt("ID"));
                cliente.setNome(res.getString("NOME"));
                cliente.setDataNascimento(res.getDate("DATANASCIMENTO").toLocalDate());
                cliente.setCpf(res.getString("CPF"));
                cliente.setEmail(res.getString("EMAIL"));
                cliente.setSenha(res.getString("SENHA"));
                cliente.setInteresses(res.getString("INTERESSES"));
                cliente.setImagemDocumento(res.getString("IMAGEMDOCUMENTO"));
                cliente.setTipoCliente(TipoClienteEnum.valueOf(res.getString("TIPOCLIENTE")));
                cliente.setPlano(PlanoEnum.valueOf(res.getString("PLANO")));
                cliente.setControleParental(res.getString("CONTROLEPARENTAL").charAt(0));
                cliente.setAcessoPcd(res.getString("ACESSOPCD").charAt(0));

                return cliente;
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean editar(Integer id, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTE SET ");
            sql.append(" nome = ?,");
            sql.append(" datanascimento = ? ");
            sql.append(" cpf = ? ");
            sql.append(" email = ? ");
            sql.append(" senha = ? ");
            sql.append(" interesses = ? ");
            sql.append(" imagemdocumento = ? ");
            sql.append(" tipoCliente = ? ");
            sql.append(" plano = ? ");
            sql.append(" controleParental = ? ");
            sql.append(" acessoPcd = ? ");
            sql.append(" WHERE id = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getSenha());
            stmt.setString(6, cliente.getInteresses());
            stmt.setString(7, cliente.getImagemDocumento());
            stmt.setString(8, cliente.getTipoCliente().name());
            stmt.setString(9, cliente.getPlano().name());
            stmt.setString(10, String.valueOf(cliente.getControleParental()));
            stmt.setString(11, String.valueOf(cliente.getAcessoPcd()));
            stmt.setInt(12, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarCliente.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}