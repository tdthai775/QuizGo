<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<section class="page-header">
    <h1 class="page-title">Quản lý người dùng</h1>
    <p class="page-subtitle">Tài khoản tham gia hệ thống</p>
</section>

<div class="card table-card">
    <div style="display:flex; justify-content: space-between; align-items:center; margin-bottom:10px;">
        <span>Danh sách người dùng</span>
        <a href="${pageContext.request.contextPath}/views/auth/register.jsp" class="btn-primary" style="text-decoration: none;">+ Thêm người dùng</a>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên đăng nhập</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Quyền</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${userList}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>
                    <span class="badge" style="background: #ebf8ff; color: #2c5282;">${u.role}</span>
                </td>
                <td class="actions">
                    <a href="${pageContext.request.contextPath}/admin/users?action=delete&id=${u.id}" 
                       onclick="return confirm('Xóa user này sẽ xóa hết lịch sử thi của họ. Bạn chắc chứ?')" 
                       style="color: red;">Xóa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>