package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리를 위한 Business Logic Layer
 *
 */
public interface ProdService {
	/**
	 * 상품 상세조회
	 * @param prodId
	 * @return 존재하지 않는다면, {@link PKNotFoundException}
	 */
	public ProdVO retrieveProd(String prodId);
	
	/**
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * @param prod
	 * @return {@link PKNotFoundException}, OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
	/**
	 * @param paging TODO
	 * @return
	 */
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> paging);
}
