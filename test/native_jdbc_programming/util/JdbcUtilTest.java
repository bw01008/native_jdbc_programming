package native_jdbc_programming.util;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JdbcUtilTest {

	@BeforeClass // 1. 테스트 수행전 호출된다.
	public static void setUpBeforeClass() throws Exception {
		System.out.printf("%s()%n", "setUpBeforeClass");
	}

	@AfterClass // 5. 테스트가 끝나면 호출된다.
	public static void tearDownAfterClass() throws Exception {
		System.out.printf("%s()%n", "tearDownAfterClass");
	}

	@Before // 2. testGetConnection()가 수행되기 전에 호출된다.
	public void setUp() throws Exception {
		System.out.printf("%s()%n", "setUp");
	}

	@After // 4. testGetConnection()가 수행되기 전에 호출된다
	public void tearDown() throws Exception {
		System.out.printf("%s()%n", "tearDown");
	}

	@Test // 3. 수행
	public void testGetConnection() {
		System.out.printf("%s()%n", "testGetConnection");
		Connection con = JdbcUtil.getConnection();
		System.out.println("con > " + con); // 출력이 된다면 DB접속 성공
		Assert.assertNotNull(con);
	}

}

