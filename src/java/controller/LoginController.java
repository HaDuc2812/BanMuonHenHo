/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import viewmodels.UserDetail;

/**
 *
 * @author maith
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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

        // Kiểm tra xem đã đăng nhập chưa
        HttpSession session = request.getSession();
        UserDetail currentUser = (UserDetail) session.getAttribute("currentUser");

        if (currentUser != null) {
            // Đã đăng nhập rồi, chuyển thẳng đến main
            response.sendRedirect("main");
        } else {
            // Chưa đăng nhập, hiển thị trang login
            request.getRequestDispatcher("index.html").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập
        UserDAO udao = new UserDAO();
        UserDetail user = udao.getUserByUsername(username);

        if (user != null && password.equals(user.getPasswordHash())) {
            // Đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            if(user.getRole().getRoleId() ==2){
                response.sendRedirect("admindashboard");
            }else{
                response.sendRedirect("views/index.html");
            }

        } else {
            // Đăng nhập thất bại
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("index.html").forward(request, response);
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
