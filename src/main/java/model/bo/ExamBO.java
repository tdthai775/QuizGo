package model.bo;
import model.bean.*;
import model.dao.*;
import java.util.*;

public class ExamBO {
	public ExamBO() {
		
	}
	private ExamDAO examDAO = new ExamDAO(); 
	private QuestionDAO questionDAO = new QuestionDAO();
	private ChoiceDAO choiceDAO = new ChoiceDAO();
	public ArrayList<Exam> getAllExam(){
		return examDAO.getAllExam();
	}
	public int getQuestionQuantiy(int examID){
		return examDAO.getQuestionQuantity(examID);
	}
	public ArrayList<Question> getQuestionInExam(int examId){
		return questionDAO.getQuestionInExam(examId);
	}
	public ArrayList<Choice> getChoiceInQuestion(Question question){
		return choiceDAO.getChoiceInQuestion(question);
	}
}
