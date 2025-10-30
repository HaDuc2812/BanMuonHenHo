/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MatchConfirmDAO;
import dal.MatchDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HA DUC
 */
public class UpdateMatchStatusServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateMatchStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMatchStatusServlet at " + request.getContextPath() + "</h1>");
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
        String matchIdParam = request.getParameter("matchId");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if (matchIdParam == null || action == null) {
            session.setAttribute("error", "Yêu cầu không hợp lệ!");
            response.sendRedirect("main");
            return;
        }

        int matchId = Integer.parseInt(matchIdParam);
        String newStatus;

        switch (action) {
            case "confirm":
                newStatus = "confirmed";
                break;
            case "reject":
                newStatus = "rejected";
                break;
            default:
                session.setAttribute("error", "Hành động không hợp lệ!");
                response.sendRedirect("main");
                return;
        }

        try {
            MatchDAO mdao = new MatchDAO();
            MatchConfirmDAO cdao = new MatchConfirmDAO();

            boolean updated = mdao.updateMatchStatus(matchId, newStatus);

            // Lấy user từ session (để ghi xác nhận)
            model.User user = (model.User) session.getAttribute("user");
            boolean inserted = false;

            System.out.println("DEBUG - Checking user session...");
            if (user == null) {
                System.out.println("⚠️ user is NULL in session!");
            } else {
                System.out.println("✅ user found in session: " + user.getUserId());
            }

            if (user != null) {
                System.out.println(">>> Calling insertMatchConfirmation()");
                inserted = cdao.insertMatchConfirmation(matchId, user.getUserId(), newStatus);
                System.out.println("<<< Done insertMatchConfirmation(): " + inserted);
            }
            System.out.println("=== UpdateMatchStatusServlet Debug ===");
            System.out.println("Match ID: " + matchId);
            System.out.println("Action: " + action);
            System.out.println("New status: " + newStatus);
            System.out.println("UpdateMatchStatus result: " + updated);
            System.out.println("InsertMatchConfirmation result: " + inserted);
            if (updated && inserted) {
                session.setAttribute("success",
                        newStatus.equals("confirmed")
                        ? "✅ Xác nhận ghép đôi thành công!"
                        : "🚫 Bạn đã từ chối ghép đôi này."
                );
            } else {
                session.setAttribute("error", "❌ Cập nhật trạng thái không thành công.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "⚠️ Đã xảy ra lỗi khi cập nhật trạng thái!");
        }

        // ✅ Redirect 1 lần duy nhất ở cuối
        response.sendRedirect("main");
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
