
<%@page import="board_proj.ds.JndiDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>

<%-- <%=JndiDS.getConnection()%> --%>
<a href="boardWriteForm.do">게시판 글쓰기</a>  <!-- 이쪽 서블렛으로 링크쓰 -->
</body>
</html>

<!-- http://localhost:8080/board_proj/boardList.do?page=1 -->