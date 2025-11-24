package model.bean;

import java.sql.Timestamp;

public class Exam {
    private int id;
    private String title;
    private String description;
    private int durationMinutes;
    private Timestamp createdAt;
    private int createdBy;
    private int status; // 1: Active, 0: Hidden

    // Field phụ hỗ trợ hiển thị (không có trong DB exams)
    private int questionCount; 

    public Exam() {}

    public Exam(int id, String title, String description, int durationMinutes, Timestamp createdAt, int createdBy, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public int getQuestionCount() { return questionCount; }
    public void setQuestionCount(int questionCount) { this.questionCount = questionCount; }
}