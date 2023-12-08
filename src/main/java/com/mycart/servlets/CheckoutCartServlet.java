package com.mycart.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycart.dao.CartDao;
import com.mycart.dao.OrdersDao;
import com.mycart.entities.Cart;
import com.mycart.entities.Orders;
import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;

@WebServlet("/checkout-cart")
public class CheckoutCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("current_user");

            if (user != null) {
                // Initialize DAOs
                CartDao cartDao = new CartDao(FactoryProvider.getFactory());
                OrdersDao ordersDao = new OrdersDao(FactoryProvider.getFactory());

                // Get the cart items for the current user from the database
                List<Cart> cartList = cartDao.getCartProductsByUserId(user.getUserId());

                if (cartList != null && !cartList.isEmpty()) {
                    // Create an order for each item in the cart
                    for (Cart cart : cartList) {
                        Orders order = new Orders();
                        order.setOrder_quantity(cart.getQuantity());
                        order.setProduct(cart.getProduct());
                        order.setUser(user);
                        ordersDao.saveOrders(order);

                        // Remove the cart item after creating an order
                        cartDao.deleteCart(cart);
                    }

                    // Forward to the order.jsp page
                    response.sendRedirect("orders.jsp");
                } else {
                    // Handle the case where the cart is empty
                    response.sendRedirect("index.jsp");
                }
            } else {
                // Handle the case where the user is not logged in
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
