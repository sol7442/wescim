package com.wowsanta.repository.hibernate;

import java.io.IOException;

import org.hibernate.Transaction;

import com.wowsanta.repository.Session;

public class HibernateSession implements Session{
	
	private org.hibernate.Session hibernateSession;
	private Transaction transaction;
	
	public HibernateSession(org.hibernate.Session session) {
		hibernateSession = session;
		transaction = session.beginTransaction();
	}

	@Override
	public void close() throws IOException {
		hibernateSession.close();
		
	}

	@Override
	public <T> T getRepository(Class<T> type) {
		T obj = null;
//		try {
//			obj =   type.newInstance();
//			if (obj instanceof Repository) {
//				Repository repository = (Repository) obj;
//				repository.setSession();
//			}
//			
//			
//		} catch (InstantiationException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
		
		return obj;
	}

	@Override
	public void commit() {
		this.transaction.commit();
	}

	@Override
	public void rollback() {
		this.transaction.rollback();
	}

}
