<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/board/register" method="POST">

        제목<input type="text" name="title"><br>
        내용<input type="text" name="content"><br>
        작성자<input type="text" name="writer"><br>
        <input type="submit">

    </form>
</body>
</html>