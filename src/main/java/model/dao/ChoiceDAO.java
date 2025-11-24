package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.bean.Choice;
import util.DBConnection;

public class ChoiceDAO {

    // Xóa đáp án cũ (Khi sửa câu hỏi)
    public void deleteChoicesByQuestionId(int questionId) {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM choices WHERE question_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Lưu danh sách đáp án mới
    public void saveChoices(int questionId, List<Choice> choices) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO choices (question_id, content, is_correct) VALUES (?, ?, ?)";
        try {
            conn.setAutoCommit(false); // Batch insert cho nhanh
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Choice c : choices) {
                ps.setInt(1, questionId);
                ps.setString(2, c.getContent());
                ps.setBoolean(3, c.isCorrect());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}