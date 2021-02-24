package native_jdbc_programming.dao;

import java.util.List;

import native_jdbc_programming.dto.Student;

public interface StudentDao {
	List<Student> selectStudentByAll();
	Student selectStudentByNo(Student student);
	
	int insertStudent(Student student);
	int updateStudent(Student student);
	int deleteStudent(Student studentNo);
	

}
