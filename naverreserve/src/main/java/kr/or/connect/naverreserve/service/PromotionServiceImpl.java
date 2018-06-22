package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.naverreserve.dao.PromotionDao;
import kr.or.connect.naverreserve.dto.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	@Transactional
	public List<Promotion> getPromotionAll() {
		return promotionDao.selectAll();
	}

	@Override
	@Transactional
	public int getPromotionCount() {
		return promotionDao.selectCount();
	}

}
