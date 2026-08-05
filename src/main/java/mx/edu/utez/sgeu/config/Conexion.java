package mx.edu.utez.sgeu.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "SGEU";
    private static final String PASSWORD = "sgeu123";

    public static Connection getConnection() {
        Connection conexion = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a Oracle");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver de Oracle");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con Oracle");
            e.printStackTrace();
        }

        return conexion;
    }
}