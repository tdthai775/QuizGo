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
@WebServlet("/examResult")
public class ExamResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamResultServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SubmissionAnswerBO submissionAnswerBO = new SubmissionAnswerBO();
		int submissionId = Integer.parseInt(request.getParameter("submissionId"));
		ArrayList<SubmissionAnswer> submissionAnswerList = submissionAnswerBO.getSubmissionAnswerBySubmissionId(submissionId);
		ArrayList<Question> questionList = new ArrayList<Question>();
		ArrayList<ArrayList<Choice>> correctAnswerList = new ArrayList<ArrayList<Choice>>();
		ArrayList<ArrayList<Choice>> userAnswerList = new ArrayList<ArrayList<Choice>>();
		for(int i = 0 ; i < submissionAnswerList.size() ; i++) {
			questionList.add(submissionAnswerBO.getQuestionInSubmissionAnswer(submissionAnswerList.get(i)));
		}
		for(int i = 0 ; i < questionList.size() ; i++) {
			correctAnswerList.add(submissionAnswerBO.getCorrectAnswer(questionList.get(i)));
		}
		for(int i = 0 ; i < submissionAnswerList.size() ; i++) {
			userAnswerList.add(submissionAnswerBO.getUserAnswer(submissionAnswerList.get(i)));
		}
		request.setAttribute("questionList", questionList);
		request.setAttribute("correctAnswerList", correctAnswerList);
		request.setAttribute("userAnswerList", userAnswerList);
		request.getRequestDispatcher("/views/user/layout.jsp?page=result").forward(request, response);
	}

}
