package kr.or.ddit.sample.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.sample.service.SampleService;

@Controller
public class SampleController {
	private SampleService service;
	
	@Required
	@Inject
	public void setService(SampleService service) {
		this.service = service;
	}
	
	@RequestMapping("/sample.do")
	public String sample(Model model,String param) {
		StringBuffer info = service.retrieveInfo();
		model.addAttribute("info", info);
		model.addAttribute("data", param);
		return "sample/view";
	}
}
