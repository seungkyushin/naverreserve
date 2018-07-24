function reservationBtn(id){
	var rvBtn = document.querySelector(".bk_btn");
	
	rvBtn.addEventListener("click",function(){
		location.href = "./reserve?id=" + id;
	});
}

function SetInfoTab(){
var infoTab = document.querySelector(".info_tab_lst");
	
	infoTab.addEventListener("click",function(e){
		for(var i=0 ; i < infoTab.childElementCount; i++)
			{
				if( e.target.innerText == infoTab.children[i].innerText)
				{
					infoTab.children[i].firstElementChild.className = "anchor active";

					if( e.target.innerText == "오시는길")
						{
							var detailArea = document.querySelector(".detail_area_wrap");
							detailArea.className ="detail_area_wrap hide";
							
							var detailLocation = document.querySelector(".detail_location");
							detailLocation.className ="detail_location";
						}
					else if( e.target.innerText == "상세정보")
						{
							var detailArea = document.querySelector(".detail_area_wrap");
							detailArea.className ="detail_area_wrap";
							
							var detailLocation = document.querySelector(".detail_location");
							detailLocation.className ="detail_location hide";
						}
					
					
				}
				else
					infoTab.children[i].firstElementChild.className = "anchor";
			}
		
			
	});
}

function setProductDetail(response){
	var responseData = JSON.parse(response.responseText);

	var template = document.querySelector("#template_item_img");
	var parser = Handlebars.compile(template.innerText); 
	var dataSize = responseData.productImages.length;
	
	//< 이미지 개수 설정
	var num = document.querySelector(".num");
	var numOff = document.querySelector(".num.off");
	num.innerHTML = 1;
	numOff.children[0].innerHTML=dataSize;

	//< 이미지 개수가 1개일경우 화살표 UI 숨김
	var btn_prev = document.querySelector(".prev");
	var btn_nxt = document.querySelector(".nxt");
	if(dataSize == 1)
	{
		btn_prev.className ="prev hide";
		btn_nxt.className ="nxt hide";
	}
                       
	
	//< 이미지 및 세부 정보 설정 
	var visualImgGroup = document.querySelector(".visual_img.detail_swipe");
	for(var i = 0; i < dataSize; i++)
		{
			var data = {
						discription : responseData.product.discription,	
						saveFileName : responseData.productImages[i].saveFileName
			};
			
			var Resulthtml = parser(data);
			visualImgGroup.innerHTML += Resulthtml;
		}

	//< 이벤트 설정
	var eventInfo = document.querySelector(".event_info");
	eventInfo.firstElementChild.innerText = responseData.product.event;
	
		
	//< VisuaImgGroup아래의 세부 소개 설정
	var displayDicription = document.querySelector(".store_details.close3");
		displayDicription.children[0].innerHTML = responseData.product.content;
	
	//< 리뷰 
		SetReviewElements(responseData);
		
	//< 세부 정보 소개 설정 
	var detailInfo = document.querySelector(".detail_info_group");
	var detailInfoIntroduce= detailInfo.querySelector(".in_dsc");
	detailInfoIntroduce.innerHTML = responseData.product.content;

	
	
	
	//< 오는길 정보 설정 
	var detailLocation = document.querySelector(".detail_location");
	var mapImg = detailLocation.querySelector(".store_map.img_thumb");
	var discription = detailLocation.querySelector(".store_name");
	
	var placeStreet = detailLocation.querySelector(".store_addr.store_addr_bold");
	var placeStreetDetail = detailLocation.querySelector(".addr_old_detail");
	var placeName = detailLocation.querySelector(".store_addr.addr_detail");
	var tel = detailLocation.querySelector(".store_tel");
	 
	mapImg.src = "/naverreserve/" + responseData.displayInfoImages.saveFileName;
	discription.innerText = responseData.product.discription;
	placeStreet.innerText = responseData.product.placeStreet;
	placeStreetDetail.innerText = responseData.product.placeStreet;
	placeName.innerText = responseData.product.placeName;
	tel.innerText = responseData.product.tel;
	tel.href = "tel:"+responseData.product.tel;

}

function SetReviewElements(responseData){
	var maxCount = responseData.comments.length;
	if( maxCount > 0){
		document.querySelector(".join_count").innerHTML = '<em class="green">' + maxCount +'건</em> 등록';
		
		var review = [];
		var avgScore = 0;
		for(var i = 0; i < maxCount; i++)
		{
			review[i] = new reviewItem(responseData.comments[i]);
			avgScore += responseData.comments[i].score;
		}
		avgScore /= maxCount;
		avgScore = avgScore.toFixed(1);
		var persent = (100/5) * avgScore;
		
		document.querySelector(".graph_value").style.width = persent + "%";
		document.querySelector(".text_value").innerHTML = '<span>'+ avgScore + '</span> <em	class="total">5.0</em>';
	}
}

function reviewItem(data){
	this.makeHTML(data);
}
reviewItem.prototype = {
		makeHTML : function(data){
			
			if( data.reservationUserCommentImage.saveFileName != null ){
				data.display = "";
				data.imageSrc = "./"+data.reservationUserCommentImage.saveFileName;
			}
			else{
				data.display = "none";
				data.imageSrc = "#";
			}
			

			document.querySelector(".list_short_review").innerHTML
			+= this.makeTemlateHTML("#listItem_review",data);
	
		},

		makeTemlateHTML : function(templateName,data){
			var tempData = data;
			var templateItem = document.querySelector(templateName);
			var paser = Handlebars.compile(templateItem.innerText);
			return paser(tempData);
		}
}
