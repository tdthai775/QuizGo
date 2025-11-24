package model.bo;

import java.util.ArrayList;
import java.util.List;
import model.bean.Question;
import model.bean.DTO.QuestionAdminView;
import model.dao.QuestionDAO;// Nếu bạn chưa có class này, dùng Question tạm cũng được

public class QuestionBO {
	private QuestionDAO questionDAO = new QuestionDAO();

    public ArrayList<Question> getQuestions(int examId) {
        return questionDAO.getQuestionsByExamId(examId);
    }

    public int createQuestion(Question q) {
        return questionDAO.createQuestion(q);
    }

    public void deleteQuestion(int id) {
        questionDAO.deleteQuestion(id);
    }
    
    // SỬA LẠI HÀM NÀY: Gọi DAO thực sự
    public List<QuestionAdminView> getAdminQuestionsWithDetails(String keyword, int examId, String type) {
        return questionDAO.getQuestionsForAdmin(keyword, examId, type);
    }
    
    public void updateQuestion(Question q) {
        questionDAO.updateQuestion(q);
    }
    
    public Question getQuestionById(int id) {
        return questionDAO.getQuestionById(id);
    }
}