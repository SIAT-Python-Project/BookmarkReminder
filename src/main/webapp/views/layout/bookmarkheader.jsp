<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="bg-primary text-white py-3 mb-4">
    <div class="container d-flex justify-content-between align-items-center">
        <h1 class="h3">Bookmark Reminder</h1>
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <h2 class="h5 text-danger mb-0">ADMIN 계정입니다.</h2>
        </c:if>
        <div>
            <c:if test="${empty sessionScope.userId}">
                <button class="btn btn-light" onclick="location.href='/loginForm.do'">로그인</button>
            </c:if>
            <c:if test="${not empty sessionScope.userId}">
                <button class="btn btn-light" onclick="location.href='/user.do'">${sessionScope.nickname}님</button>
                <button class="btn btn-light" onclick="location.href='/logout.do'">로그아웃</button>
            </c:if>
        </div>
    </div>
</header>
    