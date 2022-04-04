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
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodInsert.do")
@Controller
public class ProdInsertController {
	private String saveFolderUrl = "/resources/prodimages";
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	
  private void addAttributes(HttpServletRequest req) {
	  req.setAttribute("lprodList", othersDAO.selectLprodList());
	  								/*null값으로 입력 하여서 마이바티스로감 ! */
	  req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	  req.setAttribute("command", "INSERT");
}
	
	
	@RequestMapping("/prod/prodInsert.do") /* UI 로 이동 */
	public String  form(HttpServletRequest req) {		
		addAttributes(req);
		return "prod/prodForm";	
	}
	
	
	
	
	@RequestMapping(value="/prod/prodInsert.do",method=RequestMethod.POST)
	public String  process(
			@ModelAttribute("prod")ProdVO prod,
			@RequestPart("prodImage") MultipartFile prodImage,
			HttpServletRequest req) throws IOException  {
		
		addAttributes(req);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		//1. classpath resource
	    //2. web resource --이미지가 주소를 가질 수 있음. 
		//3. file system resource 

		
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderUrl);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) { // 폴더가 안만들어진것 
			saveFolder.mkdirs();
		}		
		
		prod.setProdImage(prodImage);
		prod.saveTo(saveFolder);
		
//		   if(prodImage !=null&& !prodImage.isEmpty()) {
			   //1. 업로드된 이미지저장
//			   String saveName = UUID.randomUUID().toString();
//			   File saveFile =new File(saveFolder,saveName);
//			   prodImage.transferTo(saveFile);
			   //2. 저장명으로 prodImg 생성
//			   prod.setProdImg(saveName);
//			  }
		
		boolean valid = new ValidatorUtils<ProdVO>().validate(prod, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시하셈.");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
