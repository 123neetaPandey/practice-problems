package com.SixDee.springboot.CrudDemo.employee.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory" , basePackages= {
		"com.SixDee.springboot.CrudDemo.employee.dao"}, transactionManagerRef = "transactionManager")
public class EmployeeDBConfig {

	@Primary
	@Bean(name="datasource")
	@ConfigurationProperties(prefix="spring.employee.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean   entityManagerFactoryBean(EntityManagerFactoryBuilder  builder,
			                 @Qualifier("datasource")  DataSource dataSource) {
		
		Map<String , Object>  properties= new HashMap<>();
		
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("spring.jpa.properties.hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("com.SixDee.springboot.CrudDemo.employee.entity").persistenceUnit("Employee").build();
	}
		
	@Primary
	@Bean(name="transactionManager")
	public PlatformTransactionManager  transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory eentityManagerFactory) {
		return new JpaTransactionManager(eentityManagerFactory);
		
	}
}
