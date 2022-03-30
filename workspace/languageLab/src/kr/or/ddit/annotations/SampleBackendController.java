package kr.or.ddit.annotations;

import kr.or.ddit.annotations.stereotype.RequestMapping;
import kr.or.ddit.annotations.stereotype.SecondAnnotation;

@SecondAnnotation("BACKEND")
public class SampleBackendController {
	
	
	
	@RequestMapping(value="/member/memberList.do", method="POST")
	public String requestHandler() {
		return "member/memberList";
	}
}
