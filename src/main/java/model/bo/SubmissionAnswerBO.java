package model.bo;

import model.bean.*;
import model.dao.*;
import java.util.*;

public class SubmissionAnswerBO {
	SubmissionAnswerDAO submissionAnswerDAO = new SubmissionAnswerDAO();
	QuestionDAO questionDAO = new QuestionDAO();
	ChoiceDAO choiceDAO = new ChoiceDAO();
	public ArrayList<SubmissionAnswer> getAllSubmissionAnswer(){
		return submissionAnswerDAO.getAllSubmissionAnswer();
	}
	public SubmissionAnswer getSubmissionAnswerById(int id) {
		return submissionAnswerDAO.getSubmissionAnswerByID(id);
	}
	public ArrayList<SubmissionAnswer> getSubmissionAnswerBySubmissionId(int submissionId){
		return submissionAnswerDAO.getSubmissionAnswerBySubmissionId(submissionId);
	}
	public Question getQuestionInSubmissionAnswer(SubmissionAnswer SA){
		return questionDAO.getQuestionInSubmissionAnswer(SA);
	}
	public ArrayList<Choice> getChoiceInQuestion(Question question){
		 return choiceDAO.getChoiceInQuestion(question);
	}
	public ArrayList<Choice> getUserAnswer(SubmissionAnswer SA) {
		return choiceDAO.getUserAnswer(SA);
	}
	public ArrayList<Choice> getCorrectAnswer(Question question){
		return choiceDAO.getCorrectAnswer(question);
	}
}
