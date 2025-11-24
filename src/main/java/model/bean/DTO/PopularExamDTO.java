package model.bean.DTO;

public class PopularExamDTO {
    private String examTitle;
    private int attemptCount; // Số lượt thi

    public PopularExamDTO(String examTitle, int attemptCount) {
        this.examTitle = examTitle;
        this.attemptCount = attemptCount;
    }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String examTitle) { this.examTitle = examTitle; }

    public int getAttemptCount() { return attemptCount; }
    public void setAttemptCount(int attemptCount) { this.attemptCount = attemptCount; }
}