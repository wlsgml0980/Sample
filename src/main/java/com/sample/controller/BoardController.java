package com.sample.controller;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.service.BoardService;
import com.sample.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	  @Inject
	  private BoardService service;



	   @RequestMapping(value = "/listAll", method = RequestMethod.GET)
	   public void listAll(Model model) throws Exception {
		    
		    model.addAttribute("list", service.listAll());
	   
	   }	
	   

	   @RequestMapping(value = "/read", method = RequestMethod.GET)	   
	   public void read(@RequestParam("no") int no, Model model) throws Exception {
		    
		   
		    service.updateHit(no);
		    model.addAttribute(service.read(no));
		    
	   }
	   
	   
	   @RequestMapping(value = "/update", method = RequestMethod.POST)
	   public String updatePOST(BoardVO board, RedirectAttributes rttr) throws Exception {

		   
	     service.update(board);
	     rttr.addFlashAttribute("msg", "SUCCESS");

	     return "redirect:/board/listAll";
	   }
	   

	   @RequestMapping(value = "/delete", method = RequestMethod.POST)
	   public String delete(@RequestParam("no") int no, RedirectAttributes rttr) 
			   throws Exception {
		
		service.delete(no);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listAll";
	   }
	   
	   
	   @RequestMapping(value = "/insert", method = RequestMethod.GET)
	  public void insertGET(BoardVO board, Model model) throws Exception {
		   logger.info("create post  addFlashAttribute...........");
	  }
	  
	   @RequestMapping(value = "/insert", method = RequestMethod.POST)
	   public String insertPOST(BoardVO board, RedirectAttributes rattr) throws Exception {	  
		   
		   logger.info("create post  addFlashAttribute...........");
		   logger.info(board.toString());
		  
		   service.insert(board);
		  
		   rattr.addFlashAttribute("msg", "SUCCESS");
		   return "redirect:/board/listAll";
	   
	   }		  

	   
	   
	   
	   
	   

	   

	   

}
