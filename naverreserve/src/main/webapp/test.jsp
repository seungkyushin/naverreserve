<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../naverreserve/css/style.css" rel="stylesheet">
<title>Insert title here</title>

</head>
<body>


		
						<!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
						<ul class="visual_img">
							<li><img src="##" class="promotion_img"></li>
						</ul>
					


	<input type="button" value="버튼" onclick="test()">


	<script>
		window.addEventListener('DOMContentLoaded',function() {

							var ElementsString = [
									'<li><img src="/naverreserve/img/1_ma_2.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/5_ma_14.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/6_ma_19.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/9_ma_25.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/11_ma_31.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/12_ma_33.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/18_ma_47.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/22_ma_56.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/41_ma_106.png" class="promotion_img"></li>',
									'<li><img src="/naverreserve/img/44_ma_113.png" class="promotion_img"></li>' ];

							var src = [ "/naverreserve/img/1_ma_2.png",
									"/naverreserve/img/5_ma_14.png",
									"/naverreserve/img/6_ma_19.png",
									"/naverreserve/img/9_ma_25.png",
									"/naverreserve/img/11_ma_31.png",
									"/naverreserve/img/12_ma_33.png",
									"/naverreserve/img/18_ma_47.png",
									"/naverreserve/img/22_ma_56.png",
									"/naverreserve/img/34_ma_86.png",
									"/naverreserve/img/41_ma_106.png",
									"/naverreserve/img/44_ma_113.png" ];

							var PromotionElements = document.querySelector(".visual_img");

							for (var i = 0; i < src.length; i++)
								PromotionElements.innerHTML += ElementsString[i];

							var animationfunc = setInterval(function() {
								var PromotionElementsitems = document.querySelector(".promotion_img");
								
								PromotionElementsitems[0].style.transform = "translateX(-414px)";

									}, 3000);

						});
	</script>
</body>
</html>