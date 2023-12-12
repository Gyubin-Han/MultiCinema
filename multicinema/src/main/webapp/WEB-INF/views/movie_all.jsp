<%@page import="com.mc.multicinema.movieinfo.dto.MovieDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#content{
	width: 1200px;
	margin:0 auto;
	height: 900px;
	
}
.movie_frame{
	display: inline-block;
	width: 220px;
	height: 400px;
	float:left;
	margin-right: 25px;
	
}
.movie_frame:nth-child(5n+1){
	
	margin-right: 0px;
	
}

.movie_title_wrap{
	height: 80px;
	text-align: center;
}
.movie_poster{
	border-radius: 10px
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>
<div id="content">
	<h1>현재 개봉중인 영화</h1>
	<%ArrayList<MovieDTO> list = (ArrayList<MovieDTO>)request.getAttribute("list");
	for(MovieDTO dto :list){
		%>
	<div class="movie_frame">
		<div>
			<a href="moviedetail?movie_cd=<%=dto.getMovie_cd() %>">
			<img class="movie_poster" src="<%=dto.getMovie_img_src() %>" alt="<%=dto.getMovie_img_src() %>">
			</a>
		</div>
		<div>
			<div class="movie_title_wrap">
				<h3><%=dto.getMovie_title() %></h3>
			</div>
		</div>
	</div>
	<%	
	}
	%>
</div>



</body>
</html>