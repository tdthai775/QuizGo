package model.bean.DTO;

import model.bean.Question;

//Kế thừa từ Question để có đủ các thuộc tính (id, content, type...)
public class QuestionAdminView extends Question {
 private String examTitle;

 public QuestionAdminView() {
     super();
 }

 public String getExamTitle() { return examTitle; }
 public void setExamTitle(String examTitle) { this.examTitle = examTitle; }
}