package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

//@WebServlet("/member/idCheck.do")
@Controller
public class IdCheckController{
   private MemberService service = new MemberServiceImpl();
   
   @RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST)
   public String idCheck(
         @RequestParam("memId") String memId
         , HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      boolean available = false;
      
      try {
         service.retrieveMember(memId);
         
      }catch (PKNotFoundException e) {
         available = true;
      }
      
      try(
         PrintWriter out = resp.getWriter();   
      ){
         out.print(available);
         return null;
      }
   }
}




