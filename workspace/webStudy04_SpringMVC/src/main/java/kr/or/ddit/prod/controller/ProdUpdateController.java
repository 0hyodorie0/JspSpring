package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodUpdate.do")
@Controller
public class ProdUpdateController {
	
	private String saveFolderUrl = "/resources/prodimages";
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	
	private void addAttributes(HttpServletRequest req) {
		  req.setAttribute("lprodList", othersDAO.selectLprodList());
		  								// null값으로 입력 하여서 마이바티스로감 ! 
		  req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
		  req.setAttribute("command", "UPDATE");
	}
	
	@RequestMapping("/prod/prodUpdate.do")
	public String form(@RequestParam(value="what",required=true) String prodId
			, HttpServletRequest req) {
		
		addAttributes(req);
		ProdVO prod= service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	
	
	@RequestMapping(value="/prod/prodUpdate.do",method=RequestMethod.POST)
	public String process(@ModelAttribute("prod")ProdVO prod,
			@RequestPart(value="prodImage",required=false) MultipartFile prodImage,
			HttpServletRequest req) throws IOException {
		
		addAttributes(req);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = new ValidatorUtils<>().validate(prod, errors, UpdateGroup.class);
		
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) { // 폴더가 안만들어진것 
			saveFolder.mkdirs();
		}
		prod.setProdImage(prodImage);
		prod.saveTo(saveFolder);
//		   if(prodImage !=null&& !prodImage.isEmpty()) {
//			   //1. 업로드된 이미지저장
//			   String saveName = UUID.randomUUID().toString();
//			   File saveFile =new File(saveFolder,saveName);
//			   prodImage.transferTo(saveFile);
//			   //2. 저장명으로 prodImg 생성
//			   prod.setProdImg(saveName);
//	   }		
		
		String viewName = null;
		
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			switch(result) {
			case OK :
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default :
				req.setAttribute("message","서버오류, 잠시뒤 해주세요 ! ");
				viewName = "prod/prodForm";
				break;
			}		
		}else {
			viewName = "prod/prodForm";
		}
		
		return viewName;
	}
}	
	  
	  
	  
	  
