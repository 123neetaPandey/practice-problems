package com.SixDee.springboot.CrudDemo.eventhistory.config;

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
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "eventhistoryEntityManagerFactory" , basePackages= {
		"com.SixDee.springboot.CrudDemo.eventhistory.dao"}, transactionManagerRef = "eventhistoryTransactionManager")
public class EventHistoryDBConfig {

	

@Bean(name="eventhistoryDatasource")
@ConfigurationProperties(prefix="spring.eventhostory.datasource")
public DataSource dataSource() {
	return DataSourceBuilder.create().build();
}


@Bean(name="eventhistoryEntityManagerFactory")
public LocalContainerEntityManagerFactoryBean   entityManagerFactoryBean(EntityManagerFactoryBuilder  builder,
		                 @Qualifier("eventhistoryDatasource")  DataSource dataSource) {
	
	Map<String , Object>  properties= new HashMap<>();
	
	properties.put("hibernate.hbm2ddl.auto", "update");
	properties.put("spring.jpa.properties.hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
	
	return builder.dataSource(dataSource).properties(properties).packages("com.SixDee.springboot.CrudDemo.eventhostory.entity").persistenceUnit("EventHostory").build();
}
	

@Bean(name="eventhistoryTransactionManager")
public PlatformTransactionManager  transactionManager(@Qualifier("eventhistoryEntityManagerFactory") EntityManagerFactory enntityManagerFactory) {
	return new JpaTransactionManager(enntityManagerFactory);
	
}
}
