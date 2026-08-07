package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;
import mx.edu.utez.sgeu.model.Lugar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LugarDAO {

    public List<Lugar> obtenerLugares() {

        List<Lugar> lugares = new ArrayList<>();

        String sql = "SELECT ID_LUGAR, NOMBRE FROM LUGARES ORDER BY NOMBRE";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Lugar lugar = new Lugar();

                lugar.setIdLugar(rs.getInt("ID_LUGAR"));
                lugar.setNombre(rs.getString("NOMBRE"));

                lugares.add(lugar);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lugares;
    }

}