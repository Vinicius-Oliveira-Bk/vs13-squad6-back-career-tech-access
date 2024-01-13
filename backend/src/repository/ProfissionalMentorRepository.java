package repository;

import exceptions.BancoDeDadosException;
import model.entidades.ProfissionalMentor;
import model.enums.AreaAtuacaoEnum;
import model.enums.NivelExperienciaEnum;
import model.enums.TipoUsuarioEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalMentorRepository implements IRepository<Integer, ProfissionalMentor> {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_PROFISSIONAL_MENTOR.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }
    @Override
    public void cadastrar(ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Integer proximoId = this.getProximoId(con);

            String sql = "INSERT INTO PROFISSIONAL_MENTOR\n" +
                    "(ID, CARTEIRA_TRABALHO, NIVEL_EXPERIENCIA, DOCUMENTOS_VALIDOS)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, proximoId);
            stmt.setString(2, mentor.getCarteiraDeTrabalho());
            stmt.setInt(3, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(4, mentor.getCertificadosDeCapacitacao().toString());

            int res = stmt.executeUpdate();
            System.out.println("adicionarMentor.res=" + res);
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
    public boolean atualizar(Integer id, ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SET PROFISSIONAL_MENTOR");
            sql.append(" area_atuacao = ? ");
            sql.append(" nivel_experiencia = ?,");
            sql.append(" carteira_trabalho = ?,");
            sql.append(" documentos_validados = ?,");
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setInt(2, mentor.getNivelExperienciaEnum().getValor());
            stmt.setString(3, mentor.getCarteiraDeTrabalho());
            stmt.setString(4, mentor.getCertificadosDeCapacitacao().toString());
            stmt.setInt(5, (int) mentor.getId());

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

            String sql = "SELECT * FROM PROFISSIONAL_MENTOR";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalMentor mentor = getProfissionalMentorFromResultSet(res);
                boolean add = mentores.add(mentor);
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
        return mentores;
    }

    private ProfissionalMentor getProfissionalMentorFromResultSet(ResultSet res) throws SQLException {
        ProfissionalMentor mentor = new ProfissionalMentor();
        mentor.setId(res.getLong("id"));
        mentor.setNome(res.getString("nome"));
        mentor.setAreaAtuacao(AreaAtuacaoEnum.valueOf(res.getString("area_atuacao")));
        mentor.setTipo(TipoUsuarioEnum.valueOf(res.getString("tipo")));
        mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));
        mentor.setNivelExperienciaEnum(NivelExperienciaEnum.valueOf(res.getString("nivel_experiencia")));
        mentor.setCertificadosDeCapacitacao(res.getString("certificados_de_capacitacao"));

        return mentor;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_MENTOR WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
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
}
