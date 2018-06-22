package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.CategoryDao;
import kr.or.connect.naverreserve.dto.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<Category> getCategorys() {
		return categoryDao.selectAll();
	}

	@Override
	public int getCategoryCount() {
		return categoryDao.selectCount();
	}

	@Override
	public Category getCategory(int id) {
		
		Category result = null;
		List<Category> param = categoryDao.selectById(id);
		
	
		if( param.isEmpty() == false)
		{
			for( Category data : param)
			{
				result = data;
				return result ;
			}
		}
		
		return result;
		
	}

}
