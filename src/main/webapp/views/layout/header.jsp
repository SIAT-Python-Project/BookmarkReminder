<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <div class="header-container">
        <h1 class="header-text">Bookmark Reminder</h1>
        <div class="header-user-info">
            <c:if test="${empty sessionScope.userId}">
                <span class="header-span"><input type="button" value="로그인" onclick="location.href='/loginForm.do'"></span>
            </c:if>
            <c:if test="${not empty sessionScope.userId}">
                <span class="header-span"><input type="button" value="${sessionScope.nickname}님" onclick="location.href='/user.do'" ></span>
                <span class="header-span"><input type="button" value="로그아웃" onclick="location.href='/logout.do'"></span>
            </c:if>
        </div>
    </div>
</header>