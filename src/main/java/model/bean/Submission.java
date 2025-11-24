package model.bean;

import java.sql.Timestamp;

public class Submission {
    private int id;
    private int userId;
    private int examId;
    private Timestamp submittedAt;
    private Double score;   // Dùng Double (có thể null)
    private String status;  // 'pending', 'done'

    // Field phụ hiển thị tên đề thi ở trang Lịch sử
    private String examTitle; 

    public Submission() {}

    public Submission(int id, int userId, int examId, Timestamp submittedAt, Double score, String status) {
        this.id = id;
        this.userId = userId;
        this.examId = examId;
        this.submittedAt = submittedAt;
        this.score = score;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }

    public Timestamp getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Timestamp submittedAt) { this.submittedAt = submittedAt; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String examTitle) { this.examTitle = examTitle; }
}