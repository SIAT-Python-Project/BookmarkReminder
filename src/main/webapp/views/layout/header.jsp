<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="header-container">
        <button style="background: none; border: none" onclick="location.href='/main.do'">
            <h1 class="header-text">Bookmark Reminder</h1>
        </button>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <h2 style="color: red; margin-top: 10px">ADMIN 계정입니다.</h2>
        </c:if>
        <div class="header-user-info">
            <c:if test="${empty sessionScope.userId}">
                <span class="header-span"><input type="button" value="로그인"
                                                 onclick="location.href='/loginForm.do'"></span>
            </c:if>
            <c:if test="${not empty sessionScope.userId}">
                <span class="header-span"><input type="button" value="${sessionScope.nickname}님"
                                                 onclick="location.href='/user.do'"></span>
                <span class="header-span"><input type="button" value="로그아웃" onclick="location.href='/logout.do'"></span>
            </c:if>
        </div>
    </div>
</header>