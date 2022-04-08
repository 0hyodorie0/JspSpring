package kr.or.ddit.dbprop.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBserPropertyDAOImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBaserPropertyList(Map<String, Object> pMap) {
		return null;
//		try(
//				Connection conn = ConnectionFactory.getConnection();
//				Statement stmt = conn.createStatement();	
//			){
//				StringBuffer sql = new StringBuffer();
//				sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION  ");
//				sql.append(" FROM DATABASE_PROPERTIES                           ");
//				
//				ResultSet rs = stmt.executeQuery(sql.toString());
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int colCount = rsmd.getColumnCount();
//				String[] headers = new String[colCount];
//				
//				pMap.put("headers", headers);
//				
//				for( int i=1; i<=colCount; i++) {
//					headers[i-1] = rsmd.getColumnName(i);
//				}
//				List<DataBasePropertyVO> list = new ArrayList<>();
//				
//				while(rs.next()) {
//					DataBasePropertyVO vo = new DataBasePropertyVO();
//					list.add(vo);
//					vo.setPropertyName(rs.getString("PROPERTY_NAME"));
//					vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
//					vo.setDescription(rs.getString("DESCRIPTION"));
//				}
//				
//				return list;
//			}catch(SQLException e){
//				throw new RuntimeException(e);
//			}
	}

}
