package controller.async;


import model.bean.Choice;
import model.bean.Job;
import model.bean.Question;
import model.bean.SubmissionAnswer;
import model.dao.JobDAO;
import model.dao.QuestionDAO;
import model.dao.SubmissionDAO;
import java.util.List;

public class GradingWorker extends Thread {
    private JobDAO jobDAO = new JobDAO();
    private SubmissionDAO submissionDAO = new SubmissionDAO();
    private QuestionDAO questionDAO = new QuestionDAO();
    
    private boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            try {
                // 1. Lấy job từ hàng đợi (status = 'queue')
                Job job = jobDAO.getPendingJob();
                
                if (job != null) {
                    System.out.println("[GradingWorker] Đang chấm submission ID: " + job.getSubmissionId());
                    
                    // Giả lập thời gian chấm (để thấy loading trên giao diện)
                    Thread.sleep(1500);
                    
                    // 2. Thực hiện chấm điểm
                    double score = calculateScore(job.getSubmissionId());
                    
                    // 3. Update điểm và trạng thái Submission -> 'done'
                    submissionDAO.updateScore(job.getSubmissionId(), score);
                    
                    // 4. Update trạng thái Job -> 'done'
                    jobDAO.updateJobStatus(job.getId(), "done", "Chấm thành công");
                    
                } else {
                    // Nếu không có việc, nghỉ 1s rồi quét tiếp
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stopWorker() {
        this.isRunning = false;
    }

    // Logic tính điểm
    private double calculateScore(int submissionId) {
        // Lấy bài làm của user
        List<SubmissionAnswer> userAnswers = submissionDAO.getAnswersBySubmissionId(submissionId);
        
        if (userAnswers.isEmpty()) return 0;
        
        // Lấy ID đề thi từ submission
        int examId = submissionDAO.getSubmissionById(submissionId).getExamId();
        
        // Lấy danh sách câu hỏi và đáp án đúng của đề đó
        List<Question> questions = questionDAO.getQuestionsByExamId(examId);
        
        int totalQuestions = questions.size();
        int correctCount = 0;

        for (Question q : questions) {
            // Tìm đáp án đúng của câu này trong DB
            int correctChoiceId = -1;
            for (Choice c : q.getChoices()) {
                if (c.isCorrect()) {
                    correctChoiceId = c.getId();
                    break;
                }
            }

            // Tìm đáp án user chọn cho câu này
            for (SubmissionAnswer ans : userAnswers) {
                if (ans.getQuestionId() == q.getId()) {
                    // Nếu user có chọn (khác null) và chọn đúng
                    if (ans.getChoiceId() != null && ans.getChoiceId() == correctChoiceId) {
                        correctCount++;
                    }
                    break;
                }
            }
        }

        if (totalQuestions == 0) return 0;
        
        // Tính điểm thang 10, làm tròn 2 chữ số
        double score = ((double) correctCount / totalQuestions) * 10.0;
        return Math.round(score * 100.0) / 100.0;
    }
}