package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/prod/prodInsert.do")
@Slf4j
public class ProdInsertController{
	@Inject
	private ProdService service;

	
	@ModelAttribute("command")
	public String command() {
		return "INSERT";
	}
	
	@GetMapping
	public String form(Model model){
		return "prod/prodForm";
	}
	
	@PostMapping
	public String process(
		@ModelAttribute("prod") ProdVO prod
		, Model model
	) throws IOException{

		Map<String, List<String>> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		
		
//		if(prodImage!=null && !prodImage.isEmpty()) {
//		1. 업로드된 이미지 저장
//			String saveName = UUID.randomUUID().toString();
//			File saveFile = new File(saveFolder, saveName);
//			prodImage.transferTo(saveFile);
//		2. 저장명으로 prodImg 생성.
//			prod.setProdImg(saveName);
//		}
		
		boolean valid = new ValidatorUtils<ProdVO>().validate(prod, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시하셈.");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
}
