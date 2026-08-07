package mx.edu.utez.sgeu.servlet;

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

    // Aquí sigue doGet()

    // Aquí sigue doPost()

}