<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
	<link rel="stylesheet" href="./css/index.css">
	<link rel="stylesheet" href="./css/reserve.css">
    <style>
	#body{
		width:50%;
	}
	input[type="text"]{
		width:200px;
		height:30px;
	}
    </style>
    <script src="script/reserve.js"></script>
</head>
<body>
<!-- 공통 UI 삽입 부분 -->
<%@ include file="/WEB-INF/views/header/mainheader.jsp" %>
<!-- 기능별 구현 부분 -->
<div id="body">
  <div id="paymentInfo"></div>
  <div id="payment">
      <div><strong>결재수단 선택</strong>&nbsp;&nbsp;
        <span><input type="radio" id="paymentTypeCard" name="paymentType" value="card"><label for="paymentTypeCard">신용/체크카드</label></span>
      </div>
      <div id="paymentForm"></div>
  </div>
</div>
<script>
	paymentInfo=document.getElementById("paymentInfo");
	paymentInfo.innerHTML=`
		<table>
			<!-- 정적 더미 데이터 --- 추후 수정 필요 -->
			<tr><td>영화명<td>:<td><span id="movie_name">이웃집 토토로</span></td></tr>
			<tr><td>인원 수<td>:<td><span id="seat_count">2</span></td></tr>
			<tr><td>가격<td>:<td><span id="price">24000원</span></td></tr>
		</table>
	`;


    let payment_type;
    const payment_element = document.querySelectorAll("input[name='paymentType']");
    payment_element.forEach((e) => {
        e.addEventListener("change", function (event) {
            payment_type = event.currentTarget.value;

            const payment_form=document.getElementById("paymentForm");
            if(payment_type=="card"){
		let regCardNumber=/^(0-9)[4]$/
                payment_form.innerHTML=`
                    <span>카드 번호 입력 : <input type="text" name="card_number" placeholder="카드번호 16자리 입력(- 포함)"></span>
                    <button class="btn">결제</button>
                `;

		let paymentCardScript=document.createElement("script");
		paymentCardScript.async=true;
		paymentCardScript.src="./script/reserve_payment_card.js";
		payment_form.appendChild(paymentCardScript);
            }

		alert("선택한 결제 수단 : " + payment_type);
        });
    });

    // const 
</script>
</body>
</html>