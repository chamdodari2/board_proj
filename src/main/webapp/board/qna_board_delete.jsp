<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="board/css/qna_board_delete.css">
</head>
<body>
	${page }
	<br> ${board_num }


	<section id="passForm">
		<form name="deleteForm"
			action="boardDeletePro.do?board_num=${board_num}" method="post">
			<input type="hidden" name="page" value="${page}" /> <input
				type="hidden" name="board_num" value="${board_num}" />

			<table>
				<tr>
					<td><label>글 비밀번호 : </label></td>
					<td><input name="board_pass" type="password"></td>
					<td><input type="submit" value="삭제" /> &nbsp;&nbsp; <input
						type="button" value="돌아가기" onclick="javscript:history.go(-1)" /></td>
				</tr>
			</table>
		</form>

	</section>
</body>
</html>