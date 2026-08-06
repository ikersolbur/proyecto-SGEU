package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;
import mx.edu.utez.sgeu.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public Usuario login(String correo, String contrasena) {

        Usuario usuario = null;

        String sql = "SELECT * FROM USUARIOS WHERE CORREO = ? AND CONTRASENA = ?";

        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                usuario.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                usuario.setMatricula(rs.getString("MATRICULA"));
                usuario.setCorreo(rs.getString("CORREO"));
                usuario.setPassword(rs.getString("CONTRASENA"));
                usuario.setEstado(rs.getString("ESTADO"));
                usuario.setIdRol(rs.getInt("ID_ROL"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

}