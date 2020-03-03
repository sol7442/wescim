package com.wowsanta.repository;

import java.io.Closeable;


public interface Session extends Closeable {

	<T> T getRepository(Class<T> type);

	void commit();
	void rollback();
}
