package mx.edu.utez.sgeu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mx.edu.utez.sgeu.dao.EventoDAO;
import mx.edu.utez.sgeu.dao.LugarDAO;
import mx.edu.utez.sgeu.dao.TipoEventoDAO;

import mx.edu.utez.sgeu.model.Evento;
import mx.edu.utez.sgeu.model.Lugar;
import mx.edu.utez.sgeu.model.TipoEvento;
import mx.edu.utez.sgeu.model.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

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

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

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

        evento.setIdUsuario(
                usuario.getIdUsuario());

        evento.setImagenEvento(
                request.getParameter("imagen"));

        evento.setFechaLimiteInscripcion(
                Date.valueOf(request.getParameter("fechaLimite")));

        boolean registrado = eventoDAO.registrarEvento(evento);

        if (registrado) {

            response.sendRedirect(request.getContextPath() + "/evento");

        } else {

            request.setAttribute("error", "No se pudo registrar el evento.");

            List<Lugar> lugares = lugarDAO.obtenerLugares();
            List<TipoEvento> tipos = tipoEventoDAO.obtenerTipos();

            request.setAttribute("lugares", lugares);
            request.setAttribute("tipos", tipos);

            request.getRequestDispatcher("/admin/crear-evento.jsp")
                    .forward(request, response);

        }

    }

}