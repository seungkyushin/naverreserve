package kr.or.connect.naverreserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.naverreserve.dto.ReservationInfo;

@Controller
public class ViewPageController {
	
	@GetMapping(path="/reservation")
	public String reservation(@RequestParam(name="id",required=true) int id,
			RedirectAttributes redirectAttr) {
		System.out.println("ViewPageController : /reservation");
		redirectAttr.addFlashAttribute("id", id);
		return "reservation";
	}
	
	@PostMapping(path="/reserve")
	public String reservation(@ModelAttribute ReservationInfo data) {
		System.out.println("ViewPageController : /reserve");
				
		System.out.println( data );
	
		
		return "redirect:/views/mainpage.html";
	}
	
	

}
