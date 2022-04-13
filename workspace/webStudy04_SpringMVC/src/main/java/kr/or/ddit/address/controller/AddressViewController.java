package kr.or.ddit.address.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressViewController {
	@RequestMapping("/address/addressMng")
	public String view() {
		return "address/view";
	}
}
