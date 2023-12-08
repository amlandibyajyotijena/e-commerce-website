<%@page import="com.mycart.dao.UserDao"%>
<%@page import="com.mycart.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.mycart.helper.FactoryProvider"%>
<%@page import="com.mycart.dao.CategoryDao"%>
<%@page import="com.mycart.entities.User"%>
<%
User user = (User) session.getAttribute("current_user");
if (user == null) {
	session.setAttribute("messege", "you are not logged in log in first");
	response.sendRedirect("login.jsp");
	return;
} else {
	if (user.getUserType().equals("normal")) {
		session.setAttribute("messege", "you are not admin!Do not access");
		response.sendRedirect("login.jsp");
		return;
	}
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.admin .card {
	border: 1px solid #c2db1a;
}

.admin .card:hover {
	border: 1px solid #e2e2e2;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container admin">
	<div class="container-fluid mt-3">
	<%
	UserDao uDao=new UserDao(FactoryProvider.getFactory());
	CategoryDao cDao=new CategoryDao(FactoryProvider.getFactory());
	ProductDao pDao=new ProductDao(FactoryProvider.getFactory());
	%>
	<%@include file="components/messege.jsp" %>
	
	</div>
		<div class="row mt-3">
			<div class="col-md-4">
				<!-- First Box -->
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid" alt="user.jpg"
								src="img/User.png">
						</div>
						<h1><%=uDao.countUsers() %></h1>
						<h1 class="text-uppercase text-muted">Users</h1>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<!-- Second Box -->
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid" alt="user.jpg"
								src="img/list.png">
						</div>
						<h1><%=cDao.countCategory() %></h1>
						<h1 class="text-uppercase text-muted">Categories</h1>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<!-- Third Box -->
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid" alt="user.jpg"
								src="img/product.png">
						</div>
						<h1><%=pDao.countProduct() %></h1>
						<h1 class="text-uppercase text-muted">Products</h1>
					</div>
				</div>
			</div>
		</div>

		<!-- Second Row -->
		<div class="row mt-4">
			<div class="col-md-6">
				<div class="card" data-toggle="modal"
					data-target="#addcategorymodal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid" alt="user.jpg"
								src="img/menu.png">
						</div>
						<p class="mt-2">Add new Category</p>
						<h1 class="text-uppercase text-muted">Add Category</h1>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card"  data-toggle="modal"
					data-target="#addproductmodal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid" alt="user.jpg"
								src="img/plus.png">
						</div>
						<p class="mt-2">Add new Product</p>
						<h1 class="text-uppercase text-muted">Add Product</h1>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="addcategorymodal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Fill Category
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="ProductOperationServlet" method="post">
					<input type="hidden" name="operation" value="addCategory">
						<div class="form-group">
							<input type="text" class="form-control" name="catTittle"
								placeholder="Enter Category Details" required />
						</div>
						<div class="form-group">
							<textarea rows="4" cols="6" class="form-control"
								placeholder="Enter Category Description" name="catDesc" required></textarea>
						</div>
						<div class="container text-center">
							<button class="btn btn-outline-success">Add Category</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>
S
	<!-- End With Category -->
	
	
	<!-- Modal 2 -->
	<div class="modal fade" id="addproductmodal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Fill Product
						Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
					<input type="hidden" name="operation" value="addProduct">
						<div class="form-group">
							<input type="text" class="form-control" name="prodName"
								placeholder="Enter Product name" required />
						</div>
						<div class="form-group">
							<input type="number" class="form-control" name="prodQuantity"
								placeholder="Enter Product Quantity" required />
						</div>
						<div class="form-group">
							<input type="number" class="form-control" name="prodPrice"
								placeholder="Enter Product Price" required />
						</div>
						<div class="form-group">
							<input type="number" class="form-control" name="prodDiscount"
								placeholder="Enter Product Discount" required />
						</div>
						
						<%
						
						List<Category>list=cDao.getCategories();
							%>
						
						<div class="form-group">
							<select name="catId" id="" class="form-control">
							<%
							for(Category c:list){
							%>
							<option value="<%=c.getCategoryId()%>"><%=c.getCategoryTitle() %></option>
							<%
							}
							%>
							</select>
						</div>
						<div class="form-group">
						<label for="prodPic">Select Picture of Product</label><br>
							<input type="file" name="prodPic" required>
						</div>
						
						<div class="form-group">
							<textarea rows="4" cols="6" class="form-control"
								placeholder="Enter Category Description" name="prodDesc" required></textarea>
						</div>
						<div class="container text-center">
							<button class="btn btn-outline-success">Add Product</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>

	<!-- End With Product -->

</body>
</html>

