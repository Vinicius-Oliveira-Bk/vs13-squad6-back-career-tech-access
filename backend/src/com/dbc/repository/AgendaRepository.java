package com.dbc.repository;

import com.dbc.model.entities.Agenda;
import com.dbc.model.enums.StatusAgendaEnum;
import com.dbc.services.ClienteServico;
import com.dbc.services.ProfissionalMentorServico;
import com.dbc.exceptions.BancoDeDadosException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaRepository implements IRepository<Long, Agenda>{

    ProfissionalMentorServico ps = new ProfissionalMentorServico();
    ClienteServico cs = new ClienteServico();

    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT SEQ_AGENDA.NEXTVAL SEQUENCE_AGENDA FROM DUAL";
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        if (result.next()) {
            return result.getLong("SEQUENCE_AGENDA");
        }
        return null;
    }

    @Override
    public Agenda cadastrar(Agenda agenda) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Long novoId = this.getProximoId(con);
            agenda.setId(novoId);

            String sql = "INSERT INTO AGENDA\n" +
                    "(ID, ID_CLIENTE, ID_MENTOR, DATA_HORA_INICIO, DATA_HORA_FIM, STATUS)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, agenda.getId().intValue());
            stmt.setInt(2, agenda.getCliente().getId().intValue());
            stmt.setInt(3, agenda.getProfissionalMentor().getId().intValue());
            stmt.setDate(4, Date.valueOf(agenda.getDataHoraInicio().toString()));
            stmt.setDate(5, Date.valueOf(agenda.getDataHoraFim().toString()));
            stmt.setInt(6, agenda.getStatusAgendaEnum().ordinal());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Quantidade de linhas inseridas: "+linhasAfetadas);
            return agenda;

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
        List<Agenda> agendamentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM AGENDA";
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            while (!result.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(result.getLong("ID_PESSOA"));
                agenda.setCliente(cs.listarUmCliente(result.getLong("ID_CLIENTE")));
                agenda.setProfissionalMentor(ps.listarUm(result.getLong("ID_PROFISSIONAL")));
                agenda.setDataHoraInicio(result.getTimestamp("DATA_HORA_INICIO").toLocalDateTime());
                agenda.setDataHoraFim(result.getTimestamp("DATA_HORA_FIM").toLocalDateTime());
                agenda.setStatusAgendaEnum(StatusAgendaEnum.fromValor(result.getInt("STATUS_AGENDA")));
                agendamentos.add(agenda);
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
        return agendamentos;
    }

    @Override
    public Agenda listarUm(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            String sql = "SELECT * FROM AGENDA WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery(sql);
            if (result.next()) {
                Agenda agendamento = new Agenda();
                agendamento.setId(result.getLong("ID_PESSOA"));
                agendamento.setCliente(cs.listarUmCliente(result.getLong("ID_CLIENTE")));
                agendamento.setProfissionalMentor(ps.listarUm(result.getLong("ID_PROFISSIONAL")));
                agendamento.setDataHoraInicio(result.getTimestamp("DATA_HORA_INICIO").toLocalDateTime());
                agendamento.setDataHoraFim(result.getTimestamp("DATA_HORA_FIM").toLocalDateTime());
                agendamento.setStatusAgendaEnum(StatusAgendaEnum.fromValor(result.getInt("STATUS_AGENDA")));
                return agendamento;
            }
            return null;
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
    public boolean atualizar(Long id, Agenda agenda) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE AGENDA SET ");
            sql.append(" id_cliente = ?,");
            sql.append(" data_hora_inicio = ?,");
            sql.append(" data_hora_fim = ? ");
            sql.append(" status_agendamento = ? ");
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, agenda.getCliente().getId());
            stmt.setTimestamp(2, Timestamp.valueOf(agenda.getDataHoraInicio()));
            stmt.setTimestamp(3, Timestamp.valueOf(agenda.getDataHoraFim()));
            stmt.setInt(4, agenda.getStatusAgendaEnum().ordinal());
            stmt.setLong(5, id);

            int result = stmt.executeUpdate();
            System.out.println("Agenda de id: "+id+" atualizada com sucesso. \n" + result);
            return result > 0;
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

            String sql = "DELETE FROM AGENDA WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int result = stmt.executeUpdate();
            System.out.println("Removido da agenda com id: " + result);

            return result > 0;
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
