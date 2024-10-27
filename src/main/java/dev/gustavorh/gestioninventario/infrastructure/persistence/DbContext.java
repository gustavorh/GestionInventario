package dev.gustavorh.gestioninventario.infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    private static String url = "jdbc:mysql://localhost:3306/GestionInventario";
    private static String user = "java";
    private static String password = "sasa";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
