package com.wowsanta.repository.hibernate;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.wowsanta.repository.DataSource;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.repository.mybatis.MyBatisConfiguration;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.ServiceStructure;
import com.wowsanta.scim.entity.Entity;
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
	public SessionFactory build() throws ScimException {
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
			
			ServiceStructure service_structure = ServiceStructure.getInstance();
			Set<Entry<Domain.Key, Entity>> entity_set = service_structure.getEntitySet();
			for (Entry<Domain.Key, Entity> entry : entity_set) {
				Entity entity = entry.getValue();
				Domain.Key key = entry.getKey();
				
				config.addAnnotatedClass(entity.getImplClss());
				
				LOGGER.system.debug("{} : {}",key,entity);
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
