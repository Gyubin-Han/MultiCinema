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
<div id="boardmain" style="text-align:center; width: 1080px; margin: auto;"></div>
<div id="reviewreply" style="text-align:center; width: 1080px; margin: auto;"></div>
<script>
let board = document.getElementById("boardmain");
let reply = document.getElementById("reviewreply");
let user = null;

onload = () => {
	
    let div = createDiv("board");
    board.appendChild(div);
    div.innerHTML = `<hr><table style="text-align:center;"><tr>
    <td style="width: 80px">${user.user_name}</td>
    <td style="text-align: center; margin:auto; width:1080px"><h1>${review.board_title}</h1></td>
    <td style="text-align: right; width: 80px">조회: ${review.board_view_count}</td>
    <td style="text-align: right; width: 80px">댓글: ${review.board_comment_count}</td>
    </tr></table><hr>
    <div id="reviewContent">
    <p style="margin-top: 50px; margin-bottom: 50px; text-align: left;">${review.board_content}</p>
    </div>
    <div id="likeControl">
    <p><img src="resources/images/like.png" id="likeReviewImg" onclick="plusLikeCount(${review.board_num})"></p>
    <p id="likecount">${review.board_like_count}</p>
    </div>
    <hr>
    `;
    
    div.innerHTML += `
        <div style="display:inline-block; width:350px; margin:auto; text-align: left;">
        <input type=button onclick="displayBoardList()" value="목록 보기"/>
        </div>
    `;
    
    if(${not empty login_user_key}) {
    	user = '${login_user_key}';
		if(${review.user_key} === user) {
    	div.innerHTML += `
    		<div style="display:inline-block; width:350px; margin:auto; text-align: right;">
            <input type=button onclick='updateReviewDetail(${review.board_num})' value="수정하기"/>
            <input type=button onclick='deleteReviewDetail(${review.board_num})' value="삭제하기"/>
            </div>
    	`;
    	}
    }
    div.innerHTML += `<hr>`;
	
    div = createDiv("reply");
    reply.append(div);
    
    div.innerHTML = `
    <form action="insertReply" onsubmit="return replyCheck()" method="post">
    <textarea name='reply_content' style='width: 800px; height: 200px; resize: none; margin: 0 auto;'></textarea>
    <input type=hidden id='user_key' value='${login_user_key}'>
    <input type=hidden id='board_num' value='${review.board_num}'>
    <input type=submit style='text-align:center; margin:auto;' value='댓글 작성'>
    </form>`;
    
    
    
}

let replyCheck = () => {
	if('${login_user_key}' === '') {
		alert("로그인 하세요");
		return false;
	} else {
		return true;
	}
}

let plusLikeCount = (board_num) => {
	if(${empty login_user_key}) {
        alert("로그인 후 누를 수 있습니다.");
        return ;
    } else if(${review.user_key} == user) {
		alert("자신의 글은 좋아요를 누를 수 없습니다.");
		return ;
	}
	
	$.ajax({
		url:"likebtn",
		data:{"board_num":${review.board_num}, "user_key":user},
		success: function(boolean) {
			if(boolean) {
				alert("좋아요를 누르셨습니다.");
				window.location.href = "reviewdetail?board_num="+${review.board_num};
			}
			else {
				alert("좋아요를 취소하셨습니다.");
				window.location.href = "reviewdetail?board_num="+${review.board_num};
			}
		}
	});
	
}

let updateReviewDetail = (board_num) => {
	window.location.href = "updatereview?board_num="+board_num+"&user_key="+${review.user_key};
}

let deleteReviewDetail = (board_num) => {
	window.location.href = "deletereview?board_num="+board_num+"&user_key="+${review.user_key};
}

let displayBoardList = () => {
	window.location.href = "board";
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