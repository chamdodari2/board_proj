package board_proj.action;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//BOARD_NAME=aaa&BOARD_PASS=aaa&BOARD_SUBJECT=aaa&BOARD_CONTENT=aaa&BOARD_FILE=math_img_1.jpg
		ActionForward forward = null;
		
		//이미지파일을 보드업로드에!
		String realFolder = "";
		String saveFolder ="/boardUpload";
		int fileSize = 5*1024*1024; //5M
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(
				request,
				realFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_name(multi.getParameter("BOARD_NAME"));
		boardDto.setBoard_pass(multi.getParameter("BOARD_PASS"));
		boardDto.setBoard_subject(multi.getParameter("BOARD_SUBJECT"));
		boardDto.setBoard_content(multi.getParameter("BOARD_CONTENT"));
		boardDto.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		//위에꺼 다 출력되는지 보장
		System.out.println("realFolder >>" + realFolder);
		System.out.println("boardDto >>" + boardDto);
		BoardWriteService service = new BoardWriteService();
		Boolean result = service.registerArticle(boardDto);
		if(!result) {
			
		}else {
			
		}
		
		return null;
	}

}
