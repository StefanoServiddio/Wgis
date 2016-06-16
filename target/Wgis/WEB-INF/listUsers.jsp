<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.serviddio.gis.controller.*, com.serviddio.gis.model.*,
java.util.ArrayList,java.util.Iterator,java.util.List"%>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Admin List Users</title>

</head>
<body>


	<header>
		<h1 align="center">ADMIN USERS LIST PANEL</h1>
	</header>

	<div class="container">

		<div class="row">
			<div class=" col-sm-2 col-md-2  col-lg-2  "></div>
			<div class="col-sm-8 col-md-8 col-lg-8">
				<a class="btn btn-default" href="./Gis" role="button">Back to
					WGIS</a> <a class="btn btn-default" href="./ListUsers" role="button">Update</a>
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
								<th>Archived</th>

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
								<td><%=usr.getEmail()%></td>
								<td><%=usr.getPassword()%></td>
								<td><%=usr.returnRole()%></td>
								<%
									if (usr.isAdmin()) {
								%>
								<td><select class="form-control input-sm role">
										<option value="Admin">Admin</option>
										<option value="User">User</option>

								</select></td>
								<%
									} else {
								%>
								<td><select class="form-control input-sm role">
										<option value="User">User</option>
										<option value="Admin">Admin</option>
								</select></td>

								<%
									}
								%>
								
								
									
								<%
									if (usr.isArchived()) {
								%>
								<td><select class="form-control input-sm archived">
										<option value="archived">Disable</option>
										<option value="enable">Enable</option>

								</select></td>
								<%
									} else {
								%>
								<td><select class="form-control input-sm archived">
										<option value="enable">Enable</option>
										<option value="archived">Disable</option>
								</select></td>

								<%
									}
								%>
								
								

							</tr>

							<%
								}
								}
							%>
							<script type="text/javascript"
								src="/Wgis/assets/js/selectRole.js"></script>
								<script type="text/javascript"
								src="/Wgis/assets/js/archivUser.js"></script>
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
			<div class="col-sm-2 col-md-2 col-lg-2"></div>

			<div class="col-sm-8 col-md-8 col-lg-8">
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
								<th>Mobile</th>
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
								<%if(uo.isMobile()){ %>
								<td>online</td>
								<%}else{ %>
								<td>offline</td>
								<%} %>

							</tr>







							<%
								}
							%>



							<%
							
							   List<UserOnline> list_mobile_user= DAOUser.getIstance().getMobileUserListOnline();
								for (UserOnline uo : list_mobile_user) {
									
								
									
								if (!counter.getSessions().contains(uo)){
							
								
							%>
							<tr class="utenti_online">
								<td><%=uo.getId()%></td>


								<td><%=uo.getName()%></td>

								<td><%=uo.getEmail()%></td>
								<td><%=uo.returnRole()%></td>

								<td>online</td>
							</tr>

							<%
								}}
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
	<h2 align="center">Attenzione Lista Utenti inesistente</h2>
	<%
		}
	%>



</body>
</html>


