package com.mycart.servlets;

import com.mycart.dao.CartDao;
import com.mycart.entities.Cart;
import com.mycart.helper.FactoryProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the cart item id from the request parameter
            int cartId = Integer.parseInt(request.getParameter("id"));

            // Create a CartDao instance
            CartDao cartDao = new CartDao(FactoryProvider.getFactory());

            // Get the Cart entity by id
            Cart cartItem = cartDao.getCartById(cartId);

            // Remove the Cart entity from the database
            cartDao.deleteCart(cartItem);

            // Redirect to the cart page after removal
            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
