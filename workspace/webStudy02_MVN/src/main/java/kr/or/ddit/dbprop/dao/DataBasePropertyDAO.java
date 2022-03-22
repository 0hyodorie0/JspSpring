package kr.or.ddit.dbprop.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * Persistence Layer
 *
 */
public interface DataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBaserPropertyList(Map<String, Object> pMap);
}
