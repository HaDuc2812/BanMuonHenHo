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
public class MainController extends HttpServlet {

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
            out.println("<title>Servlet MainController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
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

        // Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        UserDetail currentUser = (UserDetail) session.getAttribute("currentUser");

        if (currentUser == null) {
            // Chưa đăng nhập, chuyển về trang login
            response.sendRedirect("login");
            return;
        }

        // Lấy lại thông tin user mới nhất từ database
        UserDAO udao = new UserDAO();
        UserDetail updatedUser = udao.getUserByID(currentUser.getUserId());

        if (updatedUser != null) {
            session.setAttribute("currentUser", updatedUser);
            currentUser = updatedUser;
        }

        // Load tất cả tags để hiển thị khi edit
        TagDAO tdao = new TagDAO();
        List<Tag> allTags = tdao.getAllTags();

        request.setAttribute("allTags", allTags);
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("views/main.jsp").forward(request, response);
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

        // Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        UserDetail currentUser = (UserDetail) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateUserInfo(request, response, currentUser, session);
        } else if ("delete".equals(action)) {
            deleteUserAccount(request, response, currentUser, session);
        }
    }

    /**
     * Cập nhật thông tin user
     */
    private void updateUserInfo(HttpServletRequest request, HttpServletResponse response,
            UserDetail currentUser, HttpSession session)
            throws ServletException, IOException {

        try {
            // Lấy thông tin từ form
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String birthDateStr = request.getParameter("birthDate");
            String bio = request.getParameter("bio");
            String password = request.getParameter("password");

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

            // Tạo user object để update
            User updatedUser = new User();
            updatedUser.setUserId(currentUser.getUserId());
            updatedUser.setUsername(currentUser.getUsername());
            updatedUser.setPasswordHash(password.isEmpty() ? currentUser.getPasswordHash() : password);
            updatedUser.setFullName(fullName);
            updatedUser.setGender(gender);
            updatedUser.setBirthDate(birthDate);
            updatedUser.setBio(bio);
            updatedUser.setStatus(currentUser.getStatus());
            updatedUser.setRoleId(currentUser.getRole().getRoleId());

            // Cập nhật database
            UserDAO udao = new UserDAO();
            boolean success = udao.updateUser(updatedUser);

            if (success) {
                // Cập nhật tags
                udao.updateUserTags(currentUser.getUserId(), tagIds);

                // Lấy thông tin user mới từ database
                UserDetail newUserDetail = udao.getUserByID(currentUser.getUserId());
                session.setAttribute("currentUser", newUserDetail);

                request.setAttribute("success", "Cập nhật thông tin thành công!");
            } else {
                request.setAttribute("error", "Cập nhật thông tin thất bại!");
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }

        // Reload trang main
        doGet(request, response);
    }

    /**
     * Xóa tài khoản user
     */
    private void deleteUserAccount(HttpServletRequest request, HttpServletResponse response,
            UserDetail currentUser, HttpSession session)
            throws ServletException, IOException {

        try {
            UserDAO udao = new UserDAO();
            boolean success = udao.deleteUser(currentUser.getUserId());

            if (success) {
                // Xóa session và chuyển về trang login
                session.invalidate();
                response.sendRedirect("login?message=Xóa tài khoản thành công!");
            } else {
                request.setAttribute("error", "Không thể xóa tài khoản!");
                doGet(request, response);
            }

        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi xảy ra khi xóa tài khoản!");
            doGet(request, response);
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
