package model.dao;
import model.bean.*;
import java.sql.*;
import java.util.*;

public class ExamDAO {
	public ArrayList<Exam> getAllExam() {
		ArrayList<Exam> examList = new ArrayList<Exam>();
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select * from exams";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				examList.add(new Exam(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getTimestamp(5),rs.getInt(6),rs.getBoolean(7)));
			}
		}catch (Exception e) {
			
		}
		return examList;
	}
	public int getQuestionQuantity(int examID) {
		int result=-1;
		try {
			Connection con = Connect2Db.getConnection();
			Statement st = con.createStatement();
			String sql = "Select COUNT(*) from questions where exam_id = " + examID;
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				result=rs.getInt(1);
			}
		}catch (Exception e) {
			
		}
		return result;
	}
}
