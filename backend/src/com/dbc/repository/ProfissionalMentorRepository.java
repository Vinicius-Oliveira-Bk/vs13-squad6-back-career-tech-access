package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.ProfissionalMentor;
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.AreaAtuacaoEnum;
import com.dbc.model.enums.NivelExperienciaEnum;
import com.dbc.model.enums.TipoUsuarioEnum;
import com.dbc.services.UsuarioServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalMentorRepository implements IRepository<Long, ProfissionalMentor> {
    UsuarioServico us = new UsuarioServico();
    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT VS_13_EQUIPE_6.SEQ_PROFISSIONAL_MENTOR.nextval AS SEQ_PROFISSIONAL_MENTOR FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("SEQ_PROFISSIONAL_MENTOR");
        }

        return null;
    }

    @Override
    public ProfissionalMentor cadastrar(ProfissionalMentor mentor) throws BancoDeDadosException {
        return null;
    }

    public ProfissionalMentor cadastrar(ProfissionalMentor mentor, Long idUsuario) throws BancoDeDadosException{
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            mentor.setId(proximoId);

            String sql = "INSERT INTO VS_13_EQUIPE_6.PROFISSIONAL_MENTOR\n" +
                    "(ID, ID_USUARIO, AREA_ATUACAO, NIVEL_EXPERIENCIA, CARTEIRA_TRABALHO)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, proximoId);
            stmt.setLong(2, idUsuario);
            stmt.setString(3, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setInt(4, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(5, mentor.getCarteiraDeTrabalho());

            int res = stmt.executeUpdate();
            System.out.println("adicionarMentor.res=" + res);

            return mentor;
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
    public boolean atualizar(Long id, ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();

            sql.append("UPDATE VS_13_EQUIPE_6.PROFISSIONAL_MENTOR SET");
            sql.append(" area_atuacao = ? ");
            sql.append(" nivel_experiencia = ?,");
            sql.append(" carteira_trabalho = ?,");
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setInt(2, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(3, mentor.getCarteiraDeTrabalho());
            stmt.setLong(4, mentor.getUsuario().getId());

            int res = stmt.executeUpdate();
            System.out.println("editarMentor.res=" + res);

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
    public List<ProfissionalMentor> listar() throws BancoDeDadosException {
        List<ProfissionalMentor> mentores = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM VS_13_EQUIPE_6.PROFISSIONAL_MENTOR";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalMentor mentor = getProfissionalMentorFromResultSet(res);
                mentores.add(mentor);
            }
            return mentores;
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

    public ProfissionalMentor listarUm(Long id) throws BancoDeDadosException {
        Connection con = null;
        ProfissionalMentor mentor = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM VS_13_EQUIPE_6.PROFISSIONAL_MENTOR WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                mentor = new ProfissionalMentor();
                mentor.setId(res.getLong("id"));
                mentor.setAreaAtuacao(AreaAtuacaoEnum.fromValor(res.getInt("area_atuacao")));
                mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));
                mentor.setNivelExperienciaEnum(NivelExperienciaEnum.fromValor(res.getInt("nivel_experiencia")));
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
        return mentor;
    }

    @Override
    public boolean remover(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_MENTOR WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerMentorPorId.res=" + res);

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
    private ProfissionalMentor getProfissionalMentorFromResultSet(ResultSet res) throws SQLException {
        ProfissionalMentor mentor = new ProfissionalMentor();
        mentor.setId(res.getLong("id"));
        mentor.setAreaAtuacao(AreaAtuacaoEnum.fromValor(res.getInt("area_atuacao")));
        mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));
        mentor.setNivelExperienciaEnum(NivelExperienciaEnum.fromValor(res.getInt("nivel_experiencia")));

        return mentor;
    }
}
