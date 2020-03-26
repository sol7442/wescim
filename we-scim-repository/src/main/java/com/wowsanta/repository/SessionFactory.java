package com.wowsanta.repository;

public interface SessionFactory {
	public void setImplClass(String className);
	public Session openSession();
}
