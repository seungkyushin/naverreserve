function initReservationList(response){
	var responseData = JSON.parse(response.responseText);
	
	responseData.items.forEach(function(v){
		var carditem = new Carditem(v);
	})
	
	
	$(".btn_gray").click(function(){
		$(".popup_booking_wrapper").css("display","none");
		console.log("아니오");
	});
	
	$(".btn_green").click(function(){
		$(".popup_booking_wrapper").css("display","none");
		console.log("예");
	});
	
}


function Carditem(data){
	this.objData = data;
	this.makeHTML(data);
}
Carditem.prototype = {
		
		makeHTML:function(responeData){
			var resultHTML = this.makeTemplateHTML("#cardItem",responeData);
			document.querySelector(".card.confirmed").innerHTML += resultHTML;
			
			
			$(".booking_cancel").click(function(){
					$(".popup_booking_wrapper").css("display","block");
					
					document.querySelector(".pop_tit").innerHTML =" <span>["+ this.objData.productCategory + "] " 
					+ this.objData.productDescription + "</span> <small class='sm'>" +
					this.objData.reservationDate +"</small>";
					
								
				}.bind(this));
			
		
			
			
			
		},
		makeTemplateHTML:function(templateName,objData){
			var templateCardItem = document.querySelector(templateName);
			var paser = Handlebars.compile(templateCardItem.innerText);
			objData.sumPrice = this.transPriceStr(objData.sumPrice);
			return paser(objData);
		},
		
		changeButton : function(){
			var cancelBtn = document.querySelector(".booking_cancel");
			var parent = document.querySelector(".card_item").parentElement;
			
			parent.classList.forEach(function(v){ 
				if( "confirmed" == v ){
					cancelBtn.innerHTML = '<button class="btn"><span>취소</span></button>';
				}else if( "cancel" == v ){
					cancelBtn.innerHTML = '<a href="../naverreserve/reviewWrite"><button class="btn"><span>예매자 리뷰 남기기</span></button></a>';
				}else{
					cancelBtn.innerHTML = '';
				}

			});
		},
		transPriceStr : function(price){
			var tempNum = price;
			var priceAry = [];
			var count = 0;
			var result = "";
			
			if ( tempNum == 0 ) return "0";
			
			while(tempNum > 0)
			{
				priceAry.push(tempNum%10);
				tempNum /= 10;
				tempNum = parseInt(tempNum);
				count++;
				
				if( count%3 == 0 && tempNum > 0 )
				{
					priceAry.push(",");
				}
			}
			
			
			priceAry.reverse();
			priceAry.forEach(function(v,i){
				result += v;
			});	
			return result;
			
		}
		
}