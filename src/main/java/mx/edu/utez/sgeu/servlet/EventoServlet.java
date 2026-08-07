package mx.edu.utez.sgeu.servlet;
import mx.edu.utez.sgeu.model.Lugar;
import mx.edu.utez.sgeu.model.TipoEvento;

import java.util.List;

import mx.edu.utez.sgeu.dao.EventoDAO;
import mx.edu.utez.sgeu.dao.LugarDAO;
import mx.edu.utez.sgeu.dao.TipoEventoDAO;

import mx.edu.utez.sgeu.model.Evento;
import mx.edu.utez.sgeu.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

@WebServlet("/evento")
public class EventoServlet extends HttpServlet {

    private EventoDAO eventoDAO;
    private LugarDAO lugarDAO;
    private TipoEventoDAO tipoEventoDAO;

    @Override
    public void init() {

        eventoDAO = new EventoDAO();
        lugarDAO = new LugarDAO();
        tipoEventoDAO = new TipoEventoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

            List<Lugar> lugares = lugarDAO.obtenerLugares();
            List<TipoEvento> tipos = tipoEventoDAO.obtenerTipos();

            request.setAttribute("lugares", lugares);
            request.setAttribute("tipos", tipos);

            request.getRequestDispatcher("/admin/crear-evento.jsp")
                    .forward(request, response);

            return;
        }

        request.setAttribute("listaEventos", eventoDAO.obtenerEventos());

        request.getRequestDispatcher("/admin/admin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        Evento evento = new Evento();

        evento.setNombre(request.getParameter("nombre"));
        evento.setDescripcion(request.getParameter("descripcion"));
        evento.setFecha(Date.valueOf(request.getParameter("fecha")));

        evento.setHoraInicio(
                Timestamp.valueOf(request.getParameter("horaInicio").replace("T", " ") + ":00"));

        evento.setHoraFin(
                Timestamp.valueOf(request.getParameter("horaFin").replace("T", " ") + ":00"));

        evento.setCupoMaximo(Integer.parseInt(request.getParameter("cupo")));
        evento.setEstado("PROGRAMADO");
        evento.setIdLugar(Integer.parseInt(request.getParameter("lugar")));
        evento.setIdTipoEvento(Integer.parseInt(request.getParameter("tipo")));
        evento.setIdUsuario(usuario.getIdUsuario());
        evento.setImagenEvento(request.getParameter("imagen"));
        evento.setFechaLimiteInscripcion(
                Date.valueOf(request.getParameter("fechaLimite")));

        if (eventoDAO.registrarEvento(evento)) {

            response.sendRedirect(request.getContextPath() + "/evento");

        } else {

            response.sendRedirect(request.getContextPath() + "/evento?accion=crear");

        }
    }

}