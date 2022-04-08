package kr.or.ddit.prod.controller.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(basePackages="kr.or.ddit.prod")
@Slf4j
public class ProdControllerAdvice {
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		log.info("상품관리에 필요한 분류 정보를 조회하고, 컨트롤러에 위빙하였음");
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList() {
		log.info("상품관리에 필요한 거래처 정보를 조회하고, 컨트롤러에 위빙하였음");
		return othersDAO.selectBuyerList(null);
	}
	
}
