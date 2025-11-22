package model.bean;

public class Question {
    private int id;
    private int examId;
    private String content;
    private String type;

    public Question() {}

    public Question(int id, int examId, String content, String type) {
        this.id = id;
        this.examId = examId;
        this.content = content;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}