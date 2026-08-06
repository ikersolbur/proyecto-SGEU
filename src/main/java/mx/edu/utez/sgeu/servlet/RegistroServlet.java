package mx.edu.utez.sgeu.servlet;

import mx.edu.utez.sgeu.dao.UsuarioDAO;
import mx.edu.utez.sgeu.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String matricula = request.getParameter("matricula");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setMatricula(matricula);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        // 1 = Usuario normal
        usuario.setIdRol(1);

        usuario.setEstado("ACTIVO");

        UsuarioDAO dao = new UsuarioDAO();

        boolean resultado = dao.registrarUsuario(usuario);

        if (resultado) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("registro.jsp?error=true");
        }
    }
}