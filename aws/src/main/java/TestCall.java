

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Utils.MySQLConnection;

public class TestCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestCall() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName 		= request.getParameter("user_name");
		String userPassword 	= request.getParameter("user_password");
//		System.out.println(userName);
//		System.out.println(userPassword);
		if(userName != null && userPassword != null){
			if(login(userName,userPassword)){
				response.getWriter().write("Login Complete !");
			}else{
				response.getWriter().write("Username or Password is incorrect !");
			}
		}else{
			response.getWriter().write("Error !");
		}
	}

	private boolean login(String userName , String userPassword){
		boolean result = false;
		
		String SQLQuery = "SELECT USER_NAME FROM TESTAWS.USER_LOGIN WHERE USER_ID = ? AND USER_PASSWORD = ?";
		Connection con = MySQLConnection.getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			pstm = con.prepareStatement(SQLQuery);
			pstm.setString(1, userName);
			pstm.setString(2, userPassword);
			rs = pstm.executeQuery();
			if(rs.next()){
				result = true;
			}

		}catch (SQLException e) {
			System.err.println(e);
		}finally{
			try{
				if(con != null) con.close();
				if(pstm != null) pstm.close();
			}catch (Exception e) {
				System.err.println(e);
			}
		}
		return result;
	}
}
