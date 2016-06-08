<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.controller.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<link rel="stylesheet" href="./assets/css/index.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wgis</title>
<%
	HttpSession sess = request.getSession(false);
	if (sess != null) {
		sess.invalidate();
		sess = request.getSession(true);

	}
%>


</head>


<body>




	<section class="jumbotron">
		<div class="container">
			<div class="row text-center">


				<header>
					<h2>Welcome to Wonderful GIS</h2>
				</header>

				<p>Now you will be redirected to Main Page</p>
				<p>Have a nice day</p>

				<p>
					<a href="./Gis" class="btn btn-primary btn-lg" role="button">Enter</a>
				</p>

			</div>
		</div>
	</section>







	<script>
		window.setTimeout("location=('./Gis');", 5000);
	</script>
</body>
</html>
