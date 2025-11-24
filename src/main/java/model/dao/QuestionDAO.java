package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Choice;
import model.bean.Question;
import model.bean.DTO.QuestionAdminView;
import util.DBConnection;

public class QuestionDAO {

    // Lấy danh sách câu hỏi + đáp án của 1 đề thi
    public ArrayList<Question> getQuestionsByExamId(int examId) {
        ArrayList<Question> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM questions WHERE exam_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setExamId(rs.getInt("exam_id"));
                q.setContent(rs.getString("content"));
                q.setType(rs.getString("type"));
                
                // Lấy luôn danh sách lựa chọn cho câu hỏi này
                q.setChoices(getChoices(q.getId(), conn));
                
                list.add(q);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Hàm phụ trợ lấy Choice (Private dùng nội bộ)
    private List<Choice> getChoices(int questionId, Connection conn) {
        List<Choice> choices = new ArrayList<>();
        String sql = "SELECT * FROM choices WHERE question_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Choice c = new Choice();
                c.setId(rs.getInt("id"));
                c.setQuestionId(rs.getInt("question_id"));
                c.setContent(rs.getString("content"));
                c.setCorrect(rs.getBoolean("is_correct"));
                choices.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return choices;
    }
    
 // Tìm kiếm câu hỏi (Join với Exams để lấy tên đề)
    public List<QuestionAdminView> getQuestionsForAdmin(String keyword, int examId, String type) {
        List<QuestionAdminView> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        StringBuilder sql = new StringBuilder(
            "SELECT q.*, e.title as exam_title FROM questions q " +
            "JOIN exams e ON q.exam_id = e.id WHERE 1=1 "
        );

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND q.content LIKE ?");
        }
        if (examId > 0) {
            sql.append(" AND q.exam_id = ?");
        }
        if (type != null && !type.isEmpty()) {
            sql.append(" AND q.type = ?");
        }
        sql.append(" ORDER BY q.id DESC");

        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int index = 1;
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }
            if (examId > 0) {
                ps.setInt(index++, examId);
            }
            if (type != null && !type.isEmpty()) {
                ps.setString(index++, type);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionAdminView q = new QuestionAdminView();
                q.setId(rs.getInt("id"));
                q.setContent(rs.getString("content"));
                q.setType(rs.getString("type"));
                q.setExamId(rs.getInt("exam_id"));
                q.setExamTitle(rs.getString("exam_title")); // Field từ DTO
                list.add(q);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    // Hàm update câu hỏi (Admin)
    public void updateQuestion(Question q) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE questions SET exam_id=?, content=?, type=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, q.getExamId());
            ps.setString(2, q.getContent());
            ps.setString(3, q.getType());
            ps.setInt(4, q.getId());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    public Question getQuestionById(int id) {
        // Hàm này cần thiết để load dữ liệu vào form Edit
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM questions WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setExamId(rs.getInt("exam_id"));
                q.setContent(rs.getString("content"));
                q.setType(rs.getString("type"));
                conn.close();
                return q;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    // --- Các hàm Admin CRUD (Create, Delete) sẽ thêm ở đây nếu cần ---
    public int createQuestion(Question q) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO questions (exam_id, content, type) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, q.getExamId());
            ps.setString(2, q.getContent());
            ps.setString(3, q.getType());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                int id = rs.getInt(1);
                conn.close();
                return id;
            }
            conn.close();
        } catch(Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    public void deleteQuestion(int id) {
        Connection conn = DBConnection.getConnection();
        try {
            String sql = "DELETE FROM questions WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
        } catch(Exception e) { e.printStackTrace(); }
    }
}