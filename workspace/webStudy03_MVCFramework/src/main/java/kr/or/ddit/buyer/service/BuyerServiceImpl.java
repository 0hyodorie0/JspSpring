package kr.or.ddit.buyer.service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.BuyerVO;

public class BuyerServiceImpl implements BuyerService {
	private BuyerDAO buyerDAO = new BuyerDAOImpl();
	
	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = buyerDAO.selectBuyer(buyerId);
		if(buyer==null)
			throw new PKNotFoundException(buyerId+" 거래처 없음.");
		return buyer;
	}

}
