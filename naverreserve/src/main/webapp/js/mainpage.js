function ShowProductItems(){
	var activeCategory = document.querySelector(".anchor.active");
	var courrentCategoryId = activeCategory.parentElement.dataset.category;
	var productElements = document.getElementsByClassName("lst_event_box");
	var productCount = 0;
	
	var moreBtn = document.querySelector(".btn");
	
	//< 현재 상품 리스트 개수 구하기
	for( var i = 0; i < 2; i++)
		productCount += productElements[i].children.length;
	
	//< 최개 개수랑 현재 개수랑 같으면 더보기 버튼을 숨기고 서버에 요청하지 않는다.
	 var productCountElement = document.querySelector(".event_lst_txt");
	if( productCount+'개' ==  productCountElement.children[0].innerHTML)		{
		
			moreBtn.hidden = true;
		}
	else{
		moreBtn.hidden = false;
		var url = "http://localhost:8080/naverreserve/api/products?categoryId="+ courrentCategoryId +"&start=" + productCount;
		console.log(url);
		myAjax("GET",url,setProductItem);
	}
			

}
function initCategory(){
	
	myAjax("GET","http://localhost:8080/naverreserve/api/categories",categoryName);

	var categoryElements = document.querySelector(".event_tab_lst");
	categoryClicked(categoryElements);
	
}
function initPromotion(){
	myAjax("GET","http://localhost:8080/naverreserve/api/promotions",setPromotions);
}
function setPromotionsTemp(response)
{
	var responseData = JSON.parse(response.responseText);
	var PromotionElements = document.querySelector(".visual_img");
	
	var promotionItem = document.querySelector("#promotionItem");
	var parser = Handlebars.compile(promotionItem.innerText);
	
	var testHTML = "";
	responseData.items.forEach(function(e){
		var data = {
				imageSrc : "/naverreserve/" + e.imageSrc,
				tit : "",
				adr : "",
				dsc : ""
			};
			
			var resultHTML = parser(data);
			testHTML += resultHTML;
			PromotionElements.innerHTML += resultHTML;
	});

	 var count = 0;
	var animationfunc = setInterval(function(){
			var test = PromotionElements.children[count];
			
			var widthSize = test.offsetWidth;
			if(count != 0)
				PromotionElements.children[count-1].style.cssText += "display:none";
			test.style.transform +="translateX(-" + widthSize +"px)";
		 	count++;
		 	if( PromotionElements.childElementCount-1 < count)
		 		{
		 			count = 0;
		 		}
		 	
		 	if(count == 5)
		 		{
		 			//PromotionElements.children[0].style.cssText += "display:none";
		 			PromotionElements.children[0].style.transform ="";
		 		}
		
	  },2000);   


}


function setPromotions(response){
	var responseData = JSON.parse(response.responseText);
	var dataSize = responseData.size;
	var PromotionElements = document.querySelector(".visual_img");
	
	var arrayPromotionSrc = [];
	
	//< Ajax로 데이터를 받아와 hmml 수정
	for(var index = 0; index < dataSize; index++)
		{
			var html = '<li><img src=/naverreserve/' 
				+ responseData.items[index].imageSrc  + ' class="promotion_img"></li>';
			
			//< html에 Element를 정적으로 만들어 놓지 않으면 DOM조작시 CSS옵션이 잘 먹지않아
			//< 이코드는 정적으로 Element를 만들어놓고 다시 동적으로 생성하였다.
			if(index == 0)
			{
				PromotionElements.innerHTML = html;
			}				
			else if( index < 3)
				PromotionElements.innerHTML += html;

			var tempSrc = '<img src="/naverreserve/' 
				+  responseData.items[index].imageSrc + ' class="promotion_img">';
			arrayPromotionSrc[index] = html;
		}
	
	
	  var promotionImg = document.getElementsByClassName("promotion_img");
	  var isanimation = false;
	  var move = 414;
	  var showIndexMax = 3;
	  var promotionImgIndex = 0;
	  var animationfunc = setInterval(function(){
			 
		  if( isanimation )
		  {
			  var tempsrc = arrayPromotionSrc[0];
			  arrayPromotionSrc.shift();
			  arrayPromotionSrc.push(tempsrc);
			  
			  var showImageSrc="";
			  for( var index = 0; index < showIndexMax; index++ )
			  {
				  showImageSrc += arrayPromotionSrc[index];
			  }
			  
			  PromotionElements.innerHTML = showImageSrc;
			  isanimation = false;
		  }
		  else
			  {
			 	 for( var index = 0; index < showIndexMax; index++ )
			 	 {
			  		promotionImg[index].style.transform += "translateX(-"+ move +"px)";
		 	 	 }
			  	isanimation = true;
			  }
	  },1000);  
	
}
function initProduct(){
	myAjax("GET","http://localhost:8080/naverreserve/api/products",setProductItem);
}
function setProductItem(response){
	
	var responseData = JSON.parse(response.responseText);
	var ProductPreviewParent = document.getElementsByClassName("lst_event_box");


	var templateList = document.querySelector("#itemList");
	var parser = Handlebars.compile(templateList.innerText);
	
	 for(var i = 0; i < responseData.totalCount; i++)
	 {
		 
			var data = {
					displayInfoId :responseData.productsList[i].productId,
					description : responseData.productsList[i].description,
					imgPath : responseData.productsList[i].imageSrc,
					placeName : responseData.productsList[i].placeName,
					content : responseData.productsList[i].content,
					};

			
			 var resultHTML = parser(data);
		 ProductPreviewParent[i%2].innerHTML += resultHTML;
	 }
	
}

// 카테고리 별 이름 설정
function categoryName(response){
	var responseData = JSON.parse(response.responseText);
	var dataSize = responseData.size;
	var categoryElements = document.querySelector(".event_tab_lst");
	 
	//< 카테고리 별로 상품 개수 구하기
	categoryCount(response);
	
	var template = document.querySelector("#categoryList");
	var parser = Handlebars.compile(template.innerHTML);
	var data = {};
	
	for(var index = 0; index < dataSize; index++ )
		{
			data = {
					id : responseData.items[index].id,
					name : responseData.items[index].name
			}; 
		
			 var html = parser(data);
							 
			 categoryElements.innerHTML += html;
		}
}

//< 카테고리 개수 구하기 
function categoryCount(response){
	var responseData = JSON.parse(response.responseText);
	var productCountElement = document.querySelector(".event_lst_txt");
	var activeCategory = document.querySelector(".anchor.active");
	var courrentCategoryId = activeCategory.parentElement.dataset.category;
	var viewCount = 0;
	
	//< courrentCategoryId 0은 전체 
	 if( courrentCategoryId == 0)
	 {
		 for(var i =0 ;i < responseData.size; i++)
			 {
			 	viewCount += responseData.items[i].count;
			 }
		 
	 }else
	 {
		 viewCount = responseData.items[courrentCategoryId-1].count
	 }
	
 	 var html =  viewCount + '개';
	 productCountElement.firstElementChild.innerHTML = html;
	
}


function categoryClicked(Element){
	Element.addEventListener("click",function(e){

		var Targettext = event.target.innerText;
		var childCount = Element.childElementCount;
		var click = 0;
		var clickedName = [];
	 
		if( Targettext.length > 5 ) return; 
		
		for(var i = 0; i < childCount; i++)
			{	
				clickedName = Element.children[i].firstElementChild.firstElementChild.innerText;
				
				if( clickedName == Targettext )
					Element.children[i].firstElementChild.className = "anchor active";
				else
					Element.children[i].firstElementChild.className = "anchor";
			
			}
	
		
		//< product HTML 초기화
		 var ProductPreviewParent = document.getElementsByClassName("lst_event_box");
		 ProductPreviewParent[0].innerHTML = "";
	 	 ProductPreviewParent[1].innerHTML = "";
	 	 ShowProductItems();
	 	 
	 	myAjax("GET","http://localhost:8080/naverreserve/api/categories",categoryCount);
	 	 
	});
}