<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${category.categoryName}</title>
    <link rel="stylesheet" type="text/css" href="/static/css/layout/layout.css">
	<link rel="stylesheet" type="text/css" href="/static/css/category/categorydetail.css">
</head>
<%@ include file="/views/layout/header.jsp" %>
<body>
    <div>
    	<div class="background">
	    	<div class="category">
	    		<div id="category-title" class="btn-flex">
	    			<h1>${category.categoryName}</h1>
			        <!-- update category -->
			        <button class="btn" type="button" onclick="location.href='/updateCategoryForm.do?categoryId=${category.categoryId}'">수정</button>
	    		</div>
	    		<div id="category-menu" class="btn-flex">
	    			<!-- delete category -->
			        <form action="/deleteCategory.do" method="post">
			        	<button class="btn" type="submit" name="categoryId" value="${category.categoryId}" onclick="return confirm('카테고리를 삭제하시겠습니까? (카테고리에 저장된 북마크가 있을 경우, 북마크와 메모 또한 같이 삭제됩니다.)')">
			        	삭제
			        	</button>
			        </form>
			        <!-- save Bookmark -->
	        		<button class="btn" type="button" onclick="location.href='/bookmark/insert/form'">북마크 추가</button>
	        		</form>
	    		</div>
    		</div>
	        <!-- bookmark test -->
	        <c:forEach items="${requestScope.bookmarkList}" var="bookmark">
	        	<div class="bookmark">
	        		<ul>
	        			<li>
	        				<a href="/bookmark/detail?bookmarkId=${bookmark.bookmarkId}">${bookmark.bookmarkName}</a>
	        				<button class="url-btn" type="button" onclick="location.href='${bookmark.url}'">URL</button>
	        			</li>
	        		</ul>
	        	</div>
	        </c:forEach>
	        <div>
	        	<button class="btn btn-flex bottom" onclick="location.href='/mainPage.do'">메인으로</button>
	        </div>
	    </div>
    </div>
</body>
</html>
