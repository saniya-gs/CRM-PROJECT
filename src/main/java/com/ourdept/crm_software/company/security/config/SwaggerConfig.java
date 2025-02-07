package com.ourdept.crm_software.company.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI awesomeAPI() {
        return new OpenAPI()
                .info(new Info().title("Our Dept CRM Software")
                        .description("CRM Sowtware  endpoints")
                        .version("1.0")
//                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"))
                        )
                
                .externalDocs(new ExternalDocumentation()
                        .description("Vijay Roshan G, vijayroshan@hrlabs.in")
                        .url("http://hrlabs.in"));
    }

}