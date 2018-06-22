package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.Promotion;

public interface PromotionService {
	
	public List<Promotion>getPromotionAll();
	public int getPromotionCount();

}
