package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;
import mx.edu.utez.sgeu.model.TipoEvento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoEventoDAO {

    public List<TipoEvento> obtenerTipos() {

        List<TipoEvento> tipos = new ArrayList<>();

        String sql = "SELECT * FROM TIPOS_EVENTO WHERE ESTADO='ACTIVO' ORDER BY NOMBRE";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                TipoEvento tipo = new TipoEvento();

                tipo.setIdTipoEvento(rs.getInt("ID_TIPO_EVENTO"));
                tipo.setNombre(rs.getString("NOMBRE"));
                tipo.setDescripcion(rs.getString("DESCRIPCION"));
                tipo.setEstado(rs.getString("ESTADO"));

                tipos.add(tipo);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return tipos;

    }

}