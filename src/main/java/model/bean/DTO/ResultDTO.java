package model.bean.DTO;

import java.sql.Timestamp;

public class ResultDTO {
    private int submissionId;
    private String studentName; // Lấy từ users.fullname
    private String examTitle;   // Lấy từ exams.title
    private Double score;
    private Timestamp submittedAt;
    private String status;

    public ResultDTO() {}

    public ResultDTO(int submissionId, String studentName, String examTitle, Double score, Timestamp submittedAt, String status) {
        this.submissionId = submissionId;
        this.studentName = studentName;
        this.examTitle = examTitle;
        this.score = score;
        this.submittedAt = submittedAt;
        this.status = status;
    }

    // Getters and Setters
    public int getSubmissionId() { return submissionId; }
    public void setSubmissionId(int submissionId) { this.submissionId = submissionId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String examTitle) { this.examTitle = examTitle; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Timestamp getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Timestamp submittedAt) { this.submittedAt = submittedAt; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}