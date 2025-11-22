package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.*;
import model.bean.*;
import model.bo.*;
import java.util.*;
@WebServlet("/examHistory")
public class ExamHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamHistoryServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (Integer)session.getAttribute("userID");
		SubmissionBO submissionBO = new SubmissionBO();
		ArrayList<Submission> submissionList = submissionBO.getSubmission(userID);
		ArrayList<String> examTitleList = new ArrayList<String>();
		for(int i = 0 ; i < submissionList.size() ; i++) {
			examTitleList.add(submissionBO.getExamTtileById(submissionList.get(i).getExamId()));
		}
		request.setAttribute("submissionList", submissionList);
		request.setAttribute("examTitleList", examTitleList);
		request.getRequestDispatcher("/views/user/layout.jsp?page=history").forward(request, response);
	}

}
