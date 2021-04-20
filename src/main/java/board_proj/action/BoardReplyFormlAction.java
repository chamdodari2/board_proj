package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardDetailService;

public class BoardReplyFormlAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		String page = request.getParameter("page");
		int board_num =Integer.getInteger(request.getParameter("board_num"));
		BoardDetailService service = new BoardDetailService();
		BoardDto article = service.getArticle(board_num);
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		forward.setPath("/board/qna_board_reply.jsp");
		
		return null;
	}

}
