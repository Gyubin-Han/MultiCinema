<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/reviewboard/review.css">
</head>
<body>
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>
<div id="boardmain"></div>
<script>
let board = document.getElementById("boardmain");

onload = () => {
	
    let div = createDiv("board");
    board.appendChild(div);
    div.innerHTML = `<hr><table><tr>
    <td style="text-align: left; width: 80px">${user.user_name}</td>
    <td style="text-align: center; width: 700px"><h1>${review.board_title}</h1></td>
    <td style="text-align: right; width: 80px">조회: ${review.board_view_count}</td>
    <td style="text-align: right; width: 80px">댓글: ${review.board_comment_count}</td>
    </tr></table><hr>
    <div id="reviewContent">
    <p style="margin-top: 50px; margin-bottom: 50px; text-align: left;">${review.board_content}</p>
    </div>
    <div id="likeControl">
    <p><img src="resources/images/like.png" id="likeReviewImg" onclick="plusLikeCount(${review.board_num})"></p>
    <p>${review.board_like_count}</p>
    </div>
    <hr>
    `;
    
    
    div.innerHTML += `
        <div style="display:inline-block; text-align: left;">
        <p onclick="displayBoardList()" style="display:inline-block; text-align: left;">목록 보기</p>
        </div>
    `;
    
    div.innerHTML += `<hr><div id=divReviewComment></div>`;

}

let updateReviewDetail = () => {

}

let deleteReviewDetail = () => {

}

let displayBoardList = () => {

}

let createDiv = (id) => {
	let div = document.createElement("div");
	if(id !== undefined)
		div.id = id;
	return div;
}

let createSelect = (id, options) => {
	let select = document.createElement("select");
	select.id = id;
	
	for(let i = 0; i < options.length; i++)
		select.append(options[i]);
	return select;
}

let createOption = (value, text) => {
	let option = document.createElement("option");
	option.value = value;
	option.textContent = text;
	return option;
}

let createTable = (id) => {
	let table = document.createElement("table"); 
	if(id !== undefined)
		table.id = id;
	return table;
}

let createTbody = (id) => {
	let tbody = document.createElement("tbody");
	if(id !== undefined)
		tbody.id = id;
	return tbody;
}

let createTh = (text, id) => {
	let th = document.createElement("th");
	th.textContent = text;
	if(id !== undefined)
		th.id = id;
	return th;
}

let createTr = (id) => {
	let tr = document.createElement("tr");
	if(id !== undefined)
		tr.id = id;
	return tr;
}

let createTd = (text, id) => {
	let td = document.createElement("td");
	td.textContent = text;
	if(id !== undefined)
		td.id = id;
	return td;
}
</script>
</body>
</html>