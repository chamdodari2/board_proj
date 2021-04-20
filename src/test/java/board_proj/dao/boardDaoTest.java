package board_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.JdbcUtil;
import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDto;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class boardDaoTest {
	
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
		dao.setCon(con);
	}

	
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void testInsertReplyArticle() {
	System.out.println();

	}
	@Test
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum()");
		int res = dao.nextBoardNum();
		System.out.println(res);
		Assert.assertNotEquals(0,res);
		System.out.println("next Number = "+ res);
	}
	
	
	@Test
	public void test02SelectListCount() {
		System.out.println("testSelectListCount()");
		int res = dao.selectListCount();
		Assert.assertNotEquals(-1, res);
		System.out.println("res >>>" + res);
		
//		for (BoardDto t : list) {
//			System.out.println(t);
//		}
	}
	

	@Test
	public void test03SelectArticeleList() {
		System.out.println("검색");
		int page = 1;
		int limit =10;
		ArrayList<BoardDto> list = dao.selectArticeleList(page, limit);
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println("===============");
		dao.selectArticeleList(2, 10).stream().forEach(System.out::println);
	}

	@Test
	public void testSelectArticle() {
		System.out.println("testSelectArticle()");
		BoardDto board = dao.selectArticle(1);
		Assert.assertNotNull(board);
		System.out.println(board);
	}

	@Test
	public void test04InsertArticle() {///////////
		System.out.println("test02InsertArticle()");
		BoardDto newBoard = new BoardDto(
		
				"김상건",
				"1234",
				"5시 퇴근 가능할까?",
				"불가능함",
				"test.txt"
				
				);
		int res = dao.insertArticle(newBoard);
		Assert.assertEquals(1, res);
		
		
	}



	@Test
	public void test08UpdateArticle() {
		System.out.println("test08UpdateArticle()");
		int board_num=22;
		BoardDto article = dao.selectArticle(board_num);
		int res =dao.updateArticle(article);
		Assert.assertEquals(1, res);
		System.out.println("res >>>" + res);
		

	}

	@Test
	public void test09DeleteArticle() {
		System.out.println("test09DeleteArticle()");
		int board_num = dao.nextBoardNum() -1;  //최근에 등록한거! 삭제
		int res = dao.deleteArticle(board_num);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void testUpdateReadCount() {
		System.out.println("testUpdateReadCount()");
		int res =dao.updateReadCount(1);
		Assert.assertEquals(1, res);
		

	}

	@Test
	public void test07IsArticleBoardWriter() { //딜리트 하기전에 비번 맞느지 확인하기 쓴사람만 삭제가능
		System.out.println("test07IsArticleBoardWriter()");
		int board_num = 78; //22번! 삭제
		boolean res = dao.isArticleBoardWriter(board_num, "11");
		Assert.assertEquals(true, res);
		System.out.println("res >> " + res);
	}

}
