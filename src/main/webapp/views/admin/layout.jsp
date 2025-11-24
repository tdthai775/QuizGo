<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:set var="currentPage" value="${requestScope.page != null ? requestScope.page : param.page}" />

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Panel - QuizOnline</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/admin/styles/layout.css">
</head>
<body>

<div class="app">
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo-circle">Q</div>
            <div>
                <div class="brand-title">QuizOnline</div>
                <div class="brand-sub">Admin Panel</div>
            </div>
        </div>

        <nav class="sidebar-menu">
            <a href="${pageContext.request.contextPath}/admin/dashboard" 
               class="menu-item ${currentPage == 'dashboard' || empty currentPage ? 'active' : ''}">
                <i class="fas fa-home menu-icon"></i> Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/admin/exams" 
               class="menu-item ${currentPage == 'manage-exams' || currentPage == 'exam-create' || currentPage == 'exam-edit' ? 'active' : ''}">
                <i class="fas fa-file-alt menu-icon"></i> Đề thi
            </a>

            <a href="${pageContext.request.contextPath}/admin/results" 
               class="menu-item ${currentPage == 'results' || currentPage == 'result-detail' ? 'active' : ''}">
                <i class="fas fa-chart-bar menu-icon"></i> Kết quả
            </a>

            <a href="${pageContext.request.contextPath}/admin/users" 
               class="menu-item ${currentPage == 'manage-users' ? 'active' : ''}">
                <i class="fas fa-users menu-icon"></i> Người dùng
            </a>
        </nav>

        <div class="sidebar-footer">
            <div class="user-box">
                <div class="user-avatar">A</div>
                <div class="user-info">
                    <span class="user-name">${sessionScope.user.username}</span>
                    <span class="user-role">Administrator</span>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/auth?action=logout" class="btn-logout">Đăng xuất</a>
        </div>
    </aside>

    <main class="content">
        <c:choose>
            <c:when test="${empty currentPage || currentPage == 'dashboard'}">
                <jsp:include page="dashboard.jsp" />
            </c:when>

            <c:when test="${currentPage == 'manage-exams'}">
                <jsp:include page="manage-exams.jsp" />
            </c:when>
            <c:when test="${currentPage == 'exam-create'}">
                <jsp:include page="exam-create.jsp" />
            </c:when>
            <c:when test="${currentPage == 'exam-edit'}">
                <jsp:include page="exam-edit.jsp" />
            </c:when>

            <c:when test="${currentPage == 'results'}">
                <jsp:include page="manage-results.jsp" />
            </c:when>
            <c:when test="${currentPage == 'result-detail'}">
                <jsp:include page="result-detail.jsp" />
            </c:when>

            <c:when test="${currentPage == 'manage-users'}">
                <jsp:include page="manage-users.jsp" />
            </c:when>
            
            <c:when test="${currentPage == 'questions'}">
                <jsp:include page="questions.jsp" />
            </c:when>
            <c:when test="${currentPage == 'question-form'}">
                <jsp:include page="question-form.jsp" />
            </c:when>

            <c:otherwise>
                <div style="padding:20px;">404 - Không tìm thấy trang: ${currentPage}</div>
            </c:otherwise>
        </c:choose>
    </main>
</div>

</body>
</html>