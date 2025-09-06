<%@page import="com.mycompany.prjaviaoservlet.servlets.Aviao"%>
<%@page import="com.mycompany.prjaviaoservlet.servlets.AviaoDAO"%>
<%@ page import="java.util.*" %>

<%
    AviaoDAO dao = new AviaoDAO();
    List<Aviao> avioes = dao.listar();
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Lista de Aviões</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(135deg, #4facfe, #00f2fe);
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: flex-start;
                min-height: 100vh;
                padding-top: 50px;
            }

            .container {
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 8px 15px rgba(0,0,0,0.2);
                width: 500px;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 12px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #4facfe;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tr:hover {
                background-color: #c6f0fe;
            }

            a {
                display: block;
                margin-top: 20px;
                text-decoration: none;
                text-align: center;
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
            <h1>Lista de Aviões ??</h1>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Modelo</th>
                    <th>Fabricante</th>
                    <th>Capacidade</th>
                    <th>Ações</th>
                </tr>
                <% for (Aviao a : avioes) {%>
                <tr>
                    <td><%= a.getId()%></td>
                    <td><%= a.getModelo()%></td>
                    <td><%= a.getFabricante()%></td>
                    <td><%= a.getCapacidade()%></td>
                    <td>
                        <a href="AviaoServlet?action=editar&id=<%= a.getId()%>">Editar</a> |
                        <a href="AviaoServlet?action=excluir&id=<%= a.getId()%>" 
                           onclick="return confirm('Deseja realmente excluir este avião?')">Excluir</a>
                    </td>

                </tr>
                <% }%>
            </table>
            <a href="index.jsp">Cadastrar novo avião</a>
        </div>
    </body>
</html>
