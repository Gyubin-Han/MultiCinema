-- 데이터 삽입 실패해도 auto-increment가 자동으로 증가하는 것을 방지하는 설정 --
/*
window는 C:\ProgramData\MySQL\MySQL Server 8.0\my.ini 에서
innodb_autoinc_lock_mode = 0 아무데나 적기 인데 적용이 안됩니다..
*/

CREATE TABLE `user_info` (
	`user_key`	int	NOT NULL	COMMENT '가입시 숫자로 부여',
	`user_id`	varchar(16)	NOT NULL	COMMENT 'unique,',
	`user_email`	varchar(30)	NOT NULL	COMMENT 'unique,',
	`user_pw`	varchar(100)	NOT NULL,
	`user_regdate`	datetime	NOT NULL,
	`user_birthday`	datetime	NOT NULL,
	`user_pw_salt`	varchar(10)	NOT NULL	COMMENT '유저마다 임의부여',
	`is_deleted`	boolean	NOT NULL	DEFAULT false	COMMENT '일정기간 후 삭제',
	`deleted_date`	datetime	NULL	COMMENT '삭제날짜',
	`user_name`	varchar(20)	NOT NULL	COMMENT '유저이름'
);

ALTER TABLE `user_info` ADD CONSTRAINT `PK_USER_INFO` PRIMARY KEY (
	`user_key`
);

-- 유저 키 auto_increment / 유저 id unique--
alter table user_info modify user_key int auto_increment;
alter table user_info add UNIQUE (user_id);


CREATE TABLE `cinema` (
	`cinema_id`	int	NOT NULL,
	`Field2`	varchar(30)	NOT NULL	COMMENT '영화관 이름',
	`cinema_location`	varchar(50)	NULL
);

ALTER TABLE `cinema` ADD CONSTRAINT `PK_CINEMA` PRIMARY KEY (
	`cinema_id`
);

-- 시네마 id auto_increment / 시네마 이름 unique--
alter table cinema modify cinema_id int auto_increment;

-- 씨네마 이름 부분이 field2 로 들어가있어서 한번 바꾸기--
select * from cinema;
alter table cinema rename column `Field2` to `cinema_name`;
alter table cinema add UNIQUE (cinema_name);

/*
insert into cinema value(null,'가 영화관','서울시');
insert into cinema value(null,'가 영화관','서울시');
insert into cinema value(null,'나 영화관','서울시');
select * from cinema;
truncate table cinema;
*/
CREATE TABLE `movie_info` (
	`movie_cd`	int	NOT NULL	COMMENT '영화 코드',
	`movie_title`	varchar(100)	NOT NULL,
	`movie_img_src`	varchar(300)	NOT NULL	COMMENT '이미지 url 주소',
	`movie_spec`	varchar(1000)	NOT NULL	COMMENT '영화 상세정보 줄글',
	`movie_openDt`	datetime	NOT NULL	COMMENT '영화 개봉일자',
	`movie_showTm`	varchar(10)	NOT NULL	COMMENT '영화 상영시간',
	`movie_type`	varchar(30)	NOT NULL	COMMENT '영화 유형',
	`movie_makeNt`	varchar(100)	NOT NULL	COMMENT '제작국가',
	`movie_genre`	varchar(30)	NOT NULL	COMMENT '장르',
	`movie_director`	varchar(50)	NOT NULL	COMMENT '감독',
	`movie_limitAge`	int	NOT NULL	COMMENT '관람 제한 연령'
);

ALTER TABLE `movie_info` ADD CONSTRAINT `PK_MOVIE_INFO` PRIMARY KEY (
	`movie_cd`
);

-- 영화 코드/ 영화 이미지 소스파일 unique--
alter table movie_info add UNIQUE (movie_cd);
alter table movie_info add UNIQUE (movie_img_src);


CREATE TABLE `theater` (
	`theater_id`	int	NOT NULL,
	`cinema_id`	int	NOT NULL,
	`total_seat_count`	int	NOT NULL,
	`theater_name`	varchar(10)	NOT NULL	COMMENT '상영관 이름 (ex 1관)'
);

ALTER TABLE `theater` ADD CONSTRAINT `PK_THEATER` PRIMARY KEY (
	`theater_id`,
	`cinema_id`
);

-- 상영관 id unique // 영화관은 여러개 들어갈 수 있음 --
alter table theater add UNIQUE (theater_id);

CREATE TABLE `review_board` (
	`board_num`	int	NOT NULL	COMMENT '리뷰게시글 고유번호',
	`user_key`	int	NOT NULL	COMMENT '회원키',
	`movie_cd`	int	NOT NULL	COMMENT '영화키',
	`board_write_date`	datetime	NOT NULL	COMMENT '작성날짜',
	`board_title`	varchar(50)	NOT NULL	COMMENT '게시글제목',
	`board_content`	varchar(5000)	NOT NULL	COMMENT '게시글내용',
	`board_update_date`	datetime	NOT NULL	COMMENT '수정날짜',
	`board_like_count`	int	NULL	DEFAULT 0	COMMENT '좋아요수',
	`board_dislike_count`	int	NULL	DEFAULT 0	COMMENT '싫어요수',
	`board_comment_count`	int	NULL	DEFAULT 0	COMMENT '댓글수'
);

ALTER TABLE `review_board` ADD CONSTRAINT `PK_REVIEW_BOARD` PRIMARY KEY (
	`board_num`,
	`user_key`,
	`movie_cd`
);
-- 게시글 번호 auto increment--
alter table review_board modify board_num int auto_increment;

CREATE TABLE `movie_comment` (
	`comment_num`	int	NOT NULL	COMMENT '한줄평 고유번호',
	`user_key`	int	NOT NULL,
	`movie_cd`	int	NOT NULL,
	`comment_write_date`	datetime	NOT NULL	COMMENT '한줄평 작성날짜',
	`comment_content`	varchar(40)	NOT NULL	COMMENT '한줄평 내용',
	`is_deleted`	boolean	NULL	DEFAULT false	COMMENT '삭제여부',
	`deleted_date`	datetime	NULL	COMMENT '삭제날짜',
	`comment_like`	int	NULL	DEFAULT 0	COMMENT '한줄평 좋아요수',
	`comment_score`	int	NOT NULL	COMMENT '영화 평점'
);

ALTER TABLE `movie_comment` ADD CONSTRAINT `PK_MOVIE_COMMENT` PRIMARY KEY (
	`comment_num`,
	`user_key`,
	`movie_cd`
);

-- 한줄평 번호 auto increment--
alter table movie_comment modify comment_num int auto_increment;

CREATE TABLE `ticketing` (
	`ticketing_num`	int	NOT NULL	COMMENT '예매번호',
	`user_key`	int	NOT NULL	COMMENT '가입시 숫자로 부여',
	`seat_id`	varchar(20)	NOT NULL	COMMENT '좌석 고유 id',
	`sch_id`	varchar(20)	NOT NULL	COMMENT '상영 일정',
    `movie_cd` int not null,
    `sch_time` datetime NOT NULL
);


ALTER TABLE `ticketing` ADD CONSTRAINT `PK_TICKETING` PRIMARY KEY (
	`ticketing_num`,
	`user_key`,
	`seat_id`,
	`sch_id`
);

-- 예매 번호 auto increment--
alter table ticketing modify ticketing_num int auto_increment;

CREATE TABLE `seat` (
	`seat_id`	varchar(20)	NOT NULL	COMMENT '좌석 고유 id',
	`sch_id`	int	NOT NULL	COMMENT '상영 일정',
	`seat_name`	varchar(4)	NOT NULL	COMMENT '좌석번호 (ex A1)'
);

ALTER TABLE `seat` ADD CONSTRAINT `PK_SEAT` PRIMARY KEY (
	`seat_id`,
	`sch_id`
);

-- 좌석 고유정보 ( 해당 상영 스케쥴마다의 좌석임 . 1영화관 1 상영관의 7시영화 A1 좌석과 1영화관 1 상영관의 9시영화 A1 좌석은 다른 id 부여) --
alter table seat add UNIQUE (seat_id);

CREATE TABLE `screening_schedule` (
	`sch_id`		int NOT NULL	COMMENT '상영 일정',
	`theater_id`	int	NOT NULL,
	`movie_cd`	int	NOT NULL,
	`sch_time`	datetime	NOT NULL	COMMENT '상영 일시'
);

ALTER TABLE `screening_schedule` ADD CONSTRAINT `PK_SCREENING_SCHEDULE` PRIMARY KEY (
	`sch_id`,
	`theater_id`,
	`movie_cd`
);

-- 상영정보 id auto increment--
alter table screening_schedule modify sch_id int auto_increment;

CREATE TABLE `review_reply` (
	`reply_num`	int	NOT NULL	COMMENT '리뷰댓글 고유번호',
	`board_num`	int	NOT NULL	COMMENT '리뷰게시글 고유번호',
	`user_key`	int	NOT NULL	COMMENT '가입시 숫자로 부여',
	`comment_content`	varchar(500)	NOT NULL	COMMENT '게시글댓글내용',
	`comment_write_date`	datetime	NOT NULL	COMMENT '댓글작성날짜',
	`comment_like_count`	int	NULL	DEFAULT 0	COMMENT '좋아요수',
	`comment_dislike_count`	int	NULL	DEFAULT 0	COMMENT '싫어요수',
	`is_deleted`	boolean	NULL	DEFAULT false	COMMENT '삭제여부',
	`deleted_date`	datetime	NULL	COMMENT '삭제날짜'
);

ALTER TABLE `review_reply` ADD CONSTRAINT `PK_REVIEW_REPLY` PRIMARY KEY (
	`reply_num`,
	`board_num`,
	`user_key`
);

-- 리뷰게시글 댓글의 id auto increment--
alter table review_reply modify reply_num int auto_increment;


CREATE TABLE `user_like_board` (
	`user_key`	int	NOT NULL,
	`board_num`	int	NOT NULL
);

ALTER TABLE `user_like_board` ADD CONSTRAINT `PK_USER_LIKE_BOARD` PRIMARY KEY (
	`user_key`,
	`board_num`
);

CREATE TABLE `user_dislike_board` (
	`user_key`	int	NOT NULL,
	`board_num`	int	NOT NULL
);

ALTER TABLE `user_dislike_board` ADD CONSTRAINT `PK_USER_DISLIKE_BOARD` PRIMARY KEY (
	`user_key`,
	`board_num`
);

CREATE TABLE `user_like_reply` (
	`user_key`	int	NOT NULL	COMMENT '가입시 숫자로 부여',
	`reply_num`	int	NOT NULL
);

ALTER TABLE `user_like_reply` ADD CONSTRAINT `PK_USER_LIKE_REPLY` PRIMARY KEY (
	`user_key`,
	`reply_num`
);

CREATE TABLE `user_dislike_reply` (
	`user_key`	int	NOT NULL	COMMENT '가입시 숫자로 부여',
	`reply_num`	int	NOT NULL
);

ALTER TABLE `user_dislike_reply` ADD CONSTRAINT `PK_USER_DISLIKE_REPLY` PRIMARY KEY (
	`user_key`,
	`reply_num`
);

CREATE TABLE `user_like_movie_comment` (
	`user_key`	int	NOT NULL,
	`comment_num`	int	NOT NULL
);

ALTER TABLE `user_like_movie_comment` ADD CONSTRAINT `PK_USER_LIKE_MOVIE_COMMENT` PRIMARY KEY (
	`user_key`,
	`comment_num`
);

ALTER TABLE `theater` ADD CONSTRAINT `FK_cinema_TO_theater_1` FOREIGN KEY (
	`cinema_id`
)
REFERENCES `cinema` (
	`cinema_id`
);

ALTER TABLE `review_board` ADD CONSTRAINT `FK_user_info_TO_review_board_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `review_board` ADD CONSTRAINT `FK_movie_info_TO_review_board_1` FOREIGN KEY (
	`movie_cd`
)
REFERENCES `movie_info` (
	`movie_cd`
);

ALTER TABLE `movie_comment` ADD CONSTRAINT `FK_user_info_TO_movie_comment_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `movie_comment` ADD CONSTRAINT `FK_movie_info_TO_movie_comment_1` FOREIGN KEY (
	`movie_cd`
)
REFERENCES `movie_info` (
	`movie_cd`
);

ALTER TABLE `ticketing` ADD CONSTRAINT `FK_user_info_TO_ticketing_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `ticketing` ADD CONSTRAINT `FK_seat_TO_ticketing_1` FOREIGN KEY (
	`seat_id`
)
REFERENCES `seat` (
	`seat_id`
);

ALTER TABLE `seat` ADD CONSTRAINT `FK_screening_schedule_TO_seat_1` FOREIGN KEY (
	`sch_id`
)
REFERENCES `screening_schedule` (
	`sch_id`
);

ALTER TABLE `screening_schedule` ADD CONSTRAINT `FK_theater_TO_screening_schedule_1` FOREIGN KEY (
	`theater_id`
)
REFERENCES `theater` (
	`theater_id`
);

ALTER TABLE `screening_schedule` ADD CONSTRAINT `FK_movie_info_TO_screening_schedule_1` FOREIGN KEY (
	`movie_cd`
)
REFERENCES `movie_info` (
	`movie_cd`
);

ALTER TABLE `review_reply` ADD CONSTRAINT `FK_review_board_TO_review_reply_1` FOREIGN KEY (
	`board_num`
)
REFERENCES `review_board` (
	`board_num`
);

ALTER TABLE `review_reply` ADD CONSTRAINT `FK_user_info_TO_review_reply_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_like_board` ADD CONSTRAINT `FK_user_info_TO_user_like_board_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_like_board` ADD CONSTRAINT `FK_review_board_TO_user_like_board_1` FOREIGN KEY (
	`board_num`
)
REFERENCES `review_board` (
	`board_num`
);

ALTER TABLE `user_dislike_board` ADD CONSTRAINT `FK_user_info_TO_user_dislike_board_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_dislike_board` ADD CONSTRAINT `FK_review_board_TO_user_dislike_board_1` FOREIGN KEY (
	`board_num`
)
REFERENCES `review_board` (
	`board_num`
);

ALTER TABLE `user_like_reply` ADD CONSTRAINT `FK_user_info_TO_user_like_reply_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_like_reply` ADD CONSTRAINT `FK_review_reply_TO_user_like_reply_1` FOREIGN KEY (
	`reply_num`
)
REFERENCES `review_reply` (
	`reply_num`
);

ALTER TABLE `user_dislike_reply` ADD CONSTRAINT `FK_user_info_TO_user_dislike_reply_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_dislike_reply` ADD CONSTRAINT `FK_review_reply_TO_user_dislike_reply_1` FOREIGN KEY (
	`reply_num`
)
REFERENCES `review_reply` (
	`reply_num`
);

ALTER TABLE `user_like_movie_comment` ADD CONSTRAINT `FK_user_info_TO_user_like_movie_comment_1` FOREIGN KEY (
	`user_key`
)
REFERENCES `user_info` (
	`user_key`
);

ALTER TABLE `user_like_movie_comment` ADD CONSTRAINT `FK_movie_comment_TO_user_like_movie_comment_1` FOREIGN KEY (
	`comment_num`
)
REFERENCES `movie_comment` (
	`comment_num`
);
