package com.wowsanta.repository.mybatis;

import lombok.*;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

@Data
public class MyBatisDataSource  {
	private String driver;
	private String url;
	private String username;
	private String password;
	private String encPw;
	
	
	public DataSource build() {
		String password = this.password;
		
		PooledDataSource dataSource = new PooledDataSource(this.driver,this.url,this.username,password);
		
		dataSource.setDefaultAutoCommit(false);
		dataSource.setLoginTimeout(1000);
		dataSource.setDefaultNetworkTimeout(1000);
		dataSource.setPoolMaximumActiveConnections(10);
		dataSource.setPoolMaximumIdleConnections(5);
		dataSource.setPoolPingConnectionsNotUsedFor(5000);
		
		return dataSource;
	}
}
