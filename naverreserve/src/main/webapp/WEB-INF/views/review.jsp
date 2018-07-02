<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="../css/style.css" rel="stylesheet">
	<script	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>

<body>
    <div id="container">
        <!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
        <div class="header fade">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="./mainpage.html" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./mainpage.html" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <div class="top_title">
                    <a href="./detail.html" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                    <h2><span class="title"></span></h2>
                </div>
        
        	<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"> <em class="graph_value"
									style="width: 84%;"></em>
								</span> <strong class="text_value"> <span>4.2</span> <em
									class="total">5.0</em>
								</strong> <span class="join_count"><em class="green">52건</em> 등록</span>
							</div>
							<ul class="list_short_review">
								<li class="list_item">
									<div>
										<div class="review_area">
											<div class="thumb_area">
												<a href="#" class="thumb" title="이미지 크게 보기"> <img
													width="90" height="90" class="img_vertical_top"
													src="http://naverbooking.phinf.naver.net/20170306_3/1488772023601A4195_JPEG/image.jpg?type=f300_300"
													alt="리뷰이미지">
												</a> <span class="img_count" style="display: none;">1</span>
											</div>
											<h4 class="resoc_name"></h4>
											<p class="review">2층이어서 걱정했는데 꽤잘보여서 좋았습니다 고미오 너무 멋있었습니다
												사진은 커튼콜때 찍었습니다 끝나고 퇴근길도 봐서 너무 좋았어요</p>
										</div>
										<div class="info_area">
											<div class="review_info">
												<span class="grade">4.0</span> <span class="name">dbfl****</span>
												<span class="date">2017.3.5. 방문</span>
											</div>
										</div>
									</div>
								</li>
								<li class="list_item">
									<div>
										<div class="review_area no_img">
											<h4 class="resoc_name"></h4>
											<p class="review">
												너무 재밌게봤구요~<br>마지막공연 후 뒷풀이도 잘봤습니다
											</p>
										</div>
										<div class="info_area">
											<div class="review_info">
												<span class="grade">5.0</span> <span class="name">yyck****</span>
												<span class="date">2017.3.5. 방문</span>
											</div>
										</div>
									</div>
								</li>
								<li class="list_item">
									<div>
										<div class="review_area no_img">
											<h4 class="resoc_name"></h4>
											<p class="review">
												좋은 공연이었습니다. <br>머큐쇼역활 하신분의 열창이 기억에 남는 반면에,,, 로미오는 별로
												기억에 남지 않네요..
											</p>
										</div>
										<div class="info_area">
											<div class="review_info">
												<span class="grade">4.0</span> <span class="name">xero****</span>
												<span class="date">2017.3.4. 방문</span>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한
								이용자가 남긴 평가입니다.</span>
						</p>
					</div>

				</div>
				
        
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div id="footer" class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

</body>

</html>
