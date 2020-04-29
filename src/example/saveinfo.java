package example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class saveinfo
 */
@WebServlet("/saveinfo")
public class saveinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement pstmt;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3307/testdb?&serverTimezone=UTC";
	String userid = "root";
	String passwd = "1234";

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			System.out.println("연결 시작"); //연결 시작을 알기 위함
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String sql = "INSERT INTO emp VALUES(?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			
			System.out.println("pstmt선언"); //pstmt선언까지 이상 없음을 알림
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("dopost 실행"); //doPost 실행을 알림
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// request객체로 들어온 데이터를 가공

		request.setCharacterEncoding("utf-8");
		String id = "id";
		id = request.getParameter("id");
		String name = request.getParameter("name");
		int salary = Integer.valueOf(request.getParameter("salary")); //int를 받기 위함
		String depart = request.getParameter("depart");

		try {
			System.out.println("데이터 입력 시작");
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, salary);
			pstmt.setString(4, depart);
			pstmt.executeUpdate(); //명령 실행
			pstmt.close();
			con.close();

			response.setContentType("text/html;charset=EUC-KR");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("successfully inserted"); //정상처리를 알림
			out.println("</body></html>");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR : " + e);
		}
	}
}
