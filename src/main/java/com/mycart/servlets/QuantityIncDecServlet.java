package com.mycart.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycart.dao.CartDao;
import com.mycart.entities.Cart;
import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;

@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
        	PrintWriter out=response.getWriter();
            String action = request.getParameter("action");
            int cid=Integer.parseInt(request.getParameter("cid"));

            
            
            HttpSession session=request.getSession();
            User user3 = (User) session.getAttribute("current_user");
            CartDao cDao = new CartDao(FactoryProvider.getFactory());
             Cart crt=cDao.getCartById(cid);
            List<Cart> cartProudct = cDao.getCartProductsByUserId(user3.getUserId());
            System.out.println(cartProudct);
            if (action != null ) {
                if (action.equals("inc")) {
                    for (Cart cart : cartProudct) {
                        if (cart.getCartId() == cid) {
                            int quantity = cart.getQuantity();
                            int newQuantity = ++quantity;
                           
                            cDao.updateCartQuantity(cid, newQuantity);
                            break;
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart cart : cartProudct) {
                        if (cart.getCartId() == cid && cart.getQuantity() > 1) {
                            int quantity = cart.getQuantity();
                            int newQuantity = --quantity;
                            cDao.updateCartQuantity(cid, newQuantity);
                            break;
                        }
                    }
                }
                response.sendRedirect("cart.jsp");
               
            } else {
               out.println("NOT UPDATE"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
