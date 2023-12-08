package com.mycart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mycart.entities.Orders;
import com.mycart.entities.User;

public class OrdersDao {

	private SessionFactory factory;

	public OrdersDao(SessionFactory factory) {
		this.factory = factory;
	}

	public int saveOrders(Orders od) {

		Session s = this.factory.openSession();
		Transaction tx = s.beginTransaction();
		int oid = (int) s.save(od);
		tx.commit();
		s.close();
		return oid;

	}
	public List<Orders> userOrders(int userId) {
        List<Orders> list = new ArrayList<>();
        try {
        	Session session = this.factory.openSession();
        	Transaction tx = session.beginTransaction();
            // Assuming "User" is the entity representing the user and "id" is the user's ID property
            User user = session.get(User.class, userId);

            if (user != null) {
                // Assuming "Orders" is the entity representing orders and "user" is the association
                String hql = "FROM Orders WHERE user = :user ORDER BY id DESC";
                Query<Orders> query = session.createQuery(hql, Orders.class);
                query.setParameter("user", user);
                query.setMaxResults(10); // Limiting to the latest 10 orders, adjust as needed
                list = query.list();
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
	
}
