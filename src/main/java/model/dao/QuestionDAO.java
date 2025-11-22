package model.dao;

import model.bean.*;
import java.sql.*;
import java.util.*;

public class QuestionDAO {
	public ArrayList<Question> getAllQuestion() {
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select * from questions";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				questionList.add(new Question(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4)));
			}
		}catch (Exception e) {
			
		}
		return questionList;
	}
	public Question getQuestionInSubmissionAnswer(SubmissionAnswer sa) {
	    Question result = null;
	    String sql = "SELECT * FROM questions WHERE id = ?";
	    
	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, sa.getQuestionId()); 
	        
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) { 
	            result = new Question(
	                rs.getInt(1),
	                rs.getInt(2),
	                rs.getString(3),
	                rs.getString(4)
	            );
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	public ArrayList<Question> getQuestionInExam(int examId){
		ArrayList<Question> result = new ArrayList<Question>();
		Question temp = null;
		String sql = "SELECT * FROM questions WHERE exam_id = ?";
	    	       
	    try {
	        Connection con = Connect2Db.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, examId); 
	        
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) { 
	        	temp = new Question(
	                rs.getInt(1),
	                rs.getInt(2),
	                rs.getString(3),
	                rs.getString(4)
	            );
		        result.add(temp);
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}
