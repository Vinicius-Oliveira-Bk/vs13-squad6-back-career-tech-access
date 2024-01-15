package com.dbc.repository;

import com.dbc.exceptions.BancoDeDadosException;
import com.dbc.model.entities.Cliente;
import com.dbc.model.entities.ProfissionalRealocacao;
import com.dbc.model.entities.Usuario;
import com.dbc.model.enums.PlanoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalRealocacaoRepository implements IRepository<Long, ProfissionalRealocacao> {
    @Override
    public Long getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_profissional_realocacao.nextval mysequence from DUAL";
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
    public ProfissionalRealocacao cadastrar(ProfissionalRealocacao profissionalRealocacao)
            throws BancoDeDadosException {
        return null;
    }

    public ProfissionalRealocacao cadastrar(ProfissionalRealocacao profissionalRealocacao, Long idCliente)
            throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.conectar();

            Long proximoId = this.getProximoId(con);
            profissionalRealocacao.setId(proximoId);

            String sql = "INSERT INTO PROFISSIONAL_REALOCACAO\n" +
                    "(ID, ID_CLIENTE, PROFISSAO, OBJETIVO_PROFISSIONAL)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, profissionalRealocacao.getId());
            stmt.setLong(2, idCliente);
            stmt.setString(3, profissionalRealocacao.getProfissao());
            stmt.setString(4, profissionalRealocacao.getObjetivoProfissional());

            int res = stmt.executeUpdate();
            System.out.println("adicionarProfissionalRealocacao.res=" + res);
            return profissionalRealocacao;
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
    public ProfissionalRealocacao listarUm(Long id) throws BancoDeDadosException {
        ProfissionalRealocacao profissionalRealocacao = null;
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            String sql = "SELECT * FROM PROFISSIONAL_REALOCACAO WHERE ID = ?";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setLong(1, id);

                try (ResultSet res = pstmt.executeQuery()) {
                    if (res.next()) {
                        profissionalRealocacao = getProfissionalRealocacaoFromResultSet(res);
                        System.out.println(profissionalRealocacao);
                    } else {
                        System.out.println("Nenhum profissional encontrado para o Id: " + id);
                    }
                }
            }

            return profissionalRealocacao;

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
    public List<ProfissionalRealocacao> listar() throws BancoDeDadosException {
        List<ProfissionalRealocacao> profissionalRealocacaos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();
            Statement stmt = con.createStatement();

            String sql = "SELECT * \n" +
                    "    FROM PROFISSIONAL_REALOCACAO PR \n" +
                    "    JOIN CLIENTE C ON (PR.ID_CLIENTE = C.ID)\n" +
                    "    JOIN USUARIO U ON (C.ID_USUARIO = U.ID)";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                ProfissionalRealocacao profissionalRealocacao = getProfissionalRealocacaoFromResultSet(res);
                profissionalRealocacaos.add(profissionalRealocacao);
            }
            return profissionalRealocacaos;
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
    public boolean atualizar(Long id, ProfissionalRealocacao profissionalRealocacao) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.conectar();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE PROFISSIONAL_REALOCACAO SET \n");

            if (profissionalRealocacao.getProfissao() != null) {
                sql.append(" profissao = ?,");
            }
            if (profissionalRealocacao.getObjetivoProfissional() != null) {
                sql.append(" objetivo_profissional = ?,");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" WHERE id = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            int index = 1;
            if (profissionalRealocacao.getProfissao() != null) {
                stmt.setString(index++, profissionalRealocacao.getProfissao());
            }
            if (profissionalRealocacao.getObjetivoProfissional() != null) {
                stmt.setString(index++, profissionalRealocacao.getObjetivoProfissional());
            }

            stmt.setLong(index++, id);

            int res = stmt.executeUpdate();
            System.out.println("editarProfissionalRealocacao.res=" + res);

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

            String sql = "DELETE FROM PROFISSIONAL_REALOCACAO WHERE ID = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            int res = stmt.executeUpdate();
            System.out.println("removerProfissionalRealocacaoPorId.res=" + res);

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

    private ProfissionalRealocacao getProfissionalRealocacaoFromResultSet(ResultSet res) throws SQLException {
        ProfissionalRealocacao profissionalRealocacao = new ProfissionalRealocacao();
        profissionalRealocacao.setId(res.getLong("pr.id"));
        profissionalRealocacao.setProfissao(res.getString("pr.profissao"));
        profissionalRealocacao.setObjetivoProfissional(res.getString("pr.objetivo_profissional"));

        Cliente cliente = new Cliente();
        cliente.setPlano(PlanoEnum.fromValor(res.getInt("c.tipo_plano")));
        cliente.setControleParental(res.getString("c.controle_parental").charAt(0));
        Usuario usuario = new Usuario();
        usuario.setId(res.getLong("u.id"));
        usuario.setNome(res.getString("u.nome"));
        usuario.setDataNascimento(res.getDate("u.data_nascimento").toLocalDate());
        usuario.setCpf(res.getString("u.cpf"));
        usuario.setEmail(res.getString("u.email"));
        usuario.setSenha(res.getString("u.senha"));
        usuario.setAcessoPcd(res.getString("u.acesso_pcd").charAt(0));
        usuario.setTipoUsuario(res.getLong("u.tipo_usuario"));
        usuario.setInteresses(res.getString("u.interesses"));
        usuario.setImagemDocumento(res.getString("u.imagem_documento"));

        // cliente.setUsuario(usuario);
        // profissionalRealocacao.setCliente(cliente);

        return profissionalRealocacao;
    }
}
