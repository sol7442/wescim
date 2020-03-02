package com.wowsanta.repository.hibernate;

import com.wowsanta.repository.DataSource;

import lombok.Data;

@Data
public class HibernateDataSoruce extends DataSource {
	private String dialect;
	private String showsql;
	private String formatsql;
	private String hbm2ddlauto;
}
