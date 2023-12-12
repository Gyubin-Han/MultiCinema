<!-- controller call this "reviewboard/reviewboard" -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Board Dummy Data</title>
<link rel="stylesheet" type="text/css" href="resources/css/reviewboard/review.css">
<link rel="stylesheet" type="text/css" href="resources/css/reviewboard/header.css">
<script src="resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>

<div id="headerInput"></div>

<div>
	<div id="listOption" style="text-align: right">
		<select id="viewListOption">
			<option value="0">=======</option>
			<option value="10">10개씩 보기</option>
			<option value="20">20개씩 보기</option>
			<option value="50">50개씩 보기</option>
		</select>
	</div>
    <div id="reviewBoard" style="margin:auto"></div>
</div>

<script>
let limit;
let currentPage;
let board = document.getElementById("reviewBoard");
let option = "countallreview";
let search;
let keyword;
let view;

let createListTable = (list) => {
	let table = createTable("boardListTable");
    table.style.textAlign = "center";
    let tr = createTr();

    let thTextList = ["글번호", "영화", "글제목", "작성자", "작성일", "조회", "추천", "댓글"];
	let thIdList = ["thBno", "thMovieTitle", "thBoardTitle", "thWriter", "thWriteDay", "thViewCount", "thLikeCount", "thCommentCount"];
    for(let i = 0; i < thTextList.length; i++) {tr.append(createTh(thTextList[i], thIdList[i]))};

    table.append(tr);
    if(list.length > 0) {
        list.forEach(b => {
            let tr = createTr(b.board_num);
            tr.onmouseenter = function() {this.style.backgroundColor = "rgb(238, 238, 238)";}
		    tr.onmouseleave = function() {this.style.backgroundColor = "";}
            
            if(b.board_like_count >= 10) tr.style.fontWeight = "bold";
            
            tr.onclick = () => {window.location.href="reviewdetail?board_num="+b.board_num};
			
            tr.append(createTd(b.board_num));
            tr.append(createTd(b.movie_cd));
            tr.append(createTd(b.board_title));
            tr.append(createTd(b.user_key));
            tr.append(createTd(b.board_write_date));
            tr.append(createTd(b.board_view_count));
            tr.append(createTd(b.board_like_count));
            tr.append(createTd(b.board_comment_count));
            table.append(tr);
        });
    }
    return table;
}



let createPagingMenu = (option) => {
    let div = createDiv("pagingMenu");
    div.style.textAlign = "center";
    
    $.ajax({
        url:option,
        data:{"keyword":keyword, "search":search},
        success:function(count) {
            let urlParams = new URLSearchParams(window.location.search);
			
            let max_page = parseInt((count - 1) / limit) + 1;
            let start_page = parseInt((currentPage-1) / 10) * 10 + 1;
            let end_page = (start_page + 9) < max_page ? (start_page + 9) : max_page;
            
            div.innerHTML = "<ul style='text-align:center;'>";
            if(start_page !== 1)
                div.innerHTML += "<li style='display:inline; margin:6px;'><a style='text-decoration: none; color: black; font:bold;' href='"+view+"?page="+(start_page-10)+"&limit="+limit+"'>앞 페이지 보기</a></li>";
            for(let i = start_page; i <= end_page; i++) {
                if(currentPage == i) {
                    div.innerHTML += "<li style='display:inline; margin:6px;'><a style='text-decoration: none; color: black; font-weight:bold;' href='"+view+"?page="+i+"&limit="+limit+"'>"+i+"</a></li>";
                } else {
                    div.innerHTML += "<li style='display:inline; margin:6px;'>"
                    div.innerHTML += "<a style='text-decoration: none; color: black;' href='"+view+"?page="+i+"&limit="+limit+"'>"+i+"</a></li>";
                }
            }
            if(max_page > end_page)
                div.innerHTML += "<li style='display:inline; margin:6px;'><a style='text-decoration: none; color: black; font:bold' href='"+view+"?page="+(start_page+10)+"&limit="+limit+"'>뒤 페이지 보기</a></li>";
            div.innerHTML += "</ul>";
        },
        error:function(request, e) {loggingConsole(request, e)}
    });

    return div;
}

let writeBoard = () => {
	if(<%=session.getAttribute("login_user_key")  == null%>)
		alert("로그인 후에 글을 쓸 수 있습니다.");
	else
		window.location.href='writereview?login_user_key='+<%=session.getAttribute("login_user_key")%>;
}

let createSelectSearchOption = () => {
    let div = createDiv("footOption");
	
	div.innerHTML = `
		<button onclick="writeBoard()" style="text-align: center;">리뷰 쓰기</button>
		<div id="searchOption" style="text-align: right; display:inline-block;">
			<select id="searchMethod">
				<option value="board_title">게시글 제목으로 검색</option>
				<option value="user_id">작성자 아이디로 검색</option>
				<option value="movie_title">영화 제목으로 검색</option>
			</select>
			<textarea id="searchText" style="resize:none; height:20px;"></textarea>
			<button onclick="searchBoardWithText()">검색</button>
			<button onclick="displayAllBoard()">전체글보기</button>
		</div>
	`;
	
	return div;
}

let displayAllBoard = () => {
	window.location.href = 'board?page=1&limit=10';
}

let searchBoardWithText = () => {
	search = document.querySelector("#searchMethod").value;
    keyword = document.querySelector("#searchText").value;
    view = "searchboard";
    
    let urlParams = new URLSearchParams(window.location.search);
    limit = 10;
    currentPage = 1;
    option = "countsearchreview";
    
    $.ajax({
        url:"searchboard",
        data:{
        	'page':currentPage,
        	'limit':limit,
			'search':search,
			'keyword':keyword
        },
        type:'get',
        success:function(list) {refreshBoard(list, option)},
        error:function(request, e) {loggingConsole(request, e)}
	});
}

window.onload = function() {
    
    let urlParams = new URLSearchParams(window.location.search);
    limit = urlParams.get('limit') == null ? 10 : urlParams.get('limit');
    currentPage = urlParams.get('page') == null ? 1 : urlParams.get('page');
    view = "board";
    
    
	 $.ajax({
	        url:"reviewboard",
	        data:{'page':currentPage,'limit':limit},
	        type:'get',
	        success:function(list) {refreshBoard(list, option)},
	        error:function(request, e) {loggingConsole(request, e)}
	});
// 	else {
// 		view = "searchboard";
// 		option = "countsearchreview";
// 		refreshBoard(list,option);
// 	}
	
	$("#viewListOption").on('change', function(e){
        limit = $("#viewListOption").val();
        if(limit == 0)
            return ;

		currentPage = 1;
		$.ajax({
			url:"reviewboard",
			data:{'page':currentPage,'limit':limit},
			type:'get',
			success:function(list) {refreshBoard(list, option)},
			error:function(request, e) {loggingConsole(request, e)}
		});
	});

    let loggingConsole = (request, e) => {
        console.log("코드="+request.status + " 메시지=" + request.responseText + " 오류=" + e);
    }
}

let refreshBoard = (list, option) => {
    board.innerHTML = "";
    board.append(createListTable(list));
    board.append(createPagingMenu(option));
    board.append(createSelectSearchOption());
}

// 기본요소들 생성
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
<!-- <script src="resources/js//reviewboard/review_old.js"></script> -->

</body>

</html>