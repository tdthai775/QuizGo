package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import model.bean.*;
import model.bo.*;
import java.util.*;

@WebServlet("/submitExam")
public class ExamSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamSubmitServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userID"); 

        String examIdStr = request.getParameter("examId");
        int examId = Integer.parseInt(examIdStr);

        ArrayList<SubmissionAnswer> listAnswerToSave = new ArrayList<>();

       
        ExamBO examBO = new ExamBO();
        ArrayList<Question> questionList = examBO.getQuestionInExam(examId);

        for (Question q : questionList) {
            String paramName = "q_" + q.getId();

            String[] selectedChoiceIds = request.getParameterValues(paramName);

            if (selectedChoiceIds != null && selectedChoiceIds.length > 0) {
                for (String choiceIdStr : selectedChoiceIds) {
                    try {
                        int choiceId = Integer.parseInt(choiceIdStr);                            
                        SubmissionAnswer ans = new SubmissionAnswer(0, 0, q.getId(), choiceId);
                        
                        listAnswerToSave.add(ans);
                        
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                
            }
        }

        SubmissionBO submissionBO = new SubmissionBO();
        boolean isSuccess = submissionBO.submitExam(userId, examId, listAnswerToSave);

        if (isSuccess) {
            request.getRequestDispatcher("/views/user/layout.jsp?page=grading").forward(request, response);
        } 
	}

}
