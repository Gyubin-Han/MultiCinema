/**
 * 
 */
import{movie_list,user_list,history_list,review_history_list,User,Movie,Movie_watched_history,Review_history} from "./dummyObject.js";

 window.addEventListener('DOMContentLoaded', function()
	{
	let reserv_detail = document.querySelector("#myPage_navigater > #reserve_detail");
	let info_change = document.querySelector("#myPage_navigater > #info_change");
	let my_review = document.querySelector("#myPage_navigater > #my_review");
	// 내용들이 담길 부분
	let main_page = document.querySelector("#myPage_main");
	
	// 예매 내역 누른 후
	reserv_detail.addEventListener("click", show_reserve_detail);
	
	function show_reserve_detail(){
		window.location.href = "/multicinema/mypage/reservedetail";
	}
	// 회원 정보 수정 누른경우
	info_change.addEventListener("click",authentication);	
	
	function authentication(){
		window.location.href = "/multicinema/mypage/authentication";
	}
	
	// 작성 리뷰 게시물 누른 경우
	my_review.addEventListener("click", show_myReview);
	
	function show_myReview(){
		window.location.href="/multicinema/mypage/reviewboardhistory";
	}
	

	
});







