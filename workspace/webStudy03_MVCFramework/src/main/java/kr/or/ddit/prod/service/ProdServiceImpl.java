package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import oracle.net.aso.p;

public class ProdServiceImpl implements ProdService {
	private ProdDAO prodDAO = new ProdDAOImpl();
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(prodId+" 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> paging) {
		int totalRecord = prodDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ProdVO> prodList = prodDAO.selectProdList(paging);
		paging.setDataList(prodList);
		return prodList;
	}

}



















