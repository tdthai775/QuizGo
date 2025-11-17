<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="result-container">
    <div class="logo-header">
        <a href="layout.jsp?page=exam-list">Về danh sách đề thi</a>
    </div>

    <div class="result-card">
        <h1 class="result-title">Kết quả bài thi</h1>
        <p class="result-subtitle">
            Đề thi: <strong>JSP/Servlet cơ bản</strong>
        </p>

        <div class="result-grid">
            <div class="result-item">
                <div class="result-item-label">Điểm số</div>
                <div class="result-item-value score">9.0</div>
            </div>
            <div class="result-item">
                <div class="result-item-label">Số câu đúng</div>
                <div class="result-item-value">18 / 20</div>
            </div>
            <div class="result-item">
                <div class="result-item-label">Thời gian làm</div>
                <div class="result-item-value">24 phút</div>
            </div>
        </div>

        <div class="result-details">
            <p>
                <span>Thời gian bắt đầu:</span>
                <strong>10:00 - 12/11/2025</strong>
            </p>
            <p>
                <span>Thời gian nộp:</span>
                <strong>10:24 - 12/11/2025</strong>
            </p>
        </div>

        <div class="action-buttons">
            <a href="layout.jsp?page=history" class="btn btn-secondary">
                Xem lịch sử thi
            </a>
            <a href="layout.jsp?page=exam-list" class="btn btn-gradient">
                Thi đề khác
            </a>
        </div>
    </div>
</div>
