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
        <button type="button" onclick="location.href='/updateCategoryForm.do?categoryId=${category.categoryId}'">카테고리 수정</button>
        
        <!-- delete category -->
        <form action="/deleteCategory.do" method="post">
        	<button type="submit" name="categoryId" value="${category.categoryId}" onclick="return confirm('카테고리를 삭제하시겠습니까? (카테고리에 저장된 북마크가 있을 경우, 북마크와 메모 또한 같이 삭제됩니다.)')">
        	카테고리 삭제
        	</button>
        </form>
        
        <!-- save Bookmark -->
        <button type="button" onclick="location.href='/bookmark/insert/form?categoryName=${category.categoryName}'">북마크 추가</button>
        
        <!-- bookmark test -->
        <c:forEach items="${requestScope.bookmarkList}" var="bookmark">
        	<input type="hidden" name="bookmarkId" value="${bookmark.bookmarkId}">
        	<div>
	        	<tr>
	        		<td>
	        		<a href="/bookmark/detail?bookmarkId=${bookmark.bookmarkId}">${bookmark.bookmarkName}</a>
	        			<button type="button" class="btn btn-primary" onclick="location.href='${bookmark.url}'">url 이동</button>
	        		</form>
	        		</td>
	        	</tr>
        	</div>
        </c:forEach>
        
        <div>
        	<button class="main-btn" onclick="location.href='/mainPage.do'">메인으로</button>
        </div>
    </div>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
