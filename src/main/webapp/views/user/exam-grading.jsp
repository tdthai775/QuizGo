<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="center-page">
    <div class="grading-container">
        <div class="grading-card">
            <h1 class="grading-title">Trạng thái bài thi</h1>
            <p class="grading-subtitle">Bài thi: <strong>Java OOP</strong></p>

            <div class="spinner-container">
                <div class="spinner"></div>
            </div>

            <p class="grading-message">
                Bài thi của bạn đang được hệ thống chấm điểm...
            </p>
            <p class="grading-info">
                Vui lòng chờ trong giây lát hoặc quay lại trang lịch sử để kiểm tra kết quả.
            </p>

            <a href="layout.jsp?page=history" class="btn btn-secondary">
                Về lịch sử thi
            </a>
        </div>
    </div>
</div>

<script>
    // Sau này có thể gọi AJAX check trạng thái, nếu xong thì redirect result
    // setTimeout(() => location.href='user-layout.jsp?page=result&submission=2', 5000);
</script>
