<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


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

           $('#removeBtn').on('click', function() {
               
            //아래의 링크 형태의 요청은 무조건 GET 방식이므로 POST방식으로 보내야한다.
                // location.href = "/board/remove?bno=<c:out value='${board.bno}'/>";
                
                let formObj = $('form'); //블록변수 => 지역변수
                formObj.attr('action', '/board/remove');
                formObj.attr('method', 'POST');
                formObj.submit();

           });

        });
    </script>    
</head>
<body>

    <form action="/board/modify" method="POST">
    	<input type="hidden" name="bno" value="${board.bno}">
        제목 : <input type="text" name="title" value="${board.title}">
        내용 : <input type="text" name="content" value="${board.content}">
        작성자 : <input type="text" name="writer" value="${board.writer}">

        <input type="submit" value="수정">
        <button type="button" id="removeBtn">삭제하기</button>

    </form>
</body>
</html>