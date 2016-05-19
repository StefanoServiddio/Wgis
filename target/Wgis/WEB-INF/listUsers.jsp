<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
    SessionCounter counter = (SessionCounter) session.getAttribute( SessionCounter.COUNTER); 
%>
	<h1>Benvenuto Admin</h1>


	<p>
		Number of online user(s):
		<%= counter.getActiveSessionNumber() %>
	</p>
	<% String nome=counter.getSessions().get(1).getEmail(); %>

</body>
</html>


