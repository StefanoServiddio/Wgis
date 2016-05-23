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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>

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
						<h3 class="panel-title">
							Users n.
							<%=request.getAttribute("numUtenti")%></h3>

					</div>

					<table class="table table-hover" id="task-table">
						<thead>
							<tr class="utenti_db">
								<th>ID</th>
								<th>Name</th>
								<th>Email</th>
								<th>Password</th>
								<th>Role</th>
								<th>Options</th>
							</tr>
						</thead>
						<tbody>
							<%
								int count = 0;
								List<UserLog> lista = (ArrayList<UserLog>) request.getAttribute("usersList");
								if (lista != null) {
									//UserBean usr=new UserBean();
									for (UserLog usr : lista) {
										//for(Iterator<UserBean>it=lista.iterator(); it.hasNext(); usr=it.next()){ 
										count++;
							%>
							<tr class="utenti_db">
								<td><%=count%></td>
								<td><%=usr.getName()%></td>
								<td><%=usr.getEmail()%> </td>
								<td><%=usr.getPassword()%></td>
								<td><%=usr.returnRole()%></td>
								<%if(usr.isAdmin()) {%>
								<td><select class="form-control input-sm role" >
										<option value="Admin">Admin</option>
										<option value="User">User</option>

								</select></td>
								<%}else{ %>
								<td><select class="form-control input-sm role" >
										<option value="User">User</option>
										<option value="Admin">Admin</option>
								</select></td>

								<%} %>
								
							</tr>

							<%
								}
								}
							%>
                         <script type="text/javascript" src="/Wgis/assets/js/selectRole.js"></script>
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
							<tr class="utenti_online">

								<th>Session ID</th>
								<th>Name</th>
								<th>Email</th>
								<th>Role</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (UserOnline uo : counter.getSessions()) {
							%>
							<tr class="utenti_online">
								<td><%=uo.getId()%></td>


								<td><%=uo.getName()%></td>

								<td><%=uo.getEmail()%></td>
								<td><%=uo.returnRole()%></td>

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


