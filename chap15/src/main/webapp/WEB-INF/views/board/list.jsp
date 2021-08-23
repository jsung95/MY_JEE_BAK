<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>tbl_board</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js" referrerpolicy="no-referrer"></script>

    <script>

        $(function() {

            var result = '<c:out value="${result}" />'
            if(result.length > 0) {
                alert(result);
            }


            $('#regBtn').click(function() {
                location.href="/board/register?currPage=${cri.currPage}&amount=${cri.amount}&pagesPerPage=${cri.pagesPerPage}";
            });

            // paging
            $('a.prev, a.next').on('click',function(e){
                console.debug("onclicked for a.next or a.prev");
                console.log('\t+ this:' + this);
                //Event에 의한 선택 된 요소의 기본 동작을 금지(무력화)
                e.preventDefault(); 
                
                var paginationForm = $('#paginationForm');

                paginationForm.attr('action', '/board/listPerPage');
                paginationForm.attr('method', 'GET');

                paginationForm.find('input[name=currPage]').val($(this).attr('href'));
                paginationForm.find('input[name=amount]').val('${pageMaker.cri.amount}');
                paginationForm.find('input[name=pagesPerPage]').val('${pageMaker.cri.pagesPerPage}');

                paginationForm.submit();
                
                
            });//onclick for Prev,Next button




        });

    </script>

    <style>
        .clear {
            clear: both;
        }

        #wrapper {
            width: 1024px;

            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            font-size: 14px;
        }

        table {
            width: 95%;

            border: 1px ridge green;
            border-collapse: collapse;
            text-align: center;
        }

        th {
            padding: 10px;
            color: white;
            background-color: purple;
            font-size: 16px;
        }

        td{
            padding: 3px;
        }

        caption {
            font-size: 16px;
            font-weight: bold;
            padding: 0;
        }

        #topmenu > li {
            float: left;
            text-align: center;
            line-height: 50px;
            list-style: none;

            width: 33%;
            height: 50px;
        }

        #regBtn {
            width: 150px;
            height: 40px;

            border: 0;

            font-size: 15px;
            font-weight: bold;

            color: white;
            background-color: red;

            cursor: pointer;
        }

        tr:hover {
            background-color: rgb(239, 253, 226);
        }

        a, a:link, a:visited {
            text-decoration: none;
            color: black;
            cursor: pointer;
        }

        td:nth-child(2) {
            text-align: left; 

            width: 40%;
            padding-left: 10px;
        }

        /* paging */
        #pagination {
            width: 95%;
            margin: 0 auto;
        }

        #pagination ul {
            float: right;
            
        }

        #pagination li {
            float: left;

            width: 30px;
            height: 30px;

            border: 1px solid black;

            text-align: center;
            line-height: 30px;

            list-style: none;
        }

        .prev, .next {
            width: 70px !important;
            
            color: white;
            background-color: blue;
        }

        .currPage {
            
            color: white;
            background-color: green;
        }

        #mmw {
            display: block;
            width: 30px;
            height: 30px;
        }
    </style>

</head>
<body>

    <p></p>
    <div id="wrapper">

        <table border="1">

            <caption>
                <ul id="topmenu">
                    <li>
                        <form action="/board/listPerPage" method="GET" id="searchForm">
                             <input type="hidden" name="currPage" value="1">
                             <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
                             <input type="hidden" name="pagesPerPage" value="${pageMaker.cri.pagesPerPage}">

                             <select name="type" id="search1">
                                <option>검색조건</option>
                                <option value="T" ${ ( "T" eq pageMaker.cri.type ) ? "selected" : "" }>제목</option>
                                <option value="C" ${ ( "C" eq pageMaker.cri.type ) ? "selected" : "" }>내용</option>
                                <option value="W" ${ ( "W" eq pageMaker.cri.type ) ? "selected" : "" }>작성자</option>

                             </select>

                             <input type="text" name="keyword" class="search2" value="${pageMaker.cri.keyword}">

                             <button class="search1">Search</button>
                        </form>
                    </li>
                    <li>tbl_board (${pageMaker.totalAmount})</li>
                    <li><button id="regBtn" type="button">REGISTER</button></li>
                </ul>
            </caption>

            <thead>
                <tr>
                    <th>bno</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>insert_ts</th>
                    <th>update_ts</th>
                </tr>
            </thead>

            <tbody>
                
                <c:forEach items="${list}" var="board">
                <tr>
                    <td><c:out value="${board.bno}"/></td>
                    <!-- <td><a href="/board/get?bno=${board.bno}"><c:out value="${board.title}"/></a></td> -->
                    <td><a href="/board/get?bno=${board.bno}&currPage=${pageMaker.cri.currPage}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td><fmt:formatDate pattern="yyyy/MM/dd a HH:mm:ss" value="${board.insert_ts}"/></td>
                    <td><fmt:formatDate pattern="yyyy/MM/dd a HH:mm:ss" value="${board.update_ts}"/></td>
                </tr>
                </c:forEach>
            </tbody>
            <tfoot></tfoot>
        </table>


        <p>&nbsp;</p>


        <!-- 페이징  -->
        <div id="pagination">
            <form id="paginationForm" action="#" method="POST">
                <!-- 어느 화면에서든, 게시판 목록 페이지로 이동시 반드시 아래 3개의 기준 전송파라미터를 전송시키기 위해, hidden 값으로 설정 -->
                <input type="hidden" name="currPage">
                <input type="hidden" name="amount">
                <input type="hidden" name="pagesPerPage">
				<input type="hidden" name="type">
				<input type="hidden" name="keyword">
                <ul>
                    <c:if test="${pageMaker.prev}">
                        <li class="prev"><a class="prev" href="${pageMaker.startPage - 1}">Prev</a></li>
                    </c:if>
                    

                    <c:forEach 
                        begin="${pageMaker.startPage}" 
                        end="${pageMaker.endPage}"
                        var="pageNum">

                        <li class="${pageMaker.cri.currPage == pageNum? 'currPage' : ''}">
                            <a   
                               id="mmw"  
                               class="${pageMaker.cri.currPage == pageNum? 'currPage' : ''}"
                               href="/board/listPerPage?currPage=${pageNum}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}"
                               >
                               ${pageNum}
                            </a>
                         </li>
                         
                    </c:forEach>


                    <c:if test="${pageMaker.next}">
                        <li class="next"><a class="next" href="${pageMaker.endPage + 1}">Next</a></li>
                    </c:if>

                </ul>
            </form>
        </div>

    </div>
    
    <h1>${result}</h1>

</body>
</html>