package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements IRepository<Long, Usuario> {

    @Override
    public Long getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_cliente.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getLong("mysequence");
        }

        return null;
    }

    @Override
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = "INSERT INTO USUARIO\n" +
                    "(ID, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA, ACESSOPCD, TIPOUSUARIO, INTERESSES, IMAGEMDOCUMENTO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, String.valueOf(usuario.getAcessoPcd()));
            stmt.setLong(8, usuario.getTipoUsuario());
            stmt.setString(9, usuario.getInteresses());
            stmt.setString(10, usuario.getImagemDocumento());

            int res = stmt.executeUpdate();
            System.out.println("adicionarUsuario.res=" + res);
            return usuario;
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

            String sql = "DELETE FROM USUARIO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerUsuarioPorId.res=" + res);

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
    public boolean editar(Long id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" nome = ?,");
            sql.append(" dataNascimento = ? ");
            sql.append(" cpf = ? ");
            sql.append(" email = ? ");
            sql.append(" senha = ? ");
            sql.append(" acessoPcd = ? ");
            sql.append(" tipoUsuario = ? ");
            sql.append(" interesses = ? ");
            sql.append(" imagemDocumento = ? ");
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setObject(3, java.sql.Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, String.valueOf(usuario.getAcessoPcd()));
            stmt.setLong(8, usuario.getTipoUsuario());
            stmt.setString(9, usuario.getInteresses());
            stmt.setString(10, usuario.getImagemDocumento());

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

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
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getLong("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("dataNascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuario.setAcessoPcd(res.getString("acessoPcd").charAt(0));
                usuario.setTipoUsuario(res.getLong("tipoUsuario"));
                usuario.setInteresses(res.getString("interesses"));
                usuario.setImagemDocumento(res.getString("imagemDocumento"));
                usuarios.add(usuario);
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
        return usuarios;
    }

    public Usuario listarUmUsuario(Integer id) throws BancoDeDadosException {
        Usuario usuario = null;
        try (Connection con = ConexaoBancoDeDados.conectar()) {
            String sql = "SELECT * FROM USUARIO WHERE ID = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet res = stmt.executeQuery()) {
                    if (res.next()) {
                        usuario = new Usuario();
                        usuario.setId(res.getLong("id"));
                        usuario.setNome(res.getString("nome"));
                        usuario.setDataNascimento(res.getDate("dataNascimento").toLocalDate());
                        usuario.setCpf(res.getString("cpf"));
                        usuario.setEmail(res.getString("email"));
                        usuario.setSenha(res.getString("senha"));
                        usuario.setAcessoPcd(res.getString("acessoPcd").charAt(0));
                        usuario.setTipoUsuario(res.getLong("tipoUsuario"));
                        usuario.setInteresses(res.getString("interesses"));
                        usuario.setImagemDocumento(res.getString("imagemDocumento"));
                    }
                }
            } catch (SQLException e) {
                throw new BancoDeDadosException(e.getCause());
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }

        return usuario;
    }

}
