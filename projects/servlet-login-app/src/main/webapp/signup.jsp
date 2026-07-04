<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>会員登録 | Servlet/JSP ログインデモ</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>会員登録</h1>

    <c:if test="${not empty error}">
        <div class="error"><c:out value="${error}"/></div>
    </c:if>

    <form method="post" action="signup">
        <div class="field">
            <label for="username">ユーザー名</label>
            <input type="text" id="username" name="username"
                   value="<c:out value="${username}"/>" required autofocus>
        </div>
        <div class="field">
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="field">
            <label for="confirmPassword">パスワード（確認）</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>
        <button type="submit">登録する</button>
    </form>

    <div class="links">
        <a href="login">既にアカウントをお持ちの方はこちら</a>
    </div>
</div>
</body>
</html>
