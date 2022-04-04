package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController {

 private MemberService service = new MemberServiceImpl();

 
  //ui form // 입력 양식 form // 입력양식 입력 후에 post 로 보내기 ~ 
 @RequestMapping("/member/memberUpdate.do")
 public String from(HttpSession session, HttpServletRequest req){
    MemberVO authmember = (MemberVO) session.getAttribute("authMember");      
    MemberVO member = service.retrieveMember(authmember.getMemId());      
    
    req.setAttribute("member", member);      
    
    return "member/memberEdit";
 }

 
 @RequestMapping(value="/member/memberUpdate.do",method=RequestMethod.POST)
 public String proesess (@ModelAttribute("member")MemberVO member,
       @RequestPart(value="memImage", required=false) MultipartFile memImage,
       HttpServletRequest req) throws IOException {

    member.setMemImage(memImage);
    
    Map<String, List<String>> errors = new LinkedHashMap<>();
    req.setAttribute("errors", errors);
          

    
    boolean valid = new ValidatorUtils<MemberVO>().validate(member,errors,UpdateGroup.class);
    String viewName = null;
    valid =true;
    if(valid) {
       ServiceResult result = service.modifyMember(member);
       switch (result) {
          case INVALIDPASSWORD:
             viewName = "member/memberEdit";
             req.setAttribute("message", "비밀번호 오류");
             break;
          case FAIL: 
             viewName = "member/memberEdit";
             req.setAttribute("message", "서버 오류 ");
             break;
          default:
             viewName= "redirect:/myPage.do";
             break;
          }
       } else { // 검증에 통과하지 못하였을 경우 
          viewName ="member/memberEdit";
       }
    return viewName;
 }

 
}