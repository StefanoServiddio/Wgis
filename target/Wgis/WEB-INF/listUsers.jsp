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
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<link rel="stylesheet"
	href="http://bootsnipp.com/dist/bootsnipp.min.css?ver=7d23ff901039aef6293954d33d23c066">
<link href="./assets/css/userslist.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin List Users</title>

</head>
<body>


	<header>
		<h1 align="center">ADMIN USERS LIST PANEL</h1>
	</header>

	<div class="container">
	
		<div class="row">
		<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">Users</h3>

					</div>

					<table class="table table-hover" id="task-table">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Email</th>
								<th>Password</th>
							</tr>
						</thead>
						<tbody>
						<% int count=0;
						for(UserBean usr:((List<UserBean> )request.getAttribute("usersList"))){ %>
							<tr>
								<td><%= count++ %></td>
								<td><%= usr.getName() %></td>
								<td><%= usr.getEmail() %></td>
								<td><%= usr.getPassword() %></td>
							</tr>
							
							<%} %>
						
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>

	</div>








	<%
		SessionCounter counter = (SessionCounter) request.getSession().getAttribute(SessionCounter.COUNTER);
		if (counter != null) {
	%>







	<div class="container">


		<div class="row">
			<div class="col-md-3"></div>

			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							Users List Online:
							<%=counter.getActiveSessionNumber()%></h3>

					</div>
					<div class="panel-body">
						<input type="text" class="form-control" id="dev-table-filter"
							data-action="filter" data-filters="#dev-table"
							placeholder="Filter Developers" />
					</div>
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>

								<th>Session ID</th>
								<th>Name</th>
								<th>Email</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (UsersOnline uo : counter.getSessions()) {
							%>
							<tr>
								<td><%=uo.getId()%></td>


								<td><%=uo.getName()%></td>

								<td><%=uo.getEmail()%></td>

							</tr>







							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	<%
		} else {
	%>
	<h2>Attenzione Lista Utenti inesistente</h2>
	<%
		}
	%>



</body>
</html>


