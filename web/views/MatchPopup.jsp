<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Users" %>

<html>
    <head>
        <title>Match Popup</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    </head>
    <body class="popup-body">

        <div class="popup-container">

            <!-- ✅ Display success or error message -->
            <%
                String successMessage = (String) request.getAttribute("successMessage");
                String errorMessage = (String) request.getAttribute("errorMessage");

                if (successMessage != null) {
            %>
            <div class="message success"><%= successMessage %></div>
            <%
                } else if (errorMessage != null) {
            %>
            <div class="message error"><%= errorMessage %></div>
            <%
                }
            %>

            <h2>Find Match for 
                <span style="color:#ff4081;">
                    <%= ((Users)request.getAttribute("currentUser")).getFullName() %>
                </span>
            </h2>

            <table class="popup-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>Bio</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Users> similarUsers = (List<Users>) request.getAttribute("potentialMatches");
                        if (similarUsers != null && !similarUsers.isEmpty()) {
                            for (Users u : similarUsers) {
                    %>
                    <tr>
                        <td><%= u.getFullName() %></td>
                        <td><%= u.getGender() %></td>
                        <td><%= u.getBio() %></td>
                        <td>
                            <form action="creatematch" method="post">
                                <input type="hidden" name="user1" value="<%= ((Users)request.getAttribute("currentUser")).getUserId() %>">
                                <input type="hidden" name="user2" value="<%= u.getUserId() %>">
                                <input type="submit" value="Match">
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr><td colspan="4">No similar users found.</td></tr>
                    <% } %>
                </tbody>
            </table>

            <div class="popup-actions">
                <a href="<%= request.getContextPath() %>/admindashboard" class="btn">Back to Dashboard</a>
            </div>
        </div>
    </body>
</html>
