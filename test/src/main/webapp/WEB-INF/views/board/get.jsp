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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js" referrerpolicy="no-referrer"></script>
    
    
    <script>
    	$(function() {
    		$('#modifyBtn').on('click', function(){
    			location.href="/board/modify?bno=<c:out value='${board.bno}'/>";
    		});
    		
    	});
    </script>
    
</head>
<body>
	<p>글번호 : ${board.bno}</p>
    <p>제목 : ${board.title}</p>
    <p>내용 : ${board.content}</p>
    <p>작성자 : ${board.writer}</p>
    <button type="button" id="modifyBtn">수정창으로</button>
    

</body>
</html>