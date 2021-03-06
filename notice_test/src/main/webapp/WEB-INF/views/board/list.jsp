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
    <title>Document</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js" referrerpolicy="no-referrer"></script>
    
    <script>
    	$(function() {
    		$('a.prev, a.next').on('click', function(e) {
    			e.preventDefault();
    			
    			var paginationForm = $('#paginationForm');
    			
    			paginationForm.attr('action', '/board/listPerPage');
    			paginationForm.attr('method', 'GET');
    			
    			paginationForm.find('input[name=currPage]').val($(this).attr('href'));
    			paginationForm.find('input[name=amount]').val('${pageMaker.cri.amount}');
    			paginationForm.find('input[name=pagesPerPage]').val('${pageMaker.cri.pagesPerPage}');
    			paginationForm.find('input[name=type]').val('${pageMaker.cri.type}');
    			paginationForm.find('input[name=keyword]').val('${pageMaker.cri.keyword}');
    			
    			paginationForm.submit();
    		});
    	});
    </script>
    
    <style>
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
            color: yellowgreen;
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

    	.notice td, .notice a {
 	        color: saddlebrown;
            background-color: wheat;
    		font-weight: bold;
    	}
    </style>
</head>
<body>
	<a href="/board/register?currPage=${cri.currPage}&amount=${cri.amount}&pagesPerPage=${cri.pagesPerPage}">insert</a>
    <div id="wrapper">

        <table>
			<ul>
				<li>
					<form action="/board/listPerPage" method="GET" id="searchForm">
						<input type="hidden" name="currPage" value="1">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="pagesPerPage" value="${pageMaker.cri.pagesPerPage}">
						
						<select name="type" id="search1">
							<option value="T" ${ ( "T" eq pageMaker.cri.type ) ? "selected" : "" }>??????</option>
							<option value="C" ${ ( "C" eq pageMaker.cri.type ) ? "selected" : "" }>??????</option>
							<option value="W" ${ ( "W" eq pageMaker.cri.type ) ? "selected" : "" }>?????????</option>
						</select>
						<input type="text" name="keyword" class="search2" value="${pageMaker.cri.keyword}">
						
						<button class="search1">Search</button>
					</form>
				
				</li>
				<li>????????? ??? : (${pageMaker.totalAmount})</li>
				
			</ul>
		
		
            <thead>
                <tr>
                    <th>bno</th>
                    <th>title</th>
                    <th>content</th>
                    <th>memberid</th>
                    <th>insert_ts</th>
                    <th>update_ts</th>
                    <th>readcnt</th>
                    <th>bname</th>
                    <th>notice_tf</th>
                    
                    
                </tr>
            </thead>

			<c:forEach items="${list_notice}" var="notice">
			<tbody>
                
                <tr class="notice">
                    <%-- <td>${notice.bno}</td> --%>
                    <td>??????</td>
                    <td><a href="/board/get?bno=${notice.bno}&currPage=${pageMaker.cri.currPage}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}">${notice.title}</a></td>
                    <td>${notice.content}</td>
                    <td>${notice.memberid}</td>
                    <td>${notice.insert_ts}</td>
                    <td>${notice.update_ts}</td>
                    <td>${notice.readcnt}</td>
                    <td>${notice.bname}</td>
                    <td>${notice.notice_tf}</td>
                </tr>

            </tbody>
			</c:forEach>
		
			<c:forEach items="${list}" var="board">
            <tbody>
                
                <tr>
                    <td>${board.bno}</td>
                    
                    <td>
	                    <c:forEach begin="1" end="${board.repindent}">
	                    	<%= "&nbsp;&nbsp;" %>
	                    </c:forEach>
                    	<a href="/board/get?bno=${board.bno}&currPage=${pageMaker.cri.currPage}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}">${board.title}</a>
                    </td>
                    
                    <td>${board.content}</td>
                    <td>${board.memberid}</td>
                    <td><fmt:formatDate pattern="yyyy/MM/dd a HH:mm:ss" value="${board.insert_ts}"/></td>
                    <td>${board.update_ts}</td>
                    <td>${board.readcnt}</td>
                    <td>${board.bname}</td>
                    <td>${board.notice_tf}</td>
                </tr>

            </tbody>
            </c:forEach>
        </table>

		<div id="pagination">
			<form id="paginationForm" accept="#" method="POST">
				<input type="hidden" name="currPage">
				<input type="hidden" name="amount">
				<input type="hidden" name="pagesPerPage">
				<input type="hidden" name="type">
				<input type="hidden" name="keyword">
				<ul>
					<c:if test="${pageMaker.prev}">
						<li class="prev"><a class="prev" href="${pageMaker.startPage - 1}">PREV</a></li>
					</c:if>
					
					
					<c:forEach 
						begin="${pageMaker.startPage}" 
						end="${pageMaker.endPage}" 
						var="pageNum">
					
						<li class="${pageMaker.cri.currPage == pageNum? 'currPage' : ''}">
							<a 
                               id="mmw"
                               class="${pageMaker.cri.currPage == pageNum? 'currPage' : ''}"
							   href="/board/listPerPage?currPage=${pageNum}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}"
							>
							${pageNum}
							</a>
						</li>
					</c:forEach>
					
					<c:if test="${pageMaker.next}">
						<li class="next"><a class="next" href="${pageMaker.endPage + 1}">NEXT</a></li>
					</c:if>
					
					
				</ul>
			</form>
		
		</div>
		
    </div>
    
</body>
</html>