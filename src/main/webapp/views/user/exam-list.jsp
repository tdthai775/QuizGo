<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1>Danh sách đề thi</h1>
    <p>Chọn một đề thi để bắt đầu làm bài.</p>
</div>

<div class="search-box">
    <input type="text" placeholder="Tìm kiếm đề thi...">
</div>

<div class="exam-grid">
    <!-- Ví dụ tĩnh, sau này thay bằng c:forEach exams -->
    <div class="exam-card">
        <h2 class="exam-title">Đề thi Java Cơ bản</h2>
        <p class="exam-description">
            Gồm 20 câu hỏi trắc nghiệm về Java cơ bản, thời gian làm bài 30 phút.
        </p>
        <div class="exam-info">
            <div>Số câu: <strong>20</strong></div>
            <div>Thời gian: <strong>30 phút</strong></div>
        </div>
        <button class="btn btn-gradient"
                onclick="window.location.href='layout.jsp?page=take&examId=1'">
            Bắt đầu thi
        </button>
    </div>

    <!-- thêm các card khác tương tự hoặc dùng forEach -->
</div>
