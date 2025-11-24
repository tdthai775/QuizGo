package model.bean.DTO;


import java.util.List;
import model.bean.Choice;
import model.bean.Question;

public class QuestionResultDTO {
    private Question question;      // Chứa nội dung câu hỏi
    private List<Choice> options;   // Danh sách A, B, C, D
    private Integer userChoiceId;   // Đáp án user chọn (có thể null)
    private int correctChoiceId;    // Đáp án đúng thực sự
    private boolean isCorrect;      // User trả lời đúng hay sai?

    public QuestionResultDTO() {}

    // Getters and Setters
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public List<Choice> getOptions() { return options; }
    public void setOptions(List<Choice> options) { this.options = options; }

    public Integer getUserChoiceId() { return userChoiceId; }
    public void setUserChoiceId(Integer userChoiceId) { this.userChoiceId = userChoiceId; }

    public int getCorrectChoiceId() { return correctChoiceId; }
    public void setCorrectChoiceId(int correctChoiceId) { this.correctChoiceId = correctChoiceId; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
}