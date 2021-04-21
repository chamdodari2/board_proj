package board_proj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.BoardDeleteFormAction;
import board_proj.action.BoardDeleteProAction;
import board_proj.action.BoardDetailAction;
import board_proj.action.BoardFileDownProAction;
import board_proj.action.BoardListAction;
import board_proj.action.BoardModifyFormAction;
import board_proj.action.BoardModifyProAction;
import board_proj.action.BoardReplyFormAction;
import board_proj.action.BoardReplyProAction;
import board_proj.action.BoardWriteFormAction;
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
String command = request.getServletPath();
		
		System.out.println(command);
		
		ActionForward forward=null;
		Action action = null;
		
		try {
			if (command.equals("/boardWriteForm.do")) {
				action = new BoardWriteFormAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardWritePro.do")) {
				action = new BoardWriteProAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardList.do")) {
				action = new BoardListAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardDetail.do")) {
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardDeleteForm.do")) {
				action = new BoardDeleteFormAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardDeletePro.do")) {
				action = new BoardDeleteProAction();
				forward = action.execute(request, response);
			}else if (command.equals("/file_down.do")) {
				action = new BoardFileDownProAction();  //////////Pro? 쌤꺼는 Pro아닌디 이래해도되나
				forward = action.execute(request, response);
			}else if (command.equals("/boardModifyForm.do")) {
				action = new BoardModifyFormAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardModifyPro.do")) {
				action = new BoardModifyProAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);
			}else if (command.equals("/boardReplyPro.do")) {
				action = new BoardReplyProAction();
				forward = action.execute(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
	}

}
