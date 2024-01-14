package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Contato;
import com.dbc.model.enums.TipoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository implements IRepository<Long, Contato> {

    @Override
    public Long getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_contato.nextval mysequence from DUAL";
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
    public Contato cadastrar(Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            contato.setId(proximoId);

            String sql = "INSERT INTO CONTATO\n" +
                    "(ID, DESCRICAO, TELEFONE, TIPO)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, contato.getId());
            stmt.setString(2, contato.getDescricao());
            stmt.setString(3, contato.getTelefone());
            stmt.setInt(4, contato.getTipo().getValor());

            int res = stmt.executeUpdate();
            System.out.println("adicionarContato.res=" + res);
            return contato;
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

            String sql = "DELETE FROM CONTATO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerContatoPorId.res=" + res);

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
    public boolean atualizar(Long id, Contato contato) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CONTATO SET \n");

            if (contato.getDescricao() != null) {
                sql.append(" descricao = ?,");
            }
            if (contato.getTelefone() != null) {
                sql.append(" telefone = ?,");
            }

            if (contato.getTipo() != null) {
                sql.append(" tipo = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (contato.getDescricao() != null) {
                stmt.setString(index++, contato.getDescricao());
            }
            if (contato.getTelefone() != null) {
                stmt.setString(index++, contato.getTelefone());
            }

            if (contato.getTipo() != null) {
                stmt.setInt(index++, contato.getTipo().getValor());
            }

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarContato.res=" + res);

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
    public List<Contato> listar() throws BancoDeDadosException {
        List<Contato> contatos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CONTATO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Contato contato = getContatoFromResultSet(res);
                contatos.add(contato);
            }
            return contatos;
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
    public Contato listarUm(Long id) throws BancoDeDadosException {
        Contato contato = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM CONTATO WHERE ID = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setLong(1, id);

                try (ResultSet res = pstmt.executeQuery()) {
                    if (res.next()) {
                        contato = getContatoFromResultSet(res);
                        System.out.println(contato);
                    } else {
                        System.out.println("Nenhum contato encontrado para o Id: " + id);
                    }
                }
            }
            return contato;

        } catch (SQLException e) {
            e.printStackTrace();
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

    private Contato getContatoFromResultSet(ResultSet res) throws SQLException {
        Contato contato = new Contato();
        contato.setId(res.getLong("id"));
        contato.setDescricao(res.getString("descricao"));
        contato.setTelefone(res.getString("telefone"));
        contato.setTipo(TipoEnum.fromValor(res.getInt("tipo")));
        return contato;
    }
}


