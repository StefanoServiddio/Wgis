<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.controller.*, com.serviddio.gis.model.*"%>
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
	
   if(SessionCounter.isNull)
	   request.getSession().invalidate();
	   
%>





</head>


<body>




	<section class="jumbotron">
		<div class="container">
			<div class="row text-center">


				<header>
					<h1>Welcome to Wonderful GIS</h1>
				

				<h2>Now you will be redirected to Main Page</h2>
				<h2>Have a Nice Day !!!</h2>
				
				
			</header>
			
				<p>	<a href="./Gis" class="btn btn-primary btn-lg" role="button">Enter</a></p>	

			</div>
		</div>
	</section>







	<script>
		window.setTimeout("location=('./Gis');", 5000);
	</script>
</body>
</html>
