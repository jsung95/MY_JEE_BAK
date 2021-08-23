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
            $('#listBtn').click(function() {
                location.href="/board/list";
            });
        });
    </script>


    <style>


        #wrapper {
            width: 470px;

            margin: 0 auto;

            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            font-size: 14px;
        }

        #submitBtn {
            
            width: 100px;
            height: 40px;

            border: 0;
            font-size: 15px;
            font-weight: bold;

            color: white;
            background-color: green;
        }
        #listBtn {
        	
            width: 100px;
            height: 40px;

            border: 0;
            font-size: 15px;
            font-weight: bold;

            color: white;
            background-color: blue;
        }
    </style>

</head>
<body>
    <h1>/WEB-INF/views/board/register.jsp</h1>

    <hr>

    <div id="wrapper">

        <form action="/board/register" method="POST">
        
            <table>

                <tr>
                    <td><label for="title">제목</label></td>
                    <td><input type="text" name="title" id="title" size="50" placeholder="제목을 입력하세요."></td>
                </tr>
                    
                <tr>
                    <td><label for="content">내용</label></td>
                    <td><textarea name="content" id="content" cols="50" rows="10" placeholder="내용을 입력하세요"></textarea></td>
                </tr>

                <tr>
                    <td><label for="writer">작성자</label></td>
                    <td><input type="text" name="writer" id="writer" size="50" placeholder="작성자를 입력하세요."></td>
                </tr>

                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit" id="submitBtn">Register</button>
                        <button type="button" id="listBtn">List</button>
                    </td>
                </tr>
            </table>

        </form>

    </div>

</body>
</html>