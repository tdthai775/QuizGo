package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.bean.*;
import model.bo.*;
import java.util.*;
@WebServlet("/examList")
public class ExamListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamListServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExamBO examBO = new ExamBO();
		ArrayList<Exam> examList = examBO.getAllExam();
		ArrayList<Integer> questionQuantity = new ArrayList<Integer>();
		for(Exam exam : examList) {
			questionQuantity.add(examBO.getQuestionQuantiy(exam.getId()));
		}
		request.setAttribute("examList", examList);
		request.setAttribute("questionQuantity", questionQuantity);
		request.getRequestDispatcher("/views/user/layout.jsp?page=exam-list").forward(request, response);
	}
}
