<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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
<link rel="stylesheet" href="board/css/style2.css">   <!--  -->
</head>
<body>
<section id = "pageList">
<h2>
글 목록</h2>
<h4><a href="boardWriteForm.do">게시판 글쓰기</a></h4>

<table class = "tbl_type" border="1">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	<tr>
		<c:forEach var="board" items="${articleList}" > 
		<td>${board.board_num}</td>
		<td>
		   <a href="boardDetail.do?board_num=${ board.board_num}&page=${pageInfo.page}"> ${board.board_subject} </a><!--제목에 링크걸기  -->
		<td>${board.board_name}</td>
		<td>${board.board_date}</td>
		<td>${board.board_readcount}</td>
		</tr>
		</c:forEach>

<%-- <%if(articleList != null&& listCount > 0) %> --%>
</table>
</section>
<c:if test="${pageInfo.listCount > 0}"></c:if>
<%-- ${pageInfo}
 --%>
 <section id ="pageList">
	<c:if test="${pageInfo.page <= 1}">
	[이전]&nbsp; 
	</c:if> 
	<c:if test="${pageInfo.page > 1}"><!--  11페이지면 이전페이지가 있는건데 그걸 처리해야햐ㅏㅁ -->
	<a href="boardList.do?page=${pageInfo.page -1}">[이전]</a>&nbsp;
	</c:if> 

<c:forEach var="a" begin="1" end="${pageInfo.endPage}">

<c:if test="${a == pageInfo.page}">
	<span>[${a}]</span>&nbsp;
</c:if>				<!-- 지금 보고있는 페이지와 같다라면 -->

<c:if test="${a ne pageInfo.page}">
<a href="boardList.do?page=${a}">[${a}]</a>&nbsp;
</c:if>
</c:forEach>
<c:if test="${pageInfo.page >= pageInfo.maxPage}">
[다음]&nbsp; 
</c:if> 

<c:if test="${pageInfo.page < pageInfo.maxPage}"> <!--  11페이지면 이전페이지가 있는건데 그걸 처리해야햐ㅏㅁ -->
<a href="boardList.do?page=${pageInfo.page +1}">[다음]</a>&nbsp;
</c:if> 

</section>

</body>
</html>