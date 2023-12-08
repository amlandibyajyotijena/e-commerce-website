package com.mycart.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.mycart.entities.User;

public class UserDao {
	private SessionFactory factory;

	public UserDao(SessionFactory factory) {
		this.factory = factory;
	}
   
	public User getUserByEmailPassword(String email,String password) 
	{
		User user=null;
		try {
			String q="from User where userEmail=:e and userPassword=:p";
			Session s=this.factory.openSession();
			Query qu=s.createQuery(q);
			qu.setParameter("e", email);
			qu.setParameter("p", password);
			
	        user=(User)qu.uniqueResult();
			s.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	 public int countUsers() {
	        int userCount = 0;

	        try {
	        	Session s = this.factory.openSession();
	            // Using HQL (Hibernate Query Language) to count users
	            Query query = s.createQuery("select count(*) from User");
	            userCount = ((Long) query.uniqueResult()).intValue();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        } 
	        return userCount;
	    }
	
}
