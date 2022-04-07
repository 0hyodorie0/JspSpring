package kr.or.ddit.sample.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.sample.vo.MemberVO;

@Controller
@RequestMapping(value="/sampleInsert.do")
public class SampleInsertController {
   
   @GetMapping
   public String form() {
      return "sample/form";
   }
   
   String saveFolderURL = "/resources/memImages";
   
   // 컨테이너 // @Inject = 주입한다 라는 의미 
   @Inject
   WebApplicationContext context;
   
   File saveFolder;
   
   @PostConstruct
   public void init() throws IOException {
      Resource saveFolderRes = context.getResource(saveFolderURL);
      saveFolder = saveFolderRes.getFile();
      if(!saveFolderRes.exists()) {
         saveFolder.mkdirs();
      }
   }
   
   // command Obeject : 클라이언트가 보내는 데이터(parameter+part) 수집.
   
   
   // 파일 저장하는거 임
   @PostMapping
   public String process(@ModelAttribute("member") MemberVO member
         ,RedirectAttributes redirectAttributes
         ) throws IOException {
      String message = "성공";
      

      member.saveTo(saveFolder);
      
      redirectAttributes.addFlashAttribute("message",message);
      redirectAttributes.addFlashAttribute("member",member);
      
      return "redirect:/sampleInsert.do";
   }
}
