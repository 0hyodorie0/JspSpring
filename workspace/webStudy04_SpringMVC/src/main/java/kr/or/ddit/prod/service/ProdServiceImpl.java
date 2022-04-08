package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.p;
@Service
@Slf4j
public class ProdServiceImpl implements ProdService {
	@Inject
	private WebApplicationContext context;
	@Value("#{appInfo['prodImages']}")
	private String saveFolderUrl;
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		Resource saveFolderRes = context.getResource(saveFolderUrl);
		if(!saveFolderRes.exists()) {
			saveFolder = saveFolderRes.getFile();
			saveFolder.mkdirs();
		}
		log.info("상품 이미지 저장 위치, {}", saveFolder);
	}
	
	@Inject
	private ProdDAO prodDAO;
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new PKNotFoundException(prodId+" 상품이 없음.");
		return prod;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		try {
			prod.saveTo(saveFolder);
			int rowcnt = prodDAO.insertProd(prod);
			return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		try {
			prod.saveTo(saveFolder);
			int rowcnt = prodDAO.updateProd(prod);
			return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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



















