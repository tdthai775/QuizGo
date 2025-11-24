package model.bean;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private int examId;
    private String content;
    private String type; // 'single', 'multiple'
    
    // List chứa các lựa chọn của câu hỏi này (để hiển thị trên UI)
    private List<Choice> choices = new ArrayList<>();

    public Question() {}

    public Question(int id, int examId, String content, String type) {
        this.id = id;
        this.examId = examId;
        this.content = content;
        this.type = type;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<Choice> getChoices() { return choices; }
    public void setChoices(List<Choice> choices) { this.choices = choices; }
}