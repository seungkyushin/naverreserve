package kr.or.connect.naverreserve.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.naverreserve.dto.ReservationUserComment;
import kr.or.connect.naverreserve.service.ReservationService;

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
