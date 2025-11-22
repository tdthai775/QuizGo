package model.dao;

import model.bean.*;
import java.sql.*;
import java.util.*;

public class ChoiceDAO {
	public ArrayList<Choice> getAllChoice() {
		ArrayList<Choice> choiceList = new ArrayList<Choice>();
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select * from questions";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				choiceList.add(new Choice(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getBoolean(4)));
			}
		}catch (Exception e) {
			
		}
		return choiceList;
	}
	public ArrayList<Choice> getChoiceInQuestion(Question question) {
	    ArrayList<Choice> result = new ArrayList<Choice>();
	    String sql = "SELECT * FROM choices WHERE question_id = ?";
	    
	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, question.getId());
	        
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            result.add(new Choice(
	                rs.getInt(1),
	                rs.getInt(2),
	                rs.getString(3),
	                rs.getBoolean(4) 
	            ));
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public ArrayList<Choice> getUserAnswer(SubmissionAnswer sa) {
	    ArrayList<Choice> result = new ArrayList<Choice>();
	    String sql = "SELECT * FROM choices WHERE id = ?";
	    
	    if (sa.getChoiceId() == null || sa.getChoiceId() == 0) {
	        return result; 
	    }

	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, sa.getChoiceId());
	        
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            result.add(new Choice(
	                rs.getInt(1),
	                rs.getInt(2),
	                rs.getString(3),
	                rs.getBoolean(4)
	            ));
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public ArrayList<Choice> getCorrectAnswer(Question question) {
	    ArrayList<Choice> result = new ArrayList<Choice>();
	    String sql = "SELECT * FROM choices WHERE question_id = ? AND is_correct = 1";
	    
	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, question.getId());
	        
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            result.add(new Choice(
	                rs.getInt(1),
	                rs.getInt(2),
	                rs.getString(3),
	                rs.getBoolean(4)
	            ));
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public ArrayList<Choice> getCorrectAnswerByQuestionId(int questionId) {
	    ArrayList<Choice> result = new ArrayList<>();
	    String sql = "SELECT * FROM choices WHERE question_id = ? AND is_correct = 1";
	    
	    try (Connection con = Connect2Db.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	         
	        ps.setInt(1, questionId);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            result.add(new Choice(
	                rs.getInt("id"),
	                rs.getInt("question_id"),
	                rs.getString("content"),
	                rs.getBoolean("is_correct")
	            ));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public Choice getChoiceById(int choiceId) {
	    Choice c = null;
	    String sql = "SELECT * FROM choices WHERE id = ?";
	    try (Connection con = Connect2Db.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, choiceId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            c = new Choice(
	                rs.getInt("id"),
	                rs.getInt("question_id"),
	                rs.getString("content"),
	                rs.getBoolean("is_correct") 
	            );
	        }
	    } catch (Exception e) { e.printStackTrace(); }
	    return c;
	}
}
