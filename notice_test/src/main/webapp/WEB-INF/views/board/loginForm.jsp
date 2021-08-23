<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>loginForm.html</title>
</head>
<body>
    <h1>로그인 입력 화면</h1>

    <form action="login.jsp" method="get">
        <fieldset>
            <legend>로그인폼</legend>
            <ul>
                <li>
                    <label for="userid">아이디</label>
                    <input type="text" name="userid">
                </li>
                <li>
                    <label for="passwd">비밀번호</label>
                    <input type="password" name="passwd">
                </li>
                <li>
                    <input type="submit" value="전송">
                </li>
            </ul>
        </fieldset>
    </form>

    
</body>
</html>