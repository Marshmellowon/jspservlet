package example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Selectservlet
 */
@WebServlet("/Select")
public class Selectservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Selectservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		
		
		String driver = "com.mysql.jdbc.Driver"; //driver class
		String url ="jdbc:mysql://localhost:3307/testdb?&serverTimezone=UTC"; //jdbc url servertimezone: 필요
		String userid = "root"; //user
		String passwd = "1234"; //password
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			stmt = con.createStatement();
			String sql = "SELECT emp_id,ename,salary,depart FROM emp";
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
				String emp_id = rs.getString("emp_id");
				String ename = rs.getString("ename");
				int salary = rs.getInt("salary");
				String depart = rs.getString("depart");
				out.print(emp_id+"\t"+ename+"\t"+salary+"\t"+depart+"<br>");
				System.out.println("연결성공");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		out.print("</body></html>");
	}
}
