package com.wowsanta.repository.mybatis;



import org.apache.ibatis.session.SqlSessionFactory;

import com.wowsanta.repository.Session;
import com.wowsanta.repository.SessionFactory;

import lombok.Data;

@Data
public class MyBatisSessionFactory  implements SessionFactory{
	private String implClass;
	private transient SqlSessionFactory myBatisSessionfactory;
	
	@Override
	public Session openSession() {
		return new MyBatisSession(myBatisSessionfactory.openSession());
	}
}
