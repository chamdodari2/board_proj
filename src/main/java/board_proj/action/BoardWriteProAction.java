package board_proj.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action { //액션구현쓰 참조할수있당

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//BOARD_NAME=aaa&BOARD_PASS=aaa&BOARD_SUBJECT=aaa&BOARD_CONTENT=aaa&BOARD_FILE=math_img_1.jpg
	//	
		
		//이미지파일을 보드업로드에!
		String realFolder = "";
		String saveFolder ="/boardUpload";
		int fileSize = 5*1024*1024; //5M
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_name(multi.getParameter("BOARD_NAME"));
		boardDto.setBoard_pass(multi.getParameter("BOARD_PASS"));
		boardDto.setBoard_subject(multi.getParameter("BOARD_SUBJECT"));
		boardDto.setBoard_content(multi.getParameter("BOARD_CONTENT"));
		boardDto.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		//위에꺼 다 출력되는지 보장
		System.out.println("realFolder >>" + realFolder);
		System.out.println("boardDto >>" + boardDto);
		
		//service
		BoardWriteService service = new BoardWriteService();
		boolean result = service.registerArticle(boardDto);
	
		
		ActionForward forward = null;
		if(result) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do");  //일로 보내기
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
			
		}
		
		return forward;
	}

}
