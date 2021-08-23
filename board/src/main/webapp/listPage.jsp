<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <h1>게시판 목록 보기</h1>
	<a href="boardWrite.jsp">글쓰기</a>
    <table border="1">
        <tr>
            <td colspan="5">
                <form action="search.do">
                    <select name="searchName" size="1" >
                        <option value="author">작성자</option>
                        <option value="title">글제목</option>
                        <option value="author_title">작성자+제목</option>
                    </select>
                    <input type="text" name="searchValue">
                    <input type="submit" value="찾기">
                </form>
            </td>
        </tr>

        <tr>
        	<td>No.</td>
            <td>번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>날짜</td>
            <td>조회수</td>
        </tr>

    <c:forEach items="${__PAGELIST__}" var="boardList" varStatus="status">

        <tr>
        	
        	<td>${fn:length(__PAGELIST__)-status.index}</td>
        	
            <td>${boardList.num}</td>
            
            <td>
            	<c:forEach begin="1" end="${boardList.repIndent}">
            		<%= "&nbsp;&nbsp;" %>
            	</c:forEach>
            	<a href="retrieve.do?num=${boardList.num}">${boardList.title}</a>
            </td>
            <td>${boardList.author}</td>
            <td>${boardList.writeday}</td>
            <td>${boardList.readcnt}</td>

        </tr>

    </c:forEach>

    <!-- PAGE -->
    <tr>
        <td colspan="5">
            <jsp:include page="page.jsp" flush="true" />
        </td>

    </tr>


    </table>
<a href="boardWrite.jsp">글쓰기</a>
</body>
</html>