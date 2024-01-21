//package br.com.dbc.vemser.repository;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.com.dbc.vemser.model.entities.Cliente;
//import br.com.dbc.vemser.model.enums.PlanoEnum;
//import br.com.dbc.vemser.exceptions.BancoDeDadosException;
//import br.com.dbc.vemser.services.UsuarioService;
//
//public class ClienteRepository implements IRepository<Long, Cliente> {
//    UsuarioService us = new UsuarioService();
//
//    @Override
//    public Long getProximoId(Connection connection) throws SQLException {
//        String sql = "SELECT VS_13_EQUIPE_6.SEQ_CLIENTE.nextval AS SEQUENCE_CLIENTE FROM DUAL";
//
//        Statement stmt = connection.createStatement();
//        ResultSet res = stmt.executeQuery(sql);
//
//        if (res.next()) {
//            return res.getLong("SEQUENCE_CLIENTE");
//        }
//        return null;
//    }
//
//    @Override
//    public Cliente create(Cliente cliente) throws BancoDeDadosException {
//        return null;
//    }
//
//    public Cliente create(Cliente cliente, Long idUsuario) throws BancoDeDadosException {
//        Connection con = null;
//
//        try {
//            con = ConexaoBancoDeDados.conectar();
//
//            Long novoId = this.getProximoId(con);
//            cliente.setId(novoId);
//
//            String sql = "INSERT INTO VS_13_EQUIPE_6.CLIENTE\n" +
//                    "(ID, ID_USUARIO, TIPO_PLANO, CONTROLE_PARENTAL)\n" +
//                    "VALUES(?, ?, ?, ?)\n";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setLong(1, cliente.getId());
//            stmt.setLong(2, idUsuario);
//            stmt.setInt(3, cliente.getPlano().getValor());
//            stmt.setString(4, String.valueOf(cliente.getControleParental()));
//
//            int res = stmt.executeUpdate();
//            System.out.println("adicionarCliente.res=" + res);
//
//            return cliente;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public Cliente getById(Long id) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//
//            String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setLong(1, id);
//
//            ResultSet res = stmt.executeQuery();
//
//            if (res.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(res.getLong("ID"));
//                cliente.setUsuario(us.listarUm(res.getLong("ID_USUARIO")));
//                cliente.setPlano(PlanoEnum.fromValor(res.getInt("TIPO_PLANO")));
//                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
//
//                return cliente;
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Cliente> getAll() throws BancoDeDadosException {
//        List<Cliente> clientes = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM VS_13_EQUIPE_6.CLIENTE";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(res.getLong("ID"));
//                cliente.setUsuario(us.listarUm(res.getLong("ID_USUARIO")));
//                cliente.setPlano(PlanoEnum.valueOf(res.getString("TIPO_PLANO")));
//                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
//                clientes.add(cliente);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return clientes;
//    }
//
//    @Override
//    public boolean update(Long id, Cliente cliente) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//
//            StringBuilder sql = new StringBuilder();
//            sql.append("UPDATE VS_13_EQUIPE_6.CLIENTE SET ");
//            sql.append(" ID_USUARIO = ?,");
//            sql.append(" TIPO_CLIENTE = ?,");
//            sql.append(" TIPO_PLANO = ?, ");
//            sql.append(" CONTROLE_PARENTAL = ? ");
//            sql.append(" WHERE ID = ?");
//
//            PreparedStatement stmt = con.prepareStatement(sql.toString());
//
//            stmt.setLong(1, cliente.getId());
//            stmt.setString(2, cliente.getPlano().name());
//            stmt.setString(3, String.valueOf(cliente.getControleParental()));
//            stmt.setLong(4, id);
//
//            int res = stmt.executeUpdate();
//            System.out.println("editarCliente.res=" + res);
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public boolean delete(Long id) throws BancoDeDadosException {
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//
//            String sql = "DELETE FROM CLIENTE WHERE ID = ?";
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            stmt.setLong(1, id);
//
//            int res = stmt.executeUpdate();
//            System.out.println("removerClientePorId.res=" + res);
//
//            return res > 0;
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<Cliente> getClientesNaoPcd() throws BancoDeDadosException {
//        List<Cliente> clientes = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM CLIENTE C "+
//                            "LEFT JOIN PCD P ON (P.ID_CLIENTE = C.ID) " +
//                            "WHERE P.ID_CLIENTE IS NULL";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(res.getLong("ID"));
//                cliente.setUsuario(us.listarUm(res.getLong("ID_USUARIO")));
//                cliente.setPlano(PlanoEnum.valueOf(res.getString("TIPO_PLANO")));
//                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
//                clientes.add(cliente);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return clientes;
//    }
//
//    public List<Cliente> getClientesNaoEstudante() throws BancoDeDadosException {
//        List<Cliente> clientes = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM CLIENTE C "+
//                    "LEFT JOIN ESTUDANTE E ON (E.ID_CLIENTE = C.ID) " +
//                    "WHERE E.ID_CLIENTE IS NULL";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(res.getLong("ID"));
//                cliente.setUsuario(us.listarUm(res.getLong("ID_USUARIO")));
//                cliente.setPlano(PlanoEnum.valueOf(res.getString("TIPO_PLANO")));
//                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
//                clientes.add(cliente);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return clientes;
//    }
//
//    public List<Cliente> getClientesNaoProfissionalRealocacao() throws BancoDeDadosException {
//        List<Cliente> clientes = new ArrayList<>();
//        Connection con = null;
//        try {
//            con = ConexaoBancoDeDados.conectar();
//            Statement stmt = con.createStatement();
//
//            String sql = "SELECT * FROM CLIENTE C "+
//                    "LEFT JOIN PROFISSIONAL_REALOCACAO PR ON (PR.ID_CLIENTE = C.ID) " +
//                    "WHERE PR.ID_CLIENTE IS NULL";
//
//            ResultSet res = stmt.executeQuery(sql);
//
//            while (res.next()) {
//                Cliente cliente = new Cliente();
//                cliente.setId(res.getLong("ID"));
//                cliente.setUsuario(us.listarUm(res.getLong("ID_USUARIO")));
//                cliente.setPlano(PlanoEnum.valueOf(res.getString("TIPO_PLANO")));
//                cliente.setControleParental(res.getString("CONTROLE_PARENTAL").charAt(0));
//                clientes.add(cliente);
//            }
//        } catch (SQLException e) {
//            throw new BancoDeDadosException(e.getCause());
//        } finally {
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return clientes;
//    }
//}