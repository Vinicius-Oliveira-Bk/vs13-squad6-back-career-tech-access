package repository;

import exceptions.BancoDeDadosException;
import model.entidades.ProfissionalMentor;
import model.enums.AreaAtuacaoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalMentorRepository implements IRepository {
    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_pessoa2.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }
    @Override
    public void cadastrar(Object objeto) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Integer proximoId = this.getProximoId(con);
            ProfissionalMentor mentor = (ProfissionalMentor) objeto;
            // mentor.setId(proximoId);

            String sql = "INSERT INTO PROFISSIONAL_MENTOR\n" +
                    "(ID, NOME, DATA_NASCIMENTO, CPF)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, (int) mentor.getId());
            stmt.setString(2, mentor.getNome());
            stmt.setString(3, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setString(4, mentor.getCpf());

            int res = stmt.executeUpdate();
            System.out.println("adicionarPessoa.res=" + res);
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

    public boolean atualizar(Object id, ProfissionalMentor mentor) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PESSOA SET ");
            sql.append(" cpf = ?,");
            sql.append(" nome = ?,");
            sql.append(" data_nascimento = ? ");
            sql.append(" WHERE id_pessoa = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, mentor.getCpf());
            stmt.setString(2, mentor.getNome());
            stmt.setString(3, mentor.getEmail());
            stmt.setString(4, String.valueOf(mentor.getAreaAtuacao()));
            stmt.setString(5, String.valueOf(mentor.getCarteiraDeTrabalho()));
            stmt.setInt(6, (Integer) id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarPessoa.res=" + res);

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
    public List listar() throws BancoDeDadosException {
        List mentores = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PROFICIONAL_MENTOR";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalMentor mentor = new ProfissionalMentor();

                mentor.setCarteiraDeTrabalho(res.getString("carteira_trabalho"));
                mentor.setNome(res.getString("nome"));
                mentor.setAreaAtuacao(AreaAtuacaoEnum.valueOf(res.getString("area_atuacao")));
                mentor.setCpf(res.getString("cpf"));
                mentores.add(mentor);
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

    @Override
    public boolean remover(Object id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            String sql = "DELETE FROM PROFISSIONAL_MENTOR WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, (Integer) id);

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
