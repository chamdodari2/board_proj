package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDetailService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDetailService() {
	 dao.setCon(con);
	}
	
	
	
	public BoardDto getArticle(int board_num){
		//조회수 증가
		//board_num에 해당하는 BoardDto return;
		dao.updateReadCount(board_num);  //제목 누르면 조회수 증가시키고, 해당 글번호에 맞는내용 출력
		return dao.selectArticle(board_num);
	
	}
}
