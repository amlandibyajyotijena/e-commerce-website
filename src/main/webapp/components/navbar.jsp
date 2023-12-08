<%@page import="com.mycart.entities.Category"%>
<%@page import="com.mycart.dao.CategoryDao"%>
<%@page import="com.mycart.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.mycart.helper.FactoryProvider"%>
<%@page import="com.mycart.dao.ProductDao"%>
<%@page import="com.mycart.entities.User"%>
<%
User user1 = (User) session.getAttribute("current_user");
%>

<%
String cat1 = request.getParameter("category");

CategoryDao cDao1 = new CategoryDao(FactoryProvider.getFactory());
List<Category> clist2 = cDao1.getCategories();
%>

<nav class="navbar navbar-expand-lg navbar-light  custom-bg">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">SHOPNOW</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-toggle="dropdown" aria-expanded="false"> Categories </a>
					<div class="dropdown-menu">

						<%
						for (Category c1 : clist2) {
						%>

						<a class="dropdown-item" href="#"><%=c1.getCategoryTitle()%></a>

						<%
						}
						%>

						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>

			</ul>
			<ul class="navbar-nav ml-auto">
				<%
				if (user1 == null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="login.jsp">Login </a></li>
				<li class="nav-item active"><a class="nav-link"
					href="register.jsp">Register </a></li>
				<li class="nav-item active"><a class="nav-link" href="cart.jsp">Cart
				</a></li>
				<%
				} else {

				if (user1.getUserType().equals("admin")) {
				%>


				<li class="nav-item active"><a class="nav-link"
					href="admin.jsp"><%=user1.getUserName()%> </a></li>
				<li class="nav-item active"><a class="nav-link"
					href="LogoutServlet">Logout </a></li>

				<%
				} else {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp"><%=user1.getUserName()%> </a></li>
				<li class="nav-item active"><a class="nav-link" href="cart.jsp">Cart
				</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="orders.jsp">Orders </a></li>
				<li class="nav-item active"><a class="nav-link"
					href="LogoutServlet">Logout </a></li>

				<%
				}
				}
				%>

			</ul>

		</div>
	</div>
</nav>