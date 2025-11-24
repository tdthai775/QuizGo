package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.bean.DTO.DashboardStats;
import model.bean.DTO.PopularExamDTO;
import model.bean.DTO.RecentActivityDTO;
import util.DBConnection;

public class DashboardDAO {

    // 1. Lấy số liệu thống kê tổng
    public DashboardStats getStats() {
        DashboardStats stats = new DashboardStats();
        Connection conn = DBConnection.getConnection();
        try {
            // Đếm user
            PreparedStatement ps1 = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE role != 'admin'");
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) stats.setTotalUsers(rs1.getInt(1));

            // Đếm đề thi
            PreparedStatement ps2 = conn.prepareStatement("SELECT COUNT(*) FROM exams");
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) stats.setTotalExams(rs2.getInt(1));

            // Đếm lượt nộp bài
            PreparedStatement ps3 = conn.prepareStatement("SELECT COUNT(*) FROM submissions");
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) stats.setTotalSubmissions(rs3.getInt(1));

            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return stats;
    }

    // 2. Lấy hoạt động gần đây (Ví dụ: 5 bài nộp gần nhất)
    public List<RecentActivityDTO> getRecentActivities() {
        List<RecentActivityDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT s.submitted_at, u.username, e.title " +
                     "FROM submissions s " +
                     "JOIN users u ON s.user_id = u.id " +
                     "JOIN exams e ON s.exam_id = e.id " +
                     "ORDER BY s.submitted_at DESC LIMIT 5";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Giả lập dữ liệu hiển thị
                String content = "User " + rs.getString("username") + " nộp bài " + rs.getString("title");
                // Tính thời gian tương đối (đơn giản hóa bằng String)
                String timeAgo = rs.getTimestamp("submitted_at").toString(); 
                
                list.add(new RecentActivityDTO(content, timeAgo, "submission"));
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // 3. Lấy top đề thi phổ biến
    public List<PopularExamDTO> getPopularExams() {
        List<PopularExamDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT e.title, COUNT(s.id) as count " +
                     "FROM submissions s JOIN exams e ON s.exam_id = e.id " +
                     "GROUP BY e.id ORDER BY count DESC LIMIT 5";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PopularExamDTO(rs.getString("title"), rs.getInt("count")));
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}