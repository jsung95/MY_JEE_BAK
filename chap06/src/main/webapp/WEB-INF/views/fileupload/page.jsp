<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>

	<hr>

	<form action="/fileupload/doit" method="POST" enctype="multipart/form-data">
	
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>
		<div><input type="file" name="ck" multiple="multiple"></div>
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>

		<div><input type="submit" value="files"></div>
	
	</form>
</body>
</html>
