package com.pluralsight.conferencedemo.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.SQLServer2012Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//This class can be used to setup database connection but springboot gives us the property to 
//to add directly using properties file. In this example both ways of establishing database connection
//has been provided
@Configuration
public class JpaConfiguration {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		/*
		 * dataSource.setDriverClassName("org.postgresql.Driver");
		 * dataSource.setUrl("jdbc:postgresql:conference_app");
		 * dataSource.setUsername("root"); dataSource.setPassword("");
		 */
		 dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=ConferenceDB");
		 dataSource.setUsername("ConferenceAdmin");
		 dataSource.setPassword("intershop3");
		return dataSource;
	}

	@Bean
	public Map<String, Object> jpaProperties() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("hibernate.dialect", SQLServer2012Dialect.class.getName());
//		props.put("hibernate.cache.provider_class", HashtableCacheProvider.class.getName());
		return props;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.SQL_SERVER);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager( entityManagerFactory().getObject() );
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(this.getDataSource());
		lef.setJpaPropertyMap(this.jpaProperties());
		lef.setJpaVendorAdapter(this.jpaVendorAdapter());
		return lef;
	}

}
