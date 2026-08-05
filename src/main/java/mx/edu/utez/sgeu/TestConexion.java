package mx.edu.utez.sgeu;

import mx.edu.utez.sgeu.config.Conexion;
import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {

        Connection con = Conexion.getConnection();

        if (con != null) {
            System.out.println("PRUEBA CORRECTA");
        } else {
            System.out.println("PRUEBA FALLIDA");
        }
    }
}