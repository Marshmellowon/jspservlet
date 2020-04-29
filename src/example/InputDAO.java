package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InputDAO {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3307/testdb?&serverTimezone=UTC";
	String userid = "root";
	String passwd = "1234";

	public InputDAO() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void select() {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO emp(id,name,salary,depart) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "id");
			pstmt.setString(2, "name");
			pstmt.setInt(3, 0000);
			pstmt.setString(4, "depart");

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
