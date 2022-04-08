package kr.or.ddit;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	@Inject
	private SampleService service;
	@RequestMapping("/sample")
	public String sample(Model model, Locale locale) {
		
		model.addAttribute("loc", locale);
		
		return "sample/view";
	}
}







