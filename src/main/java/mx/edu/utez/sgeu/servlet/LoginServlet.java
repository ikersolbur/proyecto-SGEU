package mx.edu.utez.sgeu.servlet;

import mx.edu.utez.sgeu.dao.UsuarioDAO;
import mx.edu.utez.sgeu.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("password");

        System.out.println("Correo recibido: " + correo);
        System.out.println("Contraseña recibida: " + contrasena);

        Usuario usuario = usuarioDAO.login(correo, contrasena);

        if (usuario != null) {

            System.out.println("Usuario encontrado");

            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);

            response.sendRedirect("inicio.jsp");

        } else {

            System.out.println("Usuario no encontrado");

            request.setAttribute("error", "Correo o contraseña incorrectos");

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }
    }
}