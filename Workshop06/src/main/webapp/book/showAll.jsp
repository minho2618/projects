
<%@page import="web.servlet.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<title>Insert title here</title>
<STYLE TYPE="text/css"> table {font-size: 9pt;} </STYLE>
</head>
<body>
	<div class="container">
	<div class="jumbotron text-center">
		<h2>전체 도서 List 보기</h2>
	</div>
	<%
		ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("results");
	%>
	
	<table class="table">
		<thead class="thead-dark">
			<tr>
			<th>ISBN</th>
			<th>TITLE</th>
			<th>Catalogue</th>
			<th>Nation</th>
			<th>Publish Date</th>
			<th>Publisher</th>
			<th>Author</th>
			<th>Price</th>
			<th>Description</th>
			</tr>
		</thead>
		<%
			for (Book bk: books) {
		%>
			<tr>
				<td><%= bk.getIsbn() %></td>
				<td><%= bk.getTitle() %></td>
				<td><%= bk.getCatalogue() %></td>
				<td><%= bk.getNation() %></td>
				<td><%= bk.getPublish_date() %></td>
				<td><%= bk.getPublisher() %></td>
				<td><%= bk.getAuthor() %></td>
				<td><%= bk.getPrice() %>원</td>				
				<td><%= bk.getDescription() %></td>
			</tr>
		<%
			}
		%>
	</table>
	
	<h3 align="center"><a href="Book.html">등록 화면으로</a></h3>
	<h3 align="center"><a href="../login.html">로그인 화면으로</a></h3>
	</div>
</body>
</html>