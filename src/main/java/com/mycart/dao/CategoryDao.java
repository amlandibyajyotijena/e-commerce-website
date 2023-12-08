package com.mycart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mycart.entities.Category;

public class CategoryDao {
	private SessionFactory factory;

	public CategoryDao(SessionFactory factory) {
		this.factory = factory;
	}

	public int saveCategory(Category cat) {
		
		Session s = this.factory.openSession();
		Transaction tx = s.beginTransaction();
		int catid = (int) s.save(cat);
		tx.commit();
		s.close();
		return catid;
		
	}
	public List<Category> getCategories(){
	Session s=	this.factory.openSession();
	Query  q=  s.createQuery("from Category");
	List<Category>cls=q.list();
	return cls;
	
	}
	public Category getCategoryById(int cid) {
		Category c=null;
		Session s=this.factory.openSession();
	    c=s.get(Category.class, cid);
	    s.close();
	    return c;
	}
	public int countCategory() {
        int userCount = 0;

        try {
        	Session s = this.factory.openSession();
            // Using HQL (Hibernate Query Language) to count users
            Query query = s.createQuery("select count(*) from Category");
            userCount = ((Long) query.uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } 
        return userCount;
    }
}
