<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>유저 조회</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/users.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/views/layout/header.jsp" %>
<body>
<div class="container" style="height: ${(requestScope.users.size())*24+100}px;">
    <h1>${sessionScope.adminName}님 반가워요!</h1>
    <div class="user-header">
        <h2 class="user-text">User 목록</h2>
    </div>
    <ul class="container-element">
        <c:forEach var="user" items="${requestScope.users}">
            <li>
                <a href="/getUserCategory.do?userId=${user.id}">${user.nickname}</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
