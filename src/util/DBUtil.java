package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			String username = "root";
			String password = "Golfer31";
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/booke", username, password);
					
		}catch (Exception e) {
			e.getMessage();
			
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
