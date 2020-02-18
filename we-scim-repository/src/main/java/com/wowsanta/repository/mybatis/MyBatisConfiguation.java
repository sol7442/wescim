package com.wowsanta.repository.mybatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.config.ConfigurationFactory;
import com.wowsanta.scim.config.ScimException;
import com.wowsata.scim.UserRepository;

import lombok.Data;

@Data
public class MyBatisConfiguation {
	private String id;
	private String datasourceInfo;
	private String resourcePath;
	
	public SessionFactory build() {
		
		try {
			MyBatisDataSource data_sourec_config = ConfigurationFactory.load(MyBatisDataSource.class, datasourceInfo);
			DataSource dataSource = data_sourec_config.build();
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment(id, transactionFactory, dataSource);
			
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
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
		return null;
	}
}
