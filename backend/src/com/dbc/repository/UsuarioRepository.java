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
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.TipoUsuarioEnum;

public class UsuarioRepository implements IRepository<Long, Usuario> {
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
    public Usuario cadastrar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            usuario.setId(proximoId);

            String sql = "INSERT INTO USUARIO (ID, NOME, DATA_NASCIMENTO, CPF, EMAIL, SENHA, ACESSO_PCD, TIPO_USUARIO, INTERESSES, IMAGEM_DOCUMENTO) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setTimestamp(3, Timestamp.valueOf(usuario.getDataNascimento().atStartOfDay()));
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.setString(7, String.valueOf(usuario.getAcessoPcd()));
            stmt.setLong(8, usuario.getTipo().ordinal());
            stmt.setString(9, usuario.getInteresses());
            stmt.setString(10, usuario.getImagemDocumento());

            stmt.executeUpdate();

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
    public Usuario listarUm(Long id) throws BancoDeDadosException {
        Usuario usuario = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
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
                    usuario.setSenha(res.getString("senha"));
                    usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                    usuario.setTipo(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                    usuario.setInteresses(res.getString("interesses"));
                    usuario.setImagemDocumento(res.getString("imagem_documento"));
                }
            }
        } catch (SQLException e) {
            // Trate a exceção ou relance-a conforme necessário
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

        return usuario;
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
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                usuario.setTipo(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                usuario.setInteresses(res.getString("interesses"));
                usuario.setImagemDocumento(res.getString("imagem_documento"));
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

    @Override
    public boolean atualizar(Long id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

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
            if (usuario.getSenha() != null) {
                sql.append(" senha = ?,");
            }
            if (usuario.getAcessoPcd() != 999) {
                sql.append(" acesso_pcd = ?,");
            }
            if (usuario.getTipo() != null) {
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
            if (usuario.getSenha() != null) {
                stmt.setString(index++, usuario.getSenha());
            }
            if (usuario.getAcessoPcd() != null) {
                stmt.setString(index++, String.valueOf(usuario.getAcessoPcd()));
            }
            if (usuario.getTipo() != null) {
                stmt.setLong(index++, usuario.getTipo().ordinal());
            }
            if (usuario.getInteresses() != null) {
                stmt.setString(index++, usuario.getInteresses());
            }
            if (usuario.getImagemDocumento() != null) {
                stmt.setString(index++, usuario.getImagemDocumento());
            }

            stmt.setLong(index++, id);

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

    public List<Usuario> getUsuarioNaoProfissional() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO U " +
                    "LEFT JOIN PROFISSIONAL_MENTOR P ON (P.ID_USUARIO = U.ID) " +
                    "WHERE P.ID_USUARIO IS NULL";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getLong("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                usuario.setTipo(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                usuario.setInteresses(res.getString("interesses"));
                usuario.setImagemDocumento(res.getString("imagem_documento"));
                usuarios.add(usuario);
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
        return usuarios;
    }

    public List<Usuario> getUsuarioNaoCliente() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO U " +
                    "LEFT JOIN CLIENTE C ON (C.ID_USUARIO = U.ID) " +
                    "WHERE C.ID_USUARIO IS NULL";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getLong("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("data_nascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuario.setAcessoPcd(res.getString("acesso_pcd").charAt(0));
                usuario.setTipo(TipoUsuarioEnum.fromValor(res.getInt("tipo_usuario")));
                usuario.setInteresses(res.getString("interesses"));
                usuario.setImagemDocumento(res.getString("imagem_documento"));
                usuarios.add(usuario);
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
        return usuarios;
    }
}
