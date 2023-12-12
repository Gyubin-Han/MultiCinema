<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>마이 페이지</title>


<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="resources/css/mypageCSS.css">
<style>

</style>
</head>
<body >
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>
	
	<script type = "module" src ="resources/script/mypage.js"></script>
	
	
<div id = "myPage_navigater" >
	<div id="reserve_detail"> 예매내역</div><hr >
	<div id="info_change"> 회원 정보 수정</div><hr >
	<div id="my_review"> 작성 리뷰 게시글</div><hr >
</div>

<div id = "myPage_main" >
<form action= "changeemailresult" method="post">
	변경할 이메일 <input type="email" name = "user_email"> <br>
	다시 한번 입력해 주세요 <input type="email" name = "user_email_again"> <br>
	<input type = submit value ="이메일 변경">
</form>


</div>


</body>
</html>