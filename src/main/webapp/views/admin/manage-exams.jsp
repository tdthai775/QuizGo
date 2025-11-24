<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <div>
        <h1 class="page-title">Quản lý Đề thi</h1>
        <p class="page-subtitle">Danh sách các bài kiểm tra trong hệ thống</p>
    </div>
    <a href="${pageContext.request.contextPath}/admin/exams?action=create" class="btn-primary">
        <i class="fas fa-plus"></i> Thêm đề thi
    </a>
</div>

<div class="card">
    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên đề thi</th>
                <th>Thời gian</th>
                <th>Số câu</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="e" items="${examList}">
                <tr>
                    <td>#${e.id}</td>
                    <td>
                        <span style="font-weight:600; color:#181c32;">${e.title}</span>
                        <div style="font-size:12px; color:#a1a5b7; margin-top:4px;">${e.description}</div>
                    </td>
                    <td>${e.durationMinutes} phút</td>
                    <td>${e.questionCount} câu</td>
                    <td>
                        <c:choose>
                            <c:when test="${e.status == 1}"><span class="badge badge-success">Đang mở</span></c:when>
                            <c:otherwise><span class="badge badge-danger">Đã ẩn</span></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/exams?action=edit&id=${e.id}" 
                           class="btn-primary btn-sm" style="background:#f1faff; color:#009ef7;">
                           <i class="fas fa-edit"></i> Sửa
                        </a>
                        
                        <a href="${pageContext.request.contextPath}/admin/exams?action=delete&id=${e.id}" 
                           onclick="return confirm('Bạn có chắc chắn muốn xóa đề thi này không?')" 
                           class="btn-primary btn-sm" style="background:#fff5f8; color:#f1416c; margin-left: 5px;">
                           <i class="fas fa-trash"></i> Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            
            <c:if test="${empty examList}">
                <tr>
                    <td colspan="6" style="text-align:center; padding: 20px;">Chưa có đề thi nào.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>