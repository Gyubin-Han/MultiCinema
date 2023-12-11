<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/reviewboard/header.css">
<script src="resources/js/jquery-3.7.1.min.js"></script>
</head>
<body class="body">
	<div class="header">
		<h1 class="mainlogo">
			<a href="/multicinema" title="홈화면으로 가기">홈으로가기</a>
		</h1>
		<div class="myinformation"></div>
	</div>

	<hr>
		<ul id="mainNavigator">
			<li id="movie"><a href="movie.html">영화</a></li>
			<li id="ticketing"><a href="tiketing.html">예매</a></li>
			<li id="theater"><a href="theater.html">영화관</a></li>
            <li id="board"><a href="board">영화리뷰</a></li>
		</ul>
	<hr>
<script>

document.querySelector('.header .mainlogo').style.backgroundImage = 
	'url("resources/images/multiCinema_logo.png")';

let loginUserId = '${login_user_id}';
let myinformation = document.querySelector('.myinformation');
console.log(loginUserId);
if (loginUserId == 'null' || loginUserId == null || loginUserId == '') {
	myinformation.innerHTML = "<a href='login'>로그인하기</a><a href='membercheck'>회원가입</a>";
} else {
	myinformation.innerHTML = "<a href='logout'>로그아웃하기</a><a href='mypage'>내 정보 보기</a>";
}

</script>

</body>
</html>