package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
		String sql = "select count(*) from board";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch (SQLException e ) {
			e.printStackTrace();
		}
			
		return 0;
	}

	@Override
	public ArrayList<BoardDto> selectArticeleList(int page, int limit) { //테이블목록 출력하기!
		String sql =  "select  BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, "
					+ "BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE " 
					+ "from board " 
					+ "order by BOARD_RE_REF desc, BOARD_RE_SEQ asc "
					+ "limit ? , ? ";
			int startRow = (page-1) * limit; //해당 페이지 시작위치
			try(PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						ArrayList<BoardDto> list = new ArrayList<BoardDto>();
						do {
							list.add(getBoardDto(rs));
						}while(rs.next());
						return list;
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return null;
	}

	private BoardDto getBoardDto(ResultSet rs) throws SQLException {
		// BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, 
		//BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE 
		
		int board_num =rs.getInt("BOARD_NUM");
		String board_name =rs.getString("BOARD_NAME");
		String board_pass =rs.getString("BOARD_PASS");
		String board_subject =rs.getString("BOARD_SUBJECT");
		String board_content =rs.getString("BOARD_CONTENT");
		String board_file =rs.getString("BOARD_FILE");
		int board_re_ref =rs.getInt("BOARD_RE_REF");
		int board_re_lev =rs.getInt("BOARD_RE_LEV");
		int board_re_seq =rs.getInt("BOARD_RE_SEQ");
		int board_readcount =rs.getInt("BOARD_READCOUNT");
		Date board_date =rs.getDate("BOARD_DATE");
	
		
		return new BoardDto(board_num, board_name, board_pass, board_subject, board_content, board_file, board_re_ref, board_re_lev, board_re_seq, board_readcount, board_date);
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
