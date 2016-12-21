package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MySQLConnection {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envCtx.lookup("jdbc/testaws");			
			conn = dataSource.getConnection();
//				String strURL = "jdbc:mysql://10.151.26.46:3306";//
//				Class.forName("com.mysql.jdbc.Driver");			
//				conn = (Connection) DriverManager.getConnection(strURL,"csadmin","c$@g0sw1809");
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