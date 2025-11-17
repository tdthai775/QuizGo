<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>QuizOnline Admin</title>
	
	<link rel="stylesheet" href="<c:url value='/views/admin/styles/layout.css' />">	
</head>
<body>

<div class="app">
    <!-- ========== SIDEBAR ========== -->
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo-circle">Q</div>
            <div>
                <div class="brand-title">QuizOnline</div>
                <div class="brand-sub">Admin Panel</div>
            </div>
        </div>

        <nav class="sidebar-menu">
            <a href="layout.jsp?page=dashboard"
               class="menu-item ${param.page == 'dashboard' || empty param.page ? 'active' : ''}">
                <span class="menu-icon">🏠</span>
                <span>Dashboard</span>
            </a>

            <a href="layout.jsp?page=exams"
               class="menu-item ${param.page == 'exams' ? 'active' : ''}">
                <span class="menu-icon">📝</span>
                <span>Đề thi</span>
            </a>

            <a href="layout.jsp?page=results"
               class="menu-item ${param.page == 'results' ? 'active' : ''}">
                <span class="menu-icon">📊</span>
                <span>Kết quả</span>
            </a>

            <a href="layout.jsp?page=users"
               class="menu-item ${param.page == 'users' ? 'active' : ''}">
                <span class="menu-icon">👤</span>
                <span>Người dùng</span>
            </a>
        </nav>

        <div class="sidebar-footer">
            <div class="user-box">
                <div class="user-avatar">A</div>
                <div class="user-info">
                    <span class="user-name">admin</span>
                    <span class="user-role">Administrator</span>
                </div>
            </div>
            <button class="btn-logout">Đăng xuất</button>
        </div>
    </aside>

    <!-- ========== CONTENT ========== -->
    <main class="content">
        <c:choose>
            <c:when test="${empty param.page || param.page == 'dashboard'}">
                <jsp:include page="dashboard.jsp" />
            </c:when>

            <c:when test="${param.page == 'exams'}">
                <jsp:include page="manage-exams.jsp" />
            </c:when>

            <c:when test="${param.page == 'results'}">
                <jsp:include page="manage-results.jsp" />
            </c:when>

            <c:when test="${param.page == 'users'}">
                <jsp:include page="manage-users.jsp" />
            </c:when>
        </c:choose>
    </main>
</div>

</body>
</html>
