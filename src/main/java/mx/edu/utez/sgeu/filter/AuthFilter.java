package mx.edu.utez.sgeu.filter;

import mx.edu.utez.sgeu.model.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("*.jsp")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String pagina = req.getRequestURI();


        // Páginas públicas
        if (pagina.contains("login.jsp") ||
                pagina.contains("registro.jsp") ||
                pagina.contains("index.jsp")) {

            chain.doFilter(request, response);
            return;
        }


        HttpSession sesion = req.getSession(false);


        if (sesion != null && sesion.getAttribute("usuario") != null) {


            Usuario usuario = (Usuario) sesion.getAttribute("usuario");

            int rol = usuario.getIdRol();


            // Validación de páginas por rol

            if (pagina.contains("usuario.jsp") && rol == 1) {

                chain.doFilter(request, response);

            } else if (pagina.contains("gestor.jsp") && rol == 2) {

                chain.doFilter(request, response);

            } else if (pagina.contains("admin.jsp") && rol == 3) {

                chain.doFilter(request, response);

            } else {

                res.sendRedirect("login.jsp");
            }


        } else {

            res.sendRedirect("login.jsp");

        }
    }
}