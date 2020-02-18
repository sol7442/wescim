package com.wowsanta.repository.mybatis;



import org.apache.ibatis.session.SqlSessionFactory;

import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyBatisSessionFactory  implements SessionFactory{
	private SqlSessionFactory factory;
	
	@Override
	public Session openSession() {
		return new MyBatisSession(factory.openSession());
	}
}
