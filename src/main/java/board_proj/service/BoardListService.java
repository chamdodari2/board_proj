package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardListService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardListService() {
	 dao.setCon(con);
	}
	
	public boolean registerArticle(BoardDto boardDto) { //한개만 테스트할경우 실패하면 자동롤백되기때문에 사실 필요없다고한당
		return dao.insertArticle(boardDto) == 1 ? true : false;
	}
	
	public int getListCount() {
		return dao.selectListCount();
	}
	public ArrayList<BoardDto> getArticleList(int page,int limit){
		return dao.selectArticeleList(page, limit);
	}
}
