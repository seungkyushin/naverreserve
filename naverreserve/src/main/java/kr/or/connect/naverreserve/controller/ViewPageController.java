package kr.or.connect.naverreserve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.naverreserve.dto.ProductPrice;
import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;
import kr.or.connect.naverreserve.service.ProductService;
import kr.or.connect.naverreserve.service.ReservationService;

@Controller
public class ViewPageController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping(path="/detail")
	public String detail( @RequestParam(name="id",required=true,defaultValue="0") int id,
			ModelMap model
			){
		System.out.println("ViewPageController : /detail");
		System.out.println("id :" + id);
		
		model.addAttribute("id",id);
		
		return "detail";
	}
	@GetMapping(path="/reserve")
	public String reserve( @RequestParam(name="id",required=true,defaultValue="0") int id,
			ModelMap model
			){
		System.out.println("ViewPageController : /reserve");
		System.out.println("id :" + id);
		
		model.addAttribute("id",id);
		
		return "reserve";
	}
	
	@GetMapping(path="/bookinglogin")
	public String bookinglogin(){
		System.out.println("ViewPageController : /bookinglogin");
	
		return "bookinglogin";
	}
	
	@GetMapping(path="/review")
	public String review(){
		System.out.println("ViewPageController : /review");
	
		return "review";
	}
	
	
	
	
	@GetMapping(path="/myreservation")
	public String myreservation( @ModelAttribute("resrv_email") String email 
			,HttpSession sec
			,RedirectAttributes redirectAttr
			,ModelAndView model){
		System.out.println("ViewPageController : /myreservation");
		System.out.println(sec.getAttribute("email"));
		
	
				
		if( sec.isNew() == false && email.equals("") )
		{
			email = (String)sec.getAttribute("email");
		}
	
		sec.setAttribute("email",email);
		
		List<ReservationInfo> reservation = reservationService.getReservationInfoByEmail(email);
		
		if(reservation.isEmpty() == true )
		{
			redirectAttr.addFlashAttribute("message", "메일을 다시 확인하세요.\r\n 등록되지 않은 메일입니다.");
			
			return "redirect:/bookinglogin";
		}
		
		return "myreservation";
	}
	
	@RequestMapping(path="/logout")
	public String myreservation( HttpSession sec){
		System.out.println("ViewPageController : /logout");
		sec.removeAttribute("email");
		return "redirect:/bookinglogin";
	}
	
	

}
