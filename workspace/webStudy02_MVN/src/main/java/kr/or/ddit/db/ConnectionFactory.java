package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;



/**
 * Factory Object[Method] Pattern
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	static {
		String cpPath = "/kr/or/ddit/db/dbInfo.properties";
		try(
			InputStream inStream = ConnectionFactory.class.getResourceAsStream(cpPath);
		) {
			Properties dbInfo = new Properties();
			dbInfo.load(inStream);
			String driverClassName = dbInfo.getProperty("driverClassName");
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
//			Class.forName(driverClassName);
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(driverClassName);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			int initialSize = Integer.parseInt(dbInfo.getProperty("initialSize"));
			long maxWait = Long.parseLong(dbInfo.getProperty("maxWait"));
			int maxTotal = Integer.parseInt(dbInfo.getProperty("maxTotal"));
			ds.setInitialSize(5);
			ds.setMaxWaitMillis(2000);
			ds.setMaxTotal(10);
			dataSource = ds;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() throws SQLException {
		
		//Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
