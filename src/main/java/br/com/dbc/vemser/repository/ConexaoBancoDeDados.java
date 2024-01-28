package br.com.dbc.vemser.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConexaoBancoDeDados {

        @Value("${spring.datasource.url}")
        private String SERVER;
        @Value("${spring.datasource.username}")
        private String USER;
        @Value("${spring.datasource.password}")
        private String PASS;
        @Value("${spring.jpa.properties.hibernate.default_schema}")
        private String SCHEMA;


        public Connection conectar() throws SQLException {
            Connection con = DriverManager.getConnection(SERVER, USER, PASS);
            con.createStatement().execute("alter session set current_schema=" + SCHEMA);

            return con;
        }

        public void closeConnection(Connection connection) {
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
