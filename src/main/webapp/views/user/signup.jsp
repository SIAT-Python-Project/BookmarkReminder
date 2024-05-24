<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/user.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/views/layout/header.jsp" %>
<form action="/signup.do" method="POST" class="joinForm">

    <h2>회원가입</h2>
    <div class="textForm">
        <input name="loginId" type="text" class="id" placeholder="아이디">
    </div>
    <div class="textForm">
        <input name="pw" type="password" class="pw" placeholder="비밀번호">
    </div>
    <div class="textForm">
        <input name="pwConfirm" type="password" class="pw" placeholder="비밀번호 확인">
    </div>
    <div class="textForm">
        <input name="email" type="text" class="email" placeholder="이메일">
    </div>
    <div class="textForm">
        <input name="nickname" type="text" class="nickname" placeholder="닉네임">
    </div>
    <div class="btnForm">
        <input type="submit" class="btn" value="J O I N"/>
    </div>
</form>
</body>
</html>
