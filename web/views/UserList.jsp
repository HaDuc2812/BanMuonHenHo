<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Users" %>
<html>
    <head>
        <title>User List</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    </head>
    <body>
        <jsp:include page="AdminNavbar.jsp"></jsp:include>
            <h2>All Users</h2>
        <%
            List<Users> users = (List<Users>) request.getAttribute("users");
            if (users == null || users.isEmpty()) {
        %>
        <p style="text-align:center;">No users found.</p>
        <%
            } else {
        %>
        <table>
            <tr>
                <th>ID</th>
                <th>Tên Tài Khoản</th>
                <th>Họ Tên Người Dùng </th>
                <th>Giới tính</th>
                <th>Sinh nhật</th>
                <th>Mô tả</th>
                <th>Trạng thái</th>
                <th>Role ID</th>
                <th>Được tạo vào lúc</th>
                <th>Hành Động</th>
            </tr>
            <%
                for (Users u : users) {
            %>
            <tr>
                <td><%= u.getUserId() %></td>
                <td><%= u.getUsername() %></td>
                <td><%= u.getFullName() %></td>
                <td><%= u.getGender() %></td>
                <td><%= u.getBirthDate() %></td>
                <td><%= u.getBio() %></td>
                <td><%= u.getStatus() %></td>
                <td><%= u.getRoleId() %></td>
                <td><%= u.getCreatedAt() %></td>
                <td>
                    <form action="MatchAdd" method="get" style="display:inline;">
                        <input type="hidden" name="user_id" value="<%= u.getUserId() %>">
                        <input type="submit" value="Match">
                    </form>

                </td>
            </tr>
            <% } %>
        </table>
        <%
            }
        %>
    </body>
</html>
