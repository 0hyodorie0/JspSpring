package kr.or.ddit.dbprop.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * Business Logic Layer
 *
 */
public interface DataBasePropertyService {
	public List<DataBasePropertyVO> retrieveDataBasePropertyList(Map<String, Object> pMap);
}
