package br.com.dbc.vemser.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dbc.vemser.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.model.dtos.response.UsuarioResponseCompletoDTO;
import br.com.dbc.vemser.model.entities.Cliente;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.PlanoEnum;
import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import br.com.dbc.vemser.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClienteRepository implements IRepository<Long, Cliente> {

    private final UsuarioService usuarioService;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT VS_13_EQUIPE_6.SEQ_CLIENTE.nextval AS SEQUENCE_CLIENTE FROM DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("SEQUENCE_CLIENTE");
        }
        return null;
    }

    @Override
    public Cliente create(Cliente cliente) throws BancoDeDadosException {
        return null;
    }

    public Cliente create(Cliente cliente, Long idUsuario) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = conexaoBancoDeDados.conectar();

            Long novoId = this.getProximoId(con);
            cliente.setId(novoId);

            String sql = "INSERT INTO CLIENTE\n" +
                         "(ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)\n" +
                         "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, cliente.getId());
            stmt.setLong(2, idUsuario);
            stmt.setInt(3, cliente.getTipoPlano().getValor());
            stmt.setString(4, String.valueOf(cliente.getControleParental()));

            int res = stmt.executeUpdate();
            System.out.println("adicionarCliente.res=" + res);

            return cliente;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public Cliente getById(Long id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM CLIENTE WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(res.getLong("ID"));
                cliente.setUsuario(usuarioService.getUsuario(res.getLong("ID_USUARIO")));
                cliente.setTipoPlano(PlanoEnum.fromValor(res.getInt("TIPO_PLANO")));
                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));

                return cliente;
            } else {
                throw new RegraDeNegocioException("Nenhum cliente encontrado para o Id: " + id);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public List<Cliente> getAll() throws BancoDeDadosException {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT \tU.ID \t\t\t\tAS\tID_USUARIO,\n" +
                    "\t\tU.NOME \t\t\t\tAS\tNOME_USUARIO,\n" +
                    "\t\tU.DATA_NASCIMENTO \tAS\tDATA_NASCIMENTO_USUARIO,\n" +
                    "\t\tU.CPF \t\t\t\tAS\tCPF_USUARIO,\n" +
                    "\t\tU.EMAIL \t\t\tAS\tEMAIL_USUARIO,\n" +
                    "\t\tU.ACESSO_PCD \t\tAS\tACESSO_PCD_USUARIO,\n" +
                    "\t\tU.TIPO_USUARIO \t\tAS\tTIPO_USUARIO,\n" +
                    "\t\tU.INTERESSES \t\tAS\tINTERESSES_USUARIO,\n" +
                    "\t\tU.IMAGEM_DOCUMENTO \tAS\tIMAGEM_DOCUMENTO_USUARIO, \n" +
                    "\t\tC.ID \t\t\t\tAS\tID_CLIENTE,\n" +
                    "\t\tC.TIPO_PLANO \t\tAS\tTIPO_PLANO_CLIENTE,\n" +
                    "\t\tC.CONTROLE_PARENTAL AS\tCONTROLE_PARENTAL_CLIENTE \n" +
                    "\tFROM CLIENTE C JOIN USUARIO U ON (C.ID_USUARIO = U.ID)";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                Cliente cliente = new Cliente();
                cliente.setId(res.getLong("ID_CLIENTE"));
                cliente.setTipoPlano(PlanoEnum.fromValor(res.getInt("TIPO_PLANO_CLIENTE")));
                cliente.setControleParental(res.getString("CONTROLE_PARENTAL_CLIENTE").charAt(0));
                usuario.setId(res.getLong("id_usuario"));
                usuario.setNome(res.getString("nome_usuario"));
                usuario.setDataNascimento(res.getDate("data_nascimento_usuario").toLocalDate());
                usuario.setCpf(res.getString("cpf_usuario"));
                usuario.setEmail(res.getString("email_usuario"));
                usuario.setAcessoPcd(res.getString("acesso_pcd_usuario").charAt(0));
                usuario.setTipoUsuario(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                usuario.setInteresses(res.getString("interesses_usuario"));
                usuario.setImagemDocumento(res.getString("imagem_documento_usuario"));
                cliente.setUsuario(usuario);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
        return clientes;
    }

    @Override
    public boolean update(Long id, Cliente cliente) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTE SET ");

            sql.append(" TIPO_PLANO = ?, ");
            sql.append(" CONTROLE_PARENTAL = ? ");
            sql.append(" WHERE ID = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setInt(1, cliente.getTipoPlano().getValor());
            stmt.setString(2, String.valueOf(cliente.getControleParental()).toUpperCase());
            stmt.setLong(3, id);

            int res = stmt.executeUpdate();
            System.out.println("editarCliente.res=" + res);

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

            String sql = "DELETE FROM CLIENTE WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerClientePorId.res=" + res);

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }
}