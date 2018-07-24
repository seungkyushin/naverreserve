function setRating(){
	
	document.querySelector(".rating").addEventListener("click",function(event){
		var clickedElement = event.target;
		var count = parseInt(clickedElement.value);
		
		if(!count) return;
		
		var ratingList = document.getElementsByClassName("rating_rdo");
		
		for(var i=0; i<ratingList.length; i++)
		{
			if( i < count)
				ratingList[i].checked = true;
			else
				ratingList[i].checked = false;
		}

		var starCount = document.querySelector(".star_rank");
		starCount.innerText = count;
		starCount.classList.remove("gray_star");
		

	});
}

function reviewTextArea(){
	
	var content = document.querySelector(".review_contents");
	
	content.addEventListener("click",function(event){
		$(".review_write_info").css("display","none");
		document.querySelector(".review_textarea").focus();
		
		reviewTextCount();
	});
	
}

function reviewTextCount(){
	var guideReview = document.querySelector(".guide_review");
	
	$('.review_textarea').keyup(function (e){
	    var content = $(this).val();
	    guideReview.innerText =  content.length + "/400 (최소5자이상)";
	});

}
function isLimitReviewLength(){
	var value = $('.review_textarea').val();
	
	//< 최소 5자리 최대 400자리
	if( value < 5 || value > 400){
		return false;
	}
	
	return true;
}

function sendReview(){
	var submit = document.querySelector(".box_bk_btn");
	submit.addEventListener("click",function(event){
		
		if( isLimitReviewLength() == false ){
			alert("리뷰는 최소 5자 이상 최대 400자 이내로 작성해주시길 바랍니다.");
			return;
		}
		
		var sendData = {};
		
		document.querySelector("#score").value = document.querySelector(".star_rank").innerText;
		document.querySelector("#comment").value = document.querySelector(".review_textarea").value;

		
		console.log(JSON.stringify(document.querySelector("#score").value));
		console.log(JSON.stringify(document.querySelector("#comment").value));
		
		var test = document.getElementById('formdata');
		var formData = new FormData(test);
	
		$.ajax({          
			url : "api/reservationUserComments",
			processData : false,
			contentType : false,
			data : formData,
			type : "POST",
			error : function(){
				alert('통신을 실패했습니다. 다시 접속해 주세요.');
			},

			success : successResponse
			

		});
		
				
	});
} 
function successResponse(response)
{
	location.href="./detail?id=" + response.productId;
}
function reviewImage(){
	const elImage = document.querySelector("#reviewImageFileOpenInput");

	
	elImage.addEventListener("change", (evt) => {
		const image = evt.target.files[0];
		if(!validImageType(image)) { 
				console.warn("invalide image file type");
				return;
			}
		
		var reviewPhoto = new reviewPhotosItem(image.fileName);
        const elImage = document.querySelector(".item_thumb");
        elImage.src = window.URL.createObjectURL(image);
        
		
		
		
		});
}
function validImageType(image){
	const result = ([ 'image/jpeg',
		  'image/png',
		  'image/jpg' ].indexOf(image.type) > -1);
return result;
	
}


function reviewPhotosItem(fileName){
	this.filename = fileName;
	this.makeHTML();
}
reviewPhotosItem.prototype = {

		makeHTML : function(){
			var data = {};
			data.fileName = this.filename;
			
			document.querySelector(".lst_thumb").innerHTML
			+= this.makeTemlateHTML("#reviewPhotosItem",data);
			
			var cancelBtn = document.querySelector(".anchor");
			cancelBtn.addEventListener("click",function(){
				document.querySelector(".lst_thumb").innerHTML = "";
				});
		
		},

		makeTemlateHTML : function(templateName,data){
			var tempData = data;
			var templateItem = document.querySelector(templateName);
			var paser = Handlebars.compile(templateItem.innerText);
			return paser(tempData);
		}
		
		

}
