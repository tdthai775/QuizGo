<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<section class="page-header">
    <h1 class="page-title">Dashboard</h1>
    <p class="page-subtitle">Tổng quan hệ thống</p>
</section>

<div class="cards-row">
    <div class="card">
        <div class="card-label">Tổng đề thi</div>
        <div class="card-value">15</div>
    </div>

    <div class="card">
        <div class="card-label">Câu hỏi</div>
        <div class="card-value">320</div>
    </div>

    <div class="card">
        <div class="card-label">Người dùng</div>
        <div class="card-value">142</div>
    </div>
</div>

<div class="cards-row-2">
    <div class="card">
        <div class="card-label">Bài thi đã nộp</div>
        <div class="card-value">856</div>
    </div>
</div>

<div class="cards-row-2">
    <!-- Hoạt động gần đây -->
    <section class="panel">
        <div class="panel-title">Hoạt động gần đây</div>
        <p class="panel-subtitle">Những cập nhật mới nhất</p>

        <div class="timeline-item">
            <div class="timeline-left">
                <div class="timeline-icon">📦</div>
                <div>
                    <div>Thêm đề thi: Database SQL</div>
                    <div class="timeline-time">2 giờ trước</div>
                </div>
            </div>
        </div>

        <div class="timeline-item">
            <div class="timeline-left">
                <div class="timeline-icon">👤</div>
                <div>
                    <div>Người dùng mới: nguyenvana</div>
                    <div class="timeline-time">5 giờ trước</div>
                </div>
            </div>
        </div>

        <div class="timeline-item">
            <div class="timeline-left">
                <div class="timeline-icon">📝</div>
                <div>
                    <div>Thêm 15 câu hỏi Java OOP</div>
                    <div class="timeline-time">1 ngày trước</div>
                </div>
            </div>
        </div>
    </section>

    <!-- Đề thi phổ biến -->
    <section class="panel">
        <div class="panel-title">Đề thi phổ biến</div>

        <div class="popular-item">
            <span>JSP/Servlet</span>
            <strong>#1</strong>
        </div>
        <div class="popular-item">
            <span>Java Cơ bản</span>
            <strong>#2</strong>
        </div>
        <div class="popular-item">
            <span>Database SQL</span>
            <strong>#3</strong>
        </div>
    </section>
</div>
