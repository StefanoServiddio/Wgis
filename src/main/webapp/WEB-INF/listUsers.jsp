<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

	<h1>Utenti Online</h1>
	<%
		SessionCounter counter = (SessionCounter) session.getAttribute(SessionCounter.COUNTER);
	%>
	Numeri utenti :
	<%=counter.getActiveSessionNumber()%>
	<h2>Lista Utenti:</h2>

	<%for(UsersOnline uo:counter.getSessions()) { %>




	<%= uo.getId()%>

	Utente:
	<p><%=uo.getName()%></p>
	Email:
	<span> <%=uo.getEmail()%></span>
	<%} %>
</body>
</html>


