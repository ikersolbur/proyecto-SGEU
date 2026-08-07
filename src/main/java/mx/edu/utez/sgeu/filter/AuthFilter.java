package mx.edu.utez.sgeu.filter;

import mx.edu.utez.sgeu.model.Usuario;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("*.jsp")
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String pagina = req.getRequestURI();

        // Páginas públicas
        if (pagina.contains("login.jsp") ||
                pagina.contains("registro.jsp")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");
        int rol = usuario.getIdRol();

        // ADMIN
        if (pagina.contains("/admin/")) {

            if (rol == 3) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }

            return;
        }

        // GESTOR
        if (pagina.contains("/gestor/")) {

            if (rol == 2) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }

            return;
        }

        // USUARIO
        if (pagina.contains("/usuario/")) {

            if (rol == 1) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }

            return;
        }

        chain.doFilter(request, response);
    }
}