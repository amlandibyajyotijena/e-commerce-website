package com.mycart.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mycart.dao.CategoryDao;
import com.mycart.dao.ProductDao;
import com.mycart.entities.Category;
import com.mycart.entities.Product;
import com.mycart.helper.FactoryProvider;

/**
 * Servlet implementation class ProductOperationServlet
 */

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		try (PrintWriter out = resp.getWriter()) {

			String op = req.getParameter("operation");
			if (op.trim().equals("addCategory")) {
				// addcategory

				String tittle = req.getParameter("catTittle");
				String Description = req.getParameter("catDesc");

				Category cg = new Category();
				cg.setCategoryTitle(tittle);
				cg.setCategoryDescription(Description);

				// save category in db

				CategoryDao cgd = new CategoryDao(FactoryProvider.getFactory());
				cgd.saveCategory(cg);

				HttpSession hs = req.getSession();
				hs.setAttribute("messege", "Category saved successfully!!");
				resp.sendRedirect("admin.jsp");
				return;
			} else if (op.trim().equals("addProduct")) {
				String pname = req.getParameter("prodName");
				int pPrice = Integer.parseInt(req.getParameter("prodPrice"));
				int pDiscount = Integer.parseInt(req.getParameter("prodDiscount"));
				int pQuantity = Integer.parseInt(req.getParameter("prodQuantity"));
				int catId = Integer.parseInt(req.getParameter("catId"));
				String pDesc = req.getParameter("prodDesc");

				Part part = req.getPart("prodPic");

				Product p = new Product();
				p.setpName(pname);
				p.setpDesc(pDesc);
				p.setpPrice(pPrice);
				p.setpQuantity(pQuantity);
				p.setpPhoto(part.getSubmittedFileName());
				p.setpDiscount(pDiscount);

				// get category by id
				CategoryDao cDao = new CategoryDao(FactoryProvider.getFactory());
				p.setCatg(cDao.getCategoryById(catId));

				ProductDao pd = new ProductDao(FactoryProvider.getFactory());
				pd.saveCategory(p);
				
                //pic Upload
				String path=req.getRealPath("img")+File.separator+"products"+File.separator+part.getSubmittedFileName();
				System.out.println(path);
				
				//uploading pic code
				try {
				FileOutputStream fos=new FileOutputStream(path);
				
				InputStream is=part.getInputStream();
				//reading data
				byte[]data=new byte[is.available()];
				is.read(data);
				
				//writing the data
				fos.write(data);
				fos.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				HttpSession hs = req.getSession();
				hs.setAttribute("messege", "Product saved successfully!!");
				resp.sendRedirect("admin.jsp");
				return;

			}

		} catch (Exception e) {

		}
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
