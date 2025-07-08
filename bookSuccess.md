<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String bookTitle = request.getParameter("bookTitle"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
	.result {
		text-align: center;
	}
	
	.title {
		color: gold;
	}
	</style>
</head>
<body>
	<div class="result">
		<h2>결과 페이지</h2>
		<br>
		<span class="title"><%= bookTitle %></span> 정상적으로 저장 되었습니다.
		<br>
		<br>
		<a href="./book.html">추가 등록</a>
		<a href="AllBookServlet">도서 목록</a>
	</div>
</body>
</html>