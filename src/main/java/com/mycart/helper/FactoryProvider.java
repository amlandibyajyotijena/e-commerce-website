package com.mycart.helper;

import javax.persistence.Entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
public class FactoryProvider {
	private static SessionFactory sf;

	public static SessionFactory getFactory() {
		try {
			if (sf == null) {
				sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sf;
	}
}
