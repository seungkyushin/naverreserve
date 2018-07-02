function initReservationList(response){
	var responseData = JSON.parse(response.responseText);
	var cardItemHeaders = [];
	var status = ["confirmed","finish","cancel"];
	
	status.forEach(function(v,i){
		cardItemHeaders.push(new CarditemHeader(v,i));
	});
	var index = 0 ;
	responseData.items.forEach(function(v,i){
		
		switch( v.status )
		{
			case "confirmed":
				index = 0;
				break;
			case "finish":
				index = 1;
				break;
			case "cancel":
				index = 2;
				break;
				
		}
		cardItemHeaders[index].addCardItem(v);
		
	});
	
	cardItemHeaders.forEach(function(v){
		v.eventSetting();
	});
	
	
	$(".btn_gray").click(function(){
		$(".popup_booking_wrapper").css("display","none");
		console.log("아니오");
	});
	
	
	$(".btn_green").click(function(){
		$(".popup_booking_wrapper").css("display","none");
		console.log("예");
		
		var index = document.querySelector(".pop_tit").dataset.index;
		
		//< 1. 삭제전 옮기기 (0) "confirmed", (1) "finish", (2) "cancel"
		var data = cardItemHeaders[0].removeCardItem(index);
		data.status = "cancel";
		
		cardItemHeaders.forEach(function(v){
			v.printCardItems();
		});
	
		cardItemHeaders[2].addCardItem(data);
		
		cardItemHeaders.forEach(function(v){
			v.eventSetting();
		});
		
		var sendData = {};
		sendData['id'] = data.id;
		sendData['status'] = data.status;
		
		$.ajax({
			url : "./api/transStaus",
			type: "POST",
			data : JSON.stringify(sendData),
			contentType:"application/json",
			success : function(data, status, xhr) {
			      console.log(data);
			    },
			    error: function(jqXHR, textStatus, errorThrown) {
			      alert("error= " + errorThrown);
			    }
		});
		
		
		
	});
	
	
}


function Carditem(data){
	this.objData = data;
	this.printData = "";
	this.makeHTML();
}
Carditem.prototype = {
		
		
		makeHTML:function(){
			if( this.printData == "" ){
				this.printData = this.makeTemplateHTML("#cardItem",this.objData);
			}
			
			document.querySelector(this.returnStatus()).innerHTML += this.printData;
		},
		
		makeTemplateHTML:function(templateName,objData){
			var tempData = this.getMakeTemplateData(objData);
			var templateCardItem = document.querySelector(templateName);
			var paser = Handlebars.compile(templateCardItem.innerText);
				
			
			return paser(tempData);
		},
		getMakeTemplateData : function(obejectData){
			var result = {};
			
			for( var key in obejectData){
				result[key] = obejectData[key];
			}
			
			result.sumPrice = this.transPriceStr(obejectData.sumPrice);
			result.buttonClassName = obejectData.status;
			
			return result;
		},
		
		
		changeStatus : function(status){
			this.objData.status = status;
		},
		returnStatus : function(){
			var resultSrc = "";
			if( this.objData.status == "confirmed" ){
				resultSrc = ".card.confirmed";
			}else if( this.objData.status == "finish") {
				resultSrc = ".card.used";
			}else if(this.objData.status == "cancel"){
				resultSrc = ".card.used.cancel";
			}
			return resultSrc;
		},
		makeButtonChangeHTML : function(index){
			var btn = document.getElementsByClassName("booking_cancel "+this.objData.status);
			var html = "";
			
			if( this.objData.status == "confirmed" ){
				html = '<button class="btn"><span>취소</span></button>';
			}else if( this.objData.status == "finish") {
				html = '<a href="./reviewWrite?productId='+ this.objData.productId 
				+ '&reservationId=' + this.objData.id +'"><button class="btn"><span>예매자 리뷰 남기기</span></button></a>';
			}
			
			btn[index].innerHTML += html;

		},
		addEventButtonCancel : function(index,description,reservationDate){
			
			var btn = document.getElementsByClassName("booking_cancel");
			btn[index].addEventListener("click",function(){
				
				$(".popup_booking_wrapper").css("display","block");
				var html = '<span>'+ description +'</span>'
					+ '<small class="sm">' + reservationDate +'</small>';
				
				$(".pop_tit").html(html);
				
				document.querySelector(".pop_tit").dataset.index = index;
				
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

function CarditemHeader(status,index){
	this.index = index
	this.status = status;
	this.cardItems = [];
	this.makeHTML();
}
CarditemHeader.prototype = {
		
		printCardItems : function(){
			var className = this.getCarditemHeaderClassName(this.status);
			document.querySelector(className).innerHTML = "";
			
			this.makeHTML();
			
			this.cardItems.forEach(function(v){
				v.makeHTML();
			});
		},
		makeHTML : function(){
			var data = this.getMakeTemplateData(this.status);
			var resultHTML = this.makeTemplateHTML("#cardItemHeader",data);
			document.querySelector(this.getCarditemHeaderClassName(this.status)).innerHTML += resultHTML;
		},
		makeTemplateHTML:function(templateName,objData){
			var templateCardItem = document.querySelector(templateName);
			var paser = Handlebars.compile(templateCardItem.innerText);
			return paser(objData);
		},
		addCardItem : function(data){
			this.cardItems.push(new Carditem(data));
		},
		removeCardItem : function(index){
			var result = this.cardItems[index].objData;
			this.cardItems.splice(index,1);
			return result;
		},
		getMakeTemplateData : function(status){
			var result = {};
			if( status == "confirmed" )
			{
				
				result.type  = "check2";
				result.title = "예약 확정";
			}
			else if( status == "finish") 
			{
				result.type  = "check2";
				result.title = "이용 완료";
			}
			else if( status == "cancel")
			{
				result.type  = "cancel";
				result.title = "취소된 예약";
			}
			return result;
		},
		getCarditemHeaderClassName : function(status){
			var result = "";
			if( status == "confirmed" )
			{
				result  = ".card.confirmed";
			}
			else if( status == "finish") 
			{
				result  =  ".card.used";
			}
			else if( status == "cancel")
			{
				result  =  ".card.used.cancel";
			}
			return result;
		},
		eventSetting : function(){
			
			this.cardItems.forEach(function(v,i){
				v.makeButtonChangeHTML(i);
			});
			
			if( this.status == "confirmed")
				{
					this.cardItems.forEach(function(v,i){
						var dec = v.objData.productDescription;
						var res = v.objData.reservationDate
						v.addEventButtonCancel(i,dec,res);
					});
				}
		},
	
}