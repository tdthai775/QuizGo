<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h1>Lịch sử bài thi</h1>
    <p>Danh sách các bài thi bạn đã thực hiện.</p>
</div>

<div class="table-container">
    <table class="data-table">
        <thead>
        <tr>
            <th>Đề thi</th>
            <th>Ngày thi</th>
            <th>Điểm</th>
            <th>Trạng thái</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sub" items="${submissionList}">
            <tr>
                <td><strong>${sub.examTitle}</strong></td>
                <td><fmt:formatDate value="${sub.submittedAt}" pattern="dd/MM/yyyy HH:mm"/></td>
                <td>
                    <c:choose>
                        <c:when test="${sub.score != null}">
                            <span class="score" style="font-weight: bold; color: #007bff;">${sub.score}</span>
                        </c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${sub.status == 'done'}">
                            <span class="badge badge-success" style="background: #28a745; color: white; padding: 5px 10px; border-radius: 4px;">Đã chấm</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-warning" style="background: #ffc107; color: black; padding: 5px 10px; border-radius: 4px;">Đang chấm</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${sub.status == 'done'}">
                        <a href="${pageContext.request.contextPath}/user/exam?action=result&id=${sub.id}" class="action-link">Xem kết quả</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        
        <c:if test="${empty submissionList}">
            <tr>
                <td colspan="5" style="text-align: center;">Bạn chưa làm bài thi nào.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>