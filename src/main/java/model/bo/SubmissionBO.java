package model.bo;

import java.sql.*;
import java.util.*;
import model.dao.*;
import model.bean.*;

public class SubmissionBO {
	SubmissionDAO submissionDAO = new SubmissionDAO();
	SubmissionAnswerDAO answerDAO = new SubmissionAnswerDAO();
	JobDAO jobDAO = new JobDAO();
	ChoiceDAO choiceDAO = new ChoiceDAO();
	public ArrayList<Submission> getSubmission(int userID) {
		return submissionDAO.getSubmission(userID);
	}
	public String getExamTtileById(int examID) {
		return submissionDAO.getExamTitleByID(examID);
	}
	public boolean submitExam(int userId, int examId, ArrayList<SubmissionAnswer> answerList) {
        try {
            int submissionId = submissionDAO.insertSubmission(userId, examId);
            
            if (submissionId != -1) {
                for (SubmissionAnswer ans : answerList) {
                    ans.setSubmissionId(submissionId);
                }
                
                answerDAO.insertList(answerList);
                
                jobDAO.insertJob(submissionId);
                
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }
	public void gradeSubmission(int submissionId) {
	    try {
	        ArrayList<SubmissionAnswer> userAnswers = answerDAO.getSubmissionAnswerBySubmissionId(submissionId);

	        // GOM NHÓM: Gom các đáp án lại theo từng QuestionID
	        // Key: ID câu hỏi, Value: Danh sách ID các đáp án user chọn cho câu đó
	        Map<Integer, List<Integer>> mapStudentAnswers = new HashMap<>();

	        for (SubmissionAnswer sa : userAnswers) {
	            int qId = sa.getQuestionId();
	            
	            // Nếu chưa có trong map thì tạo list mới
	            if (!mapStudentAnswers.containsKey(qId)) {
	                mapStudentAnswers.put(qId, new ArrayList<>());
	            }
	            
	            // Thêm đáp án user chọn vào list (nếu có chọn)
	            if (sa.getChoiceId() != null && sa.getChoiceId() > 0) {
	                mapStudentAnswers.get(qId).add(sa.getChoiceId());
	            }
	        }

	        //BẮT ĐẦU CHẤM ĐIỂM TỪNG CÂU
	        int correctCount = 0;
	        int totalQuestionsProcessed = mapStudentAnswers.size(); 
	        // Lưu ý: totalQuestionsProcessed này chỉ tính những câu user ĐÃ NỘP. 
	        // Để chính xác tuyệt đối (tính cả câu user bỏ qua không nộp), 
	        // bạn nên query lấy tổng số câu hỏi từ bảng 'questions' theo exam_id.

	        for (Integer qId : mapStudentAnswers.keySet()) {
	            // A. Lấy danh sách ID user chọn
	            List<Integer> userChoiceIds = mapStudentAnswers.get(qId);
	            
	            // B. Lấy danh sách ID đáp án ĐÚNG từ Database
	            // (Bạn cần đảm bảo ChoiceDAO có hàm trả về list các đáp án đúng theo questionId)
	            ArrayList<Choice> correctChoices = choiceDAO.getCorrectAnswerByQuestionId(qId);
	            
	            // Chuyển sang List<Integer> để dễ so sánh
	            List<Integer> correctChoiceIds = new ArrayList<>();
	            for (Choice c : correctChoices) {
	                correctChoiceIds.add(c.getId());
	            }

	            // C. SO SÁNH: 
	            // 1. Số lượng phải bằng nhau (User chọn 2, Đúng có 2)
	            // 2. User phải chọn chứa tất cả đáp án đúng (Không được chọn thừa cái sai)
	            if (userChoiceIds.size() == correctChoiceIds.size() && 
	                userChoiceIds.containsAll(correctChoiceIds)) {
	                
	                correctCount++; // Đúng hoàn toàn mới được cộng điểm
	            }
	        }

	        // TÍNH ĐIỂM (Thang 10)
	        double finalScore = 0.0;
	        if (totalQuestionsProcessed > 0) {
	            finalScore = ((double) correctCount / totalQuestionsProcessed) * 10.0;
	            finalScore = Math.round(finalScore * 100.0) / 100.0;
	        }

	        System.out.println("Kết quả chấm ID " + submissionId + ": Đúng " + correctCount + "/" + totalQuestionsProcessed + " -> Điểm: " + finalScore);

	        //Update DB
	        submissionDAO.updateScoreAndStatus(submissionId, finalScore, "done");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public Job getPendingJob() {
		return jobDAO.getPendingJob();
	}
	public void updateJobStatus(int jobId, String newStatus) {
		jobDAO.updateStatus(jobId, newStatus);
	}
}
