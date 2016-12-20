package Utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MySQLConnection {
	public static Connection getConnection(){
		Connection conn = null;
		try {
				String strURL = "jdbc:mysql://10.151.26.46:3306";

				Class.forName("com.mysql.jdbc.Driver");			
				conn = (Connection) DriverManager.getConnection(strURL,"csadmin","c$@g0sw1809");
			} catch (Exception e) {			
				e.printStackTrace();	
			}
		return conn;
	}
	
	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println(con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}