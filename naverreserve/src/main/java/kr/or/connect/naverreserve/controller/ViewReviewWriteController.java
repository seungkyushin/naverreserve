package kr.or.connect.naverreserve.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewReviewWriteController {

	@GetMapping(path="/reviewWrite")
	public String reviewWritePage(@RequestParam(name="productId",required=true) int productId,
			@RequestParam(name="reservationId",required=true) int reservationId
			, HttpServletRequest req ) {
		
		System.out.println(productId);
		System.out.println(reservationId);
		
		req.setAttribute("productId", productId);
		req.setAttribute("reservationId", reservationId);
		
		return  "reviewWrite";
	}
	

}
