package com.dbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Pcd;

public class PcdRepository implements IRepository<Long, Pcd> {
    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_PCD.nextval SEQUENCE_PCD from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("SEQUENCE_PCD");
        }
        return null;
    }

    @Override
    public Pcd cadastrar(Pcd pcd) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long novoId = this.getProximoId(con);
            pcd.setId(novoId);

            String sql = "INSERT INTO PCD\n" +
                    "(ID, ID_CLIENTE, ID_PCD, TIPO_DE_DEFICIENCIA, CERTIFICADO_DE_DEFICIENCIA_GOV)\n" +
                    "VALUES(?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, pcd.getId());
            stmt.setLong(2, pcd.getIdCliente());
            stmt.setLong(3, pcd.getIdPcd());
            stmt.setString(4, pcd.getTipoDeficiencia());
            stmt.setString(5, pcd.getCertificadoDeficienciaGov());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPcd.res=" + res);

            return pcd;
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
    public Pcd listarUm(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM PCD WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Pcd pcd = new Pcd();
                pcd.setId(res.getLong("ID"));
                pcd.setIdCliente(res.getLong("ID_CLIENTE"));
                pcd.setIdPcd(res.getLong("ID_PCD"));
                pcd.setTipoDeficiencia(res.getString("TIPO_DE_DEFICIENCIA"));
                pcd.setCertificadoDeficienciaGov(res.getString("CERTIFICADO_DE_DEFICIENCIA_GOV"));

                return pcd;
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
    public List<Pcd> listar() throws BancoDeDadosException {
        List<Pcd> pcds = new ArrayList<>();
        Connection con = null;
        try {
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PCD";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Pcd pcd = new Pcd();
                pcd.setId(res.getLong("ID"));
                pcd.setIdCliente(res.getLong("ID_CLIENTE"));
                pcd.setIdPcd(res.getLong("ID_PCD"));
                pcd.setTipoDeficiencia(res.getString("TIPO_DE_DEFICIENCIA"));
                pcd.setCertificadoDeficienciaGov(res.getString("CERTIFICADO_DE_DEFICIENCIA_GOV"));
                pcds.add(pcd);
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
        return pcds;
    }

    @Override
    public boolean atualizar(Long id, Pcd pcd) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PCD SET ");
            sql.append(" ID_CLIENTE = ?,");
            sql.append(" ID_PCD = ?,");
            sql.append(" TIPO_DE_DEFICIENCIA = ?,");
            sql.append(" CERTIFICADO_DE_DEFICIENCIA_GOV = ? ");
            sql.append(" WHERE ID = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, pcd.getIdCliente());
            stmt.setLong(2, pcd.getIdPcd());
            stmt.setString(3, pcd.getTipoDeficiencia());
            stmt.setString(4, pcd.getCertificadoDeficienciaGov());
            stmt.setLong(5, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarPcd.res=" + res);

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

            String sql = "DELETE FROM PCD WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerPcdPorId.res=" + res);

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