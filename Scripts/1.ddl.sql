
drop table if exists web_gradle_erp.board;

/*use web_gradle_erp;*/

create table if not exists web_gradle_erp.board(
   BOARD_NUM INT,
   BOARD_NAME VARCHAR(20) NOT NULL,
   BOARD_PASS VARCHAR(15) NOT NULL,
   BOARD_SUBJECT VARCHAR(50) NOT NULL,
   BOARD_CONTENT VARCHAR(2000) NOT NULL,
   BOARD_FILE VARCHAR(50) NOT NULL,
   
   BOARD_RE_REF INT NOT NULL,
   
   BOARD_RE_LEV INT DEFAULT 0,     /*전부 0으로 들어간당.*/
   BOARD_RE_SEQ INT DEFAULT 0,
   BOARD_READCOUNT INT DEFAULT 0,  /*not null보다 default 0 이 좋다*/
   
   BOARD_DATE DATEtime default current_timestamp,
   PRIMARY KEY(BOARD_NUM)
); 