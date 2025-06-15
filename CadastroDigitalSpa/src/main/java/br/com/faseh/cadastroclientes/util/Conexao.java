package br.com.faseh.cadastroclientes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_clientes?allowPublicKeyRetrieval=TRUE";
    private static final String USER = "root";
    private static final String PASSWORD = "123456"; // <- Altere conforme sua senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
