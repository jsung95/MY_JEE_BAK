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
    <c:forEach items="${__RETRIEVE__}" var="retrieve">
        <h1>글 자세히 보기</h1>
        <form action="update.do" method="post">
            <input type="hidden" name="num" value="${retrieve.num}">
            글번호: ${retrieve.num} &nbsp;&nbsp;&nbsp;&nbsp;
            조회수: ${retrieve.readcnt}<br>
            
            제목 <input type="text" name="title" value="${retrieve.title}"><br>
            작성자 <input type="text" name="author" value="${retrieve.author}"><br>
            내용 <textarea name="content" rows="10">${retrieve.content}</textarea><br>
            <input type="submit" value="수정">
            
        </form>

        <a href="select.do">목록</a>
        <a href="delete.do?num=${retrieve.num}">삭제</a>
        <a href="replyui.do?num=${retrieve.num}">답변달기</a>
    </c:forEach>     

</body>
</html>