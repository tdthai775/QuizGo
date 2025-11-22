package model.dao;
import model.bean.*;
import java.sql.*;
import java.util.*;

public class SubmissionAnswerDAO {
	public ArrayList<SubmissionAnswer> getAllSubmissionAnswer(){
		ArrayList<SubmissionAnswer> result = new ArrayList<SubmissionAnswer>();
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select * from submission_answer ";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				result.add(new SubmissionAnswer(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
			}
		}catch (Exception e) {
			
		}
		return result;
	}
	public SubmissionAnswer getSubmissionAnswerByID(int id) {
		ArrayList<SubmissionAnswer> saList = getAllSubmissionAnswer();
		for(int i = 0 ; i < saList.size() ; i++) {
			if(saList.get(i).getId() == id) {
				return saList.get(i);
			}
		}
		return null;
	}
	public ArrayList<SubmissionAnswer> getSubmissionAnswerBySubmissionId(int submissionId) {
	    ArrayList<SubmissionAnswer> result = new ArrayList<SubmissionAnswer>();
	    String sql = "SELECT * FROM submission_answers WHERE submission_id = ?";
	    
	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, submissionId);         
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            result.add(new SubmissionAnswer(
	                rs.getInt(1), 
	                rs.getInt(2), 
	                rs.getInt(3), 
	                rs.getInt(4) 
	            ));
	        }
	        con.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public void insertList(ArrayList<SubmissionAnswer> list) {
	    String sql = "INSERT INTO submission_answers (submission_id, question_id, choice_id) VALUES (?, ?, ?)";
	    
	    try (Connection con = Connect2Db.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        for (SubmissionAnswer ans : list) {
	            ps.setInt(1, ans.getSubmissionId());
	            ps.setInt(2, ans.getQuestionId());
	            
	            if (ans.getChoiceId() != null && ans.getChoiceId() > 0) {
	                ps.setInt(3, ans.getChoiceId());
	            } else {
	                ps.setNull(3, java.sql.Types.INTEGER);
	            }
	            
	            ps.addBatch(); 
	        }
	        
	        ps.executeBatch(); 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
