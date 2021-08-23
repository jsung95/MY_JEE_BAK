<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>/WEB-INF/views/user/login.jsp</h1>

    <hr>

    <form action="/user/loginPost" method="POST">
        <fieldset>

            <legend>Login Form</legend>

            <div><input type="text" name="userid" placeholder="User ID"></div>
            <div><input type="password" name="userpw" placeholder="User PW"></div>
            <div>REMEMBER ME<input type="checkbox" name="rememberMe" placeholder="rememberMe"></div>

            <p></p>

            <div><button type="submit">Sign in</button></div>
        </fieldset>
    </form>
</body>
</html>