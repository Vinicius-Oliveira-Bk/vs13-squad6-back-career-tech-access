package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {

    private static final String HOST = "vemser-dbc.dbccompany.com.br";
    private static final int PORTA = 25000;
    private static final String DB = "xe";
    private static final String USUARIO = "VS_13_EQUIPE_6";
    private static final String SENHA = "oracle";
    private static final String SCHEMA = "VS_13_EQUIPE_6";

    public static void main(String[] args) {
        Connection connection = conectar();

        //fecharConexao(connection);
    }

    public static Connection conectar() {
        Connection connection = null;

        try {
            // Carregar o driver JDBC do Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@" + HOST + ":" + PORTA + "/" + DB;

            Connection con = DriverManager.getConnection(url, USUARIO, SENHA);

            con.createStatement().execute("alter session set current_schema=" + SCHEMA);

            System.out.println("Conexão bem-sucedida!");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do Oracle não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }

        return connection;
    }

    public static void fecharConexao(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexão fechada!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}