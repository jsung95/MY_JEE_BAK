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

           $('#removeBtn').on('click', function() {
               
            //아래의 링크 형태의 요청은 무조건 GET 방식이므로 POST방식으로 보내야한다.
                // location.href = "/board/remove?bno=<c:out value='${board.bno}'/>";
                
                let formObj = $('form'); //블록변수 => 지역변수
                formObj.attr('action', '/board/remove');
                formObj.attr('method', 'POST');
                formObj.submit();

           });
           

           $('#checkbox').on('click', function(){
               if($(this).is(':checked')){
                   $(this).val('T');
                   /* $('#checkbox').attr('disabled', false); */
                   $('#checkbox_hidden').attr('disabled', true);
               } else {
                   $(this).val('F');
                   $('#checkbox_hidden').attr('disabled', false);
                   /* $('#checkbox').attr('disabled', true); */
               }
           });
        
           var is = "${board.notice_tf}";
           $(this).ready(function() {
                if(is == 'T') {
                    $('#checkbox').prop('checked', true);
                } else {
                    $('#checkbox').prop('checked', false);
                }
           });





        });
    </script>    
</head>
<body>

    <form action="/board/modify" method="POST">
    	<input type="hidden" name="bno" value="${board.bno}">
        <input type="hidden" name="currPage" value="${cri.currPage}">
        <input type="hidden" name="amount" value="${cri.amount}">
        <input type="hidden" name="pagesPerPage" value="${cri.pagesPerPage}">
        
        제목 : <input type="text" name="title" value="${board.title}">
        내용 : <input type="text" name="content" value="${board.content}">
        작성자 : <input type="text" name="memberid" value="${board.memberid}">

		공지로? : 
        <input type="checkbox" id="checkbox" name="notice_tf" value="T">
        <input type="hidden" id="checkbox_hidden" name="notice_tf" value="F">

        <input type="submit" value="수정">
        <button type="button" id="removeBtn">삭제하기</button>

    </form>
</body>
</html>