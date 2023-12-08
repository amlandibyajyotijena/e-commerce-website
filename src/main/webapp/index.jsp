<%@page import="com.mycart.helper.Helper"%>
<%@page import="com.mycart.entities.Category"%>
<%@page import="com.mycart.dao.CategoryDao"%>
<%@page import="com.mycart.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.mycart.dao.ProductDao"%>
<%@page import="com.mycart.helper.FactoryProvider"%>
<html>
<head>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
body{
background-color: #060624;
}
.list-group-item.active {
	background: #c2db1a !important;
	color: black !important;
	border-color: #c2db1a !important;
}
.discount-label{
font-size: 10px;
font-style: italic!important;
/*text-decoration: line-through!important;*/
}
.product-card:hover{
    background:#B4B4B4;
	cursor: pointer;
}
.categoryactive:active{
 background-color: yellow;
}
}
</style>

</head>
<body>
	<%@include file="components/navbar.jsp"%>
<div class="container-fluid">
	<div class="row mt-3 mx-2">
		<%
	    String cat=request.getParameter("category");
		
		ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
		List<Product> list=null;
		if(cat==null||cat.equals("all")){
			 list = pDao.getAllProducts();
		}
		else{
			int c=Integer.parseInt(cat.trim());
			 list=pDao.getAllProductsById(c);
		}
		
		
		

		CategoryDao cDao = new CategoryDao(FactoryProvider.getFactory());
		List<Category> clist = cDao.getCategories();
		%>


		<!-- Category -->
		<div class="col-md-2">

			<div class="list-group mt-4">
				<a href="index.jsp?category=all" class="list-group-item list-group-item-action active">
					All Products </a>

				<%
				for (Category c : clist) {
				%>

				<a href="index.jsp?category=<%=c.getCategoryId() %>" class="list-group-item list-group-item-action categoryactive"><%=c.getCategoryTitle()%></a>

				<%
				}
				%>
			</div>
		</div>


		<!-- Products -->

		<div class="col-md-10">

			<div class="row mt-4">

				<div class="col md-12">

					<div class="card-columns">

						<!-- Traverse -->
						<%
						for (Product p : list) {
						%>
						
						<!-- Product Card -->
						
						<div class="card product-card">
                             <div class="container text-center">
                          <img class="card-img-top m-2" src="img/products/<%=p.getpPhoto() %>"style="max-height:250px;max-width: 100%;width: auto;" alt=".....">                             
                             </div>
                             
							<div class="card-body">
							
								<h5 class="card-tittle"><%=p.getpName()%></h5>
                                 <p class="card-text"><%=Helper.get10Words(p.getpDesc()) %></p>
							</div>
							
							<div class="card-footer text-center">
							
							<a class="btn custom-bg text-white" href="addtocart?id=<%=p.getpId()%>">Add to cart</a>
							
							<button class="btn btn-outline-success text-black">&#8377;<%=p.getPriceAfterDiscount() %>/-<span class="text-secondary discount-label"><%=p.getpPrice() %>   <%=p.getpDiscount() %>%</span></button>
						
							
							</div>

						</div>

						<%
						}		
										
							if (list.size() == 0) {
											
							out.println("<h3>No Items in this Category</h3>");
                            
						      }
							
						%>


					</div>

				</div>

			</div>

		</div>

	</div>
</div>

</body>
</html>

