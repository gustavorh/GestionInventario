package dev.gustavorh.gestioninventario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionInventario {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/GestionInventario";
        String user = "java";
        String password = "sasa";

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM productos");

        while (rs.next()) {
            System.out.println(rs.getLong("id"));
            System.out.println(rs.getString("nombre"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
