package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Choice;
import model.bean.Question;
import model.bean.Submission;
import model.bean.SubmissionAnswer;
import model.bean.DTO.QuestionResultDTO;
import model.dao.QuestionDAO;
import model.dao.SubmissionDAO;

public class SubmissionBO {
	private SubmissionDAO submissionDAO = new SubmissionDAO();
    private QuestionDAO questionDAO = new QuestionDAO();
    
    public int submitExam(int userId, int examId, List<SubmissionAnswer> answers) {
        return submissionDAO.submitExamTransaction(userId, examId, answers);
    }

    public List<Submission> getUserHistory(int userId) {
        return submissionDAO.getHistoryByUserId(userId);
    }

    // : Lấy chi tiết kết quả hiển thị (Logic phức tạp nhất) ---
    public List<QuestionResultDTO> getResultDetail(int submissionId) {
        List<QuestionResultDTO> resultList = new ArrayList<>();
        
        // 1. Lấy bài làm
        Submission sub = submissionDAO.getSubmissionById(submissionId);
        if (sub == null) return resultList;

        // 2. Lấy danh sách câu hỏi gốc của đề thi
        List<Question> questions = questionDAO.getQuestionsByExamId(sub.getExamId());
        
        // 3. Lấy danh sách đáp án user đã chọn
        List<SubmissionAnswer> userAnswers = submissionDAO.getAnswersBySubmissionId(submissionId);

        // 4. Ghép lại thành DTO
        for (Question q : questions) {
            QuestionResultDTO dto = new QuestionResultDTO();
            dto.setQuestion(q);
            dto.setOptions(q.getChoices()); // Đã có sẵn list choices do DAO Question lấy rồi
            
            // Tìm đáp án đúng trong list choices
            for (Choice c : q.getChoices()) {
                if (c.isCorrect()) {
                    dto.setCorrectChoiceId(c.getId());
                    break;
                }
            }

            // Tìm đáp án user chọn cho câu này
            Integer userChoiceId = null;
            for (SubmissionAnswer ans : userAnswers) {
                if (ans.getQuestionId() == q.getId()) {
                    userChoiceId = ans.getChoiceId();
                    break;
                }
            }
            dto.setUserChoiceId(userChoiceId);

            // Check đúng sai
            boolean isCorrect = (userChoiceId != null && userChoiceId == dto.getCorrectChoiceId());
            dto.setCorrect(isCorrect);

            resultList.add(dto);
        }
        return resultList;
    }
    
    // Hàm phụ lấy thông tin submission (header trang result)
    public Submission getSubmission(int submissionId) {
        return submissionDAO.getSubmissionById(submissionId);
    }
}