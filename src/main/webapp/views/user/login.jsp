<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/user.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>

<%@ include file="/views/layout/header.jsp" %>

<body>

<div class="container">
    <Form class="loginForm" action="/login.do" method="POST">
        <h2>로그인</h2>
        <div class="textForm">
            <input name="loginId" type="text" class="id" placeholder="아이디">
        </div>
        <div class="textForm">
            <input name="pw" type="password" class="pw" placeholder="비밀번호">
        </div>
        <div class="btnForm">
            <input type="submit" class="btn" value="L O G I N"/>
        </div>
    </Form>
    <div class="signup">
        <button class="signup-btn" onclick="location.href='/signupForm.do'">J O I N</button>
    </div>
</div>
</body>
</html>
