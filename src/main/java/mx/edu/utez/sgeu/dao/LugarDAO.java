package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LugarDAO {

    public ResultSet obtenerLugares(Connection con) throws SQLException {

        String sql = "SELECT ID_LUGAR, NOMBRE FROM LUGARES WHERE ESTADO='ACTIVO' ORDER BY NOMBRE";

        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeQuery();
    }

}