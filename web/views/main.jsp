<%-- 
    Document   : main
    Created on : Oct 24, 2025, 4:53:32 PM
    Author     : maith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%
        String message = (String) request.getAttribute("message");
        if (message == null) {
            message = (String) session.getAttribute("message");
            if (message != null) {
                session.removeAttribute("message");
            }
        }
        %>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                min-height: 100vh;
                padding: 20px;
            }
            .navbar {
                background: white;
                padding: 15px 30px;
                border-radius: 10px;
                margin-bottom: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 5px 20px rgba(0,0,0,0.1);
            }
            .navbar h2 {
                color: #667eea;
            }
            .navbar .user-info {
                display: flex;
                align-items: center;
                gap: 20px;
            }
            .navbar span {
                color: #666;
            }
            .btn-logout {
                padding: 8px 20px;
                background: #ff4757;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-weight: 600;
                transition: background 0.3s;
            }
            .btn-logout:hover {
                background: #ff3838;
            }
            .container {
                max-width: 1000px;
                margin: 0 auto;
                background: white;
                padding: 40px;
                border-radius: 20px;
                box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            }
            .tabs {
                display: flex;
                gap: 10px;
                margin-bottom: 30px;
                border-bottom: 2px solid #e0e0e0;
            }
            .tab {
                padding: 10px 30px;
                background: none;
                border: none;
                cursor: pointer;
                font-size: 16px;
                font-weight: 600;
                color: #666;
                border-bottom: 3px solid transparent;
                transition: all 0.3s;
            }
            .tab.active {
                color: #667eea;
                border-bottom-color: #667eea;
            }
            .tab-content {
                display: none;
            }
            .tab-content.active {
                display: block;
            }
            .profile-header {
                text-align: center;
                padding: 30px;
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                border-radius: 15px;
                margin-bottom: 30px;
            }
            .profile-header h1 {
                margin-bottom: 10px;
            }
            .profile-header .username {
                opacity: 0.9;
                font-size: 18px;
            }
            .info-grid {
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                gap: 20px;
                margin-bottom: 30px;
            }
            .info-item {
                padding: 15px;
                background: #f5f5f5;
                border-radius: 10px;
            }
            .info-item label {
                display: block;
                color: #667eea;
                font-weight: 600;
                margin-bottom: 5px;
                font-size: 14px;
            }
            .info-item .value {
                color: #333;
                font-size: 16px;
            }
            .tags-display {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
                margin-top: 10px;
            }
            .tag-badge {
                padding: 5px 15px;
                background: #667eea;
                color: white;
                border-radius: 20px;
                font-size: 14px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                color: #333;
                font-weight: 500;
            }
            input[type="text"],
            input[type="password"],
            input[type="date"],
            select,
            textarea {
                width: 100%;
                padding: 12px;
                border: 2px solid #e0e0e0;
                border-radius: 8px;
                font-size: 14px;
                font-family: inherit;
            }
            textarea {
                resize: vertical;
                min-height: 80px;
            }
            .tags-container {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
                gap: 10px;
                margin-top: 10px;
            }
            .tag-item {
                display: flex;
                align-items: center;
            }
            .tag-item input[type="checkbox"] {
                width: auto;
                margin-right: 8px;
            }
            .btn-group {
                display: flex;
                gap: 10px;
                margin-top: 30px;
            }
            .btn {
                flex: 1;
                padding: 12px;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                font-weight: 600;
                cursor: pointer;
                transition: transform 0.2s;
            }
            .btn:hover {
                transform: translateY(-2px);
            }
            .btn-primary {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
            }
            .btn-danger {
                background: #ff4757;
                color: white;
            }
            .alert {
                padding: 15px;
                border-radius: 8px;
                margin-bottom: 20px;
                text-align: center;
            }
            /* Match notification styling */
            .notify-card {
                position: fixed;
                top: 30px;
                right: 30px;
                width: 320px;
                background: white;
                border-radius: 15px;
                box-shadow: 0 10px 25px rgba(0,0,0,0.2);
                padding: 20px;
                animation: slideIn 0.5s ease-out;
                z-index: 9999;
            }

            @keyframes slideIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }

            .notify-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
            }

            .notify-header h3 {
                color: #667eea;
                font-size: 18px;
            }

            .btn-close {
                background: none;
                border: none;
                font-size: 20px;
                color: #999;
                cursor: pointer;
                transition: color 0.2s;
            }
            .btn-close:hover {
                color: #ff4757;
            }

            .notify-list {
                list-style: none;
                margin: 10px 0;
                padding-left: 0;
            }

            .notify-list li {
                padding: 8px 0;
                border-bottom: 1px solid #eee;
                font-size: 14px;
                color: #444;
            }

            .notify-list a {
                color: #667eea;
                font-weight: 600;
                text-decoration: none;
            }
            .notify-list a:hover {
                text-decoration: underline;
            }

            .notify-footer {
                text-align: right;
                margin-top: 10px;
            }

            .alert-success {
                background: #d4edda;
                color: #155724;
            }
            .alert-error {
                background: #f8d7da;
                color: #721c24;
            }
            .delete-confirm {
                background: #fff3cd;
                border: 2px solid #ffc107;
                padding: 20px;
                border-radius: 10px;
                margin-top: 20px;
            }
            .delete-confirm h3 {
                color: #856404;
                margin-bottom: 10px;
            }
            .delete-confirm p {
                color: #856404;
                margin-bottom: 15px;
            }
        </style>
        <script>
            function switchTab(tabName) {
                // Hide all tabs
                const contents = document.querySelectorAll('.tab-content');
                contents.forEach(content => content.classList.remove('active'));

                const tabs = document.querySelectorAll('.tab');
                tabs.forEach(tab => tab.classList.remove('active'));

                // Show selected tab
                document.getElementById(tabName).classList.add('active');
                document.querySelector(`[onclick="switchTab('${tabName}')"]`).classList.add('active');
            }

            function confirmDelete() {
                return confirm('Bạn có chắc chắn muốn xóa tài khoản? Hành động này không thể hoàn tác!');
            }
        </script>
    </head>
    <body>
        <c:if test="${not empty pendingMatches}">
            <div id="matchNotification" class="notify-card">
                <div class="notify-header">
                    <h3>💌 Bạn có lời mời ghép đôi mới!</h3>
                    <button class="btn-close" onclick="hideNotification()">×</button>
                </div>
                <ul class="notify-list">
                    <c:forEach var="m" items="${pendingMatches}">
                        <li>
                            <a href="mdetail?matchId=${m.matchId}">
                                💞 Match ID: ${m.matchId}
                            </a> — Đang chờ xác nhận của bạn.
                        </li>
                    </c:forEach>
                </ul>
                <div class="notify-footer">
                    <button class="btn btn-primary" onclick="hideNotification()">Đã hiểu</button>
                </div>
            </div>

            <script>
                function hideNotification() {
                    const box = document.getElementById('matchNotification');
                    box.style.opacity = '0';
                    setTimeout(() => box.style.display = 'none', 300);
                }
                function showNotify() {
                    const notify = document.getElementById("notifyWindow");
                    if (notify)
                        notify.style.display = "block";
                }

                function hideNotify() {
                    const notify = document.getElementById("notifyWindow");
                    if (notify)
                        notify.style.display = "none";
                }
            </script>
        </c:if>
        <!-- Navbar -->
        <div class="navbar">
            <h2>❤️ Bạn Muốn Hẹn Hò</h2>
            <div class="user-info">
                <span>Xin chào, <strong>${user.fullName}</strong></span>

                <!-- Bell icon -->
                <div class="bell" onclick="showNotify()">🔔</div>

                <!-- Notification Window -->
                <div class="notify-window" id="notifyWindow" style="display: none;">
                    <h3>Thông Báo</h3>
                    <c:if test="${not empty pendingMatches}">
                        <div id="matchNotification" class="notify-card">
                            <div class="notify-header">
                                <h3>💌 Bạn có lời mời ghép đôi mới!</h3>
                                <button class="btn-close" onclick="hideNotify()">×</button>
                            </div>
                            <ul class="notify-list">
                                <c:forEach var="m" items="${pendingMatches}">
                                    <li>
                                        <a href="mdetail?matchId=${m.matchId}">
                                            💞 Match ID: ${m.matchId}
                                        </a> — Đang chờ xác nhận của bạn.
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="notify-footer">
                                <button class="btn btn-primary" onclick="hideNotify()">Đã hiểu</button>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${empty pendingMatches}">
                        <div id="matchNotification" class="notify-card">
                            <div class="notify-header">
                                <h3>💌 Thông báo</h3>
                            </div>
                            <p>Hiện không có thông báo mới.</p>
                            <div class="notify-footer">
                                <button class="btn btn-primary" onclick="hideNotify()">Đóng</button>
                            </div>
                        </div>
                    </c:if>                    <button onclick="hideNotify()">Đóng</button>
                </div>
            </div>
        </div>
        <!-- Main Container -->
        <div class="container">
            <!-- Alerts -->
            <c:if test="${success != null}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${error != null}">
                <div class="alert alert-error">${error}</div>
            </c:if>

            <!-- Tabs -->
            <div class="tabs">
                <button class="tab active" onclick="switchTab('view')">👤 Thông Tin</button>
                <button class="tab" onclick="switchTab('edit')">✏️ Chỉnh Sửa</button>
                <button class="tab" onclick="switchTab('delete')">🗑️ Xóa Tài Khoản</button>
            </div>

            <!-- Tab: View Profile -->
            <div id="view" class="tab-content active">
                <div class="profile-header">
                    <h1>${user.fullName}</h1>
                    <p class="username">@${user.username}</p>
                </div>

                <div class="info-grid">
                    <div class="info-item">
                        <label>Giới tính:</label>
                        <div class="value">
                            <c:choose>
                                <c:when test="${user.gender == 'Male'}">Nam</c:when>
                                <c:when test="${user.gender == 'Female'}">Nữ</c:when>
                                <c:otherwise>Khác</c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="info-item">
                        <label>Ngày sinh:</label>
                        <div class="value">
                            <fmt:formatDate value="${user.birthDate}" pattern="dd/MM/yyyy"/>
                        </div>
                    </div>

                    <div class="info-item">
                        <label>Trạng thái:</label>
                        <div class="value">
                            <c:choose>
                                <c:when test="${user.status == 'pending'}">Đang chờ ghép đôi</c:when>
                                <c:when test="${user.status == 'matched'}">Đã được ghép đôi</c:when>
                                <c:when test="${user.status == 'confirmed'}">Đã xác nhận</c:when>
                            </c:choose>
                        </div>
                    </div>

                    <div class="info-item">
                        <label>Vai trò:</label>
                        <div class="value">${user.role.roleName}</div>
                    </div>
                </div>

                <div class="info-item" style="grid-column: 1/-1;">
                    <label>Giới thiệu:</label>
                    <div class="value">${user.bio != null ? user.bio : 'Chưa có thông tin'}</div>
                </div>

                <div class="info-item" style="margin-top: 20px;">
                    <label>💝 Sở thích của bạn:</label>
                    <div class="tags-display">
                        <c:forEach items="${user.tags}" var="tag">
                            <span class="tag-badge">${tag.tagName}</span>
                        </c:forEach>
                        <c:if test="${empty user.tags}">
                            <span style="color: #999;">Chưa có sở thích nào</span>
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- Tab: Edit Profile -->
            <div id="edit" class="tab-content">
                <h2 style="color: #667eea; margin-bottom: 20px;">Chỉnh sửa thông tin</h2>

                <form action="main" method="POST">
                    <input type="hidden" name="action" value="update">

                    <div class="form-group">
                        <label>Tên đăng nhập:</label>
                        <input type="text" value="${user.username}" disabled>
                        <small style="color: #999;">Không thể thay đổi tên đăng nhập</small>
                    </div>

                    <div class="form-group">
                        <label for="fullName">Họ và tên: *</label>
                        <input type="text" id="fullName" name="fullName" value="${user.fullName}" required>
                    </div>

                    <div class="form-group">
                        <label for="gender">Giới tính: *</label>
                        <select id="gender" name="gender" required>
                            <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Nam</option>
                            <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Nữ</option>
                            <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Khác</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birthDate">Ngày sinh: *</label>
                        <input type="date" id="birthDate" name="birthDate" 
                               value="<fmt:formatDate value='${user.birthDate}' pattern='yyyy-MM-dd'/>" required>
                    </div>

                    <div class="form-group">
                        <label for="bio">Giới thiệu bản thân:</label>
                        <textarea id="bio" name="bio">${user.bio}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="password">Mật khẩu mới:</label>
                        <input type="password" id="password" name="password" placeholder="Để trống nếu không muốn đổi">
                    </div>

                    <div class="form-group">
                        <label>💝 Sở thích của bạn:</label>
                        <div class="tags-container">
                            <c:forEach items="${allTags}" var="tag">
                                <div class="tag-item">
                                    <input type="checkbox" id="tag_${tag.tagId}" name="tags" value="${tag.tagId}"
                                           <c:forEach items="${user.tags}" var="userTag">
                                               <c:if test="${userTag.tagId == tag.tagId}">checked</c:if>
                                           </c:forEach>
                                           >
                                    <label for="tag_${tag.tagId}">${tag.tagName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="btn-group">
                        <button type="button" class="btn" style="background: #e0e0e0; color: #333;" 
                                onclick="switchTab('view')">Hủy</button>
                        <button type="submit" class="btn btn-primary">Lưu Thay Đổi</button>
                    </div>
                </form>
            </div>

            <!-- Tab: Delete Account -->
            <div id="delete" class="tab-content">
                <h2 style="color: #ff4757; margin-bottom: 20px;">⚠️ Xóa Tài Khoản</h2>

                <div class="delete-confirm">
                    <h3>Cảnh báo quan trọng!</h3>
                    <p>Việc xóa tài khoản sẽ:</p>
                    <ul style="margin-left: 20px; color: #856404;">
                        <li>Xóa vĩnh viễn tất cả thông tin cá nhân</li>
                        <li>Xóa tất cả sở thích và lịch sử ghép đôi</li>
                        <li>Không thể khôi phục lại được</li>
                    </ul>

                    <form action="MainController" method="POST" onsubmit="return confirmDelete();">
                        <input type="hidden" name="action" value="delete">
                        <div class="btn-group" style="margin-top: 20px;">
                            <button type="button" class="btn" style="background: #e0e0e0; color: #333;" 
                                    onclick="switchTab('view')">Quay Lại</button>
                            <button type="submit" class="btn btn-danger">Xác Nhận Xóa Tài Khoản</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
