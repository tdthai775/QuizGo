<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" import ="java.util.*,model.bean.*"%>


<%
	ArrayList<Question> questionList = (ArrayList<Question>)request.getAttribute("questionList");
	ArrayList<ArrayList<Choice>> correctAnswerList = (ArrayList<ArrayList<Choice>>)request.getAttribute("correctAnswerList");
	ArrayList<ArrayList<Choice>> userAnswerList = (ArrayList<ArrayList<Choice>>)request.getAttribute("userAnswerList");
%>

<div class="result-container">
    <div class="logo-header">
        <a href="layout.jsp?page=exam-list">Về danh sách đề thi</a>
    </div>
	<%for(int i = 0 ; i < questionList.size() ; i++){
		
	%>
    	<div class="result-card">
        	<h1 class="result-title">Câu hỏi: <%= questionList.get(i).getContent() %></h1>
        	<p class="result-subtitle">
            	<strong>Đáp án của bạn:</strong>
            	<%for(int j = 0 ; j < userAnswerList.get(i).size() ; j++){%>
            		<p> <%= userAnswerList.get(i).get(j).getContent() %> </p>
            	<%} %>
            	<strong>Đáp án:</strong> <br>
            	<%for(int j = 0 ; j < correctAnswerList.get(i).size() ; j++){%>
            		<p> <%= correctAnswerList.get(i).get(j).getContent() %> </p>
            	<%} %>
        	</p>
			<br>
    	</div>
    <%
	}
    %>
</div>
