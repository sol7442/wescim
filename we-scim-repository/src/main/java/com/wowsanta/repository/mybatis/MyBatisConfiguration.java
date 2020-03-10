package com.wowsanta.repository.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.wowsanta.repository.DataSource;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.entity.EntityInfo;

import lombok.Data;

@Data
public class MyBatisConfiguration extends RepositoryConfig  {
	
	private DataSource dataInfo;
	private String mapperPath;
	
	private String name;
	private boolean autoCommit 	= false;
	private int loginTimeout	= 1000;;
	private int networkTimeout	= 2000;
	private int maxActivePool	= 10;
	private int maxIdelPool		= 2;
	private int pingConnectionNotUse = 60000;
	
	
	@Override
	public SessionFactory build(Set<Entry<String, EntityInfo>> entity_set) throws ScimException {
		try {
			PooledDataSource dataSource = new PooledDataSource(
					dataInfo.getDriver(),
					dataInfo.getUrl(),
					dataInfo.getUser(),
					dataInfo.getPassword()
					);
			
			dataSource.setDefaultAutoCommit(autoCommit);
			dataSource.setLoginTimeout(loginTimeout);
			dataSource.setDefaultNetworkTimeout(networkTimeout);
			dataSource.setPoolMaximumActiveConnections(maxActivePool);
			dataSource.setPoolMaximumIdleConnections(maxIdelPool);
			dataSource.setPoolPingConnectionsNotUsedFor(pingConnectionNotUse);
			
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment(getName(), transactionFactory, dataSource);
			
			Configuration config = new Configuration();
			config.setEnvironment(environment);
			
			File map_path = new File(mapperPath); 
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
			
			SqlSessionFactory mybatis_factory = new SqlSessionFactoryBuilder().build(config);
			MyBatisSessionFactory factory = new MyBatisSessionFactory();
			factory.setMyBatisSessionfactory(mybatis_factory);
			
			return factory;
			
		} catch (Exception e) {
			throw new ScimException(e);
		}
	}

	public static MyBatisConfiguration load(String config_file) throws ScimException {
		return ConfigurationBuilder.load(MyBatisConfiguration.class, config_file);
	}
}
