package com.mycart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mycart.entities.Cart;
import com.mycart.entities.Product;

//Assuming you have a CartDao class with a Hibernate SessionFactory
public class CartDao {
	private SessionFactory factory;

	public CartDao(SessionFactory factory) {
		this.factory = factory;
	}

	// Other methods...

	public List<Product> getProductsInCartByUserId(int userId) {
		Session session = this.factory.openSession();
		Transaction transaction = null;
		List<Product> products = new ArrayList<>();

		try {
			transaction = session.beginTransaction();

			// Use HQL to fetch products from the cart for a given user ID
			String hql = "SELECT c.product FROM Cart c WHERE c.user.userId = :userId";
			Query<Product> query = session.createQuery(hql, Product.class);
			query.setParameter("userId", userId);

			products = query.list();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return products;
	}

	public List<Cart> getCartProductsByUserId(int userId) {
		List<Cart> cartList = new ArrayList<>();

		try {
			if (userId > 0) {
				Session session = this.factory.openSession();
				Transaction tx = session.beginTransaction();

				// Use HQL to fetch cart items based on user ID
				String hql = "FROM Cart WHERE user.userId = :userId";
				Query<Cart> query = session.createQuery(hql, Cart.class);
				query.setParameter("userId", userId);

				// Execute the query and get the cart items
				cartList = query.list();

				tx.commit();
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return cartList;
	}

   //Method to get Cart by ID
	public Cart getCartById(int cartId) {
		Cart cart = null;
		try (Session session = this.factory.openSession()) {
			cart = session.get(Cart.class, cartId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;
	}

	// Method to delete Cart
	public void deleteCart(Cart cart) {
		try (Session session = this.factory.openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(cart);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCartQuantity(int cartId, int quantity) {
		 try (Session session = this.factory.openSession()) {
		        Transaction tx = session.beginTransaction();
		        Query query = session.createQuery("update Cart set quantity=:newQuantity where cartId=:cartId");
		        query.setParameter("newQuantity", quantity);
		        query.setParameter("cartId", cartId);

		        query.executeUpdate();
		        tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public int getQuantityByCartId(int cartId) {
        try (Session session = this.factory.openSession()) {
            // Use HQL to fetch quantity from Cart based on cartId
            String hql = "SELECT c.quantity FROM Cart c WHERE c.cartId = :cartId";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            query.setParameter("cartId", cartId);
            Integer quantity = query.uniqueResult();

            return (quantity != null) ? quantity : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
	public Cart getCartByUserAndProduct(int userId, int productId) {
        try (Session session = this.factory.openSession()) {
            String hql = "FROM Cart WHERE user.userId = :userId AND product.pId = :productId";
            Query<Cart> query = session.createQuery(hql, Cart.class);
            query.setParameter("userId", userId);
            query.setParameter("productId", productId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	// Method to update cart
    public void updateCart(Cart cart) {
        try (Session session = this.factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(cart);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to save cart
    public void saveCart(Cart cart) {
        try (Session session = this.factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(cart);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
