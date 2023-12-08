package com.mycart.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycart.dao.CartDao;
import com.mycart.entities.Product;
import com.mycart.helper.FactoryProvider;

@WebServlet("/getProductsInCart")
public class GetProductsInCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have a session attribute "userId" set with the current user's ID
        int userId = (int) request.getSession().getAttribute("userId");

        // Assuming you have a CartDao instance
        CartDao cartDao = new CartDao(FactoryProvider.getFactory());

        // Get the products in the cart for the user
        List<Product> productsInCart = cartDao.getProductsInCartByUserId(userId);

        // Use the products as needed (e.g., pass them to a JSP page)
        request.setAttribute("productsInCart", productsInCart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
        dispatcher.forward(request, response);
    }

    // Other methods...
}

