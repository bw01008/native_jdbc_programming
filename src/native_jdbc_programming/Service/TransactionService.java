package native_jdbc_programming.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Title;
import native_jdbc_programming.util.JdbcUtil;

// commit / rollback 테스트
public class TransactionService {
	// title과 dept 추가하는 메소드
	public String transAddTitleAndDepartment(Title title, Department dept) {
		String titleSql = "insert into title values (?, ?)";
		String deptSql = "insert into department values (?, ?, ?)";
		Connection con = null; // Connection 선언
		PreparedStatement tPstmt = null; // 쿼리문이 2개이기 때문에 두번 선언해줘야 한다.(title, dept)
		PreparedStatement dPstmt = null;
		String res = null;

		try {
			con = JdbcUtil.getConnection(); // DB연결했을 때 기본적으로 오토커밋이다.
			con.setAutoCommit(false);// 오토커밋을 꺼줘야한다.

			tPstmt = con.prepareStatement(titleSql); // 쿼리문을 쏴줄 준비를 한다.
			tPstmt.setInt(1, title.getTno()); // insert 쿼리문 첫번째 물음표에 추가할 tno를 받아와서 넣어준다.
			tPstmt.setNString(2, title.getTname()); // 두번째 물음표
			tPstmt.executeUpdate(); // 쿼리문 실행

			dPstmt = con.prepareStatement(deptSql); // 쿼리문 쏴줄 준비를 한다
			dPstmt.setInt(1, dept.getDeptNo());
			dPstmt.setNString(2, dept.getDeptName());
			dPstmt.setInt(3, dept.getFloor());
			dPstmt.executeUpdate(); // 쿼리문 실행

			con.commit(); // 두 쿼리 문장이 실행되면 커밋해준다.
			res = "commit"; // commit문자열
		} catch (SQLException e) {
			res = "rollback";
			rollbackUtil(con); // rollback 예외처리를 메소드로 빼주고 호출
		} finally {
			System.out.println(res);
			closeUtil(con, tPstmt, dPstmt); // close 예외처리 메소드로 빼주고 호출
		}

		return res;
	}// end of method add

	// title과 dept 삭제하는 메소드
	public int transRemoveTitleAndDepartment(Title title, Department dept) {
		String titleSql = "delete from title where tno = ?";
		String deptSql = "delete from department where deptNo = ?";
		Connection con = null;
		PreparedStatement tPstmt = null; // 쿼리문이 2개이기 때문에 두번 선언해줘야 한다.
		PreparedStatement dPstmt = null;
		int res = 0;

		try {
			con = JdbcUtil.getConnection(); // 연결했을 때 오토커밋이다.
			con.setAutoCommit(false);// 오토커밋을 꺼줘야한다.
			
			System.out.println("res > " + res);
			tPstmt = con.prepareStatement(titleSql);
			tPstmt.setInt(1, title.getTno());
			res += tPstmt.executeUpdate();	// 성공하면 res에 1이 저장된다.
			System.out.println("res > " + res);
			
			dPstmt = con.prepareStatement(deptSql);
			dPstmt.setInt(1, dept.getDeptNo());
			res += dPstmt.executeUpdate();	// 윗 쿼리문이 성공후 이 쿼리문까지 성공되면 1이 누적 저장된다
			System.out.println("res > " + res);

			if (res == 2) {	// 총 2쿼리문이 성공했으면 res에 2가 저장되었을거니까 2와 같다면 커밋, 아니라면 롤백
				con.commit();
				System.out.println("commit()");
			} else {
				throw new SQLException();	// 예외를 던져줘서 아래에서 예외를 잡아서 처리해준다 > rollbackUtil(con);메소드 호출
			}
		} catch (SQLException e) {
			rollbackUtil(con);
		} finally {
			closeUtil(con, tPstmt, dPstmt);
		}
		return res;
	}

	public void rollbackUtil(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void closeUtil(Connection con, PreparedStatement tPstmt, PreparedStatement dPstmt) {
		try {
			con.setAutoCommit(true); // 다 끝나면 오토커밋을 다시 켜준다.
			if (tPstmt != null)
				tPstmt.close();
			if (dPstmt != null)
				dPstmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}// end of class
