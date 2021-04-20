package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyService;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int page = Integer.parseInt(request.getParameter("page"));
	//	System.out.println("board_num >>" + board_num);
		BoardModifyService service = new BoardModifyService();
		
		BoardDto article = service.getArticle(board_num);
		
		request.setAttribute("article",article);
		request.setAttribute("page", page);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_modify.jsp");
		return forward;
	}

}
