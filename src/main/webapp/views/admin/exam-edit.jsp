<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1 class="page-title">Chỉnh sửa: ${exam.title}</h1>
    <a href="${pageContext.request.contextPath}/admin/exams" style="color:var(--active-bg);">← Quay lại</a>
</div>

<div class="grid-2" style="align-items:start;">
    <div class="card">
        <h3 class="panel-title">Thông tin chung</h3>
        <form action="${pageContext.request.contextPath}/admin/exams" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${exam.id}">
            <div class="form-group"><label>Tên đề thi</label><input type="text" name="title" value="${exam.title}" class="form-control" required></div>
            <div class="form-group"><label>Mô tả</label><textarea name="description" class="form-control" rows="2">${exam.description}</textarea></div>
            <div class="grid-2">
                <div class="form-group"><label>Phút</label><input type="number" name="durationMinutes" value="${exam.durationMinutes}" class="form-control"></div>
                <div class="form-group"><label>Trạng thái</label>
                    <select name="status" class="form-control">
                        <option value="1" ${exam.status==1?'selected':''}>Mở</option>
                        <option value="0" ${exam.status==0?'selected':''}>Ẩn</option>
                    </select>
                </div>
            </div>
            <button type="submit" class="btn-primary" style="width:100%;">Lưu thay đổi</button>
        </form>
    </div>

    <div class="card">
        <div style="display:flex; justify-content:space-between; margin-bottom:20px;">
            <h3 class="panel-title">Danh sách câu hỏi (${questionList.size()})</h3>
            <button onclick="showModal('modal-add')" class="btn-primary btn-sm">+ Thêm câu</button>
        </div>
        
        <div class="question-list-container">
            <c:forEach var="q" items="${questionList}" varStatus="s">
                <div class="question-item">
                    <div class="question-header">
                        <span class="question-title">Câu ${s.index + 1}</span>
                        <div class="question-actions">
                            <a href="javascript:void(0)" onclick="showModal('modal-edit-${q.id}')" style="color:var(--active-bg);">Sửa</a>
                            
                            <a href="${pageContext.request.contextPath}/admin/questions?action=delete&id=${q.id}&examId=${exam.id}" onclick="return confirm('Xóa?')" style="color:var(--danger);">Xóa</a>
                        </div>
                    </div>
                    <p class="question-content">${q.content}</p>
                    
                    <div class="options-grid">
                        <c:forEach var="c" items="${q.choices}">
                            <div class="option-item ${c.correct ? 'correct' : ''}">
                                ${c.content} ${c.correct ? '✓' : ''}
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div id="modal-edit-${q.id}" class="modal-overlay">
                    <div class="modal-box">
                        <h3 class="modal-title">Sửa câu hỏi #${s.index + 1}</h3>
                        <form action="${pageContext.request.contextPath}/admin/questions" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="examId" value="${exam.id}">
                            <input type="hidden" name="id" value="${q.id}">
                            <input type="hidden" name="type" value="single">
                            
                            <div class="form-group">
                                <textarea name="content" class="form-control" rows="3" required>${q.content}</textarea>
                            </div>
                            
                            <label style="font-size:12px; font-weight:bold; margin-bottom:10px; display:block;">Chỉnh sửa đáp án</label>
                            
                            <c:forEach var="c" items="${q.choices}" varStatus="st">
                                <div class="modal-option-row">
                                    <input type="radio" name="choiceCorrect" value="${st.index}" ${c.correct ? 'checked' : ''}>
                                    <input type="text" name="choiceContent" value="${c.content}" class="form-control" required style="padding:8px;">
                                </div>
                            </c:forEach>
                            
                            <div class="modal-actions">
                                <button type="submit" class="btn-primary" style="flex:1;">Cập nhật</button>
                                <button type="button" onclick="closeModal('modal-edit-${q.id}')" class="btn-primary btn-cancel" style="flex:1;">Hủy</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
            
            <c:if test="${empty questionList}">
                <p style="text-align:center; color:#aaa; padding:20px;">Chưa có câu hỏi nào.</p>
            </c:if>
        </div>
    </div>
</div>

<div id="modal-add" class="modal-overlay">
    <div class="modal-box">
        <h3 class="modal-title">Thêm câu hỏi mới</h3>
        <form action="${pageContext.request.contextPath}/admin/questions" method="post">
            <input type="hidden" name="action" value="create">
            <input type="hidden" name="examId" value="${exam.id}">
            <input type="hidden" name="type" value="single">
            
            <div class="form-group">
                <textarea name="content" class="form-control" placeholder="Nội dung câu hỏi..." required></textarea>
            </div>
            
            <label style="font-size:12px; font-weight:bold; margin-bottom:10px; display:block;">Nhập đáp án</label>
            
            <c:forEach begin="0" end="3" var="i">
                <div class="modal-option-row">
                    <input type="radio" name="choiceCorrect" value="${i}" ${i==0?'checked':''}>
                    <input type="text" name="choiceContent" class="form-control" placeholder="Đáp án ${i+1}" required style="padding:8px;">
                </div>
            </c:forEach>
            
            <div class="modal-actions">
                <button type="submit" class="btn-primary" style="flex:1;">Lưu</button>
                <button type="button" onclick="closeModal('modal-add')" class="btn-primary btn-cancel" style="flex:1;">Hủy</button>
            </div>
        </form>
    </div>
</div>

<script>
    // Hàm mở modal theo ID
    function showModal(modalId) {
        document.getElementById(modalId).classList.add('active');
    }

    // Hàm đóng modal theo ID
    function closeModal(modalId) {
        document.getElementById(modalId).classList.remove('active');
    }

    // Đóng khi click ra ngoài (cho tất cả modal)
    window.onclick = function(event) {
        if (event.target.classList.contains('modal-overlay')) {
            event.target.classList.remove('active');
        }
    }
</script>