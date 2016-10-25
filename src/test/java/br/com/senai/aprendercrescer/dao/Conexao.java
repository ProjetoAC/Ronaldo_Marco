/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.aprendercrescer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Caluan Baierle
 */
public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/banco_pessoal",
                    "postgres",
                    "mobile");
        }
        return conexao;
    }

}
