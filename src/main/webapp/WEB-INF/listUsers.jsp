<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.controller.*"%>
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
		SessionCounter counter = (SessionCounter) request.getSession().getAttribute(SessionCounter.COUNTER);
	if(counter !=null){
	%>
	Numeri utenti :
	<%=counter.getActiveSessionNumber()%>
	<h2>Lista Utenti:</h2>

	<%for(UsersOnline uo:counter.getSessions()) { %>



   <h3>ID:</h3>
	<span><%= uo.getId()%></span>

	<h3>Nome:</h3>
	<span><%=uo.getName()%></span>
	<h3>Email:</h3>
	<span> <%=uo.getEmail()%></span>
	<%} %>
	<%}else{ %>
	<h2>Attenzione Lista Utenti inesistente</h2>
	<%} %>
</body>
</html>


