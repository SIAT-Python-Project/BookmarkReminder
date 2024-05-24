<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/user.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/views/layout/header.jsp" %>
<form action="/passwordUpdate.do" method="POST" class="puForm">

    <h2>비밀번호 수정</h2>
    <div class="textForm">
        <input name="originPw" type="text" class="originPw" placeholder="기존 비밀번호">
    </div>
    <div class="textForm">
        <input name="pw" type="password" class="pw" placeholder="비밀번호">
    </div>
    <div class="textForm">
        <input name="pwConfirm" type="password" class="pw" placeholder="비밀번호 확인">
    </div>
    <div class="btnForm">
        <input type="submit" class="btn" value="U P D A T E"/>
    </div>
</form>
</body>
</html>
