package native_jdbc_programming.ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import native_jdbc_programming.dto.Department;

/*JDBC프로그램 전형적인 실행순서
 * 2021.02.15
 */
public class JdbcConEx {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Department> list = null;
		try {
			// 1. JDBC드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 데이터베이스 커넥션 생성
			String url = "jdbc:mysql://localhost:3306/mysql_study?useSSL=false";
			String user = "user_mysql_study";
			String password = "rootroot";
			con = DriverManager.getConnection(url, user, password);//디비접속
			System.out.println("con > " + con); // 접속성공확인

			// 3. 쿼리 실행을 위한 statement 객체 생성
			stmt = con.createStatement();	//createStatement는 SQL Injection공격에 취약하다.
			System.out.println("stmt > " + stmt); // 날릴 준비를 해라...(?)

			// 4. 쿼리 실행
			String sql = "select deptno, deptname, floor from department";
			rs = stmt.executeQuery(sql);

			// 5. 쿼리 실행 결과 사용
			list = new ArrayList<>();
			System.out.println(list);		//비어있는 배열
			while (rs.next()) {
				list.add(getDepartment(rs));
//				Department dept = getDepartment(rs);
//				System.out.println(dept);
			}
			System.out.println(list);	// Department가 들어가 있는 배열
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver Not Found");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 커넥션 종료 역순으로 종료해야한다.
			try {rs.close();   } catch (SQLException e) {}
			try {stmt.close(); } catch (SQLException e) {}
			try {con.close();  } catch (SQLException e) {}
		}// end if finally
		
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
