<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/error/error.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/views/layout/header.jsp" %>
<div class="error-body">
    <div class="error-header" align="center">
        <h2>죄송합니다. 문제가 발생하였습니다.</h2>
    </div>

    <div class="error-message" align="center">
        <h3>${requestScope.error}</h3>
    </div>

    <div class="main" align="center">
        <button class="main-btn" onclick="location.href='/main.do'">메인으로</button>
    </div>
</div>
</body>
</html>
