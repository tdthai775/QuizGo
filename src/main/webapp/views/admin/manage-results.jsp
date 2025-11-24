<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<section class="page-header">
    <h1 class="page-title">Kết quả thi</h1>
    <p class="page-subtitle">Theo dõi điểm số của học sinh</p>
</section>

<div class="card" style="margin-bottom: 20px; padding: 15px;">
    <form action="${pageContext.request.contextPath}/admin/results" method="get" style="display: flex; gap: 10px;">
        <input type="text" name="keyword" value="${keyword}" placeholder="Tìm theo tên học sinh..." style="padding: 8px; border: 1px solid #ccc; border-radius: 4px; flex: 1;">
        
        <select name="examId" style="padding: 8px; border: 1px solid #ccc; border-radius: 4px;">
            <option value="">-- Tất cả đề thi --</option>
            <c:forEach var="exam" items="${examList}">
                <option value="${exam.id}" ${exam.id == param.examId ? 'selected' : ''}>${exam.title}</option>
            </c:forEach>
        </select>
        
        <button type="submit" class="btn-primary">Tìm kiếm</button>
    </form>
</div>

<div class="card table-card">
    <table class="data-table">
        <thead>
        <tr>
            <th>#</th>
            <th>Họ tên</th>
            <th>Đề thi</th>
            <th>Điểm</th>
            <th>Thời gian nộp</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="res" items="${resultList}">
            <tr>
                <td>${res.submissionId}</td>
                <td>${res.studentName}</td>
                <td>${res.examTitle}</td>
                <td>
                    <c:choose>
                        <c:when test="${res.score != null}">
                            <strong style="color: ${res.score >= 5 ? 'green' : 'red'}">${res.score}</strong>
                        </c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <fmt:formatDate value="${res.submittedAt}" pattern="dd/MM/yyyy HH:mm"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/results?action=detail&id=${res.submissionId}" style="color: blue;">Xem bài làm</a>
                </td>
            </tr>
        </c:forEach>
        
        <c:if test="${empty resultList}">
            <tr><td colspan="6" style="text-align: center; padding: 20px;">Không tìm thấy kết quả nào.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>