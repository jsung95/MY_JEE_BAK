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
           $('#listBtn').on('click', function() { //목록버튼 on click 이벤트 펑션 
                //href 함수들. 선택해서 사용 
            //    self.location.href = "/board/list"; 
            //    self.location = "/board/list";
            //    location = "/board/list";
               location.href = "/board/list"; //이걸 가장많이쓴다.
           });

           $('#modifyBtn').on('click', function() {
                location.href = "/board/modify?bno=<c:out value='${board.bno}'/>";
           });
        });
    </script>    

</head>
<body>
    <h1>/WEB-INF/views/board/get.jsp</h1>

    <hr>

    <div id="wrapper">

        <form action="#">

            <table border="1">
				<tr>
                    <td>번호 : ${board.bno}</td>
                </tr>
                
                <tr>
                    <td>제목 : ${board.title}</td>
                </tr>

                <tr>
                    <td>내용 : ${board.content}</td>
                </tr>

                <tr>
                    <td>작성자 : ${board.writer}</td>
                </tr>

            </table>

            <button type="button" id="modifyBtn">수정</button>
            <button type="button" id="listBtn">목록</button>

        </form>

    </div>
    

</body>
</html>