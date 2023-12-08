<%@page import="org.hibernate.hql.internal.ast.util.ASTUtil.IncludePredicate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
<%@include file="components/common_css_js.jsp"%>
<style>
body{
background-color: #060624;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container-fluid">
	<div class="row mt-3">
		<div class="col-md-4 offset-md-4">
			<div class="card mb-3">
			<%@include file="components/messege.jsp" %>
				<div class="card-body px-5">
					<div class="text-center">
					<img alt="signup" src="img/signup.png" width="20px" height="20px" class="img-fluid"/>
					</div>
					
						
					<h3 class="text-center ">Sign Up Here!!</h3>

					<form action="RegisterServlet" method="post">
						<div class="form-group">
							<label for="name">User Name</label> <input type="text" name="user_name"
								class="form-control" id="name" placeholder="Enter Here">
						</div>
						<div class="form-group">
							<label for="email">User Email</label> <input type="email" name="user_email"
								class="form-control" id="email" placeholder="Enter Here">
						</div>
						<div class="form-group">
							<label for="password">User Password</label> <input type="text" name="user_password"
								class="form-control" id="password" placeholder="Enter Here">
						</div>
						<div class="form-group">
							<label for="phone">User Phone</label> <input type="number" name="user_phone"
								class="form-control" id="phone" placeholder="Enter Here">
						</div>
						<div class="form-group">
							<label for="address">User Address</label><br>
							<textarea style="height: 100px;" class="form-control" name="user_address"
								placeholder="Enter address"></textarea>
						</div>
						<div class="container text-center">
							<button class="btn btn-outline-success border-0">Register</button>
							<button type="reset"class="btn btn-outline-success border-0">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>