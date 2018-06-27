package kr.or.connect.naverreserve.service;

import java.util.List;

import kr.or.connect.naverreserve.dto.FileInfo;
import kr.or.connect.naverreserve.dto.Product;
import kr.or.connect.naverreserve.dto.ProductImage;
import kr.or.connect.naverreserve.dto.ProductPrice;

public interface ProductService {

	public static final Integer SHOW_LIMIT = 4;
	public List<Product> getProducts();
	public List<Product> getProducts(int start);
	public List<Product> getProductsByCategoryId(int start, int categoryId,boolean isAll);
	public Product getProductById(int id);
	public Integer getCategoriesCount(int categoryId);
	public Integer getCategoriesAllCount();
	
	//productImage
	public List<ProductImage> getProductImages();
	public List<ProductImage> getProductImagesById(int productId);
	public ProductImage getProductImage(int productId,String fileType);
	
	//productPrice
	public List<ProductPrice> getProductPrices();
	public List<ProductPrice> getProductPricesByProductId(int id);
	public int getProductPricePrice(int id);
	
	//fileInfo
	public List<FileInfo> getFileInfoAll();
	public FileInfo getFileInfoById(int id);
	public String getSaveFileNameById(int id);
	
}
