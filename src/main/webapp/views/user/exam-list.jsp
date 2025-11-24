<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1>Danh sách đề thi</h1>
    <p>Chọn một đề thi để bắt đầu làm bài.</p>
</div>

<div class="search-box">
    <input type="text" placeholder="Tìm kiếm đề thi...">
</div>

<c:if test="${not empty errorMessage}">
    <div style="color: red; margin-bottom: 15px; font-weight: bold;">${errorMessage}</div>
</c:if>

<div class="exam-grid">
    <c:forEach var="exam" items="${examList}">
        <div class="exam-card">
            <h2 class="exam-title">${exam.title}</h2>
            <p class="exam-description">
                ${exam.description}
            </p>
            <div class="exam-info">
                <div>Số câu: <strong>${exam.questionCount}</strong></div>
                <div>Thời gian: <strong>${exam.durationMinutes} phút</strong></div>
            </div>
            
            <button class="btn btn-gradient"
                    onclick="window.location.href='${pageContext.request.contextPath}/user/exam?action=take&examId=${exam.id}'">
                Bắt đầu thi
            </button>
        </div>
    </c:forEach>
    
    <c:if test="${empty examList}">
        <p>Hiện tại chưa có đề thi nào.</p>
    </c:if>
</div>