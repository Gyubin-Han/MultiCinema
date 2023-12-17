<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatterBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>최고의 영화관: MultiCinema</title>
	<link rel="stylesheet" href="/resources/css/index.css">
	<link rel="stylesheet" href="/resources/css/reserve.css">
    <style>
        #date{
            margin:15px;
			text-align:center;
        }
        .dateBtn, .movie_select{
            display:inline-block;
            background-color:white;
            border:none;
            text-align:center;
            padding:5px 10px;
        }
        .dateBtn:hover, .movie_select:hover{
            background-color:lightgray;
        }
        .dateBtn:target, .movie_select:target{
            background-color:lightgreen;
        }
        #movie{
            height:100%;
            width:29%;
            margin:0.5%;
            float:left;
        }
        #result{
            width:69%;
            margin:0.5%;
            float:left;
        }
        .age_grade{
            width:20px;
            height:20px;
        }
        .movie_select{
        	width:100%;
        }
        .sunday{
            color:red;
        }
        .satday{
            color:blue;
        }
    </style>
    <script src="/resources/script/reserve.js"></script>
    <script src="/resources/script/jquery-3.7.1.min.js"></script>
    <script>
        let selectDate, selectMovie, selectMovieTitle, resultMovieList, resultTimeTable;
        $(document).ready(function(){
            // 날짜 선택 시 : 영화 목록을 서버로부터 받아와서 화면에 출력
            $(".dateBtn").click(function(){
                //alert($(this).val());
                // alert($(this).attr("value"));

                selectDate=$(this).val();
                $.ajax({
                    url:"/timetable/selectDate",
                    data:{'selectDate':selectDate},
                    type:'get',
                    dataType:'json',
                    success:function(response){
                    	//alert(response);
                    	
                    	$("#movieInTable").html("");
                    	$("#result").html("");
                        if(response.dataCnt==0){
                            alert("조회된 데이터가 없습니다.");
                        }else{
                            resultMovieList=response.movieList;
                            /* let jsonObj=JSON.parse(resultMovieList); */
                            
                            let movieInTable=$("#movieInTable")
                            movieInTable.html("");
                            let newMovieBtn;
                            $.each(resultMovieList,function(idx) {
                                // resultHtml += "<button class='movieBtn movie_select' value='"+resultMovieList[idx].movieCd+"'><img src='/resources/images/age_grade/" + resultMovieList[idx].movieLimitAge + ".png' class='age_grade'>" + resultMovieList[idx].movieTitle + "</button>";
                                newMovieBtn=$("<button>").addClass('movieBtn movie_select').attr("value",resultMovieList[idx].movieCd).html("<img src='/resources/images/age_grade/" + resultMovieList[idx].movieLimitAge + ".png' class='age_grade'> " + resultMovieList[idx].movieTitle);
                                newMovieBtn.on("click", function(){
                                	click_movieBtn(this);
                                });

                                movieInTable.append(newMovieBtn);
                            });
                        }
                    },
                    error:function(request, e){
                        alert("오류 코드 : "+request.status+" / 메시지 : "+request.responseText+" / 오류 : "+e);
                    }
                });
            });

            // 영화 선택 시 : 해당 날짜 및 영화를 상영하는 상영관의 상영 일정 정보를 받아와 출력
            function click_movieBtn(t){
                //alert($(t).val());
                // alert($(this).attr("value"));

                selectMovie=$(t).val();
                $.ajax({
                    url:"/timetable/selectDateMovie",
                    data:{'selectDate':selectDate,'selectMovie':selectMovie},
                    type:'get',
                    dataType:'json',
                    success:function(response){
                        if(response.dataCnt==0){
                            alert("조회된 데이터가 없습니다.");
                        }else{
                            resultTimeTable=response.timeTable;
                            console.log(resultTimeTable);

                            //let jsonObj=JSON.parse(resultTimeTable);
                            let resultHtml=$("<div class='result_theater'>");

                            //$.each(jsonObj,function(i) {
                           	$.each(resultTimeTable,function(i){
                                let resultPartHtml="";

                            	// resultPartHtml+="<div class='result_time'>";
                                let seats_left=Number(resultTimeTable[i]["theaterMaxPeople"])-Number(resultTimeTable[i]["nowPeople"]);
                                let newTimeBtn=$("<button>")
                                newTimeBtn.addClass('select_time').attr("value",resultTimeTable[i]["schId"]).html("<strong>"+resultTimeTable[i]["schTime"]+"</strong><br>"+resultTimeTable[i]["theaterName"]+"<br>"+seats_left+"/"+resultTimeTable[i]["theaterMaxPeople"]);
                                newTimeBtn.click(function(){
                                	click_timeBtn(this);
                               	});

                                resultHtml.append(newTimeBtn);
                            });
                            //resultHtml.append("</div>");
                            $("#result").html(resultHtml);
                        }
                    },
                    error:function(request, e){
                        alert("오류 코드 : "+request.status+" / 메시지 : "+request.responseText+" / 오류 : "+e);
                    }
                });
            };
            
            function click_timeBtn(t){
            	let timeValue=$(t).val();
            	
       			let sendForm=document.createElement("form");
       			sendForm.setAttribute("method","post");
       			sendForm.setAttribute("action","/ticketing/seatSelect");
       			
       			let sendMovieInfo=document.createElement("input");
       			sendMovieInfo.setAttribute("type","hidden");
       			sendMovieInfo.setAttribute("name","sch_id");
       			sendMovieInfo.setAttribute("value",timeValue);
       			sendForm.appendChild(sendMovieInfo);

       			document.body.appendChild(sendForm);
       			sendForm.submit();
            };
        });
    </script>
</head>
<body>
<!-- 공통 UI 삽입 부분 -->
<header class="body">
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
			<li id="movie"><a href="movie.html">영화</a></li>
			<li id="ticketing"><a href="timetable_select.html">예매</a></li>
			<li id="theater"><a href="theater.html">영화관</a></li>
		</ul>
	<hr>
</header>

<!-- 기능별 구현 부분 -->
<div id="body">
    <div id="date">
        ▼ 날짜를 선택해주세요.
        <div>
        <%
            LocalDateTime today=LocalDateTime.now();
            for(int i=0;i<8;i++){
                LocalDateTime ldt=today.plusDays(i);
                String dayValue=ldt.getYear()+""+ldt.getMonthValue()+""+ldt.getDayOfMonth();
                String weekday="", weekdayStyle="";
                switch(ldt.getDayOfWeek().getValue()){
                    case 1: weekday="월"; break;
                    case 2: weekday="화"; break;
                    case 3: weekday="수"; break;
                    case 4: weekday="목"; break;
                    case 5: weekday="금"; break;
                    case 6: weekday="토"; weekdayStyle=" satday"; break;
                    case 7: weekday="일"; weekdayStyle=" sunday"; break;
                }

                String date="<button value='"+dayValue+"' class='dateBtn"+weekdayStyle+"'>"+ldt.getMonthValue()+"/"+ldt.getDayOfMonth()+"<br>"+weekday+"</button>";
        %>
            <%=date%>
        <%
            }
        %>
        </div>
    </div>

    <div>
        <!-- <div id="movie">날짜를 먼저 선택해주세요.</div> -->
        <div id="movie">
		▼ 영화를 선택해주세요.<br><br>
            <div id="movieInTable"></div>
        </div>

        <div id="result">
        </div>
    </div>
</div>
<script>
    // let selectDate=document.querySelectorAll("#date>div>button");
    // selectDate.forEach((e) => {
    //     console.log(e.value);
    //     e.addEventListener("click",function(event){
    //         alert(e.value);
    //     });
    // });

	let selectTime=document.querySelectorAll(".select_time");
	selectTime.forEach((e) => {
		console.log(e.value);
		e.addEventListener("click",function(event){
			let sendForm=document.createElement("form");
			sendForm.setAttribute("method","post");
			sendForm.setAttribute("action","./reserve_select.html");
			
			let sendMovieInfo=document.createElement("input");
			sendMovieInfo.setAttribute("type","hidden");
			sendMovieInfo.setAttribute("name","movie_info");
			sendMovieInfo.setAttribute("value",e.value);
			sendForm.appendChild(sendMovieInfo);

			document.body.appendChild(sendForm);
			sendForm.submit();
		});
	});
</script>
</body>
</html>