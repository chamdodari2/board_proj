<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--     <%ArrayList<BordBean> articleList = (ArrayList<BoardBean>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int listCount = pageInfo.getListCount();
    int nowPage = pageInfo.getPage();
    int maxPage=pageInfo.getMaxPage();
    int startPage=pageInfo.getStartPage();
    int endPage=pageInfo.getEndPage();
    
    %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="css/style2.css">
</head>
<body>
<section id = "listForm">
<h2>
글 목록 <a href="bordWriteForm.do">게시판 글쓰기</a>
</h2>
<table>
<%-- <%if(articleList != null&& listCount > 0) %> --%>
</table>
</section>
</body>
</html>