<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.mc.multicinema.screeningschedule.dto.ScreeningScheduleToTicketingDTO" %>
<%--
/*if(session.getAttribute("userId")==null || session.getAttribute("userId").equals("")){
	%> <script>
			alert("로그인하세요.");
			location.href="/login";
		</script> <%
}
--%>
<%
String datetime=((ScreeningScheduleToTicketingDTO)request.getAttribute("schedule")).getSchDateTime().toString();
String date=datetime.split("T")[0];
String time=datetime.split("T")[1];
%>
<html>
<head>
    <meta charset="utf-8">
    <title>최고의 영화관: MultiCinema</title>
	<link rel="stylesheet" href="/resources/css/index.css">
	<link rel="stylesheet" href="/resources/css/reserve.css">
    <style>
	#people_select{
		text-align:center;
	}
        #seat_list{
            text-align:center;
        }
        #select_seat_price{
            text-align:right;
        }
	.info{
		width:350px;
	}
	.people{
		width:33px;
		height:33px;
		background-color:ghostwhite;
	}
	.people:active{
		backgroud-color:gray;
	}
	.people_select{
		background-color:lightgreen;
	}
        .non-select_seat{
            background-color:white;
            border:0px;
            width:35px;
            height:35px;
        }
        .non-select_seat:hover{
            background-color:lightgray;
        }
        .non-select_seat:active{
            background-color:gray;
        }
        .non-select_seat:disabled{
            background-color:gray;
        }
        .select_seat{
            background-color:lightgreen;
        }
        .result{
            display:inline-block;
            width:250px;
        }
    </style>
    <script src="/resources/script/reserve.js"></script>
    <script src="/resources/script/jquery-3.7.1.min.js"></script>
    <script>
    	$(document).ready(function(){
    		// 인원
    	    let people_count=0;
    	    let people_limit=0;
    		let disabledSeat,price=0;
    		let selected_seat=[];
    		
    	    const people=document.querySelectorAll(".people");
    	    people.forEach((event) => {
    	        event.addEventListener("click",function(event){
    	            people.forEach((e) => {
    	                e.classList.remove("people_select");
    	            });
    	            event.target.classList.add("people_select");
    	            people_limit=parseInt(this.value);
    	        });
    	    });

    		
    		getSeatData();
    		
    		// 현재 좌석 상태를 불러오는 함수
    		function getSeatData(){
    			$.ajax({
    				url:"/ticketing/seatAll",
                    data:{'sch_id':"${param.sch_id}"},
                    type:'get',
                    dataType:'json',
                    success:function(response){
                    	//alert(response.seatList);
	                    disabledSeat=response.seatList;
	                    
	                    for(let i=0;i<8;i++){
	                    	let row=$("<tr>");
	                    	for(let j=1;j<11;j++){
	                    		let seatName=String.fromCharCode(65+i)+j;
	                    		let seatBtn=$("<button>").addClass("non-select_seat").val(seatName).text(seatName);
    			
    							if(disabledSeat.includes(seatName)){
    								seatBtn.prop("disabled","true");
    							}
    							
    							seatBtn.click(function(){
    								selectSeat(this);
    							});
	                    		
	                    		row.append($("<td>").append(seatBtn));
	                    	}
	                    	
	                    	$("#seat_list").append(row);
	                    }
	                    
	                    //resultHtml.append("</div>");
	                    //$("#result").html(resultHtml);
                    },
                    error:function(request, e){
                        alert("오류 코드 : "+request.status+" / 메시지 : "+request.responseText+" / 오류 : "+e);
                    }
    			});
    		}
    		
    		function selectSeat(t){
    			if (selected_seat.includes(event.target.value)) {
                    selected_seat.splice(selected_seat.indexOf(event.target.value), 1);
                    event.target.classList.remove("select_seat");
                    people_count--;
                    price-=12000;
                }else if(people_count >= people_limit) {
                    alert("이미 최대 선택 가능한 좌석을 선택하셨습니다.");
                }else{
                    selected_seat.push(event.target.value);
                    event.target.classList.add("select_seat");
                    people_count++;
                    price+=12000;
                    // alert(event.target.value);
                }
    			
    			$("#select_seat_info").text(selected_seat.toString());
                $("#select_seat_price").text(price+"원");
    		}
    		
    		$("#payment_btn").click(function(){
        		if(people_count<=0){
        			alert("좌석을 먼저 선택해주세요.");
        			return;
        		}
        		
        		let paymentForm=$("<form>");
        		paymentForm.attr("method","post");
        		paymentForm.attr("action","/ticketing/payInfo");

        		let sendSchId=$("<input>");
        		sendSchId.attr("type","hidden");
        		sendSchId.attr("name","schId");
        		sendSchId.val("${param.sch_id}");
        		paymentForm.append(sendSchId);
        		
        		let sendSeat=$("<input>");
        		sendSeat.attr("type","hidden");
        		sendSeat.attr("name","seat");
        		sendSeat.val(selected_seat);
        		paymentForm.append(sendSeat);
        		
        		let sendPrice=$("<input>");
        		sendPrice.attr("type","hidden");
        		sendPrice.attr("name","price");
        		sendPrice.val(price);
        		paymentForm.append(sendPrice);

        		$("body").append(paymentForm);
        		paymentForm.submit();
    		});
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
  <div>
      <table class="table">
          <tr><td>영화명<td>:<td class="info">${schedule.movieTitle }</td></tr>
       	  <tr><td>상영관<td>:<td class="info">${schedule.cinemaName } ${schedule.theaterName }</td></tr>
          <tr><td>상영일시<td>:<td class="info"><%=date %> <%=time %></td></tr>
      </table>
  </div>
  <div id="people_select">
      인원 : <button class="people" value="1">1</button><button class="people" value="2">2</button><button class="people" value="3">3</button><button class="people" value="4">4</button><button class="people" value="5">5</button>
  </div>
  <div>
    <table id="seat_list" class="table" border="1">
        <tr><td colspan="10">SCREEN</td></tr>
    </table>
  </div>
  <div>
    <table border="1" class="table">
        <tr><td>선택된 좌석<td><span id="select_seat_info" class="result"></span><td rowspan="2"><button id="payment_btn" class="btn">결제</td></tr>
        <tr><td>총 결제 금액<td><span id="select_seat_price" class="result">0원</span></td></tr>
    </table>
  </div>
</div>
</body>
</html>