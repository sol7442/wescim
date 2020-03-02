package com.wowsanta.repository.hibernate;

import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;

import lombok.Data;

@Data
public class HibernateSessionFactory implements SessionFactory {

	private org.hibernate.SessionFactory hibernateSessionFactory;
	
	@Override
	public Session openSession() {
		return new HibernateSession(hibernateSessionFactory.openSession());
	}

}
