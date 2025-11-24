package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.bean.Job;
import util.DBConnection;

public class JobDAO {

    // Lấy 1 job đang chờ (status = 'queue') để xử lý
    public Job getPendingJob() {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM jobs WHERE status = 'queue' LIMIT 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setSubmissionId(rs.getInt("submission_id"));
                job.setStatus(rs.getString("status"));
                conn.close();
                return job;
            }
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // Cập nhật trạng thái Job (VD: từ queue -> done)
    public void updateJobStatus(int jobId, String status, String message) {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE jobs SET status = ?, result_message = ?, updated_at = NOW() WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, message);
            ps.setInt(3, jobId);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}