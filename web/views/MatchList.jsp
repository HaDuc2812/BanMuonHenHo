<%@ page import="java.util.*" %>
<%@ page import="model.Match" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

            if (matches == null || matches.isEmpty()) {
        %>
        <p style="text-align:center;">No matches found.</p>
        <%
            } else {
        %>
        <table>
            <tr>
                <th>Match ID</th>
                <th>User 1 ID</th>
                <th>User 2 ID</th>
                <th>Admin ID</th>
                <th>Match Date</th>
                <th>Status</th>
            </tr>
            <%
                for (Match m : matches) {
            %>
            <tr>
                <td><%= m.getMatchId() %></td>
                <td><%= m.getUser1Id() %></td>
                <td><%= m.getUser2Id() %></td>
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
