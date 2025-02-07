package com.ourdept.crm_software.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ourdept.crm_software.tenant_Routing_helper.TenantIdentifierResolver;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tenantEntityManagerFactory", transactionManagerRef = "tenantTransactionManager", basePackages = {
		"com.ourdept.crm_software.crm.repository" })
@DependsOn("masterDataSource")
@Profile("prod") // This configuration is active only in non-production profiles
public class ProdTenantDataSourceConfig {
	
	
	

	@Value("${spring.tenant.datasource.driver-class-name}")
	String SQL_DRIVER_CLASSNAME;
	

	
	@Value("${spring.tenant.datasource.jdbc-url}")
	String DEFAULT_TENANT_DB_URL;
	
	@Value("${spring.master.datasource.username}")
	String MASTER_DB_USERNAME;
	
	@Value("${spring.master.datasource.password}")
	String MASTER_DB_PASSWORD;
	
	@Value("${spring.tenant.datasource.username}")
	String TENANT_DB_USERNAME;
	
	@Value("${spring.tenant.datasource.password}")
	String TENANT_DB_PASSWORD;

	private static final Logger logger = LoggerFactory.getLogger(TenantDataSourceConfig.class);

	@Autowired
	@Qualifier("masterDataSource")
	private DataSource masterDataSource;

	@Primary
	@Bean(name = "tenantDataSource")
	public DataSource tenantDataSource() {
		TenantIdentifierResolver tenantIdentifierResolver = new TenantIdentifierResolver();
		logger.info("Configuring Tenant DataSource");

		Map<Object, Object> dataSourceMap = new HashMap<>();
		// Fetch all tenant details from master database
		try (Connection connection = masterDataSource.getConnection()) {
			String query = "SELECT tenantId, dbUrl, dbUsername, dbPassword FROM tenantconfiginfo";
			try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					logger.info("found tenant config info in table");
					log.info("from the DB: " + DEFAULT_TENANT_DB_URL);
					String tenantId = rs.getString("tenantId");
					String dbUrl = rs.getString("dbUrl");
					String dbUsername = rs.getString("dbUsername");
					String dbPassword = rs.getString("dbPassword");
					dataSourceMap.put(tenantId, createDataSource(dbUrl, dbUsername, dbPassword));
				}

			}
		} catch (SQLException e) {
			logger.error("Failed to fetch tenant data source configurations" + e.getMessage(), e);
		}

//	        // Define your data sources here hard coded
//	        Map<Object, Object> dataSourceMap = new HashMap<>();
//	        dataSourceMap.put("tenant1", createDataSource("jdbc:mysql://localhost:3306/tenant1_db", "root", "root"));
//	        dataSourceMap.put("tenant2", createDataSource("jdbc:mysql://localhost:3306/tenant2_db", "root", "root"));
//	        // Add more tenants as needed

		tenantIdentifierResolver.setTargetDataSources(dataSourceMap);
		tenantIdentifierResolver.setDefaultTargetDataSource(createDataSource(
				DEFAULT_TENANT_DB_URL, TENANT_DB_USERNAME, TENANT_DB_PASSWORD)); // Default
																											// datasource
		return tenantIdentifierResolver;

	}

//	 	create tenant datasource for respective tenant
	private DataSource createDataSource(String url, String username, String password) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(SQL_DRIVER_CLASSNAME);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		logger.info("create datasource for the tenant from the TenantData in master DB" + dataSource);

		return dataSource;
	}

	private DataSource createDefaultDataSource() {
		logger.info("createc Default datasource for the tenant as the Tenant datasource is not found in MasterDB");

		return DataSourceBuilder.create().url(DEFAULT_TENANT_DB_URL)
				.username(TENANT_DB_USERNAME).password(TENANT_DB_PASSWORD).driverClassName(SQL_DRIVER_CLASSNAME).build();
	}

	@Primary
	@Bean(name = "tenantEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("tenantDataSource") DataSource tenantDataSource) {
		logger.info("Configuring Tenant EntityManagerFactory");
		return builder.dataSource(tenantDataSource).packages("com.ourdept.crm_software.crm.domain.entities").build();
	}

	@Bean(name = "tenantTransactionManager")
	public PlatformTransactionManager tenantTransactionManager(
			@Qualifier("tenantEntityManagerFactory") EntityManagerFactory tenantEntityManagerFactory) {
		logger.info("Configuring Tenant TransactionManager");
		return new JpaTransactionManager(tenantEntityManagerFactory);
	}
}