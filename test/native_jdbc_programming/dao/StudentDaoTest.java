package native_jdbc_programming.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dao.impl.StudentDaoImpl;
import native_jdbc_programming.dto.Student;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentDaoTest {

	private static StudentDao dao = StudentDaoImpl.getInstance();

	// JDBCUtilTest에서 복사해와서 붙이기
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectStudentByAll() {
		System.out.printf("%s()%n","testSelectStudentByAll");
		List<Student> stdList = dao.selectStudentByAll();
		Assert.assertNotNull(stdList);
		stdList.stream().forEach(System.out::println); 
		for(Student s : stdList) {
			System.out.println(s);
		}
	}

	@Test
	public void test05SelectStudentByNo() {
		System.out.printf("%s()%n","testSelectStudentByNo");
		Student student = new Student(1);		
		Student searchstudent = dao.selectStudentByNo(student);
		
		Assert.assertNotNull(searchstudent);
//		titleList.stream().forEach(System.out::println); 이렇게 해도 된다.
		System.out.println(dao.selectStudentByNo(student));
	}

	@Test
	public void test01InsertStudent() {
		System.out.printf("%s()%n","testInsertStudent"); //제목찍어주기
		Student newStudent = new Student(5,"김예진",90,90,90);
		int res =dao.insertStudent(newStudent);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByNo(newStudent));
	}

	@Test
	public void test02UpdateStudent() {
		System.out.printf("%s()%n","testUpdateStudent"); //제목찍어주기
		Student updatestudent = new Student(3,"김재룡",99,99,99);
		int res =dao.updateStudent(updatestudent);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectStudentByNo(updatestudent));
	}

	@Test
	public void test03DeleteStudent() {
		System.out.printf("%s()%n","testDeleteStudent");  //제목찍어주기
		Student newStudent = new Student(5);
		int res = dao.deleteStudent(newStudent);
		Assert.assertEquals(1, res);
		dao.selectStudentByAll();
	}

}
