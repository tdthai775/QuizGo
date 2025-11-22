<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" import ="java.util.*,model.bean.*"%>


<div class="page-header">
    <h1>Danh sách đề thi</h1>
    <p>Chọn một đề thi để bắt đầu làm bài.</p>
</div>

<div class="search-box">
    <input type="text" placeholder="Tìm kiếm đề thi...">
</div>

<div class="exam-grid">
	<%
		ArrayList<Exam> examList = (ArrayList<Exam>)request.getAttribute("examList");
	    ArrayList<Integer> questionQuantity = (ArrayList<Integer>)request.getAttribute("questionQuantity");	
		if(examList!=null){
			for (int i = 0 ; i < examList.size() ; i ++){
	%>
    <div class="exam-card">
            
            <h2 class="exam-title"><%= examList.get(i).getTitle() %></h2> 
            <p class="exam-description"><%= examList.get(i).getDescription() %></p>
            
            <div class="exam-info">
                <div>Số câu: <strong><%= questionQuantity.get(i) %></strong></div>
                <div>Thời gian: <strong><%= examList.get(i).getDurationMinutes() %> phút</strong></div>
            </div>
            
            <a href="takeExam?examId=<%= examList.get(i).getId() %>" 
               class="btn btn-gradient">
                Bắt đầu thi
            </a>
        </div>
	<%
			}
		}
	%>
</div>
