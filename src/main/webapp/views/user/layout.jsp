<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>QuizOnline - User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/user/styles/layout.css">
<body>
<header class="header">
    <div class="header-content">
        <a href="${pageContext.request.contextPath}/user/exam?action=list" class="logo">QuizOnline</a>
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/user/exam?action=list"
               class="${param.page == 'exam-list' || empty param.page ? 'nav-active' : ''}">
                Đề thi
            </a>
            <a href="${pageContext.request.contextPath}/user/exam?action=history"
               class="${param.page == 'history' ? 'nav-active' : ''}">
                Lịch sử thi
            </a>
            
            <span>Xin chào, <strong>${sessionScope.user.fullname}</strong></span>
            <a href="${pageContext.request.contextPath}/auth?action=logout" class="logout">Đăng xuất</a>
        </nav>
    </div>
</header>

<div class="page-wrapper">
    <c:choose>
        <c:when test="${param.page == 'history'}">
            <jsp:include page="exam-history.jsp" />
        </c:when>

        <c:when test="${param.page == 'take'}">
            <jsp:include page="take-exam.jsp" />
        </c:when>

        <c:when test="${param.page == 'result'}">
            <jsp:include page="exam-result.jsp" />
        </c:when>

        <c:when test="${param.page == 'grading'}">
            <jsp:include page="exam-grading.jsp" />
        </c:when>

        <c:otherwise>
            <jsp:include page="exam-list.jsp" />
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>