/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin;

import dao.ClientDao;
import entities.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author mahdi,mohamed,atiqa,oumaima mahdi,mohamed,atiqa,oumaima mahdi,mohamed,atiqa,oumaima mahdi,mohamed,atiqa,oumaima
 */
public class LoginServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            
            HttpSession session = request.getSession();
                
            if(session.getAttribute("user") != null)
            {
                    response.sendRedirect("Admin/ProduitListe.jsp");
                    return ;
            }
            
            String login = request.getParameter("login");
            String pass = request.getParameter("pass");
            
            if(login == null || pass == null)
            {
                response.sendRedirect("Admin/login.jsp");
                return;
            }

            //LOGIN
            ClientDao dao = new ClientDao();
            Client c = dao.findByAuthentification(login, pass);

            if(c != null) {
                
                if(session.getAttribute("user") == null)
                {
                    session.setAttribute("user", c);
                    response.sendRedirect("LoginServelet");
                }
                
            }else
            {
                response.sendRedirect("Admin/login.jsp");
            }
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
}
