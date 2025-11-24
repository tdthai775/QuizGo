package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.bean.DTO.ResultDTO;
import util.DBConnection;

public class ResultDAO {

    public List<ResultDTO> searchResults(String keyword, String examIdStr, String date) {
        List<ResultDTO> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        StringBuilder sql = new StringBuilder(
            "SELECT s.id, u.fullname, e.title, s.score, s.submitted_at, s.status " +
            "FROM submissions s " +
            "JOIN users u ON s.user_id = u.id " +
            "JOIN exams e ON s.exam_id = e.id " +
            "WHERE 1=1 "
        );

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND u.fullname LIKE ? ");
        }
        if (examIdStr != null && !examIdStr.isEmpty()) {
            sql.append(" AND s.exam_id = ? ");
        }
        // date filter logic nếu cần...
        sql.append(" ORDER BY s.submitted_at DESC");

        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int index = 1;
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }
            if (examIdStr != null && !examIdStr.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(examIdStr));
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResultDTO dto = new ResultDTO();
                dto.setSubmissionId(rs.getInt("id"));
                dto.setStudentName(rs.getString("fullname"));
                dto.setExamTitle(rs.getString("title"));
                
                double score = rs.getDouble("score");
                dto.setScore(rs.wasNull() ? null : score);
                
                dto.setSubmittedAt(rs.getTimestamp("submitted_at"));
                dto.setStatus(rs.getString("status"));
                list.add(dto);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public ResultDTO getResultById(int submissionId) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT s.id, u.fullname, e.title, s.score, s.submitted_at, s.status " +
                     "FROM submissions s " +
                     "JOIN users u ON s.user_id = u.id " +
                     "JOIN exams e ON s.exam_id = e.id " +
                     "WHERE s.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, submissionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ResultDTO dto = mapRowToDTO(rs);
                conn.close();
                return dto;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // Hàm phụ để map dữ liệu cho gọn
    private ResultDTO mapRowToDTO(ResultSet rs) throws SQLException {
        ResultDTO dto = new ResultDTO();
        dto.setSubmissionId(rs.getInt("id"));
        dto.setStudentName(rs.getString("fullname"));
        dto.setExamTitle(rs.getString("title"));
        
        double score = rs.getDouble("score");
        dto.setScore(rs.wasNull() ? null : score);
        
        dto.setSubmittedAt(rs.getTimestamp("submitted_at"));
        dto.setStatus(rs.getString("status"));
        return dto;
    }
}