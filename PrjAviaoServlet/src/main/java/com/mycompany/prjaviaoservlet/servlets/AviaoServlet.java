package com.mycompany.prjaviaoservlet.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "AviaoServlet", urlPatterns = {"/AviaoServlet"})
public class AviaoServlet extends HttpServlet {

    private AviaoDAO aviaoDAO;

    public void init() {
        aviaoDAO = new AviaoDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("editar".equals(action) && idStr != null) {
            int id = Integer.parseInt(idStr);
            Aviao aviao = aviaoDAO.buscarPorId(id);
            request.setAttribute("aviao", aviao);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        if ("excluir".equals(action) && idStr != null) {
            int id = Integer.parseInt(idStr);
            aviaoDAO.excluir(id);
            response.sendRedirect(request.getContextPath() + "/lista.jsp");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/lista.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String idStr = request.getParameter("id"); // vem do input hidden se for edição
        String modelo = request.getParameter("modelo");
        String fabricante = request.getParameter("fabricante");
        String capacidadeStr = request.getParameter("capacidade");

        if (modelo == null || fabricante == null || capacidadeStr == null || capacidadeStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos os campos são obrigatórios.");
            return;
        }

        int capacidade = Integer.parseInt(capacidadeStr);

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Aviao aviao = new Aviao(id, modelo, fabricante, capacidade);
            aviaoDAO.atualizar(aviao);
        } else {
            Aviao aviao = new Aviao(modelo, fabricante, capacidade);
            aviaoDAO.salvar(aviao);
        }

        response.sendRedirect(request.getContextPath() + "/lista.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
