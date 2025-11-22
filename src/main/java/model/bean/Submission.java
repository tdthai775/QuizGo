package model.bean;
import java.sql.*;

public class Submission {
    private int id;
    private int userId;
    private int examId;
    private Timestamp submittedAt;
    private double score; 
    private String status;

    public Submission() {}

    public Submission(int id, int userId, int examId, Timestamp submittedAt, double score, String status) {
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

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
