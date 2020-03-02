package com.wowsanta.repository.mybatis;

import com.wowsanta.repository.DataSource;

import lombok.Data;

@Data
public class MyBatisDataSource extends DataSource {
	private boolean autoCommit;
	private int loginTimeout   = 3000;
	private int networkTimeout = 1000;
	private int maxPoolActive  = 10;
	private int maxPoolIdle    = 1;
}
