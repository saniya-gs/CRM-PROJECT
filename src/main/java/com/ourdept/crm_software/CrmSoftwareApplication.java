package com.ourdept.crm_software;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication(exclude = { FlywayAutoConfiguration.class })
public class CrmSoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmSoftwareApplication.class, args);
	}

}
