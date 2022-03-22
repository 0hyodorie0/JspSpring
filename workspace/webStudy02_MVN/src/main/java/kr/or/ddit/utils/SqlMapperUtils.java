package kr.or.ddit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SqlMapperUtils {
//	private Map<String, PreparedStatement> sqlMap;
	private Properties resource = new Properties();
	
	public SqlMapperUtils(String resourcePath) {
		InputStream is = SqlMapperUtils.class.getResourceAsStream(resourcePath);
		try {
			resource.loadFromXML(is);
//			sqlMap = new HashMap<>();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PreparedStatement generatePreparedStatement(Connection conn, String queryId)
			throws SQLException{
		String sql = resource.getProperty(queryId);
		return conn.prepareStatement(sql);
	}
}















