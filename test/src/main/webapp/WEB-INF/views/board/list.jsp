<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
	<a href="/board/register">insert</a>
    <div id="wrapper">

        <table>

            <thead>
                <tr>
                    <th>bno</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>insert_ts</th>
                    <th>update_ts</th>
                </tr>
            </thead>

			<c:forEach items="${list}" var="board">
            <tbody>
                
                <tr>
                    <td>${board.bno}</td>
                    <td><a href="/board/get?bno=${board.bno}"/>${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.insert_ts}</td>
                    <td>${board.update_ts}</td>
                </tr>

            </tbody>
            </c:forEach>
        </table>

    </div>
    
</body>
</html>