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
    <h1>답변 글 쓰기 화면</h1>
    <c:forEach items="${__REPLYUI__}" var="replyui">
        <form action="reply.do" method="POST">
            <input type="hidden" name="num" value="${replyui.num}">
            <input type="hidden" name="repRoot" value="${replyui.repRoot}">
            <input type="hidden" name="repStep" value="${replyui.repStep}">
            <input type="hidden" name="repIndent" value="${replyui.repIndent}">

            원글번호: ${replyui.num} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            조회수: ${replyui.readcnt}<br>

            제목 <input type="text" name="title" value="${replyui.title}"><br>
            작성자 <input type="text" name="author"><br>
            내용 <textarea name="content" rows="10">${replyui.content}</textarea><br>
            <input type="submit" value="답변달기">
        </form>

        <a href="select.do">목록보기</a>
    </c:forEach>    
</body>
</html>