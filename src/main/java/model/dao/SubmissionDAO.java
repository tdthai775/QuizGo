package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Submission;
import model.bean.SubmissionAnswer;
import util.DBConnection;

public class SubmissionDAO {

    // Giao dịch: Nộp bài -> Lưu chi tiết -> Tạo Job chấm -> Commit
    public int submitExamTransaction(int userId, int examId, List<SubmissionAnswer> answers) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement psSub = null;
        PreparedStatement psAns = null;
        PreparedStatement psJob = null;

        try {
            conn.setAutoCommit(false); // Bắt đầu Transaction

            // 1. Insert bảng SUBMISSIONS
            String sqlSub = "INSERT INTO submissions (user_id, exam_id, submitted_at, status) VALUES (?, ?, NOW(), 'pending')";
            psSub = conn.prepareStatement(sqlSub, Statement.RETURN_GENERATED_KEYS);
            psSub.setInt(1, userId);
            psSub.setInt(2, examId);
            psSub.executeUpdate();

            // Lấy ID bài nộp vừa tạo
            ResultSet rs = psSub.getGeneratedKeys();
            int submissionId = 0;
            if (rs.next()) {
                submissionId = rs.getInt(1);
            } else {
                throw new SQLException("Không tạo được submission ID");
            }

            // 2. Insert bảng SUBMISSION_ANSWERS (Batch)
            String sqlAns = "INSERT INTO submission_answers (submission_id, question_id, choice_id) VALUES (?, ?, ?)";
            psAns = conn.prepareStatement(sqlAns);
            for (SubmissionAnswer ans : answers) {
                psAns.setInt(1, submissionId);
                psAns.setInt(2, ans.getQuestionId());
                
                // Xử lý nếu user không chọn (null)
                if (ans.getChoiceId() != null) {
                    psAns.setInt(3, ans.getChoiceId());
                } else {
                    psAns.setNull(3, Types.INTEGER);
                }
                psAns.addBatch();
            }
            psAns.executeBatch();

            // 3. Insert bảng JOBS (Để Worker chấm điểm)
            String sqlJob = "INSERT INTO jobs (submission_id, status) VALUES (?, 'queue')";
            psJob = conn.prepareStatement(sqlJob);
            psJob.setInt(1, submissionId);
            psJob.executeUpdate();

            // Nếu tất cả ok -> COMMIT
            conn.commit();
            return submissionId;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); // Có lỗi -> Hoàn tác sạch sẽ
            } catch (SQLException ex) { ex.printStackTrace(); }
            return -1;
        } finally {
            // Đóng resource thủ công để an toàn
            try { if(psSub!=null) psSub.close(); } catch(Exception e){}
            try { if(psAns!=null) psAns.close(); } catch(Exception e){}
            try { if(psJob!=null) psJob.close(); } catch(Exception e){}
            try { if(conn!=null) conn.close(); } catch(Exception e){}
        }
    }
    
    // Lấy lịch sử thi của User
    public ArrayList<Submission> getHistoryByUserId(int userId) {
        ArrayList<Submission> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        // Join để lấy tên đề thi
        String sql = "SELECT s.*, e.title as exam_title " +
                     "FROM submissions s JOIN exams e ON s.exam_id = e.id " +
                     "WHERE s.user_id = ? ORDER BY s.submitted_at DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Submission s = new Submission();
                s.setId(rs.getInt("id"));
                s.setExamId(rs.getInt("exam_id"));
                s.setUserId(rs.getInt("user_id"));
                s.setSubmittedAt(rs.getTimestamp("submitted_at"));
                // Xử lý điểm số (có thể null)
                double score = rs.getDouble("score");
                if (!rs.wasNull()) {
                    s.setScore(score);
                }
                s.setStatus(rs.getString("status"));
                s.setExamTitle(rs.getString("exam_title"));
                list.add(s);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public List<SubmissionAnswer> getAnswersBySubmissionId(int submissionId) {
        List<SubmissionAnswer> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM submission_answers WHERE submission_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, submissionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubmissionAnswer sa = new SubmissionAnswer();
                sa.setId(rs.getInt("id"));
                sa.setSubmissionId(rs.getInt("submission_id"));
                sa.setQuestionId(rs.getInt("question_id"));
                
                int choiceId = rs.getInt("choice_id");
                if (rs.wasNull()) {
                    sa.setChoiceId(null);
                } else {
                    sa.setChoiceId(choiceId);
                }
                list.add(sa);
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Lấy thông tin 1 submission cụ thể (để hiện điểm số, ngày giờ trên đầu trang result)
    public Submission getSubmissionById(int submissionId) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT s.*, e.title as exam_title FROM submissions s " +
                     "JOIN exams e ON s.exam_id = e.id WHERE s.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, submissionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Submission s = new Submission();
                s.setId(rs.getInt("id"));
                s.setExamId(rs.getInt("exam_id"));
                s.setUserId(rs.getInt("user_id"));
                s.setSubmittedAt(rs.getTimestamp("submitted_at"));
                double score = rs.getDouble("score");
                s.setScore(rs.wasNull() ? null : score);
                s.setStatus(rs.getString("status"));
                s.setExamTitle(rs.getString("exam_title"));
                conn.close();
                return s;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    
 // Cập nhật điểm số và trạng thái sau khi chấm xong
    public void updateScore(int submissionId, double score) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE submissions SET score = ?, status = 'done' WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, score);
            ps.setInt(2, submissionId);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}