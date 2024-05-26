<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
    <link href="/static/css/category/categoryInsert.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/views/layout/header.jsp" %>
<body>
<div class="container">
    <Form class="categoryForm" action="/categoryInsert.do" method="POST">
        <h2>카테고리 생성</h2>
        <div class="textForm">
            <input name="categoryName" type="text" class="categoryName" placeholder="Category">
        </div>
        <div class="btnForm">
            <input type="submit" class="btn" value="C R E A T E"/>
        </div>
    </Form>

</div>
</body>
</html>
