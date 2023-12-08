package com.mycart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mycart.entities.Cart;
import com.mycart.entities.Category;
import com.mycart.entities.Product;

public class ProductDao {
	private SessionFactory factory;

	public ProductDao(SessionFactory factory) {
		this.factory = factory;
	}

	public int saveCategory(Product pd) {

		Session s = this.factory.openSession();
		Transaction tx = s.beginTransaction();
		int catid = (int) s.save(pd);
		tx.commit();
		s.close();
		return catid;

	}

	public List<Product> getAllProducts() {
		Session s = this.factory.openSession();
		Query q = s.createQuery("from Product");
		return q.list();
	}
	
	//get product of category
	public List<Product> getAllProductsById(int cid) {
		Session s = this.factory.openSession();
		Query q = s.createQuery("from Product as p where p.category.categoryId=: id");
		q.setParameter("id", cid);
		return q.list();
	}

	
	public List<Cart> getCartProducts(List<Cart> cartList) {
	    List<Cart> book = new ArrayList<>();
	    try {
	        if (cartList.size() > 0) {
	        	Session session = this.factory.openSession();
	    		Transaction tx = session.beginTransaction();
	            for (Cart item : cartList) {
	                // Use HQL to fetch product details
	                String hql = "FROM Product WHERE id = :productId";
	                Query<Product> query = session.createQuery(hql, Product.class);
	                query.setParameter("productId", item.getProduct().getpId());

	                // Execute the query and get the product
	                Product product = query.uniqueResult();

	                if (product != null) {
	                    Cart cart = new Cart();
	                    cart.setCartId(product.getpId());
	                    cart.setPrice(product.getpPrice() * item.getQuantity());
	                    cart.setQuantity(item.getQuantity());
	                    book.add(cart);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return book;
	}
	public Product getProductsById(int id) {
		Product c=null;
		Session s=this.factory.openSession();
	    c=s.get(Product.class, id);
	    s.close();
	    return c;

	    
	}
	
	// Inside your ProductDao class

	public double getTotalCartPriceByUserId(int userId) {
	    double sum = 0;

	    try {
	        if (userId > 0) {
	            Session session = this.factory.openSession();
	            Transaction tx = session.beginTransaction();

	            // Use HQL to calculate the total price directly in the database
	            String hql = "SELECT SUM(c.price) FROM Cart c WHERE c.user.userId = :userId";
	            Query<Double> query = session.createQuery(hql, Double.class);
	            query.setParameter("userId", userId);

	            // Execute the query and get the result
	            Double totalPrice = query.uniqueResult();

	            if (totalPrice != null) {
	                sum = totalPrice;
	            }

	            tx.commit();
	            session.close();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }

	    return sum;
	}
	public double getTotalCartPrice(List<Cart> cartList) {
	    double total = 0.0;

	    for (Cart cart : cartList) {
	        // Assuming that getProduct() returns the product associated with the cart item
	        Product product = cart.getProduct();
	        
	        if (product != null) {
	            double productPrice = product.getPriceAfterDiscount(); // You can modify this based on your requirements
	            int quantity = cart.getQuantity();
	            total += productPrice * quantity;
	        }
	    }

	    return total;
	}
	public int countProduct() {
        int userCount = 0;

        try {
        	Session s = this.factory.openSession();
            // Using HQL (Hibernate Query Language) to count users
            Query query = s.createQuery("select count(*) from Product");
            userCount = ((Long) query.uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } 
        return userCount;
    }

}
