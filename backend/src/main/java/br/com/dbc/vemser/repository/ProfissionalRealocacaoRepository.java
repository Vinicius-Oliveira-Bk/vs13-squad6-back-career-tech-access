package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.model.entities.ProfissionalRealocacao;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;

public class ProfissionalRealocacaoRepository implements IRepository<Long, ProfissionalRealocacao> {
    @Override
    public Long getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_profissional_realocacao.nextval mysequence from DUAL";
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
    public ProfissionalRealocacao cadastrar(ProfissionalRealocacao profissionalRealocacao) throws BancoDeDadosException {
        return null;
    }

    public ProfissionalRealocacao cadastrar(ProfissionalRealocacao profissionalRealocacao, Long idCliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            profissionalRealocacao.setId(proximoId);

            String sql = "INSERT INTO PROFISSIONAL_REALOCACAO\n" +
                    "(ID, ID_CLIENTE, PROFISSAO, OBJETIVO_PROFISSIONAL)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, profissionalRealocacao.getId());
            stmt.setLong(2, idCliente);
            stmt.setString(3, profissionalRealocacao.getProfissao());
            stmt.setString(4, profissionalRealocacao.getObjetivoProfissional());

            int res = stmt.executeUpdate();
            System.out.println("adicionarProfissionalRealocacao.res=" + res);
            return profissionalRealocacao;
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
    public ProfissionalRealocacao listarUm(Long id) throws BancoDeDadosException {
        ProfissionalRealocacao profissionalRealocacao = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM PROFISSIONAL_REALOCACAO WHERE ID = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setLong(1, id);

                try (ResultSet res = pstmt.executeQuery()) {
                    if (res.next()) {
                        profissionalRealocacao = getProfissionalRealocacaoFromResultSet(res);
                        System.out.println(profissionalRealocacao);
                    } else {
                        System.out.println("Nenhum profissional encontrado para o Id: " + id);
                    }
                }
            }
            return profissionalRealocacao;

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

    @Override
    public List<ProfissionalRealocacao> listar() throws BancoDeDadosException {
        List<ProfissionalRealocacao> profissionalRealocacaos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PROFISSIONAL_REALOCACAO WHERE ID = ID_CLIENTE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalRealocacao profissionalRealocacao = getProfissionalRealocacaoFromResultSet(res);
                profissionalRealocacaos.add(profissionalRealocacao);
            }
            return profissionalRealocacaos;
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
    public boolean atualizar(Long id, ProfissionalRealocacao profissionalRealocacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PROFISSIONAL_REALOCACAO SET \n");

            if (profissionalRealocacao.getProfissao() != null) {
                sql.append(" profissao = ?,");
            }
            if (profissionalRealocacao.getObjetivoProfissional() != null) {
                sql.append(" objetivo_profissional = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (profissionalRealocacao.getProfissao() != null) {
                stmt.setString(index++, profissionalRealocacao.getProfissao());
            }
            if (profissionalRealocacao.getObjetivoProfissional() != null) {
                stmt.setString(index++, profissionalRealocacao.getObjetivoProfissional());
            }

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarProfissionalRealocacao.res=" + res);

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
    public boolean remover(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_REALOCACAO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerProfissionalRealocacaoPorId.res=" + res);

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

    private ProfissionalRealocacao getProfissionalRealocacaoFromResultSet(ResultSet res) throws SQLException {
        ProfissionalRealocacao profissionalRealocacao = new ProfissionalRealocacao();
        profissionalRealocacao.setId(res.getLong("id"));
        profissionalRealocacao.setProfissao(res.getString("profissao"));
        profissionalRealocacao.setObjetivoProfissional(res.getString("objetivo_profissional"));
        return profissionalRealocacao;
    }
}


