package com.wowsanta.repository.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.ScimException;

import lombok.Data;

@Data
public class MyBatisConfiguation extends RepositoryConfig {
	
	private MyBatisDataSource dataInfo;
	private String resourcePath;
	
	@Override
	public SessionFactory build() {
		try {
			PooledDataSource dataSource = new PooledDataSource(
					dataInfo.getDriver(),
					dataInfo.getUrl(),
					dataInfo.getUsername(),
					dataInfo.getPassword()
					);
			dataSource.setDefaultAutoCommit(false);
			dataSource.setLoginTimeout(1000);
			dataSource.setDefaultNetworkTimeout(1000);
			dataSource.setPoolMaximumActiveConnections(10);
			dataSource.setPoolMaximumIdleConnections(5);
			dataSource.setPoolPingConnectionsNotUsedFor(5000);
			
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment(getName(), transactionFactory, dataSource);
			
			Configuration config = new Configuration();
			config.setEnvironment(environment);
			
			File map_path = new File(resourcePath); 
			if(map_path.isDirectory()) {
				for (File map_file : map_path.listFiles()) {
					if(map_file.getName().endsWith(".xml")) {
						System.out.println("load mapper xml : - : " + map_file.getName());
						try {
							XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(new FileInputStream(map_file),config,map_file.getName(),config.getSqlFragments());
							xmlMapperBuilder.parse();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
			
			SqlSessionFactory session_factory = new SqlSessionFactoryBuilder().build(config);
			return new MyBatisSessionFactory
					.MyBatisSessionFactoryBuilder()
					.factory(session_factory)
					.build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
