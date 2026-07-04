<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン | Servlet/JSP ログインデモ</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>ログイン</h1>

    <c:if test="${not empty error}">
        <div class="error"><c:out value="${error}"/></div>
    </c:if>
    <c:if test="${param.registered == '1'}">
        <div class="success">登録が完了しました。ログインしてください。</div>
    </c:if>
    <c:if test="${param.loggedout == '1'}">
        <div class="success">ログアウトしました。</div>
    </c:if>
    <c:if test="${param.requireLogin == '1'}">
        <div class="error">このページの閲覧にはログインが必要です。</div>
    </c:if>

    <form method="post" action="login">
        <div class="field">
            <label for="username">ユーザー名</label>
            <input type="text" id="username" name="username"
                   value="<c:out value="${username}"/>" required autofocus>
        </div>
        <div class="field">
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">ログイン</button>
    </form>

    <div class="links">
        <a href="signup">アカウントをお持ちでない方はこちら</a>
    </div>
</div>
</body>
</html>
