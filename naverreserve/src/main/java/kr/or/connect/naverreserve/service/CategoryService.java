package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.Category;

public interface CategoryService {
	
	public List<Category> getCategorys();
	public Category getCategory(int id);
	public int getCategoryCount();
	
}
