package model.bean;

public class SubmissionAnswer {
    private int id;
    private int submissionId;
    private int questionId;
    private Integer choiceId; 

    public SubmissionAnswer() {}

    public SubmissionAnswer(int id, int submissionId, int questionId, Integer choiceId) {
        this.id = id;
        this.submissionId = submissionId;
        this.questionId = questionId;
        this.choiceId = choiceId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSubmissionId() { return submissionId; }
    public void setSubmissionId(int submissionId) { this.submissionId = submissionId; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public Integer getChoiceId() { return choiceId; }
    public void setChoiceId(Integer choiceId) { this.choiceId = choiceId; }
}