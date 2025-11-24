package model.bo;

import java.util.ArrayList;
import model.bean.Exam;
import model.bean.Question;
import model.dao.ExamDAO;
import model.dao.QuestionDAO;

public class ExamBO {
    private ExamDAO examDAO = new ExamDAO();
    private QuestionDAO questionDAO = new QuestionDAO();

 // [CHO USER] Hàm này ExamServlet đang gọi -> Sửa để gọi getAvailableExams
    public ArrayList<Exam> getExamList() {
        return examDAO.getAvailableExams(); 
    }

    // [CHO ADMIN] Hàm này ExamManager đang gọi -> Giữ nguyên getAllExams
    public ArrayList<Exam> getAllExams() {
        return examDAO.getAllExams();
    }

    // Lấy thông tin chi tiết 1 đề thi
    public Exam getExamForTaking(int examId) {
        return examDAO.getExamById(examId);
    }
    
    // Lấy danh sách câu hỏi của đề thi đó
    public ArrayList<Question> getQuestions(int examId) {
        return questionDAO.getQuestionsByExamId(examId);
    }
    
    
    public boolean createExam(Exam exam) {
        return examDAO.createExam(exam);
    }

    public boolean updateExam(Exam exam) {
        return examDAO.updateExam(exam);
    }

    public boolean deleteExam(int id) {
        return examDAO.deleteExam(id);
    }
    
    public Exam getExamById(int id) {
        return examDAO.getExamById(id);
    }
}