package model.dao;

import model.bean.*;
import java.sql.*;
import java.util.*;

public class SubmissionDAO {
	public ArrayList<Submission> getSubmission(int userID) {
		ArrayList<Submission> submissionList = new ArrayList<Submission>();
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select * from submissions where user_id = " + userID;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				submissionList.add(new Submission(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4),rs.getDouble(5),rs.getString(6)));
			}
		}catch (Exception e) {
			
		}
		return submissionList;
	}
	public String getExamTitleByID(int examId) {
		String result = null;
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select title from exams where id = " + examId;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				result = rs.getString(1);
			}
		}catch (Exception e) {
			
		}
		return result;
	}
	public int insertSubmission(int userId, int examId) {
	    int submissionId = -1;
	    String sql = "INSERT INTO submissions (user_id, exam_id, submitted_at, status, score) VALUES (?, ?, NOW(), 'PENDING', 0)";
	    
	    try (Connection con = Connect2Db.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        
	        ps.setInt(1, userId);
	        ps.setInt(2, examId);
	        
	        int affectedRows = ps.executeUpdate();
	        
	        if (affectedRows > 0) {
	            try (ResultSet rs = ps.getGeneratedKeys()) {
	                if (rs.next()) {
	                    submissionId = rs.getInt(1);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return submissionId;
	}
	public void updateScoreAndStatus(int submissionId, double score, String status) {
        String sql = "UPDATE submissions SET score = ?, status = ? WHERE id = ?";
        
        try (Connection con = Connect2Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDouble(1, score);
            ps.setString(2, status); 
            ps.setInt(3, submissionId);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
