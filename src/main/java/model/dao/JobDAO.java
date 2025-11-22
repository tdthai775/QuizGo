package model.dao;

import model.bean.*;
import java.sql.*;
import java.util.*;


public class JobDAO {
	public Job getPendingJob() {
        Job job = null;
        String sql = "SELECT * FROM jobs WHERE status = 'PENDING' ORDER BY created_at ASC LIMIT 1";
        
        try (Connection con = Connect2Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                job = new Job();
                job.setId(rs.getInt("id"));
                job.setSubmissionId(rs.getInt("submission_id"));
                job.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    public void updateStatus(int jobId, String newStatus) {
        String sql = "UPDATE jobs SET status = ? WHERE id = ?";
        
        try (Connection con = Connect2Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, newStatus);
            ps.setInt(2, jobId);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertJob(int submissionId) {
        String sql = "INSERT INTO jobs (submission_id, status, created_at) VALUES (?, 'PENDING', NOW())";
        
        try (Connection con = Connect2Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, submissionId);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
