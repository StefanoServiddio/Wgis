<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List" %>
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
Lista utenti:
<%= counter.getActiveSessionNumber() %>
	
		<%String id=counter.getSessions().get(0).getId(); %>
 <%= id   %>
<%String name =counter.getSessions().get(0).getName();%>
Utente:
<p><%= name %> </p>
<%String email =counter.getSessions().get(0).getEmail(); %>
<span> <%=email %>	</span>
	
</body>
</html>


