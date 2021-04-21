package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  {
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		BoardModifyService service = new BoardModifyService();
		
		ActionForward forward = null;
		
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		
		try {
		if (!isArticleWriter) {
			sendMessage(response, "수정할 권한이 없습니다.");
			return forward;
		}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		BoardDto article = new BoardDto();
		article = service.getArticle(board_num);
		
		
		String board_subject = request.getParameter("board_subject");
		article.setBoard_subject(board_subject);
		
		String board_content =request.getParameter("board_content");
		article.setBoard_content(board_content);
		
		
		boolean isModifySuccess = service.modifyArticle(article);
		
		try {
		if (!isModifySuccess) {
			sendMessage(response, "수정 실패");
			return forward;

		}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardDetail.do?board_num=" + article.getBoard_num()+"&page="+ page);
		return forward;
 	
		
	}

	private void sendMessage(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
	}

}
