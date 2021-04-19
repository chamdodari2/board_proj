<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="board/css/text.css">
</head>
<body>
	<%-- ${page }
	<br> ${article } --%>
	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<section id="basicInfoArea">
			제목 : ${article.board_subject }
				 첨부파일 : 
				 <c:if test="${article.board_file ne null }">
					<a href="boardFileDownPro.do?downFile=${article.board_file }"> ${article.board_file}</a>
				</c:if>
			

		</section>
		<section id="articleContentArea">
			${article.board_content }
		</section>
	</section>
<section id="commandList">
		<a href="boardReplyForm.do?board_num=${article.board_num }&page=${page}">[답변]</a>
		<a href="boardModifyForm.do?board_num=${article.board_num }">[수정]</a>
		<a href="boardDeleteForm.do?board_num=${article.board_num }&page=${page}">[삭제]</a>
		<a href="boardList.do?page=${page}">[목록]</a>
	</section>
</body>
</html>