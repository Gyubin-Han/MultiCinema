<%@page import="com.mc.multicinema.user.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>
<div id="writeBoard" style="text-align:center; width:1080px; margin:auto;">
<form action="writenreview" method="post" onsubmit="return validateForm()">
	<table style="width: 1000px;">
		<tr style="height: 20px;">
			<td style="width: 70px;">글제목</td>
			<td><textarea name="board_title" style="width: 800px; height: 18px; resize: none; margin: 0 auto;"></textarea></td>
		</tr>
		<tr>
			<td>영화제목</td>
			<td>
				<select name="movie_cd">
					<c:forEach var="movie" items="${movies}">
					<option value="${movie.movie_cd }">${movie.movie_title }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td><textarea name="board_content" style="width: 800px; height: 600px; resize: none; margin: 0 auto; font-size:14px;"></textarea></td>
		</tr>
		<tr>
			<td style="text-align:center;" colspan="2">
				<input type=submit id="pComplete" onclick="completeWriteBoard()" value="작성 완료"/>
				<input type=button onclick="displayBoardList()" value="취소"/>
				<input type=hidden name="user_key" value="${user.user_key }"/>
			</td>
		</tr>
	</table>
</form>
</div>
<script>
function validateForm() {
    var boardTitle = document.forms["writenreview"]["board_title"].value;
    var movieCd = document.forms["writenreview"]["movie_cd"].value;
    var boardContent = document.forms["writenreview"]["board_content"].value;

    if(boardTitle.trim() === '') {
        alert('글제목을 입력하세요.');
        return false;
    } else if(movieCd === '' || movieCd === null) {
        alert('영화를 고르세요');
        return false;
    } else if(boardContent.trim() === '') {
        alert("글을 입력하세요.");
        return false;
    }

    return true;
}

let displayBoardList = () => {
	window.location.href="board";
}
</script>
</body>
</html>