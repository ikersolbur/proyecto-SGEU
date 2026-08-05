<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SGEU - Inicio de sesión</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .card {
            background-color: #e8ecef;
            padding: 30px 40px;
            border-radius: 8px;
            width: 400px;
            box-shadow: 0 4px 6px rgba(0,0,0,.1);
            border: 1px solid #ccc;
        }

        h2 {
            text-align: center;
            color: #1a1a1a;
        }

        label {
            display:block;
            margin-bottom:5px;
            font-weight:bold;
        }

        input {
            width:100%;
            padding:10px;
            border:1px solid #42b883;
            border-radius:4px;
            box-sizing:border-box;
            margin-bottom:15px;
        }


        .btn {
            width:100%;
            padding:12px;
            background:#3ae2b2;
            border:none;
            border-radius:4px;
            font-weight:bold;
            cursor:pointer;
        }


        .btn:hover {
            background:#2bc598;
        }


        .link-text {
            display:block;
            text-align:right;
            color:#3ae2b2;
            cursor:pointer;
            margin-bottom:15px;
        }


        .footer-text {
            margin-top:20px;
        }


        .error {
            color:red;
            text-align:center;
        }


    </style>

</head>


<body>


<div class="card">

    <h2>Inicio de sesión</h2>


    <%
        String error = (String) request.getAttribute("error");

        if(error != null){
    %>

    <p class="error">
        <%= error %>
    </p>

    <%
        }
    %>



    <form action="login" method="post">


        <label>
            Correo electrónico
        </label>

        <input
                type="email"
                name="correo"
                required>



        <label>
            Contraseña
        </label>

        <input
                type="password"
                name="password"
                required>


        <a class="link-text" href="recuperar.jsp">
            Recuperar contraseña
        </a>



        <button class="btn" type="submit">
            Iniciar sesión
        </button>



        <div class="footer-text">

            Crear cuenta
            <a href="registro.jsp">
                aquí
            </a>

        </div>


    </form>


</div>


</body>

</html>