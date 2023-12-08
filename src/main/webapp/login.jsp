<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<%@include file="components/common_css_js.jsp"%>
<style>
body{
background-color: #060624;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card mt-4">
					<div class="card-header custom-bg text-white text-center">
						<h3>Login Here!!</h3>
					</div>
					<div class="card-body">
					<%@include file="components/messege.jsp" %>
					
						<form action="LoginServlet" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label> <input name="email"
									type="email" class="form-control" id="exampleInputEmail1"
									aria-describedby="emailHelp" placeholder="Enter email">
								<small id="emailHelp" class="form-text text-muted">We'll
									never share your email with anyone else.</small>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label> <input name="password"
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password">
							</div>
							<a href="register.jsp" class="text-center d-block mb-3">If not registered click here</a>
							<div class="text-center">
								<button type="submit" class="btn btn-dark text-center border-0">Submit</button>
								<button type="reset" class="btn btn-dark text-center border-0">Reset</button>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>