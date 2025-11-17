<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="page-header">
    <h1>Làm bài thi</h1>
    <p>Đề: <strong>JSP/Servlet</strong></p>
</div>

<!-- header nhỏ trong trang -->
<div class="header-content" style="margin-bottom: 20px; padding: 0;">
    <div class="exam-header-info">
        <span>Đề: <strong>JSP/Servlet</strong></span>
        <span>Câu: <strong>3 / 20</strong></span>
        <span class="timer">Thời gian còn: <strong>24:15</strong></span>
    </div>
</div>

<div class="question-card">
    <div class="question-header">
        <div class="question-number">Câu hỏi 3 / 20</div>
        <div class="question-mark">1 điểm</div>
    </div>

    <h2 class="question-text">JSP là viết tắt của từ gì?</h2>

    <div class="options">
        <div class="option">
            <input type="radio" id="option-a" name="answer" value="A">
            <label for="option-a">A. Java Servlet Page</label>
        </div>
        <div class="option">
            <input type="radio" id="option-b" name="answer" value="B">
            <label for="option-b">B. Java Server Page</label>
        </div>
        <div class="option">
            <input type="radio" id="option-c" name="answer" value="C">
            <label for="option-c">C. Java Session Page</label>
        </div>
        <div class="option">
            <input type="radio" id="option-d" name="answer" value="D">
            <label for="option-d">D. Java Servlet Program</label>
        </div>
    </div>
</div>

<div class="navigation">
    <button class="btn btn-secondary">Câu trước</button>
    <button class="btn btn-gradient">Câu tiếp</button>
    <button class="btn btn-primary"
            onclick="return confirm('Bạn có chắc muốn nộp bài?')">
        Nộp bài
    </button>
</div>

<script>
    // Countdown timer demo
    let timeLeft = 24 * 60 + 15; // sec
    function updateTimer() {
        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;
        const el = document.querySelector('.timer strong');
        if (el) {
            el.textContent = minutes + ':' + seconds.toString().padStart(2, '0');
        }
        if (timeLeft <= 0) {
            alert('Hết thời gian làm bài!');
        }
        timeLeft--;
    }
    setInterval(updateTimer, 1000);
</script>
