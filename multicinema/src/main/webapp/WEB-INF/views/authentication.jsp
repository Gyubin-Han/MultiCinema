
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>마이 페이지</title>


<link rel="stylesheet" type="text/css" href="/multicinema/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="/multicinema/resources/css/mypageCSS.css">
<style>

</style>
</head>
<body >
		<div class="header">
		<h1 class="mainlogo">
			<a href="mainpage.html" title="홈화면으로 가기">홈으로가기</a>
		</h1>
		<div class="myinformation">
			<a href="main_signout.html">로그아웃하기</a>
			<a href="mypage.html">내정보확인</a>
		</div>
	</div>
		
	<hr>
		<ul id="mainNavigator">
			<li id="movie"><a href="movie.jsp">영화</a></li>
			<li id="ticketing"><a href="timetable_select.jsp">예매</a></li>
			<li id="theater"><a href="theater.jsp">영화관</a></li>
		</ul>
	<hr>
	
	<script type = "module" src ="/multicinema/resources/script/mypage.js"></script>
<div id = "myPage_navigater" >
	<div id="reserve_detail"> 예매내역</div><hr >
	<div id="info_change"> 회원 정보 수정</div><hr >
	<div id="my_review"> 작성 리뷰 게시글</div><hr >
</div>

<div id = "myPage_main" >
	<div id="container">
	<div id="contents">
		<div id="contents_header">
		본인 확인을 위해 다시한번 비밀번호를 입력해주세요
		</div>
			
			<form action="/multicinema/mypage/authenticationresult" method ="post">
				비밀번호 <input id="user_pw" type="password" name="user_pw"  placeholder="비밀번호를 입력해주세요"><br>
			<input type = submit  value = "입력" id="authentication_submit">
			</form>

	</div>
				
			
	</div>
</div>
	






</body>
</html>
