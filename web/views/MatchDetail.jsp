<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Match, viewmodels.UserDetail, model.Users" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Match Detail</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: #333;
                padding: 40px;
            }
            .container {
                background: #fff;
                max-width: 700px;
                margin: 0 auto;
                padding: 40px;
                border-radius: 20px;
                box-shadow: 0 10px 30px rgba(0,0,0,0.15);
            }
            h1 {
                color: #667eea;
                text-align: center;
                margin-bottom: 30px;
            }
            .info {
                margin-bottom: 20px;
            }
            .info label {
                font-weight: 600;
                color: #555;
            }
            .status {
                display: inline-block;
                padding: 8px 16px;
                border-radius: 20px;
                color: #fff;
                font-weight: bold;
            }
            .pending {
                background: #f1c40f;
            }
            .confirmed {
                background: #2ecc71;
            }
            .rejected {
                background: #e74c3c;
            }

            .btn-group {
                margin-top: 30px;
                text-align: center;
            }
            .btn {
                padding: 12px 25px;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                font-size: 15px;
                font-weight: 600;
                margin: 0 10px;
                transition: transform 0.2s, background 0.3s;
            }
            .btn:hover {
                transform: translateY(-2px);
            }
            .btn-confirm {
                background: #2ecc71;
                color: white;
            }
            .btn-reject {
                background: #e74c3c;
                color: white;
            }
            .btn-back {
                background: #bdc3c7;
                color: #333;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%
                Match match = (Match) request.getAttribute("match");
                UserDetail currentUser = (UserDetail) session.getAttribute("currentUser");

                Users user1 = (Users) request.getAttribute("user1");
                Users user2 = (Users) request.getAttribute("user2");
                if (match == null) {
            %>
            <h1>Match not found</h1>
            <p style="text-align:center;">This match may have been deleted or is unavailable.</p>
            <div class="btn-group">
                <a href="main" class="btn btn-back">Back to Main</a>
            </div>
            <%
                } else {
            %>
            <h1>Match Detail</h1>

            <div class="info">
                <label>Match ID:</label> <span><%= match.getMatchId() %></span>
            </div>
            <div class="info">
                <label>User 1:</label> 
                <p><b>User 1:</b> <%= user1 != null ? user1.getFullName() : "Unknown" %></p>            </div>
            <div class="info">
                <label>User 2:</label> 
                <p><b>User 2:</b> <%= user2 != null ? user2.getFullName() : "Unknown" %></p>            </div>
            <div class="info">
                <label>Admin ID:</label> <span><%= match.getAdminId() %></span>
            </div>
            <div class="info">
                <label>Match Date:</label>
                <fmt:formatDate value="<%= match.getMatchDate() %>" pattern="dd/MM/yyyy HH:mm"/>
            </div>
            <div class="info">
                <label>Status:</label>
                <span class="status <%= match.getStatus() %>"><%= match.getStatus() %></span>
            </div>

            <c:if test="${match.status eq 'pending'}">
                <form action="matchupdate" method="post">
                    <input type="hidden" name="matchId" value="<%= match.getMatchId() %>">
                    <input type="hidden" name="userId" value="<%= currentUser.getUserId() %>">
                    <div class="btn-group">
                        <button type="submit" name="action" value="confirm" class="btn btn-confirm">Confirm</button>
                        <button type="submit" name="action" value="reject" class="btn btn-reject">Reject</button>
                        <a href="main" class="btn btn-back">Back</a>
                    </div>
                </form>
            </c:if>

            <c:if test="${match.status ne 'pending'}">
                <div class="btn-group">
                    <a href="main" class="btn btn-back">Back</a>
                </div>
            </c:if>
            <%
                }
            %>
        </div>
    </body>
</html>
