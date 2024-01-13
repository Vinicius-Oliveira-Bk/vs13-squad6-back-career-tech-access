package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Endereco;
import com.dbc.model.enums.TipoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository implements IRepository<Long, Endereco> {

    @Override
    public Long getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_endereco.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getLong("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Endereco cadastrar(Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            endereco.setId(proximoId);

            String sql = "INSERT INTO ENDERECO\n" +
                    "(ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, endereco.getId());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());
            stmt.setInt(9, endereco.getTipo().getValor());

            int res = stmt.executeUpdate();
            System.out.println("adicionarEndereco.res=" + res);
            return endereco;
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

            String sql = "DELETE FROM ENDERECO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + res);

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
    public boolean atualizar(Long id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ENDERECO SET \n");

            if (endereco.getLogradouro() != null) {
                sql.append(" logradouro = ?,");
            }
            if (endereco.getNumero() != null) {
                sql.append(" numero = ?,");
            }
            if (endereco.getComplemento() != null) {
                sql.append(" complemento = ?,");
            }
            if (endereco.getCep() != null) {
                sql.append(" cep = ?,");
            }
            if (endereco.getCidade() != null) {
                sql.append(" cidade = ?,");
            }
            if (endereco.getEstado() != null) {
                sql.append(" estado = ?,");
            }
            if (endereco.getPais() != null) {
                sql.append(" pais = ?,");
            }
            if (endereco.getTipo() != null) {
                sql.append(" tipo = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (endereco.getLogradouro() != null) {
                stmt.setString(index++, endereco.getLogradouro());
            }
            if (endereco.getNumero() != null) {
                stmt.setString(index++, endereco.getNumero());
            }
            if (endereco.getComplemento() != null) {
                stmt.setString(index++, endereco.getComplemento());
            }
            if (endereco.getCep() != null) {
                stmt.setString(index++, endereco.getCep());
            }
            if (endereco.getCidade() != null) {
                stmt.setString(index++, endereco.getCidade());
            }
            if (endereco.getEstado() != null) {
                stmt.setString(index++, endereco.getEstado());
            }
            if (endereco.getPais() != null) {
                stmt.setString(index++, endereco.getPais());
            }
            if (endereco.getTipo() != null) {
                stmt.setInt(index++, endereco.getTipo().getValor());
            }

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarEndereco.res=" + res);

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
    public List<Endereco> listar() throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ENDERECO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = getEnderecoFromResultSet(res);
                enderecos.add(endereco);
            }
            return enderecos;
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

    public Endereco listarUm(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM ENDERECO WHERE ID = ? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            return getEnderecoFromResultSet(res);
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

    private Endereco getEnderecoFromResultSet(ResultSet res) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(res.getLong("id"));
        endereco.setLogradouro(res.getString("logradouro"));
        endereco.setNumero(res.getString("numero"));
        endereco.setComplemento(res.getString("complemento"));
        endereco.setCep(res.getString("cep"));
        endereco.setCidade(res.getString("cidade"));
        endereco.setEstado(res.getString("estado"));
        endereco.setPais(res.getString("pais"));
        endereco.setTipo(TipoEnum.fromValor(res.getInt("tipo")));
        return endereco;
    }
}
