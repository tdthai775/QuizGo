package model.bo;

import java.util.List;
import model.bean.Choice;
import model.dao.ChoiceDAO;

public class ChoiceBO {
    private ChoiceDAO choiceDAO = new ChoiceDAO();

    // Xóa tất cả đáp án cũ của 1 câu hỏi
    public void deleteChoicesByQuestionId(int questionId) {
        choiceDAO.deleteChoicesByQuestionId(questionId);
    }

    // Lưu danh sách đáp án mới
    public void saveChoicesForQuestion(int questionId, List<Choice> choices) {
        if (choices != null && !choices.isEmpty()) {
            choiceDAO.saveChoices(questionId, choices);
        }
    }
}