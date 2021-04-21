package board_proj.action; //p590

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardDetailService;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		///boardDetail.do?board_num=59&page=2
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int page = Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page",page);
		
		BoardDetailService service = new BoardDetailService();
		BoardDto article = service.getArticle(board_num);
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_view.jsp");
		return forward;
	}

}
