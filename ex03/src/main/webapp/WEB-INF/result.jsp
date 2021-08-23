<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h3>게시판 내용 보기</h3>
    <p>${__BOARD__}</p>

    <table border="1" style="border-collapse: collapse;">
        <caption>게시판 내용</caption>

        <thead>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
        </thead>

        <tbody>
            <c:forEach items="${__BOARD__}" var="empVO">
                <tr>
                    <td>${empVO.num}</td>
                    <td>${empVO.title}</td>
                    <td>${empVO.content}</td>
                    <td>${empVO.author}</td>
                    <td>${empVO.writeday}</td>

                </tr>

            </c:forEach>
        </tbody>

    </table>
</body>
</html>