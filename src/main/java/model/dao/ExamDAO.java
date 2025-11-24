package model.dao;

import java.sql.*;
import java.util.ArrayList;
import model.bean.Exam;
import util.DBConnection;

public class ExamDAO {

	// Lấy danh sách TẤT CẢ đề thi (cho Admin quản lý)
	public ArrayList<Exam> getAllExams() {
        ArrayList<Exam> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT e.*, COUNT(q.id) as q_count " +
                     "FROM exams e LEFT JOIN questions q ON e.id = q.exam_id " +
                     "GROUP BY e.id ORDER BY e.id DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToExam(rs));
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public ArrayList<Exam> getAvailableExams() {
        ArrayList<Exam> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        // Thêm điều kiện WHERE e.status = 1
        String sql = "SELECT e.*, COUNT(q.id) as q_count " +
                     "FROM exams e LEFT JOIN questions q ON e.id = q.exam_id " +
                     "WHERE e.status = 1 " + 
                     "GROUP BY e.id ORDER BY e.id DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToExam(rs));
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    // Lấy thông tin 1 đề thi
    public Exam getExamById(int id) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM exams WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setTitle(rs.getString("title"));
                exam.setDescription(rs.getString("description"));
                exam.setDurationMinutes(rs.getInt("duration_minutes"));
                exam.setStatus(rs.getInt("status"));
                conn.close();
                return exam;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
    public boolean createExam(Exam exam) {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO exams (title, description, duration_minutes, created_by, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, exam.getTitle());
            ps.setString(2, exam.getDescription());
            ps.setInt(3, exam.getDurationMinutes());
            ps.setInt(4, exam.getCreatedBy());
            ps.setInt(5, exam.getStatus());
            int res = ps.executeUpdate();
            conn.close();
            return res > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean updateExam(Exam exam) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE exams SET title=?, description=?, duration_minutes=?, status=? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, exam.getTitle());
            ps.setString(2, exam.getDescription());
            ps.setInt(3, exam.getDurationMinutes());
            ps.setInt(4, exam.getStatus());
            ps.setInt(5, exam.getId());
            int res = ps.executeUpdate();
            conn.close();
            return res > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean deleteExam(int id) {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM exams WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int res = ps.executeUpdate();
            conn.close();
            return res > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
 // Hàm phụ để map dữ liệu cho gọn code
    private Exam mapRowToExam(ResultSet rs) throws SQLException {
        Exam exam = new Exam();
        exam.setId(rs.getInt("id"));
        exam.setTitle(rs.getString("title"));
        exam.setDescription(rs.getString("description"));
        exam.setDurationMinutes(rs.getInt("duration_minutes"));
        exam.setQuestionCount(rs.getInt("q_count"));
        exam.setStatus(rs.getInt("status"));
        return exam;
    }
}