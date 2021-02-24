package native_jdbc_programming.ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import native_jdbc_programming.dto.Department;

/*JDBC try-catch-resource
 * 2021.02.15
 */
public class JdbcConEx3 {

	public static void main(String[] args) {
		ArrayList<Department> list = null;
		String url = "jdbc:mysql://localhost:3306/mysql_study?useSSL=false";
		String user = "user_mysql_study";
		String password = "rootroot";
		
		String sql = "select deptno, deptname, floor from department";
		
		try (Connection con = DriverManager.getConnection(url, user, password); 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql);
			)
		{
			// 1. JDBC드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			list = new ArrayList<>();
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
			System.out.println(list);	// Department가 들어가 있는 배열
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Department Query한 결과는 ");
		for(Department d : list) {
			System.out.println(d);
		}
	}// end of main

	private static Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}// end of method
}// end of class
