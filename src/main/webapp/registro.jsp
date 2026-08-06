<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Registro SGEU</title>
</head>

<body>

<h2>Crear cuenta</h2>

<form action="RegistroServlet" method="post">

  <label>Nombre:</label>
  <input type="text" name="nombre" required>
  <br><br>

  <label>Apellido paterno:</label>
  <input type="text" name="apellidoPaterno" required>
  <br><br>

  <label>Apellido materno:</label>
  <input type="text" name="apellidoMaterno">
  <br><br>

  <label>Matrícula:</label>
  <input type="text" name="matricula" required>
  <br><br>

  <label>Correo:</label>
  <input type="email" name="correo" required>
  <br><br>

  <label>Contraseña:</label>
  <input type="password" name="contrasena" required>
  <br><br>

  <input type="hidden" name="idRol" value="1">

  <button type="submit">
    Registrarse
  </button>

</form>

</body>
</html>