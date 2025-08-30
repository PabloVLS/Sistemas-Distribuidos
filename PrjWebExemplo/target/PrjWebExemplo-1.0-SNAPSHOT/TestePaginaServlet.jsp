<%-- 
    Document   : TestePaginaServlet
    Created on : 29 de ago. de 2025, 22:00:44
    Author     : Pablo
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- CSS do Bootstrap 5.3 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- JS do Bootstrap 5.3 (com Popper) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Pets</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>CADASTRO DE PETS</h2>
            <form action="${pageContext.request.contextPath}/GerenciarDados" method="post">
                <c:if test="${not empty index}">
                     <input type="hidden" name="index" value="${index}" />
                </c:if>
                <div class="mb-3 mt-3">
                    <label for="nome">Nome:</label>
                    <input type="text" class="form-control" id="nome" name="nome" value="${pet.nome}">
                </div>
                <div class="mb-3">
                    <label for="raca">Raça:</label>
                    <input type="text" class="form-control" id="raca" name="raca" value="${pet.raca}">
                </div>
                <div class="mb-3">
                    <label for="porte">Porte:</label>
                    <input type="text" class="form-control" id="porte" name="porte" value="${pet.porte}">
                </div>
                <div class="mb-3">
                    <label for="data_nasc">Data Nasc.:</label>
                    <input type="text" class="form-control" id="data_nasc" name="data_nasc" value="${pet.dataNasc}">
                </div>
                <div class="mb-3">
                    <label for="cor">Cor:</label>
                    <input type="text" class="form-control" id="cor" name="cor" value="${pet.cor}">
                </div>

                <button type="submit" class="btn btn-primary" name="acao" value="Salvar">Salvar</button>
                <button type="submit" class="btn btn-primary" name="acao" value="Listar">Listar</button>


                <h2>Lista de Pets</h2>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Porte</th>
                            <th>Cor</th>
                            <th>Data Nascimento</th>
                            <th>Raça</th>
                            <th>Opção</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pet" items="${lstPets}" varStatus="contador">
                            <tr>
                                <td><c:out value="${pet.nome}"/></td>
                                <td><c:out value="${pet.porte}"/></td>
                                <td><c:out value="${pet.cor}"/></td>
                                <td><c:out value="${pet.dataNasc}"/></td>
                                <td><c:out value="${pet.raca}"/></td>
                                <td>
                                    <button type="submit" class="btn btn-primary" name="acao" value="Editar-${contador.count}">Editar</button>
                                    <button type="submit" class="btn btn-danger" name="acao" value="Excluir - ${contador.count}">Excluir</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>

    </body>
</html>
