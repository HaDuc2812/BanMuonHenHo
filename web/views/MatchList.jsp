<%@ page import="java.util.*" %>
<%@ page import="model.Match" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dal.UsersDAO" %>
<%@ page import="model.Match" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Match List</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

    </head>
    <body>
        <jsp:include page="AdminNavbar.jsp"></jsp:include>
            <h2>All Matches</h2>

        <%
            // Get the list that servlet sent
            List<Match> matches = (List<Match>) request.getAttribute("matches");
            UsersDAO udao = new UsersDAO();
            if (matches == null || matches.isEmpty()) {
        %>
        <p style="text-align:center;">No matches found.</p>
        <%
            } else {
        %>
        <table>
            <tr>
                <th>Match ID</th>
                <th>User 1 </th>
                <th>User 2 </th>
                <th>Admin ID</th>
                <th>Match Date</th>
                <th>Status</th>
            </tr>
            <%
                for (Match m : matches) {
                String user1Name = udao.getUserNameById(m.getUser1Id());
                String user2Name = udao.getUserNameById(m.getUser2Id());
            %>
            <tr>
                <td><%= m.getMatchId() %></td>
                <td><%= user1Name %></td>
                <td><%= user2Name %></td>
                <td><%= m.getAdminId() %></td>
                <td><%= m.getMatchDate() %></td>
                <td><%= m.getStatus() %></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
    </body>
</html>
