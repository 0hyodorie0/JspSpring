package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberListController{
   
    @Inject
    private MemberService service;
   
//   @Inject
//   public MemberListController(MemberService service) {
//   super();
//   this.service = service;
//}
    @GetMapping("memberList.do")
   public String ListUI() {
      return "member/memberList";
   }


    @GetMapping(value="memberList.do", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingVO<MemberVO> listHandler(
         @RequestParam(value="page", required=false, defaultValue="1") int currentPage 
         ,@ModelAttribute(value="search") SimpleSearchVO simpleCondition
         ,Model model
    ){
   
      PagingVO<MemberVO> paging = new PagingVO<>(3, 2);
      paging.setCurrentPage(currentPage);
      paging.setSimpleCondition(simpleCondition);
      
      service.retrieveMemberList(paging);
      return paging;
     
//      req.setAttribute("paging", paging);   ============================================오로지 비동기로만 데이터를 보내주기로 약속함 
      
         // mime setting
         // marshalling
//         ObjectMapper mapper = new ObjectMapper();
//         String json = mapper.writeValueAsString(paging);// json 데이터 생성 
         // serialization (out)
//         try(
//            PrintWriter out = resp.getWriter();
//         ){
//            out.print(json);
//         }
         
   }
   
}











