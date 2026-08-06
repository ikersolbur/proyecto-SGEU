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


        Usuario usuario = usuarioDAO.login(correo, contrasena);


        if (usuario != null) {


            HttpSession sesion = request.getSession();


            sesion.setMaxInactiveInterval(30 * 60);


            sesion.setAttribute("usuario", usuario);


            switch (usuario.getIdRol()) {


                case 1:
                    response.sendRedirect("usuario.jsp");
                    break;


                case 2:
                    response.sendRedirect("gestor.jsp");
                    break;


                case 3:
                    response.sendRedirect("admin.jsp");
                    break;


                default:
                    response.sendRedirect("login.jsp");
                    break;
            }


        } else {


            if (!usuarioDAO.existeCorreo(correo)) {

                request.setAttribute("error",
                        "El correo no está registrado");


            } else {

                request.setAttribute("error",
                        "La contraseña es incorrecta");

            }


            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);

        }

    }
}