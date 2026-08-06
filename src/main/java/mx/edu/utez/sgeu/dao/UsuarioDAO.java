package mx.edu.utez.sgeu.dao;

import mx.edu.utez.sgeu.config.Conexion;
import mx.edu.utez.sgeu.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDAO {


    // REGISTRO DE USUARIO
    public boolean registrarUsuario(Usuario usuario) {

        String sql = "INSERT INTO USUARIOS " +
                "(NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, MATRICULA, CORREO, CONTRASENA, ESTADO, ID_ROL) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidoPaterno());
            ps.setString(3, usuario.getApellidoMaterno());
            ps.setString(4, usuario.getMatricula());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getContrasena());
            ps.setString(7, usuario.getEstado());
            ps.setInt(8, usuario.getIdRol());


            int filas = ps.executeUpdate();

            return filas > 0;


        } catch (SQLException e) {

            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }



    // LOGIN DE USUARIO
    public Usuario login(String correo, String contrasena) {


        Usuario usuario = null;


        String sql = "SELECT * FROM USUARIOS " +
                "WHERE CORREO = ? " +
                "AND CONTRASENA = ? " +
                "AND ESTADO = 'ACTIVO'";


        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


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
                usuario.setContrasena(rs.getString("CONTRASENA"));
                usuario.setEstado(rs.getString("ESTADO"));
                usuario.setIdRol(rs.getInt("ID_ROL"));

            }


        } catch (SQLException e) {

            System.out.println("Error en login: " + e.getMessage());

        }


        return usuario;
    }




    // VERIFICAR SI EXISTE EL CORREO
    public boolean existeCorreo(String correo) {


        String sql = "SELECT COUNT(*) FROM USUARIOS WHERE CORREO = ?";


        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, correo);


            ResultSet rs = ps.executeQuery();


            if (rs.next()) {

                return rs.getInt(1) > 0;

            }


        } catch (SQLException e) {

            System.out.println("Error verificando correo: " + e.getMessage());

        }


        return false;
    }

}