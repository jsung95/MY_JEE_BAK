<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>서브 페이지</title>
</head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

<script type="text/javascript" src="/doc/js/header_footer.js"></script>

<style>

    #section_wrap {
        width: 1000px;
        height: 700px;
        margin: 0 auto;
    }

    #aside_area {   
        float: left;
        
        width: 180px;
        height: 1000px;

        padding-right: 20px;
    }

    #aside_menu {
        width: 180px;
    }



    #aside_menu ul div {
        height: 150px;

        background-color: #E2017D;

        font-size: 30px;
        font-weight: bold;
        color: white;

        text-align: center;
        line-height: 150px;
    }

    #aside_menu ul li {
        height: 50px;

        border-bottom: 1px solid rgb(220, 220, 220);
        box-sizing: border-box;

        font-size: 15px;
        line-height: 48px;
    }

    #aside_menu ul li a {
        display: block;

        width: 150px;
        height: 50px;

        margin: 0 auto;
    }

    #aside_menu ul li:hover {
        background: url(/doc/img/section/aside/side_li_bg.jpg);
        background-repeat: no-repeat;
        background-position: right center;

        font-weight: bold;
    }



    #board_area {
        float: left;
        width: 780px;
        height: 500px;
    }

    #board_title_area {
        width: 780px;
        height: 150px;

        font-size: 50px;
        font-weight: bold;
        
        line-height: 200px;

        border-bottom: 1px solid rgb(220, 220, 220);
        box-sizing: border-box;

        padding-left: 40px;
        margin-bottom: 40px;
    }

    #board {
        width: 780px;
        margin-bottom: 40px;
    }

    table {
        width: 780px;

    }

    table, th, td {
        border: 1px solid rgb(201, 201, 201);
        border-collapse: collapse;
    }
    th {
        background-color: rgb(245, 245, 245);
        height: 50px;
        font-size: 20px;
        border-top: 3px solid #6f2b89;

    }
    th:first-child {
        color: #E2017D;
    }

    td {
        height: 40px;
        font-size: 15px;
        text-align: center;
    }
    td:first-child {
        color: #E2017D;
    }

    #board_paging {
        height: 30px;
        margin-bottom: 20px;
    }

    #board_paging_wrap {
        width: 500px;
        height: 30px;


        margin: 0 auto;
    }

    #board_paging_wrap ul {
        
    }

    #board_paging_wrap ul li {
        display: inline;
        float: left;

        width: 30px;
        height: 30px;

        text-align: center;
        line-height: 30px;

        border: 1px solid #dcdcdc;
        margin-left: -1px;

    }

    #board_paging_wrap ul li:hover {
        background-color: #6f2b89;
        color: white;
    }
    
    #board_search {

    }

    #board_search_wrap {
        width: 300px;
        text-align: center;
        margin: 0 auto;
    }

</style>

<body>
    
    <div id="wrap">

        <!-- HEADER -->
        <header id="header"></header>
        <div class="line"></div>

        <!-- SECTION -->
        <section>

            <div id="section_wrap">
                <!-- ASIDE -->
                <div id="aside_area">
                    <aside id="aside_menu">
                        <ul>
                            <div>고객센터</div>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">공지사항</a></li>
                        </ul>
                    </aside>
                </div>

                <div id="board_area">
                    
                    <div id="board_title_area">
                        공지사항
                    </div>

                    <div id="board">
                        
                        <table>
                            <thead>
                                <tr>
                                    <th scope="col" width="10%">번호</th>
                                    <th scope="col" width="*">제목</th>
                                    <th scope="col" width="10%">작성자</th>
                                    <th scope="col" width="15%">등록일</th>
                                    <th scope="col" width="10%">조회수</th>
                                    <th scope="col" width="10%">고정</th>
                                </tr>
                            </thead>

							<c:forEach items="${list_notice}" var="notice">
                            <tbody>
                                <tr>
                                    <td>공지</td>
                                    <td><a href="/board/get?bno=${notice.bno}&currPage=${pageMaker.cri.currPage}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}">${notice.title}</a></td>
                                    <td>${notice.memberid}</td>
                                    <td>${notice.insert_ts}</td>
                                    <td>${notice.readcnt}</td>
                                    <td>${notice.notice_tf}</td>
                                </tr>
                                
                            </tbody>
                            </c:forEach>
                            
                            <c:forEach items="${list}" var="board">
				            <tbody>
				                
				                <tr>
				                    <td>${board.bno}</td>
				                    <td><a href="/board/get?bno=${board.bno}&currPage=${pageMaker.cri.currPage}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}">${board.title}</a></td>
				                    <td>${board.memberid}</td>
				                    <td>${board.insert_ts}</td>
				                    <td>${board.readcnt}</td>
				                    <td>${board.notice_tf}</td>
				                </tr>
				
				            </tbody>
				            </c:forEach>

                        </table>

                    </div>

                    <div id="board_paging">
                        <div id="board_paging_wrap">
                            <ul>
                                <li>0</li>
                                <li>0</li>
                                <li>1</li>
                                <li>0</li>
                                <li>0</li>
                                <div class="clear"></div>
                            </ul>
                        </div>

                    </div>

                    <div id="board_search">
                        <div id="board_search_wrap">
                            <select name="boardSelect" id="">
                                <option value="title">제목</option>
                                <option value="author">작성자</option>
                            </select>
                            <input type="text">
                            <input type="submit" value="검색">
                        </div>
                    </div>

                </div>
                <div class="clear"></div>
                
            </div>

        </section>

        <footer id="footer">

        </footer>

    </div>
    


</body>
</html>