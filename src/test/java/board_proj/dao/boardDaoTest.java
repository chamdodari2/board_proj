package board_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

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
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum()");
		int res = dao.nextBoardNum();
		System.out.println(res);
		Assert.assertNotEquals(0,res);
		System.out.println("next Number = "+ res);
	}
	
	
	@Test
	public void testSelectListCount() {
		fail("Not yet implemented");
	}

	@Test
	public void test02SelectArticeleList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test02InsertArticle() {///////////
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
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateReadCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsArticleBoardWriter() {
		fail("Not yet implemented");
	}

}