<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Đăng ký tài khoản</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #e8eef5;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
    }

    .header {
      background-color: #4a4a6a;
      height: 8px;
      width: 100%;
    }

    .container {
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 40px 20px;
    }

    .form-card {
      background: white;
      border-radius: 16px;
      padding: 40px;
      width: 100%;
      max-width: 480px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    }

    .form-title {
      font-size: 28px;
      font-weight: 700;
      color: #2d3748;
      text-align: center;
      margin-bottom: 8px;
    }

    .form-subtitle {
      color: #718096;
      text-align: center;
      margin-bottom: 24px;
      font-size: 15px;
    }

    .message {
      margin-bottom: 16px;
      padding: 10px 12px;
      border-radius: 8px;
      font-size: 14px;
      background: #fef2f2;
      color: #b91c1c;
      border: 1px solid #fecaca;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-row {
      display: flex;
      gap: 16px;
    }

    .form-row .form-group {
      flex: 1;
    }

    label {
      display: block;
      font-size: 14px;
      color: #4a5568;
      margin-bottom: 8px;
      font-weight: 500;
    }

    input {
      width: 100%;
      padding: 14px 16px;
      border: 1px solid #e2e8f0;
      border-radius: 8px;
      font-size: 15px;
      transition: all 0.2s ease;
      outline: none;
    }

    input:focus {
      border-color: #6366f1;
      box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
    }

    .btn-primary {
      width: 100%;
      padding: 14px;
      background: linear-gradient(135deg, #6366f1 0%, #5855eb 100%);
      color: white;
      border: none;
      border-radius: 8px;
      font-size: 16px;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.2s ease;
      margin-top: 8px;
    }

    .btn-primary:hover {
      background: linear-gradient(135deg, #5855eb 0%, #4f46e5 100%);
      transform: translateY(-1px);
      box-shadow: 0 4px 12px rgba(99, 102, 241, 0.4);
    }

    .form-footer {
      text-align: center;
      margin-top: 24px;
      color: #718096;
      font-size: 14px;
    }

    .form-footer a {
      color: #6366f1;
      text-decoration: none;
      font-weight: 500;
    }

    .form-footer a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="header"></div>
  
  <div class="container">
    <div class="form-card">
      <h1 class="form-title">Đăng ký tài khoản</h1>
      <p class="form-subtitle">Tạo tài khoản để bắt đầu thi</p>

      <c:if test="${not empty errorMessage}">
        <div class="message error" style="color: red; background: #fee; padding: 10px; border-radius: 5px; margin-bottom: 10px;">
          ${errorMessage}
        </div>
      </c:if>
      
      <form action="${pageContext.request.contextPath}/auth" method="post">
        <input type="hidden" name="action" value="register"/>

        <div class="form-group">
          <label for="fullname">Họ và tên</label>
          <input type="text" id="fullname" name="fullname" value="${fullname}" required>
        </div>
        
        <div class="form-row" style="display: flex; gap: 10px;">
          <div class="form-group" style="flex: 1;">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${email}" required>
          </div>
          <div class="form-group" style="flex: 1;">
            <label for="username">Tên đăng nhập</label>
            <input type="text" id="username" name="username" value="${username}" required>
          </div>
        </div>
        
        <div class="form-row" style="display: flex; gap: 10px;">
          <div class="form-group" style="flex: 1;">
            <label for="password">Mật khẩu</label>
            <input type="password" id="password" name="password" required>
          </div>
          <div class="form-group" style="flex: 1;">
            <label for="confirmPassword">Nhập lại mật khẩu</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
          </div>
        </div>
        
        <button type="submit" class="btn-primary">Tạo tài khoản</button>
      </form>
      
      <div class="form-footer">
        Đã có tài khoản? <a href="${pageContext.request.contextPath}/auth?action=login">Đăng nhập</a>
      </div>
    </div>
  </div>
</body>
</html>