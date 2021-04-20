<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="css/qna_board_modify.css">
<script type="text/javascript">
function modifyboard(){
	modifyform.submit();
}
</script>
</head>
<body>

<section id = "writeForm">

<h2>게시판글 수정</h2>
<form action="boardModifyPro.do" method="post" name="modifyform">
<input type ="hidden" name="board_num" value = "${article.board_num }"/> <!-- 여기서 안되면 소문자로 수정하기 -->
<input type ="hidden" name="page" value = "${page }"/>
<table>
<tr>
<td class="td_left">
<label for="board_name">글쓴이</label>
</td>
<td class="td_right"><input type="text" name ="board_name" id="board_name" value="${article.board_name }" />
</td>
</tr>
<tr>
<td  class="td_left">
<label for = "board_pass">비밀번호 </label></td>
<td class="td_right">
 <input type="password" name ="board_pass" id="board_pass"/>

</td>
</tr>
<tr>
<td class="td_left"> <label for = "board_subject">제목 </label>
</td>
<td class="td_right"> <input name = "board_subject" type="text" id="board_subject" value="${article.board_subject }"/>

</td>


</tr>
<tr>
<td class="td_left"> <label for="board_content">내용 </label>
</td>
<td>
<textarea id="board_content" name="board_content" rows="15" cols="40">${article.board_content }</textarea>
</td>
</tr>
</table>
<section id = "commandCel">
<a href="javascript:modifyboard()">[수정]</a>&nbsp;
<a href="javascript:history.go(-1)">[뒤로]</a>
</section>
</form>
</section>
</body>
</html>