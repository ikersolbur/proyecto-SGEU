<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    mx.edu.utez.sgeu.model.Usuario usuario =
            (mx.edu.utez.sgeu.model.Usuario) session.getAttribute("usuario");

    if (usuario != null) {

        switch (usuario.getIdRol()) {

            case 1:
                response.sendRedirect("usuario.jsp");
                return;

            case 2:
                response.sendRedirect("gestor.jsp");
                return;

            case 3:
                response.sendRedirect("admin.jsp");
                return;
        }
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>SGEU | Inicio de sesión</title>

    <link rel="stylesheet" href="css/global.css">
    <link rel="stylesheet" href="css/auth.css">

</head>

<body>

<!-- Logo -->
<div class="logo-container">
    <img src="img/Logotipo-UTEZ.png" alt="Logo UTEZ">
</div>

<div class="login-container">

    <div class="card">

        <h2>Inicio de sesión</h2>

        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>

        <div class="error">
            <%= error %>
        </div>

        <%
            }
        %>

        <form action="login" method="post" autocomplete="off">

            <label for="correo">
                Correo electrónico
            </label>

            <input
                    id="correo"
                    type="email"
                    name="correo"
                    placeholder="Ingrese un correo"
                    autocomplete="off"
                    required>

            <label for="password">
                Contraseña
            </label>

            <input
                    id="password"
                    type="password"
                    name="password"
                    placeholder="Ingrese una contraseña"
                    autocomplete="new-password"
                    required>

            <a href="recuperar.jsp" class="link-text">
                Recuperar contraseña
            </a>

            <button type="submit" class="btn">
                Iniciar sesión
            </button>

            <div class="footer-text">
                ¿No tienes una cuenta?

                <a href="registro.jsp">
                    Crear cuenta aquí
                </a>

            </div>

        </form>

    </div>

</div>

<script src="js/auth.js"></script>

</body>
</html>