<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*,java.util.*,com.serviddio.gis.model.*, com.serviddio.gis.controller.SessionCounter"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login Successful</title>
<%if(SessionCounter.isNull)
	   request.getSession().invalidate(); %>

</head>
<body>
	<% UserReg utente =(UserReg)request.getAttribute("user");	
 
	%>
	<div class="container" align="center">
		<h3>Registration Completed Successful</h3>

		<p>you will be redirected into 5 sec</p>
		<p><%=utente.getEmail() %>
		</p>


		<button class="btn btn-default btn-danger" type="button"
			onclick="stop()">stop</button>
		<a href="/Wgis/Gis"><button type="button"
				class="btn btn-default btn-arrow-left">WonderfulGIS</button> </a>


		<p id="pageInfo"></p>

		<script type="text/javascript">
			var time = window.setTimeout("location=('/Wgis/Gis');", 5000);

			var stop = function() {
				window.clearTimeout(time);
			}

			var start = function() {
				time = window.setTimeout("location=('/Wgis/Gis');", 1000);
			}
		</script>


	</div>



</body>
</html>