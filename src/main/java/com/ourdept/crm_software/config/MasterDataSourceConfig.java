package com.ourdept.crm_software.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "masterEntityManagerFactory",
  transactionManagerRef = "masterTransactionManager",
  basePackages = { "com.ourdept.crm_software.company.tenant_registration.repository", "com.ourdept.crm_software.company.security.repository"}
)
@Profile("dev") // This configuration is active only in non-production profiles
@Order(1)
public class MasterDataSourceConfig {
	
	
    private static final Logger logger = LoggerFactory.getLogger(MasterDataSourceConfig.class);
	
	@Bean(name="masterDataSource")
	@ConfigurationProperties(prefix="spring.master.datasource")
	public DataSource masterDataSource() {
		
        logger.info("Configuring Master DataSource");
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("masterDataSource") DataSource masterDataSource) {
		
        logger.info("Configuring Master EntityManagerFactory");
		return builder
				.dataSource(masterDataSource)
				.packages("com.ourdept.crm_software.company.tenant_registration.domain.entity","com.ourdept.crm_software.company.security.entity")
				.build();
	}
	
	@Bean(name = "masterTransactionManager")
	public PlatformTransactionManager masterTransactionManager(
			@Qualifier("masterEntityManagerFactory") EntityManagerFactory masterEntityManagerFactory) {
        logger.info("Configuring Master TransactionManager");
		return new JpaTransactionManager(masterEntityManagerFactory);
	}
	

}
