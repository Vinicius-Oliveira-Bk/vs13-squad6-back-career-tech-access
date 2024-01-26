package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Pcd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PcdRepository implements IRepository<Long, Pcd> {

    private final ConexaoBancoDeDados conexaoBancoDeDados;

    private final ClienteRepository clienteRepository;
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
    public Pcd create(Pcd pcd) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();

            Long novoId = this.getProximoId(con);
            pcd.setId(novoId);

            String sql = "INSERT INTO PCD\n" +
                "(ID, ID_CLIENTE, TIPO_DEFICIENCIA, CERTIFICADO_DEFICIENCIA_GOV)\n" +
                "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, pcd.getId());
            stmt.setLong(2, pcd.getCliente() != null ? pcd.getCliente().getId() : null);
            stmt.setString(3, pcd.getTipoDeficiencia());
            stmt.setString(4, pcd.getCertificadoDeficienciaGov());

            int res = stmt.executeUpdate();

            return pcd;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public Pcd getById(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM PCD WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Pcd pcd = new Pcd();
                pcd.setId(res.getLong("ID"));
                pcd.setCliente(clienteRepository.getById(res.getLong("ID_CLIENTE")));
                pcd.setTipoDeficiencia(res.getString("TIPO_DEFICIENCIA"));
                pcd.setCertificadoDeficienciaGov(res.getString("CERTIFICADO_DEFICIENCIA_GOV"));

                return pcd;
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Pcd> getAll() throws BancoDeDadosException {
        List<Pcd> pcds = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM PCD";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Pcd pcd = new Pcd();
                pcd.setId(res.getLong("ID"));
                pcd.setCliente(clienteRepository.getById(res.getLong("ID_CLIENTE")));
                pcd.setTipoDeficiencia(res.getString("TIPO_DEFICIENCIA"));
                pcd.setCertificadoDeficienciaGov(res.getString("CERTIFICADO_DEFICIENCIA_GOV"));

                pcds.add(pcd);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
        return pcds;
    }



    @Override
    public boolean update(Long id, Pcd pcd) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PCD SET ");
            sql.append(" ID_CLIENTE = ?,");
            sql.append(" ID_PCD = ?,");
            sql.append(" TIPO_DEFICIENCIA = ?,");
            sql.append(" CERTIFICADO_DEFICIENCIA_GOV = ? ");
            sql.append(" WHERE ID = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, pcd.getCliente().getId());
            stmt.setLong(2, pcd.getId());
            stmt.setString(3, pcd.getTipoDeficiencia());
            stmt.setString(4, pcd.getCertificadoDeficienciaGov());
            stmt.setLong(5, id);

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

            String sql = "DELETE FROM PCD WHERE ID = ?";

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