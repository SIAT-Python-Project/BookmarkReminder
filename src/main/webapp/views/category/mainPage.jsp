<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BookmarkReminder</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
    <link href="/static/css/category/mainPage.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/views/layout/header.jsp" %>
<body>
<div class="container" style="height: ${(requestScope.categories.size()+requestScope.bookmarks.size())*24+200}px;">
    <h1>${sessionScope.nickname}님 반가워요!</h1>
    <div class="category-header">
        <h2 class="category-text">Category 목록</h2>
        <button class="category-btn" onclick="location.href='/categoryInsertForm.do'">카테고리 생성</button>
    </div>
    <ul class="container-element">
        <c:forEach var="category" items="${requestScope.categories}">
            <li>
                <a href="/category.do?categoryId=${category.categoryId}">${category.categoryName}</a>
            </li>
        </c:forEach>
    </ul>
    <div class="important-bookmark-header">
        <h2 class="important-bookmark-text">북마크 목록</h2>
        <button class="bookmark-btn" onclick="location.href='/bookmark/insert/form'">북마크 생성</button>
    </div>
    <ul class="container-element">
        <c:forEach var="bookmark" items="${requestScope.bookmarks}">
            <li>
                <a href="/bookmark/detail?bookmarkId=${bookmark.bookmarkId}">${bookmark.bookmarkName}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
