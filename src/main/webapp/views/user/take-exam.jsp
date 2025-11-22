<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.bean.Question" %>
<%@ page import="model.bean.Choice" %>

<%
    ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");
    ArrayList<ArrayList<Choice>> choiceList = (ArrayList<ArrayList<Choice>>) request.getAttribute("choiceList");
    
    String examIdStr = request.getParameter("examId");
    
    if (questionList == null) questionList = new ArrayList<>();
    if (choiceList == null) choiceList = new ArrayList<>();
%>

<form action="<%= request.getContextPath() %>/submitExam" method="POST" id="examForm">
    
    <input type="hidden" name="examId" value="<%= (examIdStr != null) ? examIdStr : "" %>">

    <div class="page-header">
        <h1>Làm bài thi</h1>
        <p>Mã đề: <strong><%= (examIdStr != null) ? examIdStr : "N/A" %></strong></p>
    </div>

    <div class="header-content" style="margin-bottom: 20px; padding: 0;">
        <div class="exam-header-info">
            <span>Tổng số câu: <strong><%= questionList.size() %></strong></span>
            <span class="timer">Thời gian còn: <strong>Loading...</strong></span>
        </div>
    </div>

    <% 
        for (int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            
            String inputType = "radio";
            String typeText = "(Chọn 1 đáp án)";
            
            if (q.getType() != null && q.getType().equalsIgnoreCase("multi")) {
                inputType = "checkbox";
                typeText = "(Chọn nhiều đáp án)";
            }
    %>
        
        <div class="question-card" id="question-<%= q.getId() %>">
            <div class="question-header">
                <div class="question-number">Câu hỏi <%= i + 1 %> / <%= questionList.size() %></div>
                <div class="question-mark">ID: <%= q.getId() %></div>
            </div>

            <h2 class="question-text">
                <%= q.getContent() %> 
                <span style="font-size: 14px; color: #666; font-weight: normal;">
                    <%= typeText %>
                </span>
            </h2>

            <div class="options">
                <%                  
                    ArrayList<Choice> currentChoices = new ArrayList<>();
                    if (i < choiceList.size()) {
                        currentChoices = choiceList.get(i);
                    }

                    for (int j = 0; j < currentChoices.size(); j++) {
                        Choice c = currentChoices.get(j);
                %>
                    
                    <div class="option">
                        <input type="<%= inputType %>" 
                               id="choice-<%= c.getId() %>" 
                               name="q_<%= q.getId() %>" 
                               value="<%= c.getId() %>">
                               
                        <label for="choice-<%= c.getId() %>">
                            <%= c.getContent() %>
                        </label>
                    </div>
                    
                <% 
                    } 
                %>
            </div>
        </div>
        
    <% 
        } 
    %>

    <div class="navigation">
        <button type="submit" class="btn btn-primary"
                onclick="return confirm('Bạn có chắc muốn nộp bài?')">
            Nộp bài
        </button>
    </div>
</form>

<script>
    // Script đếm ngược
    let timeLeft = 45 * 60; 
    function updateTimer() {
        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;
        const el = document.querySelector('.timer strong');
        if (el) {
            el.textContent = minutes + ':' + seconds.toString().padStart(2, '0');
        }
        if (timeLeft <= 0) {
            alert('Hết thời gian làm bài!');
            document.getElementById("examForm").submit();
        } else {
            timeLeft--;
        }
    }
    setInterval(updateTimer, 1000);
    updateTimer(); 
</script>