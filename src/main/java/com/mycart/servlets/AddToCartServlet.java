package com.mycart.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycart.dao.CartDao;
import com.mycart.dao.ProductDao;
import com.mycart.entities.Cart;
import com.mycart.entities.Product;
import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;

@WebServlet("/addtocart")
public class AddToCartServlet extends HttpServlet {
	 protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        resp.setContentType("text/html");
	        try (PrintWriter out = resp.getWriter()) {
	        	int productId = Integer.parseInt(req.getParameter("id"));

	        	try {
	        	    // Retrieve user information from the session (replace UserSessionAttribute with the actual attribute name)
	        	    HttpSession session = req.getSession();
	        	    User user = (User) session.getAttribute("current_user");

	        	    // Check if the user is logged in
	        	    if (user != null) {
	        	        ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
	        	        CartDao cartDao = new CartDao(FactoryProvider.getFactory());

	        	        // Get the product from the database
	        	        Product product = pDao.getProductsById(productId);

	        	        // Check if the product exists
	        	        if (product != null) {
	        	            // Check if the user already has the product in the cart
	        	            Cart existingCart = cartDao.getCartByUserAndProduct(user.getUserId(), productId);

	        	            if (existingCart != null) {
	        	                // If the product is already in the cart, update the quantity
	        	                existingCart.setQuantity(existingCart.getQuantity() + 1);
	        	                existingCart.setPrice(product.getPriceAfterDiscount() * existingCart.getQuantity());
	        	                cartDao.updateCart(existingCart);
	        	            } else {
	        	                // If the product is not in the cart, create a new cart item
	        	                Cart cart = new Cart();
	        	                cart.setUser(user);
	        	                cart.setProduct(product);
	        	                cart.setQuantity(1);
	        	                cart.setPrice(product.getPriceAfterDiscount());
	        	                cartDao.saveCart(cart);
	        	            }

	        	            resp.sendRedirect("index.jsp");
	        	        } else {
	        	            // Handle the case where the product does not exist
	        	            resp.sendRedirect("index.jsp");
	        	        }
	        	    }
	        	} catch (Exception e) {
	        	    e.printStackTrace();
	        	    // Handle exceptions appropriately, e.g., redirect to an error page
	        	    resp.sendRedirect("index.jsp");
	        	}}
	    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}