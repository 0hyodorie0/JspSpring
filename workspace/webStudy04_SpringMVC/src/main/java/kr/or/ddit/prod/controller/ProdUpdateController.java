package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodUpdate.do")
@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {

   @Inject
   private ProdService service;

   @ModelAttribute
   public String command() {
      return "UPDATE";
   }
   
   @GetMapping
   public String form(@RequestParam(value="what",required=true) String prodId, Model model) {
//      addAttributes(model);
      ProdVO prod = service.retrieveProd(prodId);
      
      model.addAttribute("prod", prod);
      return "prod/prodForm";
   }
   
   @PostMapping
   public String process(@ModelAttribute ProdVO prod, Model model) throws IOException {
      
//      addAttributes(model);
      Map<String, List<String>> errors = new LinkedHashMap<>();
      model.addAttribute("errors", errors);
      
      
      boolean valid = new ValidatorUtils<>().validate(prod, errors, UpdateGroup.class);
      String viewName = null;
      if(valid) {
         ServiceResult result = service.modifyProd(prod);
         if(ServiceResult.OK.equals(result)) {
            // 수정에 성공
            viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
         }else {
            model.addAttribute("message", "서버오류 잠시뒤에 실행하세요 ~");
            viewName = "prod/prodForm";
         }
      }else {
         viewName = "prod/prodForm";
      }
      return viewName;
   }
}