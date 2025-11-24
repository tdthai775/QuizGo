<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1>Làm bài thi</h1>
    <p>Đề: <strong>${exam.title}</strong></p>
</div>

<div class="header-content" style="margin-bottom: 20px; padding: 15px; background: white; border-radius: 8px; display:flex; justify-content: space-between;">
    <div class="exam-header-info">
        <span>Môn: <strong>${exam.title}</strong></span>
        <span>Tổng câu: <strong>${questionList.size()}</strong></span>
    </div>
    <div class="timer" style="font-size: 1.2em; color: #d9534f;">
        Thời gian còn: <strong id="timerDisplay">00:00</strong>
    </div>
</div>

<form id="examForm" action="${pageContext.request.contextPath}/user/exam" method="post">
    <input type="hidden" name="action" value="submit" />
    <input type="hidden" name="examId" value="${exam.id}" />

    <c:forEach var="q" items="${questionList}" varStatus="status">
        <div class="question-card" id="q-card-${status.index}" style="margin-bottom: 20px;">
            <div class="question-header">
                <div class="question-number">Câu hỏi ${status.index + 1} / ${questionList.size()}</div>
                </div>

            <h2 class="question-text">${q.content}</h2>

            <div class="options">
                <c:forEach var="c" items="${q.choices}">
                    <div class="option">
                        <input type="radio" id="opt-${c.id}" name="q_${q.id}" value="${c.id}">
                        <label for="opt-${c.id}">${c.content}</label>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>

    <div class="navigation" style="margin-top: 30px; text-align: center;">
        <button type="submit" class="btn btn-primary" style="padding: 10px 40px;"
                onclick="return confirm('Bạn có chắc muốn nộp bài?')">
            Nộp bài
        </button>
    </div>
</form>

<script>
    let durationMinutes = ${exam.durationMinutes}; 
    let timeLeft = durationMinutes * 60; 

    function updateTimer() {
        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;
        
        const display = document.getElementById('timerDisplay');
        if (display) {
            display.textContent = minutes.toString().padStart(2, '0') + ':' + seconds.toString().padStart(2, '0');
        }

        if (timeLeft <= 0) {
            clearInterval(timerInterval);
            alert('Hết thời gian làm bài! Hệ thống sẽ tự động nộp.');
            document.getElementById('examForm').submit();
        }
        timeLeft--;
    }
    
    // Gọi updateTimer ngay lập tức và sau mỗi 1s
    updateTimer();
    const timerInterval = setInterval(updateTimer, 1000);
</script>