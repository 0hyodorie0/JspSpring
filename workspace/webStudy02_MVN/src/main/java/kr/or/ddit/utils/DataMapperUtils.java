package kr.or.ddit.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataMapperUtils {
	private String snakeToCamel(String snake) {
		snake = snake.toLowerCase();
		String[] tokens = snake.split("_");
		String camel = "";
		for(String token : tokens) {
			// mem, id -> MemId
			camel += token.substring(0, 1).toUpperCase()+token.substring(1);
		}
		// memId
		return camel.substring(0, 1).toLowerCase()+camel.substring(1);
	}
	
	private Method findSetter(Class<?> resultClass, String columnName) throws IntrospectionException {
//		MEM_ID -> memId -> setMemId
		String propName = snakeToCamel(columnName);
		PropertyDescriptor pd = new PropertyDescriptor(propName, resultClass);
//		pd.getReadMethod()
		return pd.getWriteMethod();
	}
	
	public List queryForList(ResultSet rs, Class<?> resultClass) throws SQLException{
		List dataList = new ArrayList();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] names = new String[count];
			for(int i=1; i<=count; i++) {
				names[i-1] = rsmd.getColumnName(i);
			}
			while(rs.next()) {
				Object result = resultClass.newInstance();
				dataList.add(result);
				for(String name : names) {
					Method setter = findSetter(resultClass, name);
					String propName = snakeToCamel(name);
					Field fld = resultClass.getDeclaredField(propName);
					Class<?> propType = fld.getType();
					Object propValue = null;
					if(propType.equals(String.class)) {
						propValue = rs.getString(name);
					}else if(propType.equals(Boolean.class)) {
						propValue = rs.getBoolean(name);
					}else {
						propValue = rs.getInt(name);
					}
					setter.invoke(result, propValue);
				}
			}
			
			return dataList;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	public Object queryForObject(ResultSet rs, Class<?> resultClass) throws SQLException {
		Object result = null;
		List list = queryForList(rs, resultClass);
		if(!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}
}






















