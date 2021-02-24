package native_jdbc_programming.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	public static Connection getConnection() {
		String propertiesPath = "db.properties"; // resources밑에 있는 파일명과 동일
		Connection con = null;
		// InputStream : 키보드를 사용해서 입력받겠다는뜻(Scanner와 비슷)
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) { // 파일을 스트림형태로 읽어온다.
			Properties prop = new Properties();
			
			prop.load(in); // 읽어오는역할을 한다. key = value
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			/*
			 * System.out.println("prop > " + prop);
			 * 
			 * for(Entry<Object, Object> e : prop.entrySet()) { // entry(key, value)를 함께 추출
			 * 		System.out.printf("%s -> %s%n", e.getKey(), e.getValue());
			 * }
			 * System.out.println();
			 * 
			 * for(Object key : prop.keySet()) {// 키에 대응하는 value만 추출
			 * 		System.out.println(key + "->");
			 * 		System.out.println(prop.get(key)); //key에 해당하는 value가 나온다.
			 * }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}// end of Connection
}// end of class
