<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.*"%>
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
	
		<%String email=counter.getUser(0).getEmail(); %>
 <%= email   %>


	
	
</body>
</html>


