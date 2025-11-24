<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="result-container">
    <div class="logo-header" style="margin-bottom: 20px;">
        <a href="${pageContext.request.contextPath}/user/exam?action=list">← Về danh sách đề thi</a>
    </div>

    <div class="result-card">
        <h1 class="result-title">Kết quả bài thi</h1>
        <p class="result-subtitle">
            Đề thi: <strong>${submission.examTitle}</strong>
        </p>

        <c:set var="correctCount" value="0" />
        <c:forEach var="detail" items="${detailList}">
            <c:if test="${detail.correct}">
                <c:set var="correctCount" value="${correctCount + 1}" />
            </c:if>
        </c:forEach>

        <div class="result-grid">
            <div class="result-item">
                <div class="result-item-label">Điểm số</div>
                <div class="result-item-value score" style="color: #d9534f; font-size: 2em;">${submission.score}</div>
            </div>
            <div class="result-item">
                <div class="result-item-label">Số câu đúng</div>
                <div class="result-item-value">${correctCount} / ${detailList.size()}</div>
            </div>
            <div class="result-item">
                <div class="result-item-label">Ngày nộp</div>
                <div class="result-item-value">
                    <fmt:formatDate value="${submission.submittedAt}" pattern="dd/MM/yyyy"/>
                </div>
            </div>
        </div>

        <hr style="margin: 30px 0; border: 0; border-top: 1px solid #eee;">
        
        <div class="questions-review">
            <h3>Chi tiết bài làm:</h3>
            <c:forEach var="d" items="${detailList}" varStatus="status">
                <div class="review-item" style="margin-bottom: 20px; padding: 15px; border: 1px solid #eee; border-radius: 8px; 
                    background-color: ${d.correct ? '#f0fff4' : '#fff5f5'};">
                    
                    <p><strong>Câu ${status.index + 1}:</strong> ${d.question.content}</p>
                    
                    <ul style="list-style: none; padding-left: 0;">
                        <c:forEach var="opt" items="${d.options}">
                            <li style="
                                padding: 5px;
                                ${opt.id == d.correctChoiceId ? 'color: green; font-weight: bold;' : ''}
                                ${opt.id == d.userChoiceId && !d.correct ? 'color: red; text-decoration: line-through;' : ''}
                            ">
                                <c:choose>
                                    <c:when test="${opt.id == d.userChoiceId}">
                                        <i class="fas ${d.correct ? 'fa-check-circle' : 'fa-times-circle'}"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="far fa-circle"></i>
                                    </c:otherwise>
                                </c:choose>
                                ${opt.content}
                                <c:if test="${opt.id == d.correctChoiceId}"> (Đáp án đúng)</c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </div>

        <div class="action-buttons" style="margin-top: 30px; text-align: center;">
            <a href="${pageContext.request.contextPath}/user/exam?action=history" class="btn btn-secondary">
                Xem lịch sử thi
            </a>
            <a href="${pageContext.request.contextPath}/user/exam?action=list" class="btn btn-gradient">
                Thi đề khác
            </a>
        </div>
    </div>
</div>