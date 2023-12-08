<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.mycart.dao.CartDao"%>
<%@page import="com.mycart.entities.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User user3 = (User) session.getAttribute("current_user");
CartDao cDao = new CartDao(FactoryProvider.getFactory());

List<Cart> cartProudct = cDao.getCartProductsByUserId(user3.getUserId());
request.setAttribute("cart-list", cartProudct);
if (cartProudct != null) {
	ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
	List<Cart> cartProudct2 = null;
	cartProudct2 = pDao.getCartProducts(cartProudct);
	request.setAttribute("cart_list", cartProudct2);

}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
.cross-line {
      text-decoration: line-through;
    }
</style>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<%
			ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
			List<Cart> cartProudct3 = cDao.getCartProductsByUserId(user3.getUserId());
			double total = pDao.getTotalCartPrice(cartProudct3);
			
			%>

			<h3>
				Total Price: &#8377;
				<%= total%>
			</h3>
			<a class="mx-3 btn btn-primary" href="checkout-cart">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">BuyNow</th>
					<th scope="col">Cancel</th>

				</tr>
			</thead>
			<tbody>
				<%
				User user2 = (User) session.getAttribute("current_user");
				if (cartProudct != null) {
					System.out.println(cartProudct);
					for (Cart c : cartProudct) {
						if (c.getUser().getUserId() == user2.getUserId()) {
					if (c.getProduct() != null) {
				%>
				<tr>
					<td><%=c.getProduct().getpName()%></td>
					<td><%=c.getProduct().getCatg().getCategoryTitle()%></td>
					<td>&#8377;<%=c.getProduct().getPriceAfterDiscount()%><span class="text-secondary discount-label cross-line">&#8377;<%=c.getProduct().getpPrice()%></span></td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id"
								value="<%=c.getProduct().getpId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre"
									href="quantity-inc-dec?action=inc&cid=<%=c.getCartId()%>"><i
									class="fas fa-plus-square"></i></a> 
									<input type="text"
									name="quantity" class="form-control"
									value="<%=c.getQuantity()%>" readonly>
									 <a
									class="btn btn-sm btn-decre"
									href="quantity-inc-dec?action=dec&cid=<%=c.getCartId()%>"><i
									class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a href="remove-from-cart?id=<%= c.getCartId() %>"
						class="btn btn-sm btn-danger">Remove</a></td>
				</tr>

				<%
				}
				}
				}
				}
				%>

			</tbody>
		</table>
	</div>


	<%@include file="components/footer.jsp"%>
</body>
</html>