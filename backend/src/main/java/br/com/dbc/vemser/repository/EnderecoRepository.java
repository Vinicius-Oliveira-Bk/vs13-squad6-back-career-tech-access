package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.entities.Endereco;
import br.com.dbc.vemser.model.enums.TipoEnum;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepository implements IRepository<Long, Endereco> {
    @Override
    public Long getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_endereco.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getLong("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Endereco create(Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            endereco.setId(proximoId);

            String sql = "INSERT INTO ENDERECO\n" +
                    "(ID, LOGRADOURO, NUMERO, COMPLEMENTO, CEP, CIDADE, ESTADO, PAIS, TIPO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, endereco.getId());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());
            stmt.setInt(9, endereco.getTipo().getValor());

            int res = stmt.executeUpdate();
            System.out.println("adicionarEndereco.res=" + res);
            return endereco;
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
    public Endereco getById(Long id) throws BancoDeDadosException {
        Endereco endereco = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM ENDERECO WHERE ID = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setLong(1, id);

                try (ResultSet res = pstmt.executeQuery()) {
                    if (res.next()) {
                        endereco = getEnderecoFromResultSet(res);
                    } else {
                        throw new RegraDeNegocioException("Nenhum endereço encontrado para o Id: " + id);
                    }
                } catch (RegraDeNegocioException e) {
                    throw new RuntimeException(e);
                }
            }
            return endereco;

        } catch (SQLException e) {
            e.printStackTrace();
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
    public List<Endereco> getAll() throws BancoDeDadosException {
        List<Endereco> enderecos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM ENDERECO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Endereco endereco = getEnderecoFromResultSet(res);
                enderecos.add(endereco);
            }
            return enderecos;
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
    public boolean update(Long id, Endereco endereco) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ENDERECO SET \n");

            sql.append(" logradouro = ?,");
            sql.append(" numero = ?,");
            sql.append(" complemento = ?,");
            sql.append(" cep = ?,");
            sql.append(" cidade = ?,");
            sql.append(" estado = ?,");
            sql.append(" pais = ?,");
            sql.append(" tipo = ?,");

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            stmt.setString(index++, endereco.getLogradouro());
            stmt.setString(index++, endereco.getNumero());
            stmt.setString(index++, endereco.getComplemento());
            stmt.setString(index++, endereco.getCep());
            stmt.setString(index++, endereco.getCidade());
            stmt.setString(index++, endereco.getEstado());
            stmt.setString(index++, endereco.getPais());
            stmt.setInt(index++, endereco.getTipo().getValor());

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarEndereco.res=" + res);

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

            String sql = "DELETE FROM ENDERECO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerEnderecoPorId.res=" + res);

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

    private Endereco getEnderecoFromResultSet(ResultSet res) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(res.getLong("id"));
        endereco.setLogradouro(res.getString("logradouro"));
        endereco.setNumero(res.getString("numero"));
        endereco.setComplemento(res.getString("complemento"));
        endereco.setCep(res.getString("cep"));
        endereco.setCidade(res.getString("cidade"));
        endereco.setEstado(res.getString("estado"));
        endereco.setPais(res.getString("pais"));
        endereco.setTipo(TipoEnum.fromValor(res.getInt("tipo")));
        return endereco;
    }
}
