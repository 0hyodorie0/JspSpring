package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdRetrieveController{
   @Inject
   private ProdService service;
   @GetMapping("prodList.do")
   public String ListUI() {
      return "prod/prodList";
   }
   
   @GetMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public PagingVO<ProdVO> prodList(
         @RequestParam(required=false, defaultValue="1") int[] currentPage
         ,@ModelAttribute("detailCondition") ProdVO detailCondition
         , HttpServletResponse resp
   ) throws IOException{
      
      PagingVO<ProdVO> paging = new PagingVO<>(3, 2);
      paging.setCurrentPage(currentPage[0]);
      paging.setDetailCondition(detailCondition);
      
      service.retrieveProdList(paging);
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
   
   @GetMapping("prodView.do")
   public String view(
         @RequestParam("what") String prodId
         , Model model) {
      ProdVO prod = service.retrieveProd(prodId);
      model.addAttribute("prod", prod);
      return "prod/prodView";
   }
   
}

