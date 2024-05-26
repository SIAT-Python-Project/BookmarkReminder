<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${category.categoryName}</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>${category.categoryName}</h1>
        <!-- update category -->
        <form action="/updateCategory.do" method="post"></form>
        
        <!-- delete category -->
        <form action="/deleteCategory.do" method="post">
        	<button type="submit">카테고리 삭제</button>
        </form>
        
        <!-- save Bookmark -->
        
        <!-- bookmark test -->
        <c:forEach items="${requestScope.bookmarkList}" var="bookmark">
        	<input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
        	<div>
	        	<tr>
	        		<td>
	        		<a href="/bookmark/detail?bookmarkId=${bookmark.bookmarkId}">${bookmark.bookmarkName}</a>
	        		<form action="${bookmark.url}" method="get">
	        			<button type="sumbit" class="btn btn-primary">url 이동</button>
	        		</form>
	        		</td>
	        	</tr>
        	</div>
        </c:forEach>
    </div>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
