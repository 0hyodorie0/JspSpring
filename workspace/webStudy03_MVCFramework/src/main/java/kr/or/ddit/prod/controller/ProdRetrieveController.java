package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdRetrieveController{
   private ProdService service = new ProdServiceImpl();
   
   @RequestMapping("/prod/prodList.do")
   public String prodList(
         @RequestParam(value="page", required=false, defaultValue="1") int[] currentPage
         , @RequestParam(value="prodLgu", required=false) String prodLgu
         , @RequestParam(value="prodBuyer", required=false) String prodBuyer
         , @RequestParam(value="prodName", required=false) String prodName
         , HttpServletRequest req
         , HttpServletResponse resp
   ) throws IOException{
      String accept = req.getHeader("Accept");
      ProdVO detailCondition = new ProdVO();
      req.setAttribute("detailCondition", detailCondition);
      detailCondition.setProdLgu(prodLgu);
      detailCondition.setProdBuyer(prodBuyer);
      detailCondition.setProdName(prodName);
      
      PagingVO<ProdVO> paging = new PagingVO<>(3, 2);
      paging.setCurrentPage(currentPage[0]);
      paging.setDetailCondition(detailCondition);
      
      service.retrieveProdList(paging);
      
//      req.setAttribute("paging", paging);
      
      if (accept.contains("json")){
         // mime setting
         resp.setContentType("application/json;charset=utf-8");
         // marshalling
         ObjectMapper mapper = new ObjectMapper();
         String json = mapper.writeValueAsString(paging);// json 데이터 생성 
         // serialization (out)
         try(
            PrintWriter out = resp.getWriter();
         ){
            out.print(json);
         }
         return null;
         
      }else {
         return "prod/prodList";
         
      }
   }
   
   @RequestMapping("/prod/prodView.do")
   public String view(@RequestParam("what") String prodId, HttpServletRequest req){
      ProdVO prod = service.retrieveProd(prodId);
      req.setAttribute("prod", prod);
      return "prod/prodView";
   }
   
}
