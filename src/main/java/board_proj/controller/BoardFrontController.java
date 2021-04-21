package board_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.BoardDeleteProAction;
import board_proj.action.BoardDetailAction;
import board_proj.action.BoardFileDownProAction;
import board_proj.action.BoardListAction;
import board_proj.action.BoardModifyFormAction;
import board_proj.action.BoardModifyProAction;
import board_proj.action.BoardReplyFormAction;
import board_proj.action.BoardReplyProAction;
import board_proj.action.BoardWriteProAction;
import board_proj.dto.ActionForward;

@WebServlet("*.do") // 모든 확장자가 do인 애들이 부르면 각 요청에 맞는 jsp로 포워딩쓰
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//		String requestURI= request.getRequestURI();
//		String contextPath = request.getContextPath(); 이거두개 대신 밑에 한줄로 해결쓰
		String command = request.getServletPath();
//		String command= requestURI.substring(contextPath.length());		
//		System.out.println(requestURI + " >> " + contextPath + " >>" + command);
		System.out.println(command);

		ActionForward forward = null; // 저밑에 포워딩하기가 서블릿마다 다 다르기 때문에 요거를 캡슐화 한거임. 다형성으로 -> 메소드는 동일한데 결과가 다르게 나온느것 의미
		Action action = null;

		// 여기서 구분쓰
		if (command.equals("/boardWriteForm.do")) { // 얘(게시판글쓰기) 불렀으면
		//	request.getRequestDispatcher(forward.getPath()).forward(request, response);
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp"); // boardWriteForm.do(게시판글쓰기) 불렀으면 일로가룜
		} else if (command.equals("/boardWritePro.do")) { // 게시판 글등록 화면에서 등록 버튼 누르면 얘 호출하게된다.
			
			// BOARD_NAME=aaa&BOARD_PASS=aaa&BOARD_SUBJECT=aaa&BOARD_CONTENT=aaa&BOARD_FILE=math_img_1.jpg
			// 전송한 내용을 받아와서 인서트 해야한당. 서비스 만들어서!!
			System.out.println("boardWritePro.do 불른거맞으면  BoardWriteProAction(서블렛)으로 이동해서 수행!");
			action = new BoardWriteProAction(); // 1 생성은 여기, 수행은 오버라이딩 한곳에서 BoardWriteProAction
			try {
				forward = action.execute(request, response); // 여기로 전가하는것. 네가 처리해라!! 결과값은 내가 받을겡 포워딩할지 리다이렉트할지
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardList.do")) {
			action = new BoardListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("boardList >>>>>>>  52뜬당. 입력되어있는 갯수");
		} else if (command.equals("/boardDetail.do")) {
			// 게시글 제목 누르면
			action = new BoardDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardReplyForm.do")) {
			action = new BoardReplyFormAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/boardDeleteForm.do")) { // 4.19
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);

			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);

			forward = new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		} else if (command.equals("/boardDeletePro.do")) {
			// voardDeletePro.do?board_num=26&page=1

			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}//http://localhost:8080/board_proj/file_down?downFile=windows.png
		else if (command.equals("/boardFileDownPro.do")) { //얘 부르면
			action = new BoardFileDownProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/boardModifyForm.do")) { //  >> Pro.do 로 이동
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/boardModifyPro.do")) { //  >> Pro.do 로 이동
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/boardReplyForm.do")) { // 답변글
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/boardReplyPro.do")) { // 답변글
			action = new BoardReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);

			}

		}

	}

}
