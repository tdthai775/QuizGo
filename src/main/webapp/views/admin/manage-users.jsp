<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<section class="page-header">
    <h1 class="page-title">Quản lý người dùng</h1>
    <p class="page-subtitle">Tài khoản tham gia hệ thống</p>
</section>

<div class="card table-card">
    <div style="display:flex; justify-content: space-between; align-items:center; margin-bottom:10px;">
        <span>Danh sách người dùng</span>
        <button class="btn-primary">+ Thêm người dùng</button>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>#</th>
            <th>Tên đăng nhập</th>
            <th>Họ tên</th>
            <th>Quyền</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>admin</td>
            <td>Quản trị viên</td>
            <td><span class="badge">Admin</span></td>
            <td>Hoạt động</td>
            <td class="actions">
                <a>Sửa</a>
                <a>Xóa</a>
            </td>
        </tr>
        <tr>
            <td>2</td>
            <td>nguyenvana</td>
            <td>Nguyễn Văn A</td>
            <td><span class="badge">User</span></td>
            <td>Hoạt động</td>
            <td class="actions">
                <a>Sửa</a>
                <a>Xóa</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
