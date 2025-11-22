<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" import ="java.util.*,model.bean.*"%>

<div class="page-header">
    <h1>Lịch sử bài thi</h1>
    <p>Danh sách các bài thi bạn đã thực hiện.</p>
</div>

<div class="table-container">
    <table class="data-table">
        <thead>
        <tr>
            <th>Đề thi</th>
            <th>Ngày thi</th>
            <th>Điểm</th>
            <th>Trạng thái</th>
            <th>Chi tiết</th>
        </tr>
        </thead>
        <tbody>
        <%
		ArrayList<Submission> submissionList = (ArrayList<Submission>)request.getAttribute("submissionList");
        ArrayList<String> examTitleList = (ArrayList<String>)request.getAttribute("examTitleList");
		if(submissionList!=null){
			for (int i = 0 ; i < submissionList.size() ; i ++){
		%>
        <tr>
            <td><strong><%= examTitleList.get(i) %></strong></td>
            <td> <%= submissionList.get(i).getSubmittedAt() %></td>
            <% if(submissionList.get(i).getStatus().equals("done")){
            %>
            	<td><span class="score"><%= submissionList.get(i).getScore() %></span></td>
            	<td><span class="badge badge-success">Đã chấm</span></td>
            	<td><a href="examResult?submissionId=<%=submissionList.get(i).getId() %>" class="action-link">Xem</a></td>
            <%
            }
            else{
            %>
            	<td><span class="score warning">Đang chấm</span></td>
            	<td><span class="badge badge-warning">Pending</span></td>
            	<td><span class="score warning">Wait</a></td>
            <%
            }
            %>
        </tr>
        <%
			}
		}
		%>
        </tbody>
    </table>
</div>
