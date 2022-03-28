package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리를 위한 prersistence Layer
 *
 */
public interface ProdDAO {
	
	public ProdVO selectProd(String prodId);
	public int insertProd(ProdVO prod);
	public int updateprod(ProdVO prod);
	
	public int selectTotalRecord(PagingVO<ProdVO> paging);
	public List<ProdVO> selectProdList(PagingVO<ProdVO> paging);
	
}
