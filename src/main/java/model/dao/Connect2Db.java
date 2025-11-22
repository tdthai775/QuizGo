package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect2Db {
	 private static final String URL = "jdbc:mysql://localhost:3306/dbquizgo";
	    private static final String USER = "root";
	    private static final String PASS = "";

	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASS);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            throw new SQLException("Không thể tải driver MySQL!");
	        }
	    }
}
