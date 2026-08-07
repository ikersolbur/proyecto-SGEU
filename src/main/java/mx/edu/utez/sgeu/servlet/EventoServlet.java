package mx.edu.utez.sgeu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import mx.edu.utez.sgeu.dao.EventoDAO;
import mx.edu.utez.sgeu.model.Evento;
import mx.edu.utez.sgeu.model.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@WebServlet("/evento")
public class EventoServlet extends HttpServlet {

    private EventoDAO eventoDAO;

    @Override
    public void init() {
        eventoDAO = new EventoDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listaEventos", eventoDAO.obtenerEventos());

        request.getRequestDispatcher("/admin/admin.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario =
                (Usuario) request.getSession().getAttribute("usuario");

        Evento evento = new Evento();

        evento.setNombre(request.getParameter("nombre"));
        evento.setDescripcion(request.getParameter("descripcion"));

        evento.setFecha(Date.valueOf(request.getParameter("fecha")));

        evento.setHoraInicio(
                Timestamp.valueOf(request.getParameter("horaInicio").replace("T", " ") + ":00"));

        evento.setHoraFin(
                Timestamp.valueOf(request.getParameter("horaFin").replace("T", " ") + ":00"));

        evento.setCupoMaximo(
                Integer.parseInt(request.getParameter("cupo")));

        evento.setEstado("PROGRAMADO");

        evento.setIdLugar(
                Integer.parseInt(request.getParameter("lugar")));

        evento.setIdTipoEvento(
                Integer.parseInt(request.getParameter("tipo")));

        evento.setIdUsuario(usuario.getIdUsuario());

        evento.setImagenEvento(
                request.getParameter("imagen"));

        evento.setFechaLimiteInscripcion(
                Date.valueOf(request.getParameter("fechaLimite")));

        if (eventoDAO.registrarEvento(evento)) {

            response.sendRedirect(request.getContextPath() + "/evento");
        } else {

            response.sendRedirect(request.getContextPath() + "/admin/crear-evento.jsp");

        }

    }

}
