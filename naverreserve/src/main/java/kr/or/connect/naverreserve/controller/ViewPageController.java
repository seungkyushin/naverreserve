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
	

	@PostMapping(path="/reservation")
	public String reservation(@ModelAttribute ReservationInfo data,
			HttpServletRequest req) {
		System.out.println("ViewPageController : /reserve");
		
		reservationService.insertReservationInfo(data);
		
		
		ReservationInfo ri = reservationService.getReservationInfoById(data.getProductId());
		List<ProductPrice> price =  productService.getProductPricesById(data.getProductId());
		
		String test = req.getParameter("count");
		String[] srcList = test.split(":");
		int index = 0;
		
		System.out.println(price);
		
		for( ProductPrice d : price )
		{
			ReservationInfoPrice rip = new ReservationInfoPrice();
			rip.setReservationInfoId(ri.getId());
			rip.setProductPriceId(d.getId());
			rip.setCount(Integer.parseInt(srcList[index]));
			index++;
			reservationService.insertReservationInfoPrice(rip);
		
		}
	
		return "mainpage";
	}
	
	@GetMapping(path="/bookinglogin")
	public String bookinglogin(){
		System.out.println("ViewPageController : /bookinglogin");
	
		return "bookinglogin";
	}
	
	
	@PostMapping(path="/myreservation")
	public String myreservation( @ModelAttribute("resrv_email") String email 
			,HttpSession sec
			,RedirectAttributes redirectAttr
			,ModelAndView model){
		System.out.println("ViewPageController : /myreservation");
		
		System.out.println(email);
		
		List<ReservationInfo> reservation = reservationService.getReservationInfoByEmail(email);
		
		if(reservation.isEmpty() == true )
		{
			redirectAttr.addAttribute("message", "해당 이메일의 예약 내역이 존재하지 않습니다.");
			
			return "redirect:/bookinglogin";
		}
		
		sec.setAttribute("email",email);
		
		return "myreservation";
	}
	
	
	
	

}
