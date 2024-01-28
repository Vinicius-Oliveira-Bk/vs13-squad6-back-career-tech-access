package br.com.dbc.vemser.repository;

import br.com.dbc.vemser.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.model.entities.Usuario;
import br.com.dbc.vemser.model.enums.TipoUsuarioEnum;
import br.com.dbc.vemser.services.ContatoService;
import br.com.dbc.vemser.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository implements IRepository<Long, Usuario> {

    private final EnderecoService enderecoService;
    private final ContatoService contatoService;
    private final ConexaoBancoDeDados conexaoBancoDeDados;

    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_usuario.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("mysequence");
        }

        return null;
    }

    @Override
    public Usuario create(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = "INSERT INTO USUARIO (ID, NOME, DATA_NASCIMENTO, CPF, EMAIL, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO) " +
                         "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setTimestamp(3, Timestamp.valueOf(usuario.getDataNascimento().atStartOfDay()));
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, String.valueOf(usuario.getAcessoPcd()));
            stmt.setLong(7, usuario.getTipoUsuario().getValor());
            stmt.setString(8, usuario.getInteresses());
            stmt.setString(9, usuario.getImagemDocumento());

            stmt.executeUpdate();

            return usuario;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
    }

    @Override
    public Usuario getById(Long id) throws BancoDeDadosException {
        Usuario usuario = null;
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            String sql = "SELECT * FROM USUARIO WHERE id = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, id);
                ResultSet res = stmt.executeQuery();

                if (res.next()) {
                    usuario = new Usuario();
                    usuario.setId(res.getLong("id"));
                    usuario.setNome(res.getString("nome"));
                    usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                    usuario.setCpf(res.getString("cpf"));
                    usuario.setEmail(res.getString("email"));
                    usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                    usuario.setTipoUsuario(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                    usuario.setInteresses(res.getString("interesses"));
                    usuario.setEnderecos(enderecoService.getEnderecosByUser(usuario.getId()));
                    usuario.setContatos(contatoService.getContatosByUser(usuario.getId()));
                    usuario.setImagemDocumento(res.getString("imagem_documento"));
                }
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e);
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }

        return usuario;
    }

    @Override
    public List<Usuario> getAll() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getLong("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                usuario.setTipoUsuario(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                usuario.setInteresses(res.getString("interesses"));
                usuario.setImagemDocumento(res.getString("imagem_documento"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            conexaoBancoDeDados.closeConnection(con);
        }
        return usuarios;
    }

    @Override
    public boolean update(Long id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = conexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET \n");

            if (usuario.getNome() != null) {
                sql.append(" nome = ?,");
            }
            if (usuario.getDataNascimento() != null) {
                sql.append(" data_nascimento = ?,");
            }
            if (usuario.getCpf() != null) {
                sql.append(" cpf = ?,");
            }
            if (usuario.getEmail() != null) {
                sql.append(" email = ?,");
            }
            if (usuario.getAcessoPcd() != 999) {
                sql.append(" acesso_pcd = ?,");
            }
            if (usuario.getTipoUsuario() != null) {
                sql.append(" tipo_usuario = ?,");
            }
            if (usuario.getInteresses() != null) {
                sql.append(" interesses = ?,");
            }
            if (usuario.getImagemDocumento() != null) {
                sql.append(" imagem_documento = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (usuario.getNome() != null) {
                stmt.setString(index++, usuario.getNome());
            }
            if (usuario.getDataNascimento() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(usuario.getDataNascimento().atStartOfDay()));
            }
            if (usuario.getCpf() != null) {
                stmt.setString(index++, usuario.getCpf());
            }
            if (usuario.getEmail() != null) {
                stmt.setString(index++, usuario.getEmail());
            }
            if (usuario.getAcessoPcd() != null) {
                stmt.setString(index++, String.valueOf(usuario.getAcessoPcd()));
            }
            if (usuario.getTipoUsuario() != null) {
                stmt.setLong(index++, usuario.getTipoUsuario().getValor());
            }
            if (usuario.getInteresses() != null) {
                stmt.setString(index++, usuario.getInteresses());
            }
            if (usuario.getImagemDocumento() != null) {
                stmt.setString(index++, usuario.getImagemDocumento());
            }

            stmt.setLong(index++, id);

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

            String sql = "DELETE FROM USUARIO WHERE ID = ?";

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

    public static Usuario getUsuarioByResultSet(ResultSet res) throws SQLException {
        Usuario usuario = new Usuario();

        usuario.setId(res.getLong("ID_USUARIO"));
        usuario.setEmail(res.getString("EMAIL"));
        usuario.setNome(res.getString("NOME"));
        usuario.setTipoUsuario(TipoUsuarioEnum.fromValor(res.getInt("TIPO_USUARIO")));
        usuario.setCpf(res.getString("CPF"));
        usuario.setAcessoPcd(res.getString("ACESSO_PCD").charAt(0));
        usuario.setInteresses(res.getString("INTERESSES"));
        usuario.setImagemDocumento(res.getString("IMAGEM_DOCUMENTO"));
        usuario.setDataNascimento(res.getDate("DATA_NASCIMENTO").toLocalDate());

        return usuario;
    }
}
