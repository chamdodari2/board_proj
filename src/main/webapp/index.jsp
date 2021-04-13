
<%@page import="board_proj.ds.JndiDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
방가~~!
<%-- <%=JndiDS.getConnection()%> --%>
<a href="boardWriteForm.do">게시판 글쓰기</a>  <!-- 이쪽 서블렛으로 링크쓰 -->
</body>
</html>