<%-- 
    Document   : AdminNavbar
    Created on : Oct 23, 2025, 3:31:19 PM
    Author     : HA DUC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Navbar -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">

    <div class="navbar">
        <a href="<%=request.getContextPath()%>/Userlist">User List</a>
        <a href="<%=request.getContextPath()%>/Match">Match List</a>
        <a href="<%=request.getContextPath()%>/admindashboard">Back to dashboard</a>
    </div>
</html>
