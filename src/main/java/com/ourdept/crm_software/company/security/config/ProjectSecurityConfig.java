package com.ourdept.crm_software.company.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ourdept.crm_software.company.security.exceptionHandling.CustomAccessDeniedHandler;
import com.ourdept.crm_software.company.security.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import com.ourdept.crm_software.company.security.filters.AuthoritiesLoggingAfterFilter;
import com.ourdept.crm_software.company.security.filters.AuthoritiesLoggingAtFilter;
import com.ourdept.crm_software.company.security.filters.CsrfCookieFilter;
import com.ourdept.crm_software.company.security.filters.JWTTokenGeneratorFilter;
import com.ourdept.crm_software.company.security.filters.JWTTokenValidatorFilter;
import com.ourdept.crm_software.company.security.filters.RequestValidationBeforeFilter;
import com.ourdept.crm_software.company.security.filters.TenantInterceptor;
import com.ourdept.crm_software.crm.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("dev") // This configuration is active only in non-production profiles
public class ProjectSecurityConfig {
	
	@Lazy
	  @Autowired
	    private JWTTokenValidatorFilter jwtTokenValidatorFilter;

	/**
	 * Configures the SecurityFilterChain for the application.
	 * 
	 * @param http The HttpSecurity object to customize security configurations
	 * @return SecurityFilterChain instance with the security setup
	 * @throws Exception If there is an issue with the configuration
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// Handler to extract CSRF tokens from requests and set them as attributes
		CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

		// Configuring the session management to be stateless (no session tracking)
		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				// Configuring CORS to allow requests from specific origins and methods
				.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // Allowing only
																										// this origin
						config.setAllowedMethods(Collections.singletonList("*")); // Allowing all HTTP methods
						config.setAllowCredentials(true); // Allowing credentials (cookies, authorization headers, etc.)
						config.setAllowedHeaders(Collections.singletonList("*")); // Allowing all headers
						config.setExposedHeaders(Arrays.asList("Authorization")); // Exposing specific headers to the
																					// client
						config.setMaxAge(3600L); // Setting max age for pre-flight requests
						return config;
					}
				}))
//				.csrf(csrf -> csrf.disable())
				// Configuring CSRF protection with specific endpoints excluded
				.csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
						.ignoringRequestMatchers("/contact", "/register", "/signup", "/employeeLogin", "/user",
								"/api/tenants/create","/hrm/createEmployee",
//								"/api/**",
								"/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/", "/v3/api-docs",
								"/v3/api-docs/**", "/webjars/**") // Excluding these endpoints from
						// CSRF protection
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Storing CSRF token in a
																								// cookie that is
																								// accessible by
																								// JavaScript
				// Adding custom filters in the security filter chain
				.addFilterBefore(new TenantInterceptor(), BasicAuthenticationFilter.class) // Adding a				
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // Adding CSRF cookie filter
				.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // Adding a
																										// custom
																										// request
																										// validation
																										// filter before
																										// basic auth
				.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class) // Adding a filter
																										// to log
																										// authorities
																										// after basic
																										// auth
				.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class) // Adding a filter to
																								// log authorities at a
																								// specific point in the
																								// chain
				.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class) // Adding a JWT token
																								// generator filter
				.addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class) // Adding a JWT token
																									// validator filter
				// Configuring HTTP security to allow only non-secure (HTTP) channels
				.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure())
				// Configuring authorization rules for different endpoints
				.authorizeHttpRequests((requests) -> requests

						.requestMatchers("/api/admin/**").hasRole("ADMIN") // "USER" role required for "/myCards"
						.requestMatchers("/api/manager/**").hasAnyRole("MANAGER") // "USER" role required for "/myLoans"
						.requestMatchers("/api/hr/**").hasAnyRole("HR") // "USER" role required for "/myLoans"
						.requestMatchers("/api/emp/**").hasAnyRole("EMPLOYEE", "ADMIN","MANAGER", "HR") // "USER" role required for "/myLoans"
						.requestMatchers("/api/**").authenticated() // Requires authentication for "/user"
						.requestMatchers("/notices", "/contact", "/error", "/register", "/hrm/createEmployee",
								"/invalidSession", "/signup", "/employeeLogin", "/api/tenants/create",
								"/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/", "/v3/api-docs",
								"/v3/api-docs/**", "/webjars/**","/user")
						.permitAll()); // Allowing public access to these endpoints
		
			http.formLogin(withDefaults()); // Enabling form-based login with default settings

		http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); // Configuring
																										// basic
																										// authentication
																										// with a custom
																										// entry point
		http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler())); // Handling access
																									// denied exceptions
																									// with a custom
																									// handler
		return http.build(); // Building and returning the configured SecurityFilterChain
	}

	
	  @Bean
	    public JWTTokenValidatorFilter jwtTokenValidatorFilter(EmployeeRepository employeeRepository) {
	        return new JWTTokenValidatorFilter(employeeRepository);
	    }

	
	/**
	 * Provides a PasswordEncoder bean that supports various encoding formats.
	 * 
	 * @return PasswordEncoder instance
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // Delegates to the appropriate encoder based
																			// on prefix
	}

	/**
	 * Provides a bean for checking compromised passwords using an external API.
	 * 
	 * @return CompromisedPasswordChecker instance
	 */
	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker(); // Checks passwords against a compromised password database
	}

	/**
	 * Configures and provides an AuthenticationManager with custom authentication
	 * provider.
	 * 
	 * @param userDetailsService The UserDetailsService to load user-specific data
	 * @param passwordEncoder    The PasswordEncoder to encode passwords
	 * @return AuthenticationManager instance
	 */
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		log.info("");
		TenantUserPwdAuthenticationProvider authenticationProvider = new TenantUserPwdAuthenticationProvider(
				userDetailsService, passwordEncoder); // Custom authentication provider
		ProviderManager providerManager = new ProviderManager(authenticationProvider); // ProviderManager managing the
																						// custom provider
		providerManager.setEraseCredentialsAfterAuthentication(false); // Retain credentials after authentication
		return providerManager; // Return the configured AuthenticationManager
	}

}