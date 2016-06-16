<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >


<title>Wonderful GiS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<%
  HttpSession sess = request.getSession();
  if(sess!= null &&(sess.getAttribute("user"))!=null)
  {	  
	  System.out.println(request.getSession().getAttribute("user"));
	  response.sendRedirect("./Success");
  }
  else{
	  System.out.println("user sessione non trovato");
  
   %>
	<div class="container-fluid" style="padding: 12px;">
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><a href="./Gis">WGIS</a> Sign Up</h3>
					</div>
					<div class="panel-body">
					<div class="alert alert-danger">
								<strong>Attention!</strong> User already registered.
							</div>
						<form accept-charset="UTF-8" action="SignUp" method="post"
							role="form">
							<fieldset>
							<div class="form-group">
									<input id="name "class="form-control" placeholder="yourname"
										name="name" type="text">
								</div>
								<div class="form-group">
									<input id="email "class="form-control" placeholder="yourmail@example.com"
										name="email" type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
							
								<input id="sign" class="btn btn-lg btn-success btn-block" type="submit"
									value="Sign Up">
							</fieldset>
						</form>
						<hr />

						
					</div>
				</div>
			</div>
		</div>
	</div>
	<%} %>
</body>