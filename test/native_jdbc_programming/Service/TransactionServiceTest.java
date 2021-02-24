package native_jdbc_programming.Service;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import native_jdbc_programming.dto.Department;
import native_jdbc_programming.dto.Title;
// 각각 4가지의 경우를 테스트한다.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionServiceTest {
	private static TransactionService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//TransactionServiceTest를 실행하기 전에 수행
		service = new TransactionService();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//TransactionServiceTest를 실행한 후에 수행
		service = null;
	}

	@After
	public void tearDown() throws Exception {
		//test메서드 끝날때마다 호출
		System.out.println();
	}

	@Test
	//Title insert 성공 Department insert 실패
	public void test01TransAddTitleAndDepartment_FailTitle() {
		System.out.printf("%s()%n", "testTransAddTitleAndDepartment_FailTitle");
		Title title = new Title(1, "인턴");
		Department dept = new Department(5, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	//Title insert 실패 Department insert 성공
	public void test02TransAddTitleAndDepartment_FailDept() {
		System.out.printf("%s()%n", "testTransAddTitleAndDepartment_FailDept");
		Title title = new Title(6, "인턴");
		Department dept = new Department(1, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	//Title insert 실패 Department insert 실패
	public void test03TransAddTitleAndDepartment_FailBoth() {
		System.out.printf("%s()%n", "testTransAddTitleAndDepartment_FailBoth");
		Title title = new Title(1, "인턴");
		Department dept = new Department(1, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("rollback", res);
	}
	@Test
	//Title insert 성공 Department insert 성공
	public void test04TransAddTitleAndDepartment_Success() {
		System.out.printf("%s()%n", "testTransAddTitleAndDepartment_Success");
		Title title = new Title(6, "인턴");
		Department dept = new Department(5, "비상계획부", 10);
		
		String res = service.transAddTitleAndDepartment(title, dept);
		Assert.assertEquals("commit", res);
	}
/////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void test05TransRemoveTitleAndDepartment_FailTitle() {
		System.out.printf("%s()%n", "testCloseUtil_FailTitle");
		Title title = new Title(0);
		Department dept = new Department(5);
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);	// 기대값이 1인 이유는 title은 삭제가 실패되고 department는 성공해서 1이 누적되어 반환된다.
	}
	@Test
	public void test06TransRemoveTitleAndDepartment_FailDept() {
		System.out.printf("%s()%n", "testCloseUtil_FailDept");
		Title title = new Title(6);
		Department dept = new Department(6);
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(1, res);
	}
	@Test
	public void test07TransRemoveTitleAndDepartment_FailBoth() {
		System.out.printf("%s()%n", "testCloseUtil_FailBoth");
		Title title = new Title(0);
		Department dept = new Department(6);
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(0, res);	// 기댓값이 0인 이유는 title, department 모두 삭제가 실패했기때문에 누적된 수는 0이다.
	}
	@Test
	public void test08TransRemoveTitleAndDepartment_Success() {
		System.out.printf("%s()%n", "testCloseUtil_Success");
		Title title = new Title(6);
		Department dept = new Department(5);
		int res = service.transRemoveTitleAndDepartment(title, dept);
		Assert.assertEquals(2, res);	//기댓값이 2인 이유는 모두 삭제 성공해서 누적된 수가 2이기때문이다
	}

}
