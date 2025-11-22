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
@WebServlet("/takeExam")
public class ExamTakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public ExamTakeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExamBO examBO = new ExamBO();
		int examId = Integer.parseInt(request.getParameter("examId"));
		ArrayList<Question> questionList = examBO.getQuestionInExam(examId);
		ArrayList<ArrayList<Choice>> choiceList = new ArrayList<>();
		for(int i = 0 ; i< questionList.size() ; i++) {
			choiceList.add(examBO.getChoiceInQuestion(questionList.get(i)));
		}
		request.setAttribute("questionList", questionList);
		request.setAttribute("choiceList", choiceList);
		request.getRequestDispatcher("/views/user/layout.jsp?page=take").forward(request, response);
	}

}
