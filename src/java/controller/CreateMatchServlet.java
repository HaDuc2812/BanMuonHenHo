/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MatchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import model.Match;

/**
 *
 * @author HA DUC
 */
public class CreateMatchServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateMatchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateMatchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        try {
            // 1️⃣ Get user IDs from the form
            int user1Id = Integer.parseInt(request.getParameter("user1"));
            int user2Id = Integer.parseInt(request.getParameter("user2"));

            // 2️⃣ Get current admin ID (from session or hardcode for now)
            //HttpSession session = request.getSession();
            //Integer adminId = (Integer) session.getAttribute("adminId");
            // 2️⃣ Hardcode admin ID for now (since no session)
            int adminId = 1; // Change this to match your actual admin ID

            // 3️⃣ Create a new match object
            Match m = new Match();
            m.setUser1Id(user1Id);
            m.setUser2Id(user2Id);
            m.setAdminId(adminId);
            m.setStatus("pending");

            // 4️⃣ Insert into DB
            MatchDAO dao = new MatchDAO();
            boolean success = dao.addMatch(m);

            // 5️⃣ Forward or redirect with message
            if (success) {
                request.setAttribute("successMessage", "Match created successfully!");

            } else {
                request.setAttribute("errorMessage", "Failed to create match. Please try again.");
            }
            request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMess", "error creating match");
            request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
        }
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
