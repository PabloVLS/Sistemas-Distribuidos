<!DOCTYPE html>
<%@page import="com.mycompany.prjaviaoservlet.servlets.Aviao"%>
<%@page import="com.mycompany.prjaviaoservlet.servlets.AviaoDAO"%>
<%@ page import="java.util.*" %>

<%
    Aviao aviao = (Aviao) request.getAttribute("aviao");
%>

<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Aviões</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #4facfe, #00f2fe);
                margin: 0;
                padding: 0;
                display: flex;
                height: 100vh;
                justify-content: center;
                align-items: center;
            }

            .container {
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 8px 15px rgba(0,0,0,0.2);
                width: 350px;
                text-align: center;
            }

            h1 {
                margin-bottom: 20px;
                color: #333;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                text-align: left;
                margin-bottom: 5px;
                font-weight: bold;
                color: #444;
            }

            input {
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                transition: border-color 0.3s;
            }

            input:focus {
                border-color: #4facfe;
                outline: none;
            }

            button {
                padding: 10px;
                border: none;
                background: #4facfe;
                color: white;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                cursor: pointer;
                transition: background 0.3s;
            }

            button:hover {
                background: #00c6fb;
            }

            a {
                display: block;
                margin-top: 15px;
                text-decoration: none;
                color: #4facfe;
                font-weight: bold;
                transition: color 0.3s;
            }

            a:hover {
                color: #007bbf;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Cadastro de Aviões</h1>
            <form action="AviaoServlet" method="post">
                <input type="hidden" name="id" value="<%= aviao != null ? aviao.getId() : ""%>">

                <label for="modelo">Modelo</label>
                <input type="text" id="modelo" name="modelo" 
                       value="<%= aviao != null ? aviao.getModelo() : ""%>" required>

                <label for="fabricante">Fabricante</label>
                <input type="text" id="fabricante" name="fabricante" 
                       value="<%= aviao != null ? aviao.getFabricante() : ""%>" required>

                <label for="capacidade">Capacidade</label>
                <input type="number" id="capacidade" name="capacidade" 
                       value="<%= aviao != null ? aviao.getCapacidade() : ""%>" required>

                <button type="submit">Salvar</button>
            </form>
            <a href="lista.jsp">Ver lista de aviões ??</a>
        </div>
    </body>
</html>
