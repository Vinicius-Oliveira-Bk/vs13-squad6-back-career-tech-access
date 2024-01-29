package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Estudante;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EstudanteRepository implements IRepository<Long, Estudante> {

    private final ClienteRepository clienteRepository;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

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
    public Estudante create(Estudante estudante) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            estudante.setId(proximoId);

            String sql = "INSERT INTO ESTUDANTE (ID, ID_CLIENTE, MATRICULA, COMPROVANTE_MATRICULA, INSTITUICAO, CURSO, DATA_INICIO, DATA_TERMINO) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, estudante.getId());
            stmt.setLong(2, estudante.getCliente().getId());
            stmt.setString(3, estudante.getMatricula());
            stmt.setString(4, estudante.getComprovanteMatricula());
            stmt.setString(5, estudante.getInstituicao());
            stmt.setString(6, estudante.getCurso());
            stmt.setTimestamp(7, Timestamp.valueOf(estudante.getDataInicio().atStartOfDay()));
            stmt.setTimestamp(8, Timestamp.valueOf(estudante.getDataTermino().atStartOfDay()));

            stmt.executeUpdate();

            return estudante;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public List<Estudante> getAll() throws BancoDeDadosException {
        List<Estudante> estudantes = new ArrayList<>();
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT E.ID AS ID_ESTUDANTE," +
                    " E.MATRICULA AS MATRICULA," +
                    " E.COMPROVANTE_MATRICULA AS COMPROVANTE_MATRICULA," +
                    " E.MATRICULA AS MATRICULA," +
                    " E.INSTITUICAO AS INSTITUICAO," +
                    " E.CURSO AS CURSO," +
                    " E.DATA_INICIO AS DATA_INICIO," +
                    " E.DATA_TERMINO AS DATA_TERMINO," +
                    " CLI.ID AS ID_CLIENTE," +
                    " CLI.TIPO_PLANO AS TIPO_PLANO," +
                    " CLI.CONTROLE_PARENTAL AS CONTROLE_PARENTAL," +
                    " U.ID AS ID_USUARIO," +
                    " U.NOME AS NOME," +
                    " U.EMAIL AS EMAIL," +
                    " U.CPF AS CPF," +
                    " U.ACESSO_PCD AS ACESSO_PCD," +
                    " U.TIPO_USUARIO AS TIPO_USUARIO," +
                    " U.INTERESSES AS INTERESSES," +
                    " U.IMAGEM_DOCUMENTO AS IMAGEM_DOCUMENTO," +
                    " U.DATA_NASCIMENTO AS DATA_NASCIMENTO" +
                    " FROM ESTUDANTE E" +
                    " JOIN CLIENTE CLI" +
                    " ON CLI.ID = E.ID_CLIENTE" +
                    " JOIN USUARIO U" +
                    " ON U.ID = CLI.ID_USUARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Estudante estudante = new Estudante();

                estudante.setId(res.getLong("id_estudante"));
                estudante.setCliente(ClienteRepository.getClienteByResulSet(res));
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
            conexaoBancoDeDados.closeConnection(con);
        }
        return estudantes;
    }


    @Override
    public Estudante getById(Long id) throws BancoDeDadosException {
        Estudante estudante = null;
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM ESTUDANTE WHERE id = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);
                ResultSet res = stmt.executeQuery();

                if (res.next()) {
                    estudante = new Estudante();
                    estudante.setId(res.getLong("id"));
                    estudante.setCliente(clienteRepository.getById(res.getLong("id_cliente")));
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
            conexaoBancoDeDados.closeConnection(con);
        }

        return estudante;
    }

    @Override
    public boolean update(Long id, Estudante estudante) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();

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
                stmt.setString(index++, estudante.getComprovanteMatricula());
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
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public boolean delete(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "DELETE FROM ESTUDANTE WHERE ID = ?";

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

}