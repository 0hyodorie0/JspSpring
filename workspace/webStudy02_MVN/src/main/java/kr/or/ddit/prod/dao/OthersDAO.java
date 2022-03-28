package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;

public interface OthersDAO {
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList();
}
