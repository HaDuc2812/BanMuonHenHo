package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Users;

/**
 *
 * @author HA DUC
 */
public class MatchAddServlet extends HttpServlet {

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
            out.println("<title>Servlet MatchAddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MatchAddServlet at " + request.getContextPath() + "</h1>");
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
        try {
            // 1️⃣ Get selected user ID from parameter
            String userIdParam = request.getParameter("user_id");
            if (userIdParam == null || userIdParam.isEmpty()) {
                request.setAttribute("errorMessage", "No user selected for matching.");
                request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
                return;
            }

            int userId = Integer.parseInt(userIdParam);

            // 2️⃣ Get selected user info
            UsersDAO userDAO = new UsersDAO();
            Users currentUser = userDAO.getUsersById(userId);

            if (currentUser == null) {
                request.setAttribute("errorMessage", "User not found.");
                request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
                return;
            }

            // 3️⃣ Find possible matches (opposite gender + share ≥1 tag)
            List<Users> potentialMatches = userDAO.getPotentialMatches(currentUser);

            // 4️⃣ Set attributes for the popup page
            request.setAttribute("currentUser", currentUser);
            request.setAttribute("potentialMatches", potentialMatches);

            // 5️⃣ Forward to popup JSP
            request.getRequestDispatcher("/views/MatchPopup.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing match request: " + e.getMessage());
            request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
        }
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
