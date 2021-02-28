package native_jdbc_programming.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import native_jdbc_programming.dao.StudentDao;
import native_jdbc_programming.dto.Student;
import native_jdbc_programming.util.JdbcUtil;

public class StudentDaoImpl implements StudentDao {
	// 싱글톤 객체 생성
	private static StudentDaoImpl instanse = new StudentDaoImpl();

	private StudentDaoImpl() {
	}

	public static StudentDaoImpl getInstance() {
		return instanse;
	}

	@Override
	public List<Student> selectStudentByAll() {
		String sql = "select stdno, stdname, kor, eng, math from student";
		ArrayList<Student> list = null;
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				list = new ArrayList<>();
				do {
					list.add(getStudent(rs));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private Student getStudent(ResultSet rs) throws SQLException {
		// stdno, stdname, kor, eng, math
		int stdNo = rs.getInt("stdno");
		String stdName = rs.getString("stdname");
		int kor = rs.getInt("kor");
		int eng = rs.getInt("eng");
		int math = rs.getInt("math");
		return new Student(stdNo, stdName, kor, eng, math);
	}

	@Override
	public Student selectStudentByNo(Student student) {
		String sql = " select stdno,stdname,kor,eng,math from student where stdno = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getStudent(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertStudent(Student student) {
		String sql = "insert into student values (?,?,?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			pstmt.setString(2, student.getstdName());
			pstmt.setInt(3, student.getKor());
			pstmt.setInt(4, student.getEng());
			pstmt.setInt(5, student.getMath());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		String sql = "update student set stdname = ? where stdno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, student.getstdName());
			pstmt.setInt(2, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteStudent(Student student) {
		String sql = "delete from student where stdno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, student.getStdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return 0;
	}

}