package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardReplyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardReplyService() {
	 dao.setCon(con);
	}
	
	
	
	public boolean isArticleWriter(int board_num, String pass) {
		return dao.isArticleBoardWriter(board_num, pass);
	}
	
	
	public boolean modifyArticle(BoardDto article) {
		return dao.updateArticle(article) == 1? true:false;
	}
	
	public BoardDto getArticle(int board_num){
		//조회수 증가
		//board_num에 해당하는 BoardDto return;
		
		return dao.selectArticle(board_num);
	
	}
}
