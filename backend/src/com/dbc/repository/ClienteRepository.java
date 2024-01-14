package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.enums.PlanoEnum;
import com.dbc.model.enums.TipoClienteEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IRepository<Long, Cliente> {
    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT VS_13_EQUIPE_6.SEQ_CLIENTE.nextval AS SEQUENCE_CLIENTE FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("SEQUENCE_CLIENTE");
        }
        return null;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long novoId = this.getProximoId(con);
            cliente.setId(novoId);

            String sql = "INSERT INTO VS_13_EQUIPE_6.CLIENTE\n" +
                    "(ID, ID_USUARIO, TIPO_CLIENTE, TIPO_PLANO, CONTROLE_PARENTAL)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, cliente.getId());
            stmt.setLong(2, cliente.getIdCliente());
            stmt.setString(3, cliente.getTipoCliente().name());
            stmt.setString(4, cliente.getPlano().name());
            stmt.setString(5, String.valueOf(cliente.getControleParental()));

            int res = stmt.executeUpdate();
            System.out.println("adicionarCliente.res=" + res);

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
    public boolean remover(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM CLIENTE WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

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
                cliente.setId(res.getLong("ID"));
                cliente.setIdCliente(res.getLong("ID_CLIENTE"));
                cliente.setNome(res.getString("NOME"));
                cliente.setDataNascimento(res.getDate("DATA_NASCIMENTO").toLocalDate());
                cliente.setCpf(res.getString("CPF"));
                cliente.setEmail(res.getString("EMAIL"));
                cliente.setSenha(res.getString("SENHA"));
                cliente.setInteresses(res.getString("INTERESSES"));
                cliente.setImagemDocumento(res.getString("IMAGEM_DOCUMENTO"));
                cliente.setTipoCliente(TipoClienteEnum.valueOf(res.getString("TIPO_CLIENTE")));
                cliente.setPlano(PlanoEnum.valueOf(res.getString("PLANO")));
                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
                cliente.setAcessoPcd(res.getString("ACESSO_PCD").charAt(0));
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
    public Cliente listarUm(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getLong("ID"));
                cliente.setIdCliente(res.getLong("ID_CLIENTE"));
                cliente.setNome(res.getString("NOME"));
                cliente.setDataNascimento(res.getDate("DATA_NASCIMENTO").toLocalDate());
                cliente.setCpf(res.getString("CPF"));
                cliente.setEmail(res.getString("EMAIL"));
                cliente.setSenha(res.getString("SENHA"));
                cliente.setInteresses(res.getString("INTERESSES"));
                cliente.setImagemDocumento(res.getString("IMAGEM_DOCUMENTO"));
                cliente.setTipoCliente(TipoClienteEnum.valueOf(res.getString("TIPO_CLIENTE")));
                cliente.setPlano(PlanoEnum.valueOf(res.getString("PLANO")));
                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
                cliente.setAcessoPcd(res.getString("ACESSO_PCD").charAt(0));

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
    public boolean atualizar(Long id, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE VS_13_EQUIPE_6.CLIENTE SET ");
            sql.append(" ID_USUARIO = ?,");
            sql.append(" TIPO_CLIENTE = ?,");
            sql.append(" TIPO_PLANO = ?, ");
            sql.append(" CONTROLE_PARENTAL = ? ");
            sql.append(" WHERE ID = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getTipoCliente().name());
            stmt.setString(3, cliente.getPlano().name());
            stmt.setString(4, String.valueOf(cliente.getControleParental()));
            stmt.setLong(5, id);

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