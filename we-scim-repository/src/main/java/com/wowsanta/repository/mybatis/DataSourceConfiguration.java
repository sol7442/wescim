package com.wowsanta.repository.mybatis;

import lombok.*;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

@Data
@Builder
public class DataSourceConfiguration {
	private String driver;
	private String url;
	private String username;
	private String password;
	private String encPw;
	
	public DataSource build() {
		String password = this.password;
		
		
		PooledDataSource dataSource = new PooledDataSource(this.driver,this.url,this.username,password);
		// pool setting //
		
		
		return dataSource;
	}
}
