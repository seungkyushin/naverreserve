package kr.or.connect.naverreserve.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.naverreserve.dto.Category;
import kr.or.connect.naverreserve.dto.DisplayInfo;
import kr.or.connect.naverreserve.dto.DisplayInfoImage;
import kr.or.connect.naverreserve.dto.FileInfo;
import kr.or.connect.naverreserve.dto.Product;
import kr.or.connect.naverreserve.dto.ProductImage;
import kr.or.connect.naverreserve.dto.ProductPrice;
import kr.or.connect.naverreserve.dto.Promotion;
import kr.or.connect.naverreserve.dto.ReservationInfo;
import kr.or.connect.naverreserve.dto.ReservationInfoPrice;
import kr.or.connect.naverreserve.service.CategoryService;
import kr.or.connect.naverreserve.service.DisplayInfoImageServie;
import kr.or.connect.naverreserve.service.DisplayInfoService;
import kr.or.connect.naverreserve.service.ProductService;
import kr.or.connect.naverreserve.service.PromotionService;
import kr.or.connect.naverreserve.service.ReservationService;

@RestController
@RequestMapping(path="/api")
public class ApiController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PromotionService promotionService;

	@Autowired
	DisplayInfoService displayInfoService;
	
	@Autowired
	DisplayInfoImageServie displayInfoImageServie;
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(path="/categories")
	public Map<String,Object> categories( )  {

		System.out.println("@GetMapping : /categories");

		List<Category> categoriesDto = categoryService.getCategorys();
		Map<String,Object> resultDataMap = new HashMap<>();
		List<Object> resultCategoryList = new ArrayList<>();
				
		for(Category data : categoriesDto)
		{
				Map<String, Object> addMap = new HashMap<>();
			
				addMap.put("id", data.getId());
				addMap.put("name", data.getName());
				addMap.put("count", productService.getCategoriesCount(data.getId()));
				resultCategoryList.add(addMap);
		
		}

		resultDataMap.put("items", resultCategoryList);
		resultDataMap.put("size", resultCategoryList.size());
		return resultDataMap;
	}
	
	@GetMapping(path="/promotions")	
	public Map<String,Object> promotion() {
		System.out.println("@GetMapping : /promotions");
		
		List<Promotion> promotionsDto = promotionService.getPromotionAll();
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Object> promotionList = new ArrayList<>();
		
		for(Promotion data : promotionsDto)
		{
			Product productItem = productService.getProductById(data.getProductId());
			ProductImage productImageItem = productService.getProductImage(data.getProductId(), "ma");
			Category categoryItem = categoryService.getCategory(productItem.getCategory_id());
			Integer ProductImageId = productService.getFileInfoById(productImageItem.getFileId()).getId();

					Map<String, Object> addMap = new HashMap<>();
					
					addMap.put("id", productItem.getId());
					addMap.put("productId", data.getProductId());
					addMap.put("categoryId", productItem.getCategory_id());
					addMap.put("description", productItem.getDescription());
					addMap.put("categoryName", categoryItem.getName());
					addMap.put("productImageId", ProductImageId);
					addMap.put("imageSrc", productService.getSaveFileNameById(ProductImageId));
					
					promotionList.add(addMap);
				
		}
		
		
		resultMap.put("items", promotionList);
		resultMap.put("size", promotionList.size());
		
		return resultMap;
	}
	@GetMapping(path="/products")
	public Map<String,Object> getProducts(@RequestParam(name="categoryId",required=true,defaultValue="0") int categoryId,
											@RequestParam(name="start",required=true,defaultValue="0") int start) {
		System.out.println("@GetMapping : /products?categoryId=" +categoryId+ "&start="+start);
		
		//< 이미지 경로, 제목, 내용
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//< 모든 판매 리스트 
		List<Product> products = null;
		
		if( categoryId == 0)
		{
			products = productService.getProducts(start);
		}
		else
		{
			products = productService.getProductsByCategoryId(start, categoryId,false);
		}
		
		List<Object> productsList = new ArrayList<Object>();
		//< 이미지 
		List<String> srcList = new ArrayList<String>();
		
		for( Product product : products)
		{
			Map<String,Object> item = new HashMap<String,Object>();
			
			
			item.put("content", product.getContent());
			item.put("description", product.getDescription());
			item.put("productId", product.getId());
		
			
			DisplayInfo DisplayInfoDto = displayInfoService.getDisplayInfoByProductId(product.getId());
			item.put("placeName", DisplayInfoDto.getPlaceName());
			
			productsList.add(item);
			
			ProductImage productImageItem = productService.getProductImage(product.getId(), "th");
			String src = productService.getSaveFileNameById(productImageItem.getFileId());
			item.put("imageSrc", "/naverreserve/" +	src);
			
		}
		
		resultMap.put("totalCount",products.size());
		resultMap.put("productsList",productsList);
		
		return resultMap;
	}
	@GetMapping(path="/products/{displayInfoId}")
	public Map<String,Object> productsid(@PathVariable("displayInfoId") int displayInfoId) {
		System.out.println("@GetMapping : /products"+ "?displayInfoId=" + displayInfoId);
		
		//< 결과값
		Map<String,Object> resultMap = new HashMap<String,Object>();

		//< prdouct
		Product ProductDto = productService.getProductById(displayInfoId);
		Category CategoryDto = categoryService.getCategory(ProductDto.getCategory_id());
		DisplayInfo DisplayInfoDto = displayInfoService.getDisplayInfoByProductId(ProductDto.getId());
		
		Map<String,Object> prdouct = new HashMap<String,Object>();
		prdouct.put("id", ProductDto.getId());
		prdouct.put("categoryId", ProductDto.getCategory_id());
		prdouct.put("displayInfoId", DisplayInfoDto.getId());
		prdouct.put("name", CategoryDto.getName());
		prdouct.put("discription", ProductDto.getDescription());
		prdouct.put("content", ProductDto.getContent());
		prdouct.put("event", ProductDto.getEvent());
		prdouct.put("openingHours", DisplayInfoDto.getOpeningHours());
		prdouct.put("placeName", DisplayInfoDto.getPlaceName());
		prdouct.put("placeLot", DisplayInfoDto.getPlaceLot());
		prdouct.put("placeStreet", DisplayInfoDto.getPlaceStreet());
		prdouct.put("tel", DisplayInfoDto.getTel());
		prdouct.put("email", DisplayInfoDto.getEmail());
		prdouct.put("createDate", DisplayInfoDto.getCreateDate());
		prdouct.put("modefiyDate", DisplayInfoDto.getModifyDate());


		//< productImage
		List<ProductImage> productImageDto = productService.getProductImagesById(ProductDto.getId());
		List<Object> productImageList = new ArrayList<Object>();
		
		for(ProductImage pi : productImageDto)
		{
			
			if(pi.getType().equals("th")) continue;
						
			FileInfo fileInfoDto = productService.getFileInfoById(pi.getFileId());
			
			Map<String,Object> productImage = new HashMap<String,Object>();
			productImage.put("productId", ProductDto.getId());
			productImage.put("productImageId", pi.getId());
			productImage.put("type", pi.getType());
			productImage.put("fileInfoId", fileInfoDto.getId());
			productImage.put("fileName", fileInfoDto.getFileName());
			productImage.put("saveFileName", fileInfoDto.getSaveFileName());
			productImage.put("contentType", fileInfoDto.getContentType());
			productImage.put("deleteFlag", fileInfoDto.isDeleteFlag());
			productImage.put("createDate", fileInfoDto.getCreateDate());
			productImage.put("modifyDate", fileInfoDto.getModifyDate());
			
			productImageList.add(productImage);
		}
		
		
		//< displayInfoImages
		DisplayInfoImage displayInfoImageDto = displayInfoImageServie.getDisplayInfoImageByDisplayInfoId(DisplayInfoDto.getId());
		FileInfo fileInfoDto = productService.getFileInfoById(displayInfoImageDto.getFileId());
		
		Map<String,Object> displayInfoImages = new HashMap<String,Object>();
		displayInfoImages.put("id", ProductDto.getId());
		displayInfoImages.put("displayInfoId", DisplayInfoDto.getId());
		displayInfoImages.put("fileId", fileInfoDto.getId());
		displayInfoImages.put("fileName", fileInfoDto.getFileName());
		displayInfoImages.put("saveFileName", fileInfoDto.getSaveFileName());
		displayInfoImages.put("contentType", fileInfoDto.getContentType());
		displayInfoImages.put("deleteFlag", fileInfoDto.isDeleteFlag());
		displayInfoImages.put("createDate", fileInfoDto.getCreateDate());
		displayInfoImages.put("modifyDate", fileInfoDto.getModifyDate());
		
		
/*		//< comments
		ReservationUserComment reservationUserCommentDto
		= reservationUserCommentService.getReservationUserCommentByProductId(productImageDto.get(0).getId());
		
		//< reservatuibUserCommentImages
		
		ReservationUserCommentImage reservationUserCommentImageDto
		= reservationUserCommentImageService.getReservationUserCommentImageById(reservationUserCommentDto.getProductId());
		*/
		resultMap.put("product",prdouct);
		resultMap.put("productImages",productImageList);
		resultMap.put("displayInfoImages",displayInfoImages);
		//resultMap.put("comments",reservationUserCommentDto);
		//resultMap.put("reservatuibUserCommentImages",srcList);
		resultMap.put("avgScore",3.0);
		//resultMap.put("productPrice",srcList);
		
		
		return resultMap;
	}

	
	
	
	@PostMapping(path="/reservation")
	public Map<String,Object> reservation(	@RequestBody Map<String,Object> param  )	 {
		System.out.println("ApiController : /reservation");
		Map<String,Object> resultMap = new HashMap<String,Object>();
			
		
		ReservationInfo reservationInfoDto = new ReservationInfo();
		List<Map<String,Object>> test = (List<Map<String,Object>>)param.get("prices");
				
		
		//< 예약 DB 생성 
		reservationInfoDto.setProductId(Integer.parseInt((String)param.get("productId")));
		reservationInfoDto.setReservationEmail((String)param.get("reservationEmail"));
		reservationInfoDto.setReservationName((String)param.get("reservationName"));
		reservationInfoDto.setReservationTel((String)param.get("reservationTel"));
		Date date = new Date();
		reservationInfoDto.setCreateDate(date);
		reservationInfoDto.setModifyDate(date);

		reservationService.insertReservationInfo(reservationInfoDto);
		
		ReservationInfo ri = reservationService.getReservationInfoById(reservationInfoDto.getProductId());

		for(Map<String,Object> data : test)
		{
			System.out.println(data);
			ReservationInfoPrice reservationInfoPriceDto = new ReservationInfoPrice();
			reservationInfoPriceDto.setReservationInfoId(ri.getId());
			
			int id = Integer.parseInt(data.get("productPriceId").toString());
			int count = Integer.parseInt(data.get("count").toString());
			System.out.println(id);
			System.out.println(count);
			reservationInfoPriceDto.setProductPriceId(id);
			reservationInfoPriceDto.setCount(count);
		
			reservationService.insertReservationInfoPrice(reservationInfoPriceDto);
			
		}
		
		resultMap.put("result", "ok");
		
		return resultMap;
	}
	@GetMapping(path="/reservationInfos/{id}")
	public Map<String,Object> reservationInfos(@PathVariable(name="id") int id){
		System.out.println("ApiController : /reservationInfos");
		//< 결과값
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<ProductPrice> price =  productService.getProductPricesByProductId(id);
		resultMap.put("price", price);
		return resultMap;
	}
	
	@GetMapping(path="/bookingList")
	public Map<String,Object> reservationInfos(HttpServletRequest req){
		System.out.println("ApiController : /bookingList");
		String email = req.getParameter("email");
		//< 결과값
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Object> rsultList = new ArrayList<Object>();
		List<ReservationInfo> reservationInfo =  reservationService.getReservationInfoByEmail(email);
		
		for( ReservationInfo data : reservationInfo) {
			Map<String,Object> item = new HashMap<String,Object>();
			
			item.put("id", data.getId());
			item.put("productId", data.getProductId());
			
			DisplayInfo displayInfodto = displayInfoService.getDisplayInfoByProductId(data.getProductId());
			Product productdto = productService.getProductById(data.getProductId());
			Category categorydto = categoryService.getCategory(productdto.getCategory_id());
			
			item.put("productCategory", 	categorydto.getName());
			item.put("productDescription", 	productdto.getDescription());
			item.put("productTel", 	displayInfodto.getTel());;
			item.put("placeStreet", 	displayInfodto.getPlaceStreet());
			
			item.put("reservationName", 	data.getReservationName());
			item.put("reservationTel", 	data.getReservationTel());
			item.put("reservationEmail", 	data.getReservationEmail());
			item.put("status", 	data.getStatus());
			
			List<ReservationInfoPrice> reservationInfoPricedto 
			= reservationService.getReservationInfoPrice(data.getId());
			
			int sumPrice = 0;
			for( ReservationInfoPrice  reservationprice : reservationInfoPricedto) {
				int price = productService.getProductPricePrice(reservationprice.getProductPriceId());
				int count =	reservationprice.getCount();
				sumPrice += price * count;
				
				item.put("sumPrice", 	sumPrice);
				item.put("reservationDate", data.getReservationDate());
				item.put("createDate", data.getCreateDate());
				item.put("modifyDate", data.getModifyDate());
				
				
			}
			
			rsultList.add(item);

		}
		resultMap.put("items", rsultList);
		
		return resultMap;
	}
	
}
