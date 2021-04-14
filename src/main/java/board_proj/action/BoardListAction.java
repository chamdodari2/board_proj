package board_proj.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.dto.PageInfo;
import board_proj.service.BoardListService;

public class BoardListAction implements Action {

	@Override // 결국 얘가 수행되는거당
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 페이지로이동
		int page = 1; // 한 화면에
		int limit = 10; // 보일 게시글 수
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		BoardListService service = new BoardListService();

		ArrayList<BoardDto> list = service.getArticleList(page, limit);
		list.stream().forEach(System.out::println);

		// 총 리스트 갯수
		int listCount = service.getListCount();

		// 21.0 /5 = 5
		int maxPage = (int) Math.ceil((double) listCount / limit);

		// 1 page 1~5, 2 page 6~10, 3page 11~15....
		// 11 page 51~56,
		// [이전] [1][2][3][4][5][6][7][8][9][10][다음]
		// [이전] [11][12][13][14][15][16][17][18][19][20][다음]
		// [이전] [21][22][23][24][25][26][27][28][29][30][다음]
		// [이전] [31][32][33][다음]
		//page = 16;

		int startPage = (int) Math.floor(page / 10) * 10 + 1;
		// int startPage = (((int) ((double) page / limit + 0.9)) - 1) * 10 + 1;
		// 안되는거 int startPage2 = (page / limit) + 1; // 6페이지라면 61번째 게시물 ---> (61/10)
		int endPage = startPage + 9;
		if (endPage > maxPage) { // maxPage가 23을 가리키고있당
			endPage = maxPage;
		}
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		// int startPage = ((int) (Math.ceil((double) page / limit) - 1) * 10) + 1; //
		// ceil로 반올림 해줘ㅏ야한다. 이 스타트 페이지는 [1] 인지
		// 찾는거다


		request.setAttribute("articleList", list);
		request.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();		
		forward.setPath("/board/qna_board_list.jsp"); // 포워딩쓰
		return forward;

	}

}
