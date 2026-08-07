<%@ page import="mx.edu.utez.sgeu.model.Lugar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Crear Evento | SGEU</title>

    <link rel="stylesheet" href="../css/dashboard.css">

</head>

<body>

<div class="container">

    <main class="content">

        <h1>Crear evento</h1>

        <form action="../evento" method="post">

            <div class="grupo">

                <label>Nombre</label>

                <input
                        type="text"
                        name="nombre"
                        required>

            </div>

            <div class="grupo">

                <label>Descripción</label>

                <textarea
                        name="descripcion"
                        rows="4"
                        required></textarea>

            </div>

            <div class="grupo">

                <label>Fecha</label>

                <input
                        type="date"
                        name="fecha"
                        required>

            </div>

            <div class="grupo">

                <label>Hora de inicio</label>

                <input
                        type="datetime-local"
                        name="horaInicio"
                        required>

            </div>

            <div class="grupo">

                <label>Hora de fin</label>

                <input
                        type="datetime-local"
                        name="horaFin"
                        required>

            </div>

            <div class="grupo">

                <label>Cupo máximo</label>

                <input
                        type="number"
                        name="cupo"
                        min="1"
                        required>

            </div>

            <div class="grupo">

                <label>Lugar</label>

                <select name="lugar" required>

                    <% for(Lugar lugar : lugares){ %>

                    <option value="<%= lugar.getIdLugar() %>">

                        <%= lugar.getNombre() %>

                    </option>

                    <% } %>

                </select>

            </div>

            <div class="grupo">

                <label>Tipo de evento</label>

                <select name="tipo" required>

                    <option value="1">Conferencia</option>

                </select>

            </div>

            <div class="grupo">

                <label>Imagen</label>

                <input
                        type="text"
                        name="imagen"
                        placeholder="evento.jpg">

            </div>

            <div class="grupo">

                <label>Fecha límite de inscripción</label>

                <input
                        type="date"
                        name="fechaLimite"
                        required>

            </div>

            <div class="botones">

                <button type="submit">

                    Guardar evento

                </button>

                <a href="admin.jsp">

                    Cancelar

                </a>

            </div>

        </form>

    </main>

</div>

</body>

</html>