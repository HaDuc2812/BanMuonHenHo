/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TagDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Tag;
import model.User;
import viewmodels.UserDetail;

/**
 *
 * @author maith
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        TagDAO tdao = new TagDAO();
        List<Tag> allTags = tdao.getAllTags();

        request.setAttribute("allTags", allTags);
        request.getRequestDispatcher("views/register.jsp").forward(request, response);
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

        try {
            // Lấy thông tin từ form
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String birthDateStr = request.getParameter("birthDate");
            String bio = request.getParameter("bio");

            // Validate password
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
                TagDAO tdao = new TagDAO();
                request.setAttribute("allTags", tdao.getAllTags());
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
                return;
            }

            // Chuyển đổi ngày sinh
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse(birthDateStr);

            // Lấy tags được chọn
            String[] selectedTags = request.getParameterValues("tags");
            List<Integer> tagIds = new ArrayList<>();
            if (selectedTags != null) {
                for (String tagId : selectedTags) {
                    tagIds.add(Integer.parseInt(tagId));
                }
            }

            // Tạo user mới (role_id = 1 là User, status = pending)
            User newUser = new User(username, password, fullName, gender,
                    birthDate, bio, "pending", 1);

            // Lưu vào database
            UserDAO udao = new UserDAO();
            int userId = udao.createUser(newUser);

            if (userId > 0) {
                // Thêm tags cho user
                if (!tagIds.isEmpty()) {
                    udao.updateUserTags(userId, tagIds);
                }

                // Tự động đăng nhập sau khi đăng ký
                UserDetail userDetail = udao.getUserByID(userId);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", userDetail);

                // Chuyển đến trang main
                response.sendRedirect("main");

            } else {
                // Username đã tồn tại
                request.setAttribute("error", "Username đã tồn tại! Vui lòng chọn username khác.");
                TagDAO tdao = new TagDAO();
                request.setAttribute("allTags", tdao.getAllTags());
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("Register Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            TagDAO tdao = new TagDAO();
            request.setAttribute("allTags", tdao.getAllTags());
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
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
