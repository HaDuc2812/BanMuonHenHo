<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@page import="model.Users" %>
<%@page import="model.Match" %>

<html>
    <head>
        <title>Ban Muon Hen Ho - Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

    </head>
    <body>

        <div class="header">
            <h1>Admin Dashboard - Ban Muon Hen Ho</h1>
        </div>

        <jsp:include page="AdminNavbar.jsp"></jsp:include>

            <div class="container">

                <!-- Dashboard Cards -->
                <div>
                    <div class="card">
                        <h2>Tổng người dùng</h2>
                        <p>${totalUsers}</p> <!-- Placeholder, populate from servlet -->
                    </div>
                    <div class="card">
                        <h2>Tổng các mối đã được ghép</h2>
                        <p>${totalMatches}</p>
                    </div>
                    <div class="card">
                        <h2>Những mối đợi xác nhận</h2>
                        <p>${pendingMatches}</p>
                    </div>
                    <div class="card">
                        <h2>Mối đã hoàn thành</h2>
                        <p>${completedMatches}</p>
                    </div>
                </div>

                <!-- Users Table -->
                <section id="userList">
                    <div class="table-container">
                        <h2>Một số người dùng</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Họ tên</th>
                                    <th>Giới tính</th>
                                    <th>Sinh nhật</th>
                                    <th>Mô tả</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                            <% 
                                List<Users> users = (List<Users>) request.getAttribute("users");
                                if (users != null) {
                                    for (Users user : users) { 
                            %>
                            <tr>
                                <td><%= user.getUserId() %></td>
                                <td><%= user.getFullName() %></td>
                                <td><%= user.getGender() %></td>
                                <td><%= user.getBirthDate() %></td>
                                <td><%= user.getBio() %></td>
                                <td><%= user.getStatus() %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="7">No users found.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </section>

            <!-- Matches Table -->
            <section id="matchList">
                <div class="table-container">
                    <h2>All Matches</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Match ID</th>
                                <th>User 1</th>
                                <th>User 2</th>
                                <th>Admin Assigned</th>
                                <th>Match Date</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<Match> matches = (List<Match>) request.getAttribute("matches");
                                if (matches != null) {
                                    for (Match match : matches) { 
                            %>
                            <tr>
                                <td><%= match.getMatchId() %></td>
                                <td><%= match.getUser1Id() %></td> <!-- Replace with name if available -->
                                <td><%= match.getUser2Id() %></td> <!-- Replace with name if available -->
                                <td><%= match.getAdminId() %></td> <!-- Replace with name if available -->
                                <td><%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(match.getMatchDate()) %></td>
                                <td><%= match.getStatus() %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="7">Không tìm thấy mối nào.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </body>
</html>
