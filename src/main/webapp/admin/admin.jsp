<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mx.edu.utez.sgeu.model.Usuario"%>
<%@ page import="mx.edu.utez.sgeu.model.Evento" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getIdRol() != 3) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%
    List<Evento> listaEventos =
            (List<Evento>) request.getAttribute("listaEventos");
%>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Administrador | SGEU</title>

    <link rel="stylesheet"
           href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

</head>

<body>

<div class="container">

    <!-- ================= SIDEBAR ================= -->

    <aside class="sidebar">

        <div>

            <div class="logo">

                <img src="../img/Logotipo-UTEZ.png" alt="UTEZ">

            </div>

            <nav>

                <a href="#" class="active">
                    <i class="fa-solid fa-house"></i>
                    <span>Inicio</span>
                </a>

                <a href="#">
                    <i class="fa-solid fa-calendar-days"></i>
                    <span>Mis eventos</span>
                </a>

                <a href="../admin/crear-evento.jsp">
                    <i class="fa-solid fa-circle-plus"></i>
                    <span>Crear evento</span>
                </a>

                <a href="#">
                    <i class="fa-solid fa-gear"></i>
                    <span>Configuración</span>
                </a>

            </nav>

        </div>

        <div class="logout">

            <a href="logout">

                <i class="fa-solid fa-right-from-bracket"></i>

                <span>Cerrar sesión</span>

            </a>

        </div>

    </aside>

    <!-- ================= CONTENIDO ================= -->

    <main class="content">

        <div class="top">

            <h1>
                Eventos recientes
            </h1>

        </div>

        <div class="filters">

            <select>

                <option>
                    Categoría
                </option>

                <option>
                    Deportivos
                </option>

                <option>
                    Académicos
                </option>

                <option>
                    Culturales
                </option>

            </select>

            <input
                    type="text"
                    placeholder="Buscar evento...">

        </div>

        <!-- ================= TARJETAS ================= -->

        <section class="cards">

            <%
                if (listaEventos != null && !listaEventos.isEmpty()) {

                    for (Evento evento : listaEventos) {
            %>

            <div class="card">

                <img src="../img/<%= evento.getImagenEvento() %>" alt="Evento">

                <div class="info">

                    <h3><%= evento.getNombre() %></h3>

                    <div class="dato">
                        📅 <%= evento.getFecha() %>
                    </div>

                    <div class="dato">
                        👥 Cupo: <%= evento.getCupoMaximo() %>
                    </div>

                    <button class="btn-detalles">

                        Ver detalles

                    </button>

                </div>

            </div>

            <%
                }

            } else {
            %>

            <h2>No hay eventos registrados.</h2>

            <%
                }
            %>

        </section>

        <!-- ================= PAGINACION ================= -->

        <div class="pagination">

            <button>

                ←

            </button>

            <button class="active">

                1

            </button>

            <button>

                2

            </button>

            <button>

                3

            </button>

            <button>

                →

            </button>

        </div>

    </main>

</div>

<script src="../js/dashboard.js"></script>

</body>

</html>