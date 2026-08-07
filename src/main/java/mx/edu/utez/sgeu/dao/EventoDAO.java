package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;
import mx.edu.utez.sgeu.model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public boolean registrarEvento(Evento evento) {

        String sql = "INSERT INTO EVENTOS (" +
                "NOMBRE, DESCRIPCION, FECHA, HORA_INICIO, HORA_FIN, " +
                "CUPO_MAXIMO, ESTADO, ID_LUGAR, ID_TIPO_EVENTO, " +
                "ID_USUARIO, IMAGEN_EVENTO, FECHA_LIMITE_INSCRIPCION" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getDescripcion());
            ps.setDate(3, evento.getFecha());
            ps.setTimestamp(4, evento.getHoraInicio());
            ps.setTimestamp(5, evento.getHoraFin());
            ps.setInt(6, evento.getCupoMaximo());
            ps.setString(7, evento.getEstado());
            ps.setInt(8, evento.getIdLugar());
            ps.setInt(9, evento.getIdTipoEvento());
            ps.setInt(10, evento.getIdUsuario());
            ps.setString(11, evento.getImagenEvento());
            ps.setDate(12, evento.getFechaLimiteInscripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Evento> obtenerEventos() {

        List<Evento> lista = new ArrayList<>();

        String sql = "SELECT * FROM EVENTOS ORDER BY FECHA DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Evento evento = new Evento();

                evento.setIdEvento(rs.getInt("ID_EVENTO"));
                evento.setNombre(rs.getString("NOMBRE"));
                evento.setDescripcion(rs.getString("DESCRIPCION"));
                evento.setFecha(rs.getDate("FECHA"));
                evento.setHoraInicio(rs.getTimestamp("HORA_INICIO"));
                evento.setHoraFin(rs.getTimestamp("HORA_FIN"));
                evento.setCupoMaximo(rs.getInt("CUPO_MAXIMO"));
                evento.setEstado(rs.getString("ESTADO"));
                evento.setIdLugar(rs.getInt("ID_LUGAR"));
                evento.setIdTipoEvento(rs.getInt("ID_TIPO_EVENTO"));
                evento.setIdUsuario(rs.getInt("ID_USUARIO"));
                evento.setImagenEvento(rs.getString("IMAGEN_EVENTO"));
                evento.setFechaLimiteInscripcion(rs.getDate("FECHA_LIMITE_INSCRIPCION"));

                lista.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}