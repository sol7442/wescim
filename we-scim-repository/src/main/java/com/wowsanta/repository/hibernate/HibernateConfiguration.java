package com.wowsanta.repository.hibernate;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.wowsanta.repository.DataSource;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

@Data
public class HibernateConfiguration extends RepositoryConfig {
	
	private DataSource dataInfo;
	
	private String dialect;
	private String showsql;
	private String formatsql;
	private String hbm2ddlauto;
	
	
	@Override
	public SessionFactory build(Set<Entry<String, EntityInfo>> entity_set) throws ScimException {
		HibernateSessionFactory session_factory  = new HibernateSessionFactory();
		
		try {
			
			Configuration config = new Configuration();
			Properties properties = new Properties();
			properties.put(Environment.DRIVER,dataInfo.getDriver());	
			properties.put(Environment.URL,dataInfo.getUrl());
			properties.put(Environment.USER,dataInfo.getUser());
			properties.put(Environment.PASS,dataInfo.getPassword());
			properties.put(Environment.DIALECT,this.dialect);
			properties.put(Environment.SHOW_SQL,this.showsql);
			properties.put(Environment.FORMAT_SQL,this.formatsql);
			properties.put(Environment.HBM2DDL_AUTO,this.hbm2ddlauto);
			properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
			config.setProperties(properties);
			
			if(entity_set != null) {
				LOGGER.system.debug("regist entity class : {}",entity_set.size());
				for (Entry<String, EntityInfo> entry : entity_set) {
					EntityInfo entity = entry.getValue();
					String name= entry.getKey();
					config.addAnnotatedClass(entity.getImplClss());
					
					LOGGER.system.debug("{} : {}",name,entity);
				}	
			}
			
			org.hibernate.SessionFactory sessionFactory = config.buildSessionFactory();
			session_factory.setHibernateSessionFactory(sessionFactory);
		}catch (Exception e) {
			throw new ScimException(e);
		}
		
		return session_factory;
	}

	public static HibernateConfiguration load(String config_file) throws ScimException {
		return ConfigurationBuilder.load(HibernateConfiguration.class, config_file);
	}
}
