<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js" referrerpolicy="no-referrer"></script>

    <script>
        
        $(function() {
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
	<h1>답글달기</h1>
    <form action="/board/answer" method="POST">
       	<input type="hidden" name="currPage" value="${cri.currPage}">
       	<input type="hidden" name="currPage" value="${cri.amount}">
       	<input type="hidden" name="currPage" value="${cri.pagesPerPage}">
       	
       	
       	<%-- <input type="hidden" name="bno" value="${board.bno}"> --%>
       	<input type="hidden" name="reproot" value="${board.reproot}">
       	<input type="hidden" name="repstep" value="${board.repstep}">
       	<input type="hidden" name="repindent" value="${board.repindent}">
        <table>

            
            <tr>
                <th>title</th>
                <td><input type="text" name="title" value="${board.title}"></td>
            </tr>

            <tr>
                <th>content</th>
                <td><textarea name="content" cols="30" rows="10"></textarea></td>
            </tr>

            <tr>
                <th>memberid</th>
                <td><input type="text" name="memberid"></td>
            </tr>

            <tr>
                <th>bname</th>
                <td><input type="text" name="bname" value="${board.bname}"></td>
            </tr>

            <tr>
                <th>공지로?</th>
                <td><input type="checkbox" id="checkbox" name="notice_tf" value="T"></td>
                <td><input type="hidden" id="checkbox_hidden" name="notice_tf" value="F"></td>
            </tr>
        
            <tr>
                <td><input type="submit" value="send"></td>
            </tr>




        </table>

    </form>
</body>
</html>