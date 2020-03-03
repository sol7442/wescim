package com.wowsanta.repository.mybatis;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.wowsanta.repository.Session;

public class MyBatisSession implements Session{
	private final SqlSession session;
	
	public MyBatisSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public void close() throws IOException {
		session.close();
	}

	@Override
	public <T> T getRepository(Class<T> type) {
		return session.getMapper(type);
	}
	@Override
	public void commit() {
		this.session.commit();
	}
	@Override
	public void rollback() {
		this.session.rollback();
	}
}
