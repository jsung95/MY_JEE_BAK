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
               location.href = "/board/listPerPage?currPage=${cri.currPage}&amount=${cri.amount}&pagesPerPage=${cri.pagesPerPage}" //이걸 가장많이쓴다.
           });

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
    <h1>/WEB-INF/views/board/modify.jsp</h1>

    <hr>

    <div id="wrapper">

        <form action="/board/modify" method="POST">
            <input type="hidden" name="bno" value="${board.bno}">
            <!-- 어느 화면에서든, 게시판 목록 페이지로 이동시 반드시 아래 3개의 기준 전송파라미터를 전송시키기 위해, hidden 값으로 설정 -->
            <input type="hidden" name="currPage" value="${cri.currPage}">
            <input type="hidden" name="amount" value="${cri.amount}">
            <input type="hidden" name="pagesPerPage" value="${cri.pagesPerPage}">
            <table border="1">
				<tr>
                    <td>Bno : </td>
                    <td>${board.bno}</td>
                </tr>
                
                <tr>
                    <td><label for="title">Title : </label></td>
                    <td><input type="text" name="title" value="${board.title}"></td>
                </tr>

                <tr>
                    <td><label for="content">Content : </label></td>
                    <td><input type="text" name="content" value="${board.content}"></td>
                </tr>

                <tr>
                    <td><label for="writer">Writer : </label></td>
                    <td><input type="text" name="writer" value="${board.writer}"></td>
                </tr>

            </table>

            <button type="submit" id="submitBtn">수정</button>

            <button type="button" id="removeBtn">삭제</button>
            <button type="button" id="listBtn">목록</button>

        </form>

    </div>
    

</body>
</html>