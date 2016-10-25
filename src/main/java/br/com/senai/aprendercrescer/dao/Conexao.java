
package br.com.senai.aprendercrescer.dao;

import br.com.senai.aprendercrescer.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/database_tcc",
                    "postgres",
                    "postgres1");
        }
        return conexao;
    }

}
