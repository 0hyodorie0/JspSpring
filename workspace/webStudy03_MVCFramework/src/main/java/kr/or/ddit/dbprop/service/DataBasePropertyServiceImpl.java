package kr.or.ddit.dbprop.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import kr.or.ddit.dbprop.dao.DataBasePropertyDAO;
import kr.or.ddit.dbprop.dao.DataBserPropertyDAOImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements DataBasePropertyService {
	private DataBasePropertyDAO dao = new DataBserPropertyDAOImpl();
	
	@Override
	public List<DataBasePropertyVO> retrieveDataBasePropertyList(Map<String, Object> pMap) {
		String pattern = "%s : %tc";
		List<DataBasePropertyVO> list = dao.selectDataBaserPropertyList(pMap);
		for(DataBasePropertyVO vo : list) {
			String newValue = String.format(pattern, vo.getPropertyValue(), Calendar.getInstance());
			vo.setPropertyValue(newValue);
		}
		return list;
	}

}
