<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="page-header">
    <h1 class="page-title">Thêm đề thi mới</h1>
    <a href="${pageContext.request.contextPath}/admin/exams" style="color:var(--active-bg);">← Quay lại</a>
</div>
<div class="card" style="max-width:700px;">
    <form action="${pageContext.request.contextPath}/admin/exams" method="post">
        <input type="hidden" name="action" value="create">
        <div class="form-group"><label>Tên đề thi</label><input type="text" name="title" class="form-control" required placeholder="Nhập tên đề thi..."></div>
        <div class="form-group"><label>Mô tả</label><textarea name="description" class="form-control" rows="3"></textarea></div>
        <div class="grid-2">
            <div class="form-group"><label>Thời gian (phút)</label><input type="number" name="durationMinutes" class="form-control" value="45" required></div>
            <div class="form-group"><label>Trạng thái</label><select name="status" class="form-control"><option value="1">Đang mở</option><option value="0">Đã ẩn</option></select></div>
        </div>
        <button type="submit" class="btn-primary">Tạo đề thi</button>
    </form>
</div>
