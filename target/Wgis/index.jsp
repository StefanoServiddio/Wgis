<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.controller.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wgis</title>



</head>


<body>
<% HttpSession sess = request.getSession(false);
 if(sess!=null)
 {
	 sess.invalidate();
	 sess = request.getSession(true);
 	
 }  
%>
	<h2>Welcome to Wonderful GIS</h2>




	<div>Now you will be redirected to Main Page</div>
	<div>Have a nice day</div>


	<script>
	window.setTimeout("location=('./Gis');",5000);
	</script>
</body>
</html>
