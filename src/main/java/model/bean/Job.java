package model.bean;

import java.sql.Timestamp;

public class Job {
    private int id;
    private int submissionId;
    private String status; 
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String resultMessage;

    public Job() {}

    public Job(int id, int submissionId, String status, Timestamp createdAt, Timestamp updatedAt, String resultMessage) {
        this.id = id;
        this.submissionId = submissionId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resultMessage = resultMessage;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSubmissionId() { return submissionId; }
    public void setSubmissionId(int submissionId) { this.submissionId = submissionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public String getResultMessage() { return resultMessage; }
    public void setResultMessage(String resultMessage) { this.resultMessage = resultMessage; }
}