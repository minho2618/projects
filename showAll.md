<%@page import="servlet.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	
	<style>
	body {
		border: 5px solid black;
		width: 800px;
		margin: 10px;
		padding: 10px;
	}
	</style>
</head>
<body>
	<div class="jumbortorn text-center">
		<h2>Product List</h2>
	</div>
	
	<table class="table table-dark">
	  <thead>
	    <tr>
	      <th scope="col">도서번호</th>
	      <th scope="col">도서제목</th>
	      <th scope="col">도서종류</th>
	      <th scope="col">출판국가</th>
	      <th scope="col">출 판 일</th>
	      <th scope="col">출 판 사</th>
	      <th scope="col">저 자</th>
	      <th scope="col">도서가격</th>
	      <th scope="col">요약내용</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<%
		for (Book b : bookList) {
		%>
		<tr>
	      <th scope="row"><%= b.getIsbn() %></th>
	      <td><%= b.getTitle() %></td>
	      <td><%= b.getCatalogue() %></td>
	      <td><%= b.getNation() %></td>
	      <td><%= b.getPublish_date() %></td>
	      <td><%= b.getPublisher() %></td>
	      <td><%= b.getAuthor() %></td>
	      <td><%= b.getPrice() %></td>
	      <td><%= b.getDescription() %></td>
	    </tr>
		<%
		}
		%>
	  </tbody>
	</table>
	
	
	<h2><a href="index.html">INDEX</a></h2>
</body>
</html>