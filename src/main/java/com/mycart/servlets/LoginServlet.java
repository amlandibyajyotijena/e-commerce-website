package com.mycart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mycart.dao.UserDao;
import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {

			String email = req.getParameter("email");
			String password = req.getParameter("password");

			// validation

			// user authentic
			UserDao userdao = new UserDao(FactoryProvider.getFactory());

			User user = userdao.getUserByEmailPassword(email, password);
			HttpSession httpsession = req.getSession();
			if (user == null) {

				httpsession.setAttribute("messege", "invalid details!!try With Correct Credential");
				resp.sendRedirect("login.jsp");
				return;
			} else {
				out.println("<h1>Welcome " + user.getUserName() + "</h1>");
				
				httpsession.setAttribute("current_user", user);

				if (user.getUserType().equals("admin")) {
					// admin.jsp
					resp.sendRedirect("admin.jsp");
				} else if (user.getUserType().equals("normal")) {
					// normal.jsp
					resp.sendRedirect("normal.jsp");

				}else {
					out.println("does not identified user type");
				}

			}

		} catch (Exception e) {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
