<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="center-page">
    <div class="grading-container">
        <div class="grading-card" style="text-align: center; padding: 50px;">
            <h1 class="grading-title">Đang chấm điểm</h1>
            <p class="grading-subtitle">Hệ thống đang xử lý bài làm của bạn...</p>

            <div class="spinner-container" style="margin: 30px 0;">
                <div class="spinner" style="border: 4px solid #f3f3f3; border-top: 4px solid #3498db; border-radius: 50%; width: 40px; height: 40px; animation: spin 2s linear infinite; margin: 0 auto;"></div>
            </div>
            <style>
                @keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
            </style>

            <p class="grading-message">Vui lòng không tắt trình duyệt.</p>
            
            <a href="${pageContext.request.contextPath}/user/exam?action=history" class="btn btn-secondary" style="margin-top: 20px;">
                Về lịch sử thi
            </a>
        </div>
    </div>
</div>

<script>
	const urlParams = new URLSearchParams(window.location.search);
	const subId = urlParams.get('id');
	
	// Hàm kiểm tra
	if (subId) {
	    const interval = setInterval(function() {
	        // Gọi Server check status
	        fetch('${pageContext.request.contextPath}/user/exam?action=check-status&id=' + subId)
	            .then(response => response.text())
	            .then(status => {
	                console.log("Status: " + status); // Debug xem server trả về gì
	                
	                if (status.trim() === 'done') {
	                    clearInterval(interval); // Dừng loop
	                    // Chuyển sang trang kết quả
	                    window.location.href = '${pageContext.request.contextPath}/user/exam?action=result&id=' + subId;
	                }
	            })
	            .catch(err => console.error(err));
	    }, 2000); 
	} else {
	    alert("Lỗi: Không tìm thấy bài thi để chấm!");
	    window.location.href = "${pageContext.request.contextPath}/user/exam?action=history";
	}
</script>