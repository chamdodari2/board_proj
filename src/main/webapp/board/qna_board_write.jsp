<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="board/css/style.css"> <!--  href="board/css/style.css"  -->
</head>
<body>
<section id = "writeForm">
	<h2>게시판글등록</h2>
	<form action="boardWritePro.do" method = "post" enctype="multipart/form-data" name="boardform">
	<table>
	<tr>
	<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
	<td class="td_right"><input type="text" name="BOARD_NAME" id="BOARD_NAME" required /></td>
	</tr>
		<tr>
	<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
	<td class="td_right"><input type="password" name="BOARD_PASS" id="BOARD_PASS" required /></td>
	</tr>
		<tr>
	<td class="td_left"><label for="BOARD_SUBJECT">제목</label></td>
	<td class="td_right"><input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" required /></td>
	</tr>
	<tr>
	<td class="td_left"><label for="BOARD_CONTENT">내용</label></td>
	<td><textarea id ="BOARD_CONTENT" name="BOARD_CONTENT" cols="40" rows="15" required></textarea></td>
	</tr>
	<tr>
	<td class="td_left"><label for="BOARD_FILE">파일첨부</label></td>
	<td class="td_right"><input type="file" name="BOARD_FILE" id="BOARD_FILE" required /></td>
	</tr>
	
	
	</table>
	<section id ="commandCell">
	<input type="submit" value="등록"/>&nbsp;&nbsp;
	<input type="reset" value="다시쓰기" />
	
	
	</section>
	</form>
</section>
<br>

</body>
</html>