<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <div>
        <h1 class="page-title">Dashboard</h1>
        <p class="page-subtitle">Tổng quan hệ thống trắc nghiệm</p>
    </div>
</div>

<div class="stats-grid">
    <div class="stat-card">
        <div class="stat-num">${dashboardStats.totalExams}</div>
        <div class="stat-label">Tổng đề thi</div>
    </div>
    <div class="stat-card">
        <div class="stat-num">${dashboardStats.totalUsers}</div>
        <div class="stat-label">Thí sinh đăng ký</div>
    </div>
    <div class="stat-card">
        <div class="stat-num">${dashboardStats.totalSubmissions}</div>
        <div class="stat-label">Lượt thi đã nộp</div>
    </div>
</div>

<div class="grid-2">
    <div class="card">
        <h3 class="panel-title" style="margin-bottom:20px;">Hoạt động gần đây</h3>
        <c:forEach var="act" items="${recentActivities}">
            <div class="timeline-item">
                <div class="timeline-left">
                    <div class="timeline-icon" style="display:inline-block; margin-right:10px;">🕒</div>
                    <span>${act.content}</span>
                </div>
                <div class="timeline-time">${act.timeAgo}</div>
            </div>
        </c:forEach>
    </div>

    <div class="card">
        <h3 class="panel-title" style="margin-bottom:20px;">Đề thi phổ biến</h3>
        <c:forEach var="ex" items="${popularExams}">
            <div class="popular-item">
                <span style="font-weight:600;">${ex.examTitle}</span>
                <span class="badge badge-success">${ex.attemptCount} lượt</span>
            </div>
        </c:forEach>
    </div>
</div>