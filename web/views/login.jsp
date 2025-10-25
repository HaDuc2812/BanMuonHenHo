<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bạn Muốn Hẹn Hò</title>
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
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .container {
                background: white;
                padding: 40px;
                border-radius: 20px;
                box-shadow: 0 10px 40px rgba(0,0,0,0.2);
                width: 400px;
            }
            h1 {
                text-align: center;
                color: #667eea;
                margin-bottom: 10px;
            }
            .subtitle {
                text-align: center;
                color: #666;
                margin-bottom: 30px;
                font-size: 14px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 5px;
                color: #333;
                font-weight: 500;
            }
            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 12px;
                border: 2px solid #e0e0e0;
                border-radius: 8px;
                font-size: 14px;
                transition: border-color 0.3s;
            }
            input[type="text"]:focus,
            input[type="password"]:focus {
                outline: none;
                border-color: #667eea;
            }
            .btn {
                width: 100%;
                padding: 12px;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                font-weight: 600;
                cursor: pointer;
                transition: transform 0.2s, box-shadow 0.2s;
            }
            .btn:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(0,0,0,0.2);
            }
            .btn-login {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                margin-bottom: 15px;
            }
            .btn-register {
                background: white;
                color: #667eea;
                border: 2px solid #667eea;
            }
            .error {
                background: #fee;
                color: #c33;
                padding: 10px;
                border-radius: 8px;
                margin-bottom: 20px;
                text-align: center;
            }
            .success {
                background: #efe;
                color: #3c3;
                padding: 10px;
                border-radius: 8px;
                margin-bottom: 20px;
                text-align: center;
            }
            .divider {
                text-align: center;
                margin: 20px 0;
                color: #999;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>❤️ Bạn Muốn Hẹn Hò</h1>
            <p class="subtitle">Tìm kiếm người phù hợp với bạn</p>

            <!--            <% if (request.getAttribute("error") != null) { %>
                        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
            <% } %>

            <% if (request.getParameter("message") != null) { %>
            <div class="success">
            <%= request.getParameter("message") %>
        </div>
            <% } %>-->

            <form action="<%= request.getContextPath() %>/LoginController" method="POST">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-login">Đăng Nhập</button>
            </form>

            <div class="divider">HOẶC</div>

            <form action="<%= request.getContextPath() %>/RegisterController" method="GET">
                <button type="submit" class="btn btn-register">Tạo Tài Khoản Mới</button>
            </form>
        </div>
    </body>
</html>
