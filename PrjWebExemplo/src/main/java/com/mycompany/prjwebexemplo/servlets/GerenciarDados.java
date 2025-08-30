package com.mycompany.prjwebexemplo.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "GerenciarDados", urlPatterns = {"/GerenciarDados"})
public class GerenciarDados extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        String[] parteAcao = acao.split("-");
        int index = -1;
        if (parteAcao.length > 1) {
            acao = parteAcao[0].trim(); 
            try {
                index = Integer.parseInt(parteAcao[1].trim()) - 1;
            } catch (NumberFormatException e) {
                index = -1;
            }

            // Se for editar, trocamos para ação que preenche o formulário
            if (acao.equalsIgnoreCase("Editar")) {
                acao = "Mostra_Objt";
            }
        }
        System.out.println(acao);
        System.out.println(index);

        try {

            switch (acao) {

                case "Salvar":
                    salvarPets(request, response);
                    break;
                case "Listar":
                    getPets(request, response);
                    break;
                case "Excluir":
                    excluirPets(request, response, index);
                    break;
                case "Mostra_Objt":
                    getPets(request, response, index);
                    break;

            }

        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void salvarPets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String raca = request.getParameter("raca");
        String porte = request.getParameter("porte");
        String dataNasc = request.getParameter("data_nasc");
        String cor = request.getParameter("cor");
        String indexParam = request.getParameter("index"); 

        List<Pet> lstPets = new BuscarArquivo().lerArq();

        if (indexParam != null && !indexParam.isEmpty()) {
            // Atualiza pet existente
            int index = Integer.parseInt(indexParam);
            Pet petExistente = lstPets.get(index);
            petExistente.setNome(nome);
            petExistente.setRaca(raca);
            petExistente.setPorte(porte);
            petExistente.setDataNasc(dataNasc);
            petExistente.setCor(cor);
            new GravarArquivo().reescreverArq(index, petExistente);
        } else {
            // Cria novo pet
            Pet novoPet = new Pet();
            novoPet.setNome(nome);
            novoPet.setRaca(raca);
            novoPet.setPorte(porte);
            novoPet.setDataNasc(dataNasc);
            novoPet.setCor(cor);
            lstPets.add(novoPet);
            new GravarArquivo().escreverArq(novoPet);
        }

        // Atualiza atributo para listar
        request.setAttribute("lstPets", lstPets);

        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    private void excluirPets(HttpServletRequest request, HttpServletResponse response, int index) throws ServletException, IOException {

        List<Pet> lstPets = new BuscarArquivo().lerArq();
        lstPets.remove(index);
        new GravarArquivo().excluirPet(lstPets);
        request.setAttribute("lstPets", lstPets);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    private void getPets(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Pet> lstPets = new BuscarArquivo().lerArq();
        request.setAttribute("lstPets", lstPets);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

    private void getPets(HttpServletRequest request, HttpServletResponse response, int index) throws IOException, ServletException {

        List<Pet> lstPets = new BuscarArquivo().lerArq();
        Pet pet = lstPets.get(index);
        request.setAttribute("pet", pet);
        request.setAttribute("index", index);
        String path = "/TestePaginaServlet.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);

    }

}