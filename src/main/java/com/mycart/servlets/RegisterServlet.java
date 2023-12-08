package com.mycart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {
			try {
				String username = req.getParameter("user_name");
				String useremail = req.getParameter("user_email");
				String userpassword = req.getParameter("user_password");
				String userphone = req.getParameter("user_phone");
				String useraddress = req.getParameter("user_address");
				if (username.isEmpty()) {
					out.println("Name is blank");
				}

				// creating usr obj to store data
				User user = new User(username, useremail, userpassword, userphone, "default.jpg", useraddress,"normal", null);
				Session hs = FactoryProvider.getFactory().openSession();
				Transaction tx = hs.beginTransaction();
				int userId = (int) hs.save(user);

				tx.commit();

				hs.close();
                
				HttpSession httpsession=req.getSession();
				httpsession.setAttribute("messege", "Registration Successfull!!"+userId);
				resp.sendRedirect("register.jsp");
				return;
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		processRequest(req, resp);
	}

}
