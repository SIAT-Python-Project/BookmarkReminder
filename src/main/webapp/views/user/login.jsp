<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/css/user/user.css" rel="stylesheet" type="text/css">
    <link href="/static/css/layout/layout.css" rel="stylesheet" type="text/css">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>

<%@ include file="/views/layout/header.jsp" %>

<body>

<div class="container">
    <form id="loginForm" class="loginForm">
        <h2>로그인</h2>
        <div class="textForm">
            <input id="id" name="loginId" type="text" class="id" placeholder="아이디" required>
        </div>
        <div class="textForm">
            <input id="pw" name="pw" type="password" class="pw" placeholder="비밀번호" required>
        </div>
        <div class="btnForm">
            <input type="submit" class="btn" value="L O G I N"/>
        </div>
    </form>
    <div class="signup">
        <button class="signup-btn" onclick="location.href='/signupForm.do'">J O I N</button>
    </div>
</div>

<script type="text/javascript">
    function login(e) {
        e.preventDefault();

        const loginId = document.getElementById('id').value;
        const password = document.getElementById('pw').value;

        axios.post('/login.do', {
            loginId: loginId,
            pw: password
        })
            .then(function (response) {
                const jsonResponse = response.data;

                if (jsonResponse.loginSuccess) {
                    window.location.href = jsonResponse.redirectURL;
                } else {
                    alert(jsonResponse.message);
                }
            })
            .catch(function () {
                alert('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
            });
    }

    document.getElementById('loginForm').addEventListener('submit', login);
</script>

</body>
</html>
