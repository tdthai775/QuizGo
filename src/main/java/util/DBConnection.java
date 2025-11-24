package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/dbquiz";	
		String user = "root";
		String pass = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,user, pass);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
