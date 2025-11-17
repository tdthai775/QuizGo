<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1>Lịch sử bài thi</h1>
    <p>Danh sách các bài thi bạn đã thực hiện.</p>
</div>

<div class="table-container">
    <table class="data-table">
        <thead>
        <tr>
            <th>Đề thi</th>
            <th>Ngày thi</th>
            <th>Điểm</th>
            <th>Trạng thái</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <!-- ví dụ tĩnh, sau này dùng c:forEach history -->
        <tr>
            <td><strong>JSP/Servlet cơ bản</strong></td>
            <td>12/11/2025 10:00</td>
            <td><span class="score">9.0</span></td>
            <td><span class="badge badge-success">Đã chấm</span></td>
            <td><a href="layout.jsp?page=result&submission=1" class="action-link">Xem</a></td>
        </tr>
        <tr>
            <td><strong>Java OOP</strong></td>
            <td>10/11/2025 09:30</td>
            <td><span class="score warning">Đang chấm</span></td>
            <td><span class="badge badge-warning">Pending</span></td>
            <td><a href="layout.jsp?page=grading&submission=2" class="action-link">Trạng thái</a></td>
        </tr>
        </tbody>
    </table>
</div>
