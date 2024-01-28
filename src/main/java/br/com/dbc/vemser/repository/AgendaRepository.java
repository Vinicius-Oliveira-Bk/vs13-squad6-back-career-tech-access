package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.response.AgendaResponseDTO;
import br.com.dbc.vemser.model.entities.Agenda;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.enums.StatusAgendaEnum;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.services.ClienteService;
import br.com.dbc.vemser.services.ProfissionalMentorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AgendaRepository {
    private final ProfissionalMentorService profissionalMentorService;
    private final ClienteService clienteService;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    public Long getProximoId(Connection connection) throws SQLException {

        String sql = "SELECT SEQ_AGENDA.NEXTVAL AS SEQUENCE_AGENDA FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        if (result.next()) {
            return result.getLong("SEQUENCE_AGENDA");
        }
        return null;
    }

    public Agenda create(Agenda agenda) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            Long novoId = this.getProximoId(con);
            agenda.setId(novoId);

            String sql = "INSERT INTO AGENDA\n" +
                         "(ID, ID_MENTOR, DATA_INICIO, DATA_FIM, STATUS)\n" +
                         "VALUES(?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, agenda.getId().intValue());
            stmt.setLong(2, agenda.getProfissionalMentor().getIdProfissionalMentor());
            stmt.setTimestamp(3, Timestamp.valueOf(agenda.getDataHoraInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(agenda.getDataHoraFim()));
            stmt.setInt(5, agenda.getStatusAgendaEnum().getValor());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Quantidade de linhas inseridas: "+linhasAfetadas);
            return agenda;

        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    public List<Agenda> getAll() throws Exception {
        List<Agenda> agendamentos = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM AGENDA";

            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(result.getLong("ID"));
                if (result.getLong("ID_CLIENTE") != 0) {
                    agenda.setCliente(clienteService.getCliente(result.getLong("ID_CLIENTE")));
                }
                agenda.setProfissionalMentor(profissionalMentorService.getProfissionalMentor(result.getLong("ID_MENTOR")));
                agenda.setDataHoraInicio(result.getTimestamp("DATA_INICIO").toLocalDateTime());
                agenda.setDataHoraFim(result.getTimestamp("DATA_FIM").toLocalDateTime());
                agenda.setStatusAgendaEnum(StatusAgendaEnum.fromValor(result.getInt("STATUS")));
                agendamentos.add(agenda);
            }
        } catch (SQLException | NullPointerException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RegraDeNegocioException(e.getMessage());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
        return agendamentos;
    }

    public Agenda getById(Long id) throws Exception {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM AGENDA WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Agenda agendamento = new Agenda();
                agendamento.setId(result.getLong("ID"));
                if (result.getLong("ID_CLIENTE") != 0) {
                    agendamento.setCliente(clienteService.getCliente(result.getLong("ID_CLIENTE")));
                }
                agendamento.setProfissionalMentor(profissionalMentorService.getProfissionalMentor(result.getLong("ID_MENTOR")));
                agendamento.setDataHoraInicio(result.getTimestamp("DATA_INICIO").toLocalDateTime());
                agendamento.setDataHoraFim(result.getTimestamp("DATA_FIM").toLocalDateTime());
                agendamento.setStatusAgendaEnum(StatusAgendaEnum.fromValor(result.getInt("STATUS")));
                return agendamento;
            }
            throw new RegraDeNegocioException("Não há agenda com este id!");
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RegraDeNegocioException(e.getMessage());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    public boolean update(Long id, Agenda agenda) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            int result;
            StringBuilder sql = new StringBuilder();
            if (Objects.isNull(agenda.getCliente())) {
                sql.append("UPDATE AGENDA SET ");
                sql.append(" id_cliente = null,");
                sql.append(" data_inicio = ?,");
                sql.append(" data_fim = ? ,");
                sql.append(" status = ? ");
                sql.append(" WHERE id = ? ");

                PreparedStatement stmt = con.prepareStatement(sql.toString());

                stmt.setTimestamp(1, Timestamp.valueOf(agenda.getDataHoraInicio()));
                stmt.setTimestamp(2, Timestamp.valueOf(agenda.getDataHoraFim()));
                stmt.setInt(3, agenda.getStatusAgendaEnum().getValor());
                stmt.setLong(4, id);

                result = stmt.executeUpdate();
            } else {
                sql.append("UPDATE AGENDA SET ");
                sql.append(" id_cliente = ?,");
                sql.append(" data_inicio = ?,");
                sql.append(" data_fim = ? ,");
                sql.append(" status = ? ");
                sql.append(" WHERE id = ? ");

                PreparedStatement stmt = con.prepareStatement(sql.toString());

                stmt.setLong(1, agenda.getCliente().getId());
                stmt.setTimestamp(2, Timestamp.valueOf(agenda.getDataHoraInicio()));
                stmt.setTimestamp(3, Timestamp.valueOf(agenda.getDataHoraFim()));
                stmt.setInt(4, agenda.getStatusAgendaEnum().getValor());
                stmt.setLong(5, id);
                result = stmt.executeUpdate();
            }

            return result > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    public boolean delete(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "DELETE FROM AGENDA WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int result = stmt.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }
}
