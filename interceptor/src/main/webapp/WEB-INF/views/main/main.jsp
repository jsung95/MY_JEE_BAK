<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js" referrerpolicy="no-referrer"></script>

<script>
	$(function() {
		$('#logout').on('click', function(){
			location.href="/user/logout";
		});
	});

</script>

</head>
<body>
	<h1>main.jsp</h1>
	<hr>
	
	<button type="button" id="logout">Logout</button>

</body>
</html>