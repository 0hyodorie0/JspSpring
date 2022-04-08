package kr.or.ddit.member.controller;

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

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberInsertController{
	@Inject
   private MemberService service;
   
   @GetMapping
   public String doGet(){
      return "member/memberForm";
   }

   
   @PostMapping(value="/memberInsert.do")
   public String process(
      @ModelAttribute("member") MemberVO member
      ,  Model model
   ){
      
      Map<String, List<String>> errors = new LinkedHashMap<>();
      model.addAttribute("errors", errors);
      
      boolean valid = new ValidatorUtils<MemberVO>().validate(member, errors, InsertGroup.class);
      String viewName = null;
      if(valid) {
         ServiceResult result = service.createMember(member);
         switch (result) {
         case PKDUPLICATED:
            model.addAttribute("message", "아이디 중복");
            viewName = "member/memberForm";
            break;
         case FAIL:
            model.addAttribute("message", "서버 오류, 잠시 뒤 다시 실행하세요.");
            viewName = "member/memberForm";
            break;

         default:
            viewName = "redirect:/";
            break;
         }
      }else {
         viewName = "member/memberForm";
      }
      
      return viewName;
   }
}