
-- 답변
select  * from board  where BOARD_RE_REF =78;

update board set BOARD_RE_SEQ  = BOARD_RE_SEQ +1 where BOARD_RE_REF =23 and BOARD_RE_SEQ  0?

-- 23번에 대해서 답변 
update board set BOARD_RE_SEQ  = BOARD_RE_SEQ +1 where BOARD_RE_REF =23 and BOARD_RE_SEQ  0?

-- 입력 . 답변글에서는 파일은 받지않기 근데 낫널로 설정해줘서 아무것도 없는거라도 넣어줘야함

insert into board (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)  
values (26,'김상건?111','111',,,,,,);




select max(board_num) from board ; 

INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE)
-- read board
UPDATE BOARD
SET BOARD_READCOUNT = BOARD_READCOUNT +1
WHERE BOARD_NUM =23;


VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt', 0, 0, 0, 0, '2021-03-03');


INSERT INTO web_gradle_erp.board
(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF)
VALUES(1, '김상건', '1111', '마칠시간', '5시', 'test.txt',0);


select max(board_num) from board;

select * from board ;

select  BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, (BOARD_DATE now())  from board;


/* list 페이징
 * 
 *  [1]번 누를경우, [2]번 누를경우, [3]번 누를경우에 따라 아래 각 페이지에 게시물들이 보여야한다.
 *  (page -1 ) *10  -> 1 page는  0번게시물부터, 2 page = 10번게시물부터 시작,  3page = 20번째게시물부터  10개 보여줌
 * 
 * */
select  BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc  
limit 0,10; 
/*1ㅠㅔ이지부터 10개를 말함*/


select  BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
from board
order by BOARD_RE_REF desc, BOARD_RE_SEQ asc  
limit 10,10; 



SELECT * FROM board WHERE board_num =26;
SELECT 1 FROM board WHERE board_num =25 and board_pass = '1234'; /* 넘어온 비번값이 1234냐*/ 

DELETE
FROM board WHERE board_num = 26;

select BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE from board
order by BOARD_RE_REF desc,  BOARD_RE_SEQ;


update board  set BOARD_SUBJECT ='aaa',BOARD_CONTENT='aaa'
where BOARD_NUM =77;