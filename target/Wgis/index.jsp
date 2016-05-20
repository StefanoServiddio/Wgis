<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.serviddio.gis.model.*"%>
 <%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wgis</title>
<% HttpSession sess = request.getSession();
if(sess.getAttribute(SessionCounter.COUNTER)==null)
{
	SessionCounter sessCont= new SessionCounter();
     sess.setAttribute(SessionCounter.COUNTER, sessCont);
} 


%>


</head>


<body>
  
	<h2>Welcome to Wonderful GIS</h2>




	<div>Now you will be redirected to Main Page</div>
	<div>Have a nice day</div>
    

	<script>
	window.setTimeout("location=('/Wgis/Gis');",5000);
	</script>
</body>
</html>
