package kr.or.connect.naverreserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.naverreserve.dao.FileInfoDao;
import kr.or.connect.naverreserve.dao.ProductDao;
import kr.or.connect.naverreserve.dao.ProductImageDao;
import kr.or.connect.naverreserve.dao.ProductPriceDao;
import kr.or.connect.naverreserve.dto.FileInfo;
import kr.or.connect.naverreserve.dto.Product;
import kr.or.connect.naverreserve.dto.ProductImage;
import kr.or.connect.naverreserve.dto.ProductPrice;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductImageDao productImageDao;
	
	@Autowired
	ProductPriceDao	productPriceDao;
			
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public List<Product> getProducts() {
		return productDao.selectAll();
	}

	@Override
	public Integer getCategoriesCount(int categoryId) {
		
		return productDao.selectCategroyCount(categoryId);
	}

	@Override
	public Integer getCategoriesAllCount() {
	
		 return productDao.selectAllCount();
	}

	@Override
	public Product getProductById(int id) {
		return productDao.selectById(id);

	}

	@Override
	public List<Product> getProducts(int start) {
		
		return productDao.selectLimit(start, SHOW_LIMIT);
	}

	@Override
	public List<Product> getProductsByCategoryId(int start, int categoryId,boolean isAll) {
		
		if( isAll == true )
			return productDao.selectByCategroyId(categoryId);
		else
			return productDao.selectByCategroyId(start,SHOW_LIMIT,categoryId);
	}
	
	
	//productImage
	@Override
	public List<ProductImage> getProductImages() {
		return productImageDao.selectAll();
	}

	@Override
	public ProductImage getProductImage(int productId,String fileType) {
		ProductImage result = null;
		List<ProductImage> param = productImageDao.selectByproductId(productId);
		
		for( ProductImage data : param)
		{
			if( data.getType().equals(fileType))
			{
				result = data;
				break;
			}
		}
		return result;
	}

	@Override
	public List<ProductImage> getProductImagesById(int productId) {
		return productImageDao.selectByproductId(productId);
	}

	
	//productPrice
	@Override
	public List<ProductPrice> getProductPrices() {
		return productPriceDao.selectAll();
	}

	@Override
	public List<ProductPrice> getProductPricesById(int id) {
		return productPriceDao.selectById(id);
	}

	@Override
	public int getProductPriceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	//fileInfo
	@Override
	public List<FileInfo> getFileInfoAll() {
		return fileInfoDao.selectAll();
	}

	@Override
	public FileInfo getFileInfoById(int id) {
		return fileInfoDao.selectByproductImageId(id);
	}

	@Override
	public String getSaveFileNameById(int id) {
		return fileInfoDao.selectByproductImageId(id).getSaveFileName();
	}
}
