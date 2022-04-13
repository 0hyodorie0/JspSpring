package kr.or.ddit.orm.lab1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.orm.dao.DataBasePropertyDAO;
import kr.or.ddit.orm.vo.DataBasePropertyVO;

//@Repository
public class DataBasePropertyDAOImpl_NonMybatis implements DataBasePropertyDAO {
	
	private final DataSource dataSource;
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
//	@Inject
	public DataBasePropertyDAOImpl_NonMybatis(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
		sql.append("FROM DATABASE_PROPERTIES                         ");
		// column 하나만 조회하는 경우
//		return jdbcTemplate.queryForList(sql.toString(), String.class);
		return jdbcTemplate.query(sql.toString(), new RowMapper<DataBasePropertyVO>() {
			@Override
			public DataBasePropertyVO mapRow(ResultSet rs, int arg1) throws SQLException {
				DataBasePropertyVO vo = new DataBasePropertyVO();
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				vo.setDescription(rs.getString("DESCRIPTION"));
				return vo;
			}
		});
	}

	@Override
	public DataBasePropertyVO selectDataBaseProperty(String propertyName) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
		sql.append("FROM DATABASE_PROPERTIES ");
		sql.append("WHERE PROPERTY_NAME = :propertyName");
		
		DataBasePropertyVO detailCondition = new DataBasePropertyVO();
		detailCondition.setPropertyName(propertyName);
		
		return namedParameterJdbcTemplate.query(sql.toString(), 
				new BeanPropertySqlParameterSource(detailCondition),
				(rs, rowcnt) ->{
					DataBasePropertyVO vo = new DataBasePropertyVO();
					vo.setPropertyName(rs.getString("PROPERTY_NAME"));
					vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
					vo.setDescription(rs.getString("DESCRIPTION"));
					return vo;
				}).get(0);
	}

}
