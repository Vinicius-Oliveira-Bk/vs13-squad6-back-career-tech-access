package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.services.UsuarioService;

public class ProfissionalMentorRepository implements IRepository<Long, ProfissionalMentor> {
    private final UsuarioService us;

    public ProfissionalMentorRepository(UsuarioService us) {
        this.us = us;
    }

    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_PROFISSIONAL_MENTOR.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("mysequence");
        }

        return null;
    }

    @Override
    public ProfissionalMentor create(ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);

            String sql = "INSERT INTO PROFISSIONAL_MENTOR\n" +
                    "(ID, ID_USUARIO, AREA_ATUACAO, CARTEIRA_TRABALHO, NIVEL_EXPERIENCIA)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, proximoId);
            stmt.setLong(2, mentor.getUsuario().getId());
            stmt.setString(3, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setString(4, mentor.getCarteiraDeTrabalho());
            stmt.setInt(5, mentor.getNivelExperienciaEnum().getValor());

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
    public ProfissionalMentor getById(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();


            String sql = "SELECT * FROM PROFISSIONAL_MENTOR " +
                    "JOIN USUARIO ON PROFISSIONAL_MENTOR.ID_USUARIO = USUARIO.ID " +
                    "JOIN CLIENTE ON USUARIO.ID = CLIENTE.ID_USUARIO WHERE id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                ProfissionalMentor mentor = getProfissionalMentorFromResultSet(res);
                System.out.println(mentor);
            }
            return getProfissionalMentorFromResultSet(res);
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
    public List<ProfissionalMentor> getAll() throws BancoDeDadosException {
        List<ProfissionalMentor> mentores = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PROFISSIONAL_MENTOR " +
                    "JOIN USUARIO ON PROFISSIONAL_MENTOR.ID_USUARIO = USUARIO.ID " +
                    "JOIN CLIENTE ON USUARIO.ID = CLIENTE.ID_USUARIO";

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

    @Override
    public boolean update(Long id, ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SET PROFISSIONAL_MENTOR");
            sql.append(" id_usuario = ? ");
            sql.append(" area_atuacao = ? ");
            sql.append(" nivel_experiencia = ?,");
            sql.append(" carteira_trabalho = ?,");
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setLong(2, mentor.getUsuario().getId());
            stmt.setInt(3, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(4, mentor.getCarteiraDeTrabalho());
            stmt.setLong(5, mentor.getId());

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
    public boolean delete(Long id) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_MENTOR WHERE id = ?";

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

        int areaAtuacaoOrdinal = res.getInt("area_atuacao");
        AreaAtuacaoEnum areaAtuacao = AreaAtuacaoEnum.values()[areaAtuacaoOrdinal - 1];
        if (areaAtuacao == null) {
            areaAtuacao = AreaAtuacaoEnum.OUTROS;
        }
        mentor.setAreaAtuacao(areaAtuacao);

        mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));

        int nivelExperienciaOrdinal = res.getInt("nivel_experiencia");
        NivelExperienciaEnum nivelExperiencia = NivelExperienciaEnum.values()[nivelExperienciaOrdinal - 1];
        if (nivelExperiencia == null) {
            nivelExperiencia = NivelExperienciaEnum.JUNIOR;
        }
        mentor.setNivelExperienciaEnum(nivelExperiencia);

        return mentor;
    }
}
