package board_proj.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardReplyProService;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		BoardDto article = boardArticle(request);
		System.out.println("article >>>> "+article);
		
		
		BoardReplyProService service = new BoardReplyProService();
		boolean res = service.replyArticle(article);
		System.out.println("res >>>  " + res);
		ActionForward forward = null;
		if(res) {
			forward = new ActionForward("boardList.do?page="+ page , true);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert (' 답변 등록 실패 ')");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		return forward;
		 

	}

	private BoardDto boardArticle(HttpServletRequest request) {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int board_re_ref = Integer.parseInt(request.getParameter("board_re_ref"));
		int board_re_lev = Integer.parseInt(request.getParameter("board_re_lev"));
		int board_re_seq = Integer.parseInt(request.getParameter("board_re_seq"));
	
		String board_name =  request.getParameter("board_name");
		String board_pass =  request.getParameter("board_pass");
		String board_subject =  request.getParameter("board_subject");
		String board_content =  request.getParameter("board_content");
		
	
		return new BoardDto(board_num, board_name, board_pass, board_subject, board_content, "",
				board_re_ref, board_re_lev, board_re_seq, 0, null);
	}

}
