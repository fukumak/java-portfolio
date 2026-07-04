<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>マイページ | Servlet/JSP ログインデモ</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>マイページ</h1>

    <div class="userbox">
        ようこそ、<strong><c:out value="${sessionScope.loginUser.username}"/></strong> さん<br>
        登録日時: <c:out value="${sessionScope.loginUser.registeredAt}"/>
    </div>

    <p>このページは AuthFilter によりセッション未ログイン時はアクセスできません。</p>

    <div class="links">
        <a href="logout">ログアウト</a>
    </div>
</div>
</body>
</html>
