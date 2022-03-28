package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public interface ProdService {
	
	/**
	 * 상품 상세 조회
	 * @param prodId
	 * @return 존재하지 않는다면, {@ling PKNotFoundException}
	 */
	public ProdVO retrieveProd(String prodId);
	
	public ServiceResult createProd(ProdVO prod);
	public ServiceResult modifyProd(ProdVO prod);
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> paging);
}
