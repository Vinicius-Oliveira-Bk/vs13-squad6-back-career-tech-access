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
        String sql = "SELECT SEQ_CLIENTE.nextval SEQUENCE_CLIENTE from DUAL";

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

            String sql = "INSERT INTO CLIENTE\n" +
                    "(ID, ID_CLIENTE, NOME, DATA_NASCIMENTO, CPF, EMAIL, SENHA, INTERESSES, IMAGEM_DOCUMENTO, TIPO_CLIENTE, PLANO, CONTROLE_PARENTAL, ACESSO_PCD)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, cliente.getId());
            stmt.setLong(2, cliente.getIdCliente());
            stmt.setString(3, cliente.getNome());
            stmt.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(5, cliente.getCpf());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getSenha());
            stmt.setString(8, cliente.getInteresses());
            stmt.setString(9, cliente.getImagemDocumento());
            stmt.setString(10, cliente.getPlano().name());
            stmt.setString(11, String.valueOf(cliente.getControleParental()));
            stmt.setString(12, String.valueOf(cliente.getAcessoPcd()));

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
            sql.append("UPDATE CLIENTE SET ");
            sql.append(" ID_CLIENTE = ?,");
            sql.append(" NOME = ?,");
            sql.append(" DATA_NASCIMENTO = ?, ");
            sql.append(" CPF = ?, ");
            sql.append(" EMAIL = ?, ");
            sql.append(" SENHA = ?, ");
            sql.append(" INTERESSES = ?, ");
            sql.append(" IMAGEM_DOCUMENTO = ?, ");
            sql.append(" TIPO_CLIENTE = ?, ");
            sql.append(" PLANO = ?, ");
            sql.append(" CONTROLE_PARENTAL = ?, ");
            sql.append(" ACESSO_PCD = ? ");
            sql.append(" WHERE ID = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getSenha());
            stmt.setString(7, cliente.getInteresses());
            stmt.setString(8, cliente.getImagemDocumento());
            stmt.setString(9, cliente.getTipoCliente().name());
            stmt.setString(10, cliente.getPlano().name());
            stmt.setString(11, String.valueOf(cliente.getControleParental()));
            stmt.setString(12, String.valueOf(cliente.getAcessoPcd()));
            stmt.setLong(13, id);

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