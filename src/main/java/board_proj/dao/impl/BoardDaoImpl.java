package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {

	private static final BoardDaoImpl instance = new BoardDaoImpl();

	public static BoardDaoImpl getInstance() { // 싱글턴패턴
		return instance;

	}

	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<BoardDto> selectArticeleList(int page, int limit) {
		return null;
	}

	@Override
	public BoardDto selectArticle(int board_num) {
		return null;
	}

	@Override /////////////////////
	public int insertArticle(BoardDto article) { //BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, db에서 0으로 초기화해줘서 무조건 입력할필요없당
		String sql = "INSERT INTO board" 
				   + " (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE,"
				   + " BOARD_RE_REF) VALUES" //1번글에 대한 답글 써야해서 같은걸 가져와야함
				   + " (? , ? , ? , ? , ? , ? , ?)";
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			int nextNum =nextBoardNum();
			pstmt.setInt(1, nextNum );
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, nextNum);
//			pstmt.setInt(7, article.getBoard_re_ref());
//			pstmt.setInt(8, article.getBoard_re_lev());
//			pstmt.setInt(9, article.getBoard_re_seq());
//			pstmt.setInt(10, article.getBoard_readcount());
		
			return pstmt.executeUpdate();
		}catch( SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertReplyArticle(BoardDto article) {
		return 0;
	}

	@Override
	public int updateArticle(BoardDto article) {
		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReadCount(int board_num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isArticleBoardWriter(int board_num, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(board_num) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery()) {		
			if (rs.next()) {		
				return rs.getInt(1) + 1;  //현재 있는 게시물 번호 중에 제일 큰값. +1로 게시물을 넣을거당
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
