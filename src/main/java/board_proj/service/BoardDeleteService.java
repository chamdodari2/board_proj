package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDeleteService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDeleteService() {
	 dao.setCon(con);
	}
	
	public boolean isArticleWriter(int board_num, String pass) {
		return dao.isArticleBoardWriter(board_num, pass);
	}
	public boolean removeArticle(int board_num) {
		return dao.deleteArticle(board_num) == 1 ? true :false;  //1이면 삭제성공, 아니면 실패시키기
	}

}
