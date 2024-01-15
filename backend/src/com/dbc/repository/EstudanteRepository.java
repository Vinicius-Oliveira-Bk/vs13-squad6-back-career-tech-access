package com.dbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Estudante;

public class EstudanteRepository implements IRepository<Long, Estudante> {
    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_estudante.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next())
            return res.getLong("mysequence");

        return null;
    }

    @Override
    public Estudante cadastrar(Estudante estudante) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            estudante.setId(proximoId);

            String sql = "INSERT INTO ESTUDANTE (ID, MATRICULA, COMPROVANTE_MATRICULA, INSTITUICAO, CURSO, DATA_INICIO, DATA_TERMINO) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, estudante.getId());
            stmt.setString(2, estudante.getMatricula());
            stmt.setString(3, estudante.getComprovanteMatricula());
            stmt.setString(4, estudante.getInstituicao());
            stmt.setString(5, estudante.getCurso());
            stmt.setTimestamp(6, Timestamp.valueOf(estudante.getDataInicio().atStartOfDay()));
            stmt.setTimestamp(7, Timestamp.valueOf(estudante.getDataTermino().atStartOfDay()));

            stmt.executeUpdate();

            return estudante;
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
    public List<Estudante> listar() throws BancoDeDadosException {
        List<Estudante> estudantes = new ArrayList<>();
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ESTUDANTE";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Estudante estudante = new Estudante();

                estudante.setId(res.getLong("id"));
                estudante.setMatricula(res.getString("matricula"));
                estudante.setComprovanteMatricula(res.getString("comprovante_matricula"));
                estudante.setInstituicao(res.getString("instituicao"));
                estudante.setCurso(res.getString("curso"));
                estudante.setDataInicio(res.getDate("data_inicio").toLocalDate());
                estudante.setDataTermino(res.getDate("data_termino").toLocalDate());

                estudantes.add(estudante);
            }
        } catch (SQLException e) {
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estudantes;
    }

    @Override
    public Estudante listarUm(Long id) throws BancoDeDadosException {
        Estudante estudante = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM ESTUDANTE WHERE id = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);
                ResultSet res = stmt.executeQuery();

                if (res.next()) {
                    estudante = new Estudante();
                    estudante.setId(res.getLong("id"));
                    estudante.setMatricula(res.getString("matricula"));
                    estudante.setComprovanteMatricula(res.getString("comprovante_matricula"));
                    estudante.setInstituicao(res.getString("instituicao"));
                    estudante.setCurso(res.getString("curso"));
                    estudante.setDataInicio(res.getDate("data_inicio").toLocalDate());
                    estudante.setDataTermino(res.getDate("data_termino").toLocalDate());
                }
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return estudante;
    }

    @Override
    public boolean atualizar(Long id, Estudante estudante) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ESTUDANTE SET \n");

            if (estudante.getMatricula() != null) {
                sql.append(" matricula = ?,");
            }
            if (estudante.getComprovanteMatricula() != null) {
                sql.append(" comprovante_matricula = ?,");
            }
            if (estudante.getInstituicao() != null) {
                sql.append(" instituicao = ?,");
            }
            if (estudante.getCurso() != null) {
                sql.append(" curso = ?,");
            }
            if (estudante.getDataInicio() != null) {
                sql.append(" data_inicio = ?,");
            }
            if (estudante.getDataTermino() != null) {
                sql.append(" data_termino = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (estudante.getMatricula() != null) {
                stmt.setString(index++, estudante.getMatricula());
            }
            if (estudante.getComprovanteMatricula() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(estudante.getComprovanteMatricula()));
            }
            if (estudante.getInstituicao() != null) {
                stmt.setString(index++, estudante.getInstituicao());
            }
            if (estudante.getCurso() != null) {
                stmt.setString(index++, estudante.getCurso());
            }
            if (estudante.getDataInicio() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(estudante.getDataInicio().atStartOfDay()));
            }
            if (estudante.getDataTermino() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(estudante.getDataTermino().atStartOfDay()));
            }

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarEstudante.res=" + res);

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

            String sql = "DELETE FROM ESTUDANTE WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();

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
