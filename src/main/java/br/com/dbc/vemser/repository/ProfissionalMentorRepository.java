package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.model.entities.ProfissionalMentor;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.AreaAtuacaoEnum;
import br.com.dbc.vemser.model.enums.NivelExperienciaEnum;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfissionalMentorRepository implements IRepository<Long, ProfissionalMentor> {

    private final UsuarioService usuarioService;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

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
            con = conexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            mentor.setIdProfissionalMentor(proximoId);

            String sql = "INSERT INTO PROFISSIONAL_MENTOR\n" +
                    "(ID, ID_USUARIO, AREA_ATUACAO, CARTEIRA_TRABALHO, NIVEL_EXPERIENCIA)\n" +
                    "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, proximoId);
            stmt.setLong(2, mentor.getUsuario().getId());
            stmt.setInt(3, mentor.getAreaAtuacao().getValor());
            stmt.setString(4, mentor.getCarteiraDeTrabalho());
            stmt.setInt(5, mentor.getNivelExperienciaEnum().getValor());

            int res = stmt.executeUpdate();

            return mentor;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public ProfissionalMentor getById(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();
            ProfissionalMentor mentor = new ProfissionalMentor();

            String sql = "SELECT * FROM PROFISSIONAL_MENTOR PM WHERE PM.ID = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                mentor = getProfissionalMentorFromResultSet(res);
            }
            return mentor;
        } catch (Exception e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public List<ProfissionalMentor> getAll() throws BancoDeDadosException {
        List<ProfissionalMentor> mentores = new ArrayList<>();
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT PM.ID                AS ID,\n" +
                    "       PM.AREA_ATUACAO      AS AREA_ATUACAO,\n" +
                    "       PM.CARTEIRA_TRABALHO AS CARTEIRA_TRABALHO,\n" +
                    "       PM.NIVEL_EXPERIENCIA AS NIVEL_EXPERIENCIA,\n" +
                    "       U.ID                 AS ID_USUARIO,\n" +
                    "       U.NOME               AS NOME,\n" +
                    "       U.CPF                AS CPF,\n" +
                    "       U.EMAIL              AS EMAIL,\n" +
                    "       U.ACESSO_PCD         AS ACESSO_PCD,\n" +
                    "       U.INTERESSES         AS INTERESSES,\n" +
                    "       U.IMAGEM_DOCUMENTO   AS IMAGEM_DOCUMENTO,\n" +
                    "       U.DATA_NASCIMENTO    AS DATA_NASCIMENTO,\n" +
                    "       U.TIPO_USUARIO       AS TIPO_USUARIO\n" +
                    "FROM PROFISSIONAL_MENTOR PM\n" +
                    "         JOIN USUARIO U ON U.ID = PM.ID_USUARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalMentor mentor = getProfissionalMentorFromResultSet(res);
                mentores.add(mentor);
            }
            return mentores;
        } catch (Exception e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public boolean update(Long id, ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PROFISSIONAL_MENTOR");
            sql.append(" SET AREA_ATUACAO = ?, ");
            sql.append(" NIVEL_EXPERIENCIA = ?,");
            sql.append(" CARTEIRA_TRABALHO = ?");
            sql.append(" WHERE ID = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, mentor.getAreaAtuacao().getValor());
            stmt.setInt(2, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(3, mentor.getCarteiraDeTrabalho());
            stmt.setLong(4, id);

            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public boolean delete(Long id) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_MENTOR WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    private ProfissionalMentor getProfissionalMentorFromResultSet(ResultSet res) throws Exception {
        Usuario usuario = UsuarioRepository.getUsuarioByResultSet(res);

        ProfissionalMentor mentor = new ProfissionalMentor();

        mentor.setIdProfissionalMentor(res.getLong("id"));
        mentor.setUsuario(usuario);
        int areaAtuacaoOrdinal = res.getInt("area_atuacao");
        AreaAtuacaoEnum areaAtuacao = AreaAtuacaoEnum.values()[areaAtuacaoOrdinal - 1];
        mentor.setAreaAtuacao(areaAtuacao == null ? AreaAtuacaoEnum.OUTROS : areaAtuacao);
        mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));
        int nivelExperienciaOrdinal = res.getInt("nivel_experiencia");
        NivelExperienciaEnum nivelExperiencia = NivelExperienciaEnum.values()[nivelExperienciaOrdinal - 1];
        mentor.setNivelExperienciaEnum(nivelExperiencia == null ? NivelExperienciaEnum.JUNIOR : nivelExperiencia);

        return mentor;
    }
}