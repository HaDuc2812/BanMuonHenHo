<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng Ký - Bạn Muốn Hẹn Hò</title>
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
                padding: 40px 20px;
            }

            .container {
                background: white;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 10px 25px rgba(0,0,0,0.2);
                max-width: 600px;
                margin: 0 auto;
            }

            h1 {
                text-align: center;
                color: #667eea;
                margin-bottom: 10px;
                font-size: 28px;
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

            .required {
                color: #c33;
            }

            input[type="text"],
            input[type="password"],
            input[type="date"],
            textarea,
            select {
                width: 100%;
                padding: 12px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 14px;
                font-family: inherit;
                transition: border-color 0.3s;
            }

            input:focus,
            textarea:focus,
            select:focus {
                outline: none;
                border-color: #667eea;
            }

            textarea {
                resize: vertical;
                min-height: 80px;
            }

            .tags-container {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
                gap: 10px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background: #f9f9f9;
            }

            .tag-item {
                display: flex;
                align-items: center;
            }

            .tag-item input[type="checkbox"] {
                width: auto;
                margin-right: 8px;
            }

            .tag-item label {
                margin: 0;
                font-weight: normal;
                cursor: pointer;
            }

            .error {
                background-color: #fee;
                color: #c33;
                padding: 12px;
                border-radius: 5px;
                margin-bottom: 20px;
                border-left: 4px solid #c33;
                font-size: 14px;
            }

            .btn {
                width: 100%;
                padding: 12px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                font-weight: 600;
                cursor: pointer;
                transition: all 0.3s;
            }

            .btn-primary {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                color: white;
                margin-bottom: 10px;
            }

            .btn-primary:hover {
                transform: translateY(-2px);
                box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            }

            .login-link {
                text-align: center;
                margin-top: 20px;
                color: #666;
                font-size: 14px;
            }

            .login-link a {
                color: #667eea;
                text-decoration: none;
                font-weight: 600;
            }

            .login-link a:hover {
                text-decoration: underline;
            }

            .row {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 15px;
            }

            @media (max-width: 768px) {
                .row {
                    grid-template-columns: 1fr;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>💑 Bạn Muốn Hẹn Hò</h1>
            <p class="subtitle">Tạo tài khoản để bắt đầu hành trình tìm kiếm</p>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form action="RegisterController" method="POST">
                <div class="form-group">
                    <label for="username">Tên đăng nhập <span class="required">*</span></label>
                    <input type="text" id="username" name="username" 
                           placeholder="Chọn tên đăng nhập" required>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="password">Mật khẩu <span class="required">*</span></label>
                        <input type="password" id="password" name="password" 
                               placeholder="Nhập mật khẩu" required>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Xác nhận mật khẩu <span class="required">*</span></label>
                        <input type="password" id="confirmPassword" name="confirmPassword" 
                               placeholder="Nhập lại mật khẩu" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="fullName">Họ và tên <span class="required">*</span></label>
                    <input type="text" id="fullName" name="fullName" 
                           placeholder="Nhập họ và tên đầy đủ" required>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="gender">Giới tính <span class="required">*</span></label>
                        <select id="gender" name="gender" required>
                            <option value="">Chọn giới tính</option>
                            <option value="Male">Nam</option>
                            <option value="Female">Nữ</option>
                            <option value="Other">Khác</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="birthDate">Ngày sinh <span class="required">*</span></label>
                        <input type="date" id="birthDate" name="birthDate" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="bio">Giới thiệu bản thân</label>
                    <textarea id="bio" name="bio" 
                              placeholder="Viết vài dòng về bản thân bạn..."></textarea>
                </div>

                <div class="form-group">
                    <label>Sở thích của bạn</label>
                    <div class="tags-container">
                        <c:forEach items="${allTags}" var="tag">
                            <div class="tag-item">
                                <input type="checkbox" id="tag${tag.tagId}" 
                                       name="tags" value="${tag.tagId}">
                                <label for="tag${tag.tagId}">${tag.tagName}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Đăng Ký</button>
            </form>

            <div class="login-link">
                Đã có tài khoản? <a href="index.html">Đăng nhập ngay</a>
            </div>
        </div>
    </body>
</html>