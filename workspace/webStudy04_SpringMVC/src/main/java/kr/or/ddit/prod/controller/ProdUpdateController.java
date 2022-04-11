package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	
	@Inject
	private ProdService service;
	
	@ModelAttribute("command")
	public String command() {
		return "UPDATE";
	}
	
	@GetMapping
	public String form(
		@RequestParam(value="what", required=true) String prodId
		, Model model
	) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@PostMapping
	public String process(
		@ModelAttribute("prod") ProdVO prod
		, Model model		
	) throws IOException {
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		
//		if(prodImage!=null && !prodImage.isEmpty()) {
////		1. 업로드된 이미지 저장
//			String saveName = UUID.randomUUID().toString();
//			File saveFile = new File(saveFolder, saveName);
//			prodImage.transferTo(saveFile);
////		2. 저장명으로 prodImg 생성.
//			prod.setProdImg(saveName);
//		}
		
		boolean valid = new ValidatorUtils<>().validate(prod, errors, UpdateGroup.class);
		
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시 하시오.!");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}










