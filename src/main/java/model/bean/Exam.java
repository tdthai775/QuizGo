package model.bean;
import java.sql.*;
public class Exam {
	private int id;
    private String title;
    private String description;
    private int durationMinutes;
    private Timestamp createdAt;
    private int createdBy;
    private boolean status; 

    
    public Exam(int id, String title, String description, int durationMinutes, Timestamp createdAt, int createdBy, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.status = status;
    }

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

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
