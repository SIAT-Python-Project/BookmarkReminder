<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${requestScope.userDTO.nickname}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/user.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/views/layout/header.jsp" %>
<body>
    <div class="user-info-body">
        <h2>${requestScope.userDTO.nickname} 님 정보</h2>
        <div class="textForm userFrom">
            <span>로그인 ID</span>
            <span name="loginId" type="text" class="id">${requestScope.userDTO.loginId}</span>
        </div>
        <div class="textForm userFrom">
            <span>이메일</span>
            <span name="email" type="text" class="email">${requestScope.userDTO.loginId}</span>
        </div>
        <div class="textForm userFrom">
            <span>닉네임</span>
            <span name="nickname" type="text" class="nickname">${requestScope.userDTO.nickname}</span>
        </div>
        <div class="textForm userFrom">
            <span>가입 날짜</span>
            <span name="created-date" type="text" class="created-date">${requestScope.userDTO.createdDate}</span>
        </div>
        <div class="btnForm user-info-btn">
            <button class="btn" value="비밀번호 수정" onclick="location.href='/passwordUpdateForm.do'">비밀번호 수정</button>
        </div>
    </div>
</body>
</html>
